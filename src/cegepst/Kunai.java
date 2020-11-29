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
        setSpeed(3);
        disableGravity();
        setDimension(20, 9);
        setDirection(this.player.getDirection());
        if (super.getDirection() == Direction.LEFT) {
            super.teleport(player.getX() - 3, player.getY());
        } else {
            super.teleport(player.getX() + 3, player.getY());
        }
        loadFrames();
    }

    @Override
    public void update() {
        super.move(player.getDirection());
        System.out.println(height);
    }

    @Override
    public void draw(Buffer buffer) {
//        if (super.getDirection() == Direction.LEFT) {
//            buffer.drawImage(leftFrame, x, y);
//        } else {
//            buffer.drawImage(rightFrame, x, y);
//        }
        buffer.drawRectangle(width, height, x, y, Color.red);
    }

    private void loadFrames() {
        leftFrame = ImageLoader.getInstance().getKunaiFrames("left");
        rightFrame = ImageLoader.getInstance().getKunaiFrames("right");
    }
}
