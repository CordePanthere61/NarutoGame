package cegepst.engine.entity;

import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveAccordingToHandler() {
        if (!controller.isMoving()) {
            return;
        } else if (controller.isRightPressed()) {
            moveRight();
        } else if (controller.isLeftPressed()) {
            moveLeft();
        }
    }
}
