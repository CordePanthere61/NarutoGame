package cegepst;


import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.entity.StaticEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Blockade extends MovableEntity {

    public Blockade(int x, int y, int width, int height) {
        this.teleport(x, y);
        this.setDimension(width, height);
        super.disableGravity();
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Buffer buffer) {

    }
}
