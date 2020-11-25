package cegepst;

import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class World extends ControllableEntity {

    private Image background;

    public World(MovementController controller) {
        super(controller);
        super.disableGravity();
        super.setSpeed(5);
        super.setDimension(1600, 608);
        super.teleport(0,0);
        loadBackground();
    }

    @Override
    public void update() {
        super.update();
        moveOppositeToHandler();

    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(background, x, y);
    }

    private void loadBackground() {
        background = ImageLoader.getInstance().getMapImage();
    }
}
