package cegepst;

import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.graphics.Buffer;

public class Camera extends ControllableEntity {


    public Camera(MovementController controller) {
        super(controller);
        setDimension(800,600);
        setSpeed(5);
        teleport(0,0);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
    }

    @Override
    public void draw(Buffer buffer) {

    }
}
