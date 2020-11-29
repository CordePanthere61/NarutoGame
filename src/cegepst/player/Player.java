package cegepst.player;

import cegepst.Animator;
import cegepst.GamePad;
import cegepst.ImageLoader;
import cegepst.Kunai;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;
import java.util.ArrayList;

public class Player extends ControllableEntity {



    private GamePad gamePad;
    private Animator animator;


    private boolean moveFreely = false;

    public Player(GamePad controller) {
        super(controller);
        this.gamePad = controller;
        animator = new Animator(this);

        setSpeed(5);
        setDimension(20,56);
        teleport(120,100);
        loadAnimatorFrames();
    }

    @Override
    public void update() {
        super.update();
        if (moveFreely) {
            moveAccordingToHandler();
        }
        if (gamePad.isJumpPressed()) {
            startJump();
        }

        //updateDimensions();
        animator.update();

        //System.out.println("X : " + x + " Y : " + y);

    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(animator.determineWhichFrameToDraw(), x, y);

        //drawHitBox(buffer);
    }

    public void setMoveFreely(boolean input) {
        moveFreely = input;
    }

    public void loadAnimatorFrames() {
        animator.setLeftFrames(8, ImageLoader.getInstance().getPlayerFrames("left"));
        animator.setRightFrames(8, ImageLoader.getInstance().getPlayerFrames("right"));
        animator.setIdleFrames(2, ImageLoader.getInstance().getPlayerFrames("idle"));
        animator.setDownFrame(1, ImageLoader.getInstance().getPlayerFrames("down"));
        animator.setUpFrame(1, ImageLoader.getInstance().getPlayerFrames("up"));
        animator.setDoubleJumpFrames(8, ImageLoader.getInstance().getPlayerFrames("doubleJump"));
    }

    @Override
    public boolean hasMoved() {
        return gamePad.isMovementKeyPressed();
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

    public Kunai fire() {
        System.out.println("fired");
        return new Kunai(this);
    }
}
