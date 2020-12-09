package cegepst.enemies;

import cegepst.Kunai;
import cegepst.World;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;

import java.awt.*;
import java.util.ArrayList;


public abstract class Enemy extends MovableEntity {

    private Player target;
    private int speed = 1;
    protected String type;

    public Enemy(Player target, int x, int y){
        this.target = target;
        setSpeed(speed);
        animator.setAnimationSpeed(13);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        super.animator.update();
    }

    @Override
    public void draw(Buffer buffer) {
        if (!animator.isDeadAnimationFinished()) {
            buffer.drawImage(super.animator.determineWhichFrameToDraw(), x, y);
        }
    }

    public abstract void loadFrames();

    public abstract void updateKunais(ArrayList<Kunai> kunais);

    public void followTarget() {
        if (!gravity.isFalling()) {
            if (target.getY() > y) {
                moveRight();
            } else {
                move(getTargetDirection());
            }
        }
    }

    public String getType() {
        return type;
    }

    public boolean targetIsInRange() {
        return target.getX() >= (x - 600);
    }

    protected Direction getTargetDirection() {
        if (target.getX() > x) {
            return Direction.RIGHT;
        } else if (target.getX() < x) {
            return Direction.LEFT;
        }
        return Direction.LEFT;
    }


}