package cegepst;

import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class World extends ControllableEntity {

    private Image background;
    private boolean touchedBound;
    private Blockade ground;

    public World(MovementController controller) {
        super(controller);
        super.disableGravity();
        super.setSpeed(5);
        super.setDimension(1600, 608);
        super.teleport(0,0);
        loadBackground();
        ground  = new Blockade();
        ground.setDimension(this.width, 20);
        ground.teleport(0, 560);
    }

    @Override
    public void update() {
        super.update();
        if (!touchedBound) {
            moveOppositeToHandler();
        }
//        System.out.println("X : " + x + " Y : " + y);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(background, x, y);
//        ground.draw(buffer);
    }

    public void isTouchingBound() {
        touchedBound = true;
    }

    public void isNotTouchingBound() {
        touchedBound = false;
    }

    private void loadBackground() {
        background = ImageLoader.getInstance().getMapImage();
    }


}
