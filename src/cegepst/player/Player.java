package cegepst.player;

import cegepst.GamePad;
import cegepst.ImageLoader;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;

public class Player extends ControllableEntity {

    private static final int ANIMATION_SPEED = 4;

    private GamePad gamePad;
    private ImageRotate imageRotate;

    private Image[] leftFrames;
    private Image[] rightFrames;
    private Image[] idleFrames;
    private Image[] downFrame;
    private Image[] upFrame;
    private Image[] doubleJumpFrames;
    private int currentAnimationFrame = 0; // idle frame (middle)
    private int nextFrame = ANIMATION_SPEED;

    public Player(GamePad controller) {
        super(controller);
        this.gamePad = controller;
        this.imageRotate = new ImageRotate();
        setSpeed(5);
        setDimension(20,56);
        teleport(120,100);
        loadFrames();
    }

    @Override
    public void update() {
        super.update();
        //moveAccordingToHandler();
        if (gamePad.isJumpPressed()) {
            startJump();
        }
        //updateDimensions();
        updateCurrentAnimationFrame();

    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(determineWhichFrameToDraw(), x, y);
        //drawHitBox(buffer);
    }

    public void loadFrames() {
        leftFrames = ImageLoader.getInstance().getPlayerFrames("left");
        rightFrames = ImageLoader.getInstance().getPlayerFrames("right");
        idleFrames = ImageLoader.getInstance().getPlayerFrames("idle");
        downFrame = ImageLoader.getInstance().getPlayerFrames("down");
        upFrame = ImageLoader.getInstance().getPlayerFrames("up");
        doubleJumpFrames = ImageLoader.getInstance().getPlayerFrames("doubleJump");
    }

    private Image determineWhichFrameToDraw() {
        if (hasMoved()) {
            if (super.getDirection() == Direction.RIGHT) {
                return rightFrames[currentAnimationFrame];
            } else if (super.getDirection() == Direction.LEFT) {
                return leftFrames[currentAnimationFrame];
            } else if (super.getDirection() == Direction.DOWN) {
                return downFrame[0];
            } else if (super.getDirection() == Direction.UP) {
//                if (super.hasDoubleJumped()) {
//                    return imageRotate.rotateImage(doubleJumpFrames[0], width, height);
//                }
                return upFrame[0];
            }
        } else {
            if (super.getDirection() == Direction.RIGHT) {
                return idleFrames[0];
            } else if (super.getDirection() == Direction.LEFT) {
                return idleFrames[1];
            } else if (super.getDirection() == Direction.DOWN) {
                return idleFrames[0];
            }
        }
        return null;
    }

//    private void updateDimensions() {
//        if (hasMoved()) {
//            if (super.getDirection() == Direction.UP) {
//                super.setDimension(30,56);
//            }
//            super.setDimension(20,56);
//        } else {
//            super.setDimension(20,56);
//        }
//    }

    private void updateCurrentAnimationFrame() {
        if (super.hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 0;
        }
    }
}
