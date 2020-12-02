package cegepst;

import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;

import java.awt.*;

public class Kunai extends MovableEntity {

    private Image leftFrame;
    private Image rightFrame;
    private Player player;

    public Kunai(Player player) {
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
        super.move(super.getDirection());
    }

    @Override
    public void draw(Buffer buffer) {
        if (super.getDirection() == Direction.LEFT) {
            buffer.drawImage(leftFrame, x, y);
        } else {
            buffer.drawImage(rightFrame, x, y);
        }

    }

    private void loadFrames() {
        leftFrame = ImageLoader.getInstance().getKunaiFrames("left");
        rightFrame = ImageLoader.getInstance().getKunaiFrames("right");
    }
}
