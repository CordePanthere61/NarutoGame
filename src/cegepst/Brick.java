package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.entity.StaticEntity;
import com.sun.scenario.effect.Color4f;

import javax.swing.plaf.ColorChooserUI;
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
