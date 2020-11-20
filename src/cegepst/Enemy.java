package cegepst;

import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Enemy extends MovableEntity {

    private Player target;
    private java.awt.Color color = new Color(255,0,0);

    public Enemy(Player target){
        this.target = target;
        setSpeed(5);
        setDimension(20,56);
        teleport(650,100);
        loadFrames();
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, color);
    }

    private void loadFrames() {

    }
}
