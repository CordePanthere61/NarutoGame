package cegepst;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.entity.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {

    private Color color = new Color(255,255,255, 50);

    public Brick(int x, int y) {
        setDimension(200,20);
        teleport(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {

        buffer.drawRectangle(x, y, width, height, color);
    }
}
