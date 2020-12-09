package cegepst;

import cegepst.Blockade;
import cegepst.World;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Camera extends ControllableEntity {

    private World world;
    private Player player;
    private ArrayList<Blockade> borders;

    public Camera(MovementController controller, World world, Player player) {
        super(controller);
        super.setSpeed(5);
        super.disableGravity();
        super.setDimension(800,600);
        super.teleport(0,0);
        this.world = world;
        this.player = player;
        //setBorders();
    }

    @Override
    public void update() {
        super.update();
        if (super.collisionBoundIntersectWith(world)) {
            world.isNotTouchingBound();
            player.setMoveFreely(false);
        } else {
            world.isTouchingBound();
            player.setMoveFreely(true);
        }
    }

    @Override
    public void draw(Buffer buffer) {
//        for (Blockade blockade  : borders) {
//            blockade.draw(buffer);
//        }
    }
}
