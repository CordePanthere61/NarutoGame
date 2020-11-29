package cegepst.player;

import cegepst.World;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;

import java.awt.*;

public class Camera extends ControllableEntity {

    private World world;
    private Player player;

    public Camera(MovementController controller, World world, Player player) {
        super(controller);
        super.setSpeed(5);
        super.disableGravity();
        super.setDimension(800,600);
        super.teleport(0,0);
        this.world = world;
        this.player = player;
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

    }
}
