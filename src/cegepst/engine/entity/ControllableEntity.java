package cegepst.engine.entity;

import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    @Override
    public void update() {
        super.update();
        updateDirection();
        //moveAccordingToHandler();
    }

    public void updateDirection() {
        if (controller.isRightPressed()) {
            super.setDirection(Direction.RIGHT);
        } else if (controller.isLeftPressed()) {
            super.setDirection(Direction.LEFT);
        } else if (super.getDirection() == Direction.DOWN && !hasSpaceBelow()) {
            super.setDirection(Direction.RIGHT);
        }
    }

    public void moveOppositeToHandler() {
        if (!controller.isMoving()) {
            return;
        }
        if (controller.isRightPressed()) {
            moveLeft();
        } else if (controller.isLeftPressed()) {
            moveRight();
        }
    }

    public void moveAccordingToHandler() {
        if (!controller.isMoving()) {
            return;
        }
        if (controller.isRightPressed()) {
            moveRight();
        } else if (controller.isLeftPressed()) {
            moveLeft();
        }
    }
}
