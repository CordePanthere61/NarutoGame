package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;

public class Player extends ControllableEntity {

    private static final int ANIMATION_SPEED = 4;

    private Image[] leftFrames;
    private Image[] rightFrames;
    private Image[] idleFrames;
    private int currentAnimationFrame = 0; // idle frame (middle)
    private int nextFrame = ANIMATION_SPEED;

    public Player(MovementController controller) {
        super(controller);
        setSpeed(4);
        setDimension(20,56);
        teleport(100,100);
        loadFrames();
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
        updateCurrentAnimationFrame();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(determineWhichFrameToDraw(), x, y);
    }

    private Image determineWhichFrameToDraw() {
        if (hasMoved()) {
            super.setDimension(40,52);
            if (super.getDirection() == Direction.RIGHT) {
                return rightFrames[currentAnimationFrame];
            } else if (super.getDirection() == Direction.LEFT) {
                return leftFrames[currentAnimationFrame];
            }
        } else {
            super.setDimension(20,56);
            if (super.getDirection() == Direction.RIGHT) {
                return idleFrames[0];
            } else if (super.getDirection() == Direction.LEFT) {
                return idleFrames[1];
            }
        }
        return null;
    }

    public void loadFrames() {
        leftFrames = ImageLoader.getInstance().getPlayerFrames("left");
        rightFrames = ImageLoader.getInstance().getPlayerFrames("right");
        idleFrames = ImageLoader.getInstance().getPlayerFrames("idle");
    }

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
        }
    }
}
