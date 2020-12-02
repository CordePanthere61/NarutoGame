package cegepst;

import cegepst.engine.controls.Direction;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.entity.MovableEntity;
import cegepst.player.Player;

import java.awt.*;

public class Animator {

    private MovableEntity entity;

    private static final int ANIMATION_SPEED = 4;
    private Image[] leftFrames;
    private Image[] rightFrames;
    private Image[] idleFrames;
    private Image[] downFrame;
    private Image[] upFrame;
    private Image[] doubleJumpFrames;
    private Image[] fireLeftFrames;
    private Image[] fireRightFrames;
    private boolean fired = false;
    private int fireCooldown = 0;
    private int currentAnimationFrame = 0; // idle frame (middle)
    private int nextFrame = ANIMATION_SPEED;

    public Animator(MovableEntity entity) {
        this.entity = entity;
    }

    public void update() {
        updateCurrentAnimationFrame();
        updateFireAnimation();
    }

    public void setDownFrame(int nbImages, Image[] images) {
        downFrame = new Image[nbImages];
        downFrame = images;
    }

    public void setUpFrame(int nbImages, Image[] images) {
        upFrame = new Image[nbImages];
        upFrame = images;
    }

    public void setLeftFrames(int nbImages, Image[] images) {
        leftFrames = new Image[nbImages];
        leftFrames = images;
    }

    public void setRightFrames(int nbImages, Image[] images) {
        rightFrames = new Image[nbImages];
        rightFrames = images;
    }

    public void setIdleFrames(int nbImages, Image[] images) {
        idleFrames = new Image[nbImages];
        idleFrames = images;
    }

    public void setDoubleJumpFrames(int nbImages, Image[] images) {
        doubleJumpFrames = new Image[nbImages];
        doubleJumpFrames = images;
    }

    public void setFireRightFrames(int nbImages, Image[] images) {
        fireRightFrames = new Image[nbImages];
        fireRightFrames = images;
    }

    public void setFireLeftFrames(int nbImages, Image[] images) {
        fireLeftFrames = new Image[nbImages];
        fireLeftFrames = images;
    }

    public Image determineWhichFrameToDraw() {
        if (entity.hasMoved() && !fired) {
            if (entity.getDirection() == Direction.RIGHT) {
                return rightFrames[currentAnimationFrame];
            } else if (entity.getDirection() == Direction.LEFT) {
                return leftFrames[currentAnimationFrame];
            } else if (entity.getDirection() == Direction.DOWN) {
                if (entity.hasDoubleJumped()) {
                    return doubleJumpFrames[currentAnimationFrame];
                }
                return downFrame[0];
            } else if (entity.getDirection() == Direction.UP) {
                if (entity.hasDoubleJumped()) {
                    return doubleJumpFrames[currentAnimationFrame];
                }
                return upFrame[0];
            }
        } else if (fired) {
            if (entity.getDirection() == Direction.LEFT) {
                return fireLeftFrames[currentAnimationFrame];
            } else if (entity.getDirection() == Direction.RIGHT) {
                return fireRightFrames[currentAnimationFrame];
            }
        } else {
            if (entity.getDirection() == Direction.RIGHT) {
                return idleFrames[0];
            } else if (entity.getDirection() == Direction.LEFT) {
                return idleFrames[1];
            } else if (entity.getDirection() == Direction.DOWN) {
                if (entity.hasDoubleJumped()) {
                    return doubleJumpFrames[currentAnimationFrame];
                }
                return idleFrames[0];
            } else if (entity.getDirection() == Direction.UP) {
                return upFrame[0];
            }
        }
        return null;
    }

    public void fireAnimation() {
        fired = true;
        fireCooldown = 20;
    }

    private void updateFireAnimation() {
        fireCooldown--;
        if (fireCooldown <= 0) {
            fired = false;
            fireCooldown = 0;
        }
    }

    private void updateCurrentAnimationFrame() {
        if (fired) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= fireLeftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else if (!entity.hasSpaceBelow() || entity.hasMoved()) {
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
