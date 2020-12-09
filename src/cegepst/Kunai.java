package cegepst;

import cegepst.enemies.Enemy;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Kunai extends MovableEntity {

    private Image leftFrame;
    private Image rightFrame;
    private MovableEntity player;

    public Kunai(MovableEntity player) {
        this.player = player;
        setSpeed(6);
        disableGravity();
        setDimension(20, 9);
        setDirection(player.getDirection());
        if (super.getDirection() == Direction.LEFT) {
            super.teleport(player.getX() - 3, player.getY() + 28);
        } else {
            super.teleport(player.getX() + 23, player.getY() + 28);
        }
        loadFrames();
    }

    @Override
    public void update() {
        super.update();
        super.move(super.getDirection());

    }

    @Override
    public void draw(Buffer buffer) {
        if (!deleted) {
            if (super.getDirection() == Direction.LEFT) {
                buffer.drawImage(leftFrame, x, y);
            } else {
                buffer.drawImage(rightFrame, x, y);
            }
        }


    }

    public void deleteEnemiesHit(World world) {
        ArrayList<Enemy> enemies = world.getEnemies();
        for (Enemy entity : enemies) {
            if (this.collisionBoundIntersectWith(entity)) {
                entity.delete();
                this.delete();
            }
        }
    }

    public void killPlayerIfHits(Player player) {
        if (this.collisionBoundIntersectWith(player)) {
            player.kill();
            this.delete();
        }
    }

    private void loadFrames() {
        leftFrame = ImageLoader.getInstance().getKunaiFrames("left");
        rightFrame = ImageLoader.getInstance().getKunaiFrames("right");
    }
}
