package cegepst;

import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import java.awt.*;

public class Animator {

    private MovableEntity entity;


    private static final int FIRE_ANIMATION_SPEED = 6;
    private static final int DOUBLE_JUMP_ANIMATION_SPEED = 12;
    private static final int LOG_ANIMATION_SPEED = 10;
    private Image[] leftFrames;
    private Image[] rightFrames;
    private Image[] idleFrames;
    private Image[] downFrame;
    private Image[] upFrame;
    private Image[] doubleJumpFrames;
    private Image[] fireLeftFrames;
    private Image[] fireRightFrames;
    private Image[] deadAnimationFrames;
    private Image[] meleeAttackLeftFrames;
    private Image[] meleeAttackRightFrames;
    private Image[] logAnimationFrames;
    private boolean fired = false;
    private boolean doubleJumped = false;
    private boolean deadAnimation = false;
    private boolean meleeAttackAnimation = false;
    private boolean logAnimation = false;
    private int currentAnimationFrame = 0; // idle frame (middle)
    private int animationSpeed = 4;
    private int nextFrame = animationSpeed;


    public Animator(MovableEntity entity) {
        this.entity = entity;
    }

    public void update() {
        updateCurrentAnimationFrame();
        if (entity.isDeleted()) {
            deadAnimation();
        }
        //updateFireAnimation();
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

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    public void setLeftMeleeAttackFrames(int nbImages, Image[] images) {
        meleeAttackLeftFrames = new Image[nbImages];
        meleeAttackLeftFrames = images;
    }

    public void setRightMeleeAttackFrames(int nbImages, Image[] images) {
        meleeAttackRightFrames = new Image[nbImages];
        meleeAttackRightFrames = images;
    }

    public void setLogAnimationFrames(int nbImages, Image[] images) {
        logAnimationFrames = new Image[nbImages];
        logAnimationFrames = images;
    }

    public Image determineWhichFrameToDraw() {
        if (logAnimation) {
            return logAnimationFrames[currentAnimationFrame];
        } else if (meleeAttackAnimation) {
            if (entity.getDirection() == Direction.LEFT) {
                return meleeAttackLeftFrames[currentAnimationFrame];
            } else {
                return meleeAttackRightFrames[currentAnimationFrame];
            }
        } else if (doubleJumped) {
            return doubleJumpFrames[currentAnimationFrame];
        } else if (entity.hasMoved() && !fired) {
            if (entity.getDirection() == Direction.RIGHT) {
                return rightFrames[currentAnimationFrame];
            } else if (entity.getDirection() == Direction.LEFT) {
                return leftFrames[currentAnimationFrame];
            } else if (entity.getDirection() == Direction.DOWN) {
                return downFrame[0];
            } else if (entity.getDirection() == Direction.UP) {
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
                return downFrame[0];
            } else if (entity.getDirection() == Direction.UP) {
                return upFrame[0];
            }
        }
        return null;
    }

    public void fireAnimation() {
        fired = true;
        currentAnimationFrame = 0;
    }

    public void doubleJumpAnimation() {
        doubleJumped = true;
        currentAnimationFrame = 0;
    }

    public void deadAnimation() {
        deadAnimation = true;
    }

    public boolean isDeadAnimationFinished() {
        return deadAnimation;
    }

    public boolean logAnimationFinished() {
        return !logAnimation;
    }

    public void meleeAttack() {
        meleeAttackAnimation = true;
        currentAnimationFrame = 0;
    }

    public void logAnimation() {
        logAnimation = true;
        currentAnimationFrame = 0;
    }

    public boolean meleeAttackFinished() {
        return !meleeAttackAnimation;
    }

    private void updateCurrentAnimationFrame() {
        if (logAnimation) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= logAnimationFrames.length) {
                    currentAnimationFrame = 0;
                    logAnimation = false;
                }
                nextFrame = LOG_ANIMATION_SPEED;
            }
        } else if (meleeAttackAnimation) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= meleeAttackRightFrames.length) {
                    currentAnimationFrame = 0;
                    meleeAttackAnimation = false;
                }
                nextFrame = FIRE_ANIMATION_SPEED;
            }
        } else if (fired) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= fireLeftFrames.length) {
                    currentAnimationFrame = 0;
                    fired = false;
                }
                nextFrame = FIRE_ANIMATION_SPEED;
            }
        } else if (doubleJumped) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= doubleJumpFrames.length) {
                    currentAnimationFrame = 0;
                    doubleJumped = false;
                }
                nextFrame = DOUBLE_JUMP_ANIMATION_SPEED;
            }
        } else if (!entity.hasSpaceBelow() || entity.hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = animationSpeed;
            }
        } else {
            currentAnimationFrame = 0;
        }
    }

}
