package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;

public class Player extends ControllableEntity {


    public Player(MovementController controller) {
        super(controller);
        setSpeed(3);
        setDimension(30,30);
        teleport(100,100);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.red);
    }
}
