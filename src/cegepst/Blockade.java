package cegepst;


import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.entity.StaticEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Blockade extends MovableEntity {

    public Blockade() {
        super.disableGravity();
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Buffer buffer) {
            buffer.drawRectangle(x, y, width, height,
                    new Color(255, 0, 0, 100));
    }
}
