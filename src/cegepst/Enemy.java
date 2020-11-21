package cegepst;

import cegepst.engine.controls.Direction;
import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Enemy extends MovableEntity {

    private Player target;

    public Enemy(Player target){
        this.target = target;
        setSpeed(2);
        setDimension(20,56);
        teleport(550,100);
        loadFrames();
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        followTarget();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.green);
    }

    private void loadFrames() {

    }

    private void followTarget() {
        if (!gravity.isFalling()) {

            if (target.getY() > y) {
                moveRight();
            } else {
                move(getTargetDirection());
            }

        }
    }

    private Direction getTargetDirection() {
        if (target.getX() > x) {
            return Direction.RIGHT;
        } else if (target.getX() < x) {
            return Direction.LEFT;
        }
        return Direction.LEFT;
    }
}
