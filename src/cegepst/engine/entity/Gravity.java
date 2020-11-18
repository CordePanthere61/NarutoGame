package cegepst.engine.entity;

import cegepst.engine.controls.Direction;

public class Gravity {

    private Collision collision;
    private MovableEntity entity;

    private double fallingSpeed = 1; // falling speed;
    private double jumpSpeed = 4;
    private int jumpMaxHeight = 24; // jumping max
    private int currentJumpMeter = 0;

    private boolean jumping;
    private boolean falling;

    public Gravity(MovableEntity entity,Collision collision) {
        this.collision = collision;
        this.entity = entity;
    }

    public void update() {
        if (jumping) {
            jumpAnimation();
        } else {
            // Are we falling?
            if (entity.hasSpaceBelow()) {
                fallingAnimation();
            } else {
                falling = false;
                fallingSpeed = 1; // reset
            }
        }
    }

    private void fallingAnimation() {
        falling = true;
        entity.move(Direction.DOWN);
        fallingSpeed += 0.15; // Acceleration constant (custom to game)
    }

    private void jumpAnimation() {
        entity.move(Direction.UP);
        currentJumpMeter++;
        jumpSpeed -= 0.05; // deceleration
        if (jumpSpeed < 1) {
            jumpSpeed = 1; // minimum
        }
        if (currentJumpMeter == jumpMaxHeight) {
            jumping = false;
            currentJumpMeter = 0;
            jumpSpeed = 4; // reset
        }
    }

    public void updateCollisionSpeed(Direction direction) {
        if (jumping) {
            // Limit movement in midair (jump mobility)
            System.out.println("IM JUMPING " + jumpSpeed);
            collision.setSpeed((direction == Direction.UP) ? (int) jumpSpeed : 2); //mid air speed
        } else if (falling) {
            // Limit movement in midair (fall mobility)
            System.out.println("IM FALLING " + fallingSpeed);
            collision.setSpeed((direction == Direction.DOWN) ? (int) fallingSpeed : 2);
        } else {
            // Make sure to apply basic speed for other cases
            collision.setSpeed(entity.getSpeed());
        }
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void jump() {
        jumping = true;
    }

}
