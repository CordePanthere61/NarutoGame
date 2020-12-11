package cegepst;

import cegepst.engine.entity.StaticEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Hud extends StaticEntity {

    private int nbLogs;
    private Image image;

    public Hud() {
        nbLogs = 3;
        loadImage();
    }

    @Override
    public void draw(Buffer buffer) {
        for (int i = 0; i < nbLogs; i ++) {
            buffer.drawImage(image, 35 * i, 20);
        }
    }

    public void reduceLog() {
        nbLogs--;
    }

    private void loadImage() {
        image = ImageLoader.getInstance().getLogImage();
    }
}
