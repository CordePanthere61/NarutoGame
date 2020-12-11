package cegepst.enemies;

import cegepst.ImageLoader;
import cegepst.Kunai;
import cegepst.player.Player;

import java.util.ArrayList;


public class Deidara extends Enemy{

    private int fireCooldown = 0;
    private Player target;

    public Deidara(Player target, int x, int y) {
        super(target, x, y);
        super.setDimension(40, 56);
        this.target = target;
        this.type = "range";
        teleport(x,y);
        loadFrames();
    }

    @Override
    public void update() {
        super.update();
        setDirection(super.getTargetDirection());
        fireCooldown--;
        if (fireCooldown <= 0) {
            fireCooldown = 0;
        }
    }


    @Override
    public void loadFrames() {
        animator.setIdleFrames(2, ImageLoader.getInstance().getDeidaraFrames("idle"));
        animator.setLeftFrames(6, ImageLoader.getInstance().getDeidaraFrames("left"));
        animator.setRightFrames(6, ImageLoader.getInstance().getDeidaraFrames("right"));
        animator.setDownFrame(1, ImageLoader.getInstance().getDeidaraFrames("down"));
        animator.setFireLeftFrames(4, ImageLoader.getInstance().getDeidaraFrames("fireLeft"));
        animator.setFireRightFrames(4, ImageLoader.getInstance().getDeidaraFrames("fireRight"));
    }

    @Override
    public void updateKunais(ArrayList<Kunai> kunais) {
        if (targetIsInRange() && canFire()) {
            kunais.add(fire());
        }
    }

    public Kunai fire() {
        fireCooldown = 250;
        super.animator.fireAnimation();
        return new Kunai(this);
    }

    public boolean canFire() {
        return fireCooldown == 0;
    }

}
