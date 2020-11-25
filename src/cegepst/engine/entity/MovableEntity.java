package cegepst.engine.entity;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.controls.Direction;

import java.awt.*;

public abstract class MovableEntity extends UpdatableEntity {

    private final Collision collision;
    protected Gravity gravity;
    private Direction direction = Direction.RIGHT;
    protected int speed = 1;
    private boolean moved;
    private boolean doubleJumped = false;
    private boolean gravityEnabled = true;
    private int jumpCooldown;
    private int lastX;
    private int lastY;


    public MovableEntity() {
        collision = new Collision(this);
        gravity = new Gravity(this, collision);
    }

    @Override
    public void update() { // done first before any other action
        if (gravityEnabled) {
            gravity.update();
        }
        if (!hasSpaceBelow() && doubleJumped) {
            doubleJumped = false;
        }
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
        jumpCooldown--;
        if (jumpCooldown < 0) {
            jumpCooldown = 0;
        }
        //System.out.println(doubleJumped);
    }



    public void startJump() {
        if (!gravityEnabled) {
            return;
        }

        if (gravity.canDoubleJump() && !doubleJumped && jumpCooldown == 0) {
            gravity.jump();
            //System.out.println("double jumped");
            doubleJumped = true;
        } else if (!hasSpaceBelow()) {
            //System.out.println("jumped");
            gravity.jump();
            jumpCooldown = 30;
        }

    }

    public void setGravityEnabled(boolean gravityEnabled) {
        this.gravityEnabled = gravityEnabled;
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void move(Direction direction) {
        gravity.updateCollisionSpeed(direction);
        this.direction = direction;
        int allowedSpeed = collision.getAllowedSpeed(direction);
        x += direction.getVelocityX(allowedSpeed);
        y += direction.getVelocityY(allowedSpeed);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean hasMoved() {
        return moved;
    }

    public boolean hasDoubleJumped() {
        return doubleJumped;
    }

    public void drawHitBox(Buffer buffer) {
        if (hasMoved()) {
            Rectangle hitBox = getCollisionBound(direction);
            buffer.drawRectangle(hitBox.x, hitBox.y, hitBox.width, hitBox.height, new Color(255, 0, 0, 200));
        }
    }

    public boolean collisionBoundIntersectWith(StaticEntity other) {
        if (other == null) {
            return false;
        }
        return getCollisionBound(direction).intersects(other.getBounds());
    }

    public Rectangle getCollisionBound(Direction direction) {
        switch (direction) {
            case UP: return getCollisionUpperBound();
            case DOWN: return getCollisionLowerBound();
            case LEFT: return getCollisionLeftBound();
            case RIGHT: return getCollisionRightBound();
        }
        return getBounds();
    }

    public boolean hasSpaceBelow() {
        return collision.getAllowedSpeed(Direction.DOWN) > 0;
    }

    private Rectangle getCollisionUpperBound() {
        return new Rectangle(x, y - speed, width, collision.getSpeed());
    }

    private Rectangle getCollisionLowerBound() {
        return new Rectangle(x, y + height, width, collision.getSpeed());
    }

    private Rectangle getCollisionLeftBound() {
        return new Rectangle(x - speed, y, collision.getSpeed(), height);
    }

    private Rectangle getCollisionRightBound() {
        return new Rectangle(x + width, y, collision.getSpeed(), height);
    }
}
