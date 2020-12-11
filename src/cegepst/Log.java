package cegepst;

import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Log extends MovableEntity {

    private int cooldown = 60;
    private Image image;

    public Log(int x, int y) {
        setDimension(25, 20);
        teleport(x , y);
        loadImage();
    }

    @Override
    public void update() {
        super.update();
        cooldown--;
        if (cooldown < 0) {
            cooldown = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (cooldown != 0) {
            buffer.drawImage(image, x , y);
        }
    }

    public void loadImage() {
        this.image = ImageLoader.getInstance().getLogImage();
    }
}
