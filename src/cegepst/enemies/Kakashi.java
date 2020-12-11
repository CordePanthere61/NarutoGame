package cegepst.enemies;

import cegepst.ImageLoader;
import cegepst.Kunai;
import cegepst.player.Player;

import java.util.ArrayList;

public class Kakashi extends Enemy{

    private Player target;
    private boolean attack = false;

    public Kakashi(Player target, int x, int y) {
        super(target, x, y);
        super.setDimension(40,56);
        this.target = target;
        this.type = "melee";
        teleport(x,y);
        loadFrames();
    }

    @Override
    public void update() {
        if (!deleted) {
            super.update();
            if (targetIsInRange()) {
                super.followTarget();
            }
            if (collisionBoundIntersectWith(target) && !attack) {
                animator.meleeAttack();
                attack = true;
            }
            if (attack && animator.meleeAttackFinished()) {
                attack(target);
            }
        }
    }

    @Override
    public void loadFrames() {
        animator.setIdleFrames(2, ImageLoader.getInstance().getKakashiFrames("idle"));
        animator.setLeftFrames(6, ImageLoader.getInstance().getKakashiFrames("left"));
        animator.setRightFrames(6, ImageLoader.getInstance().getKakashiFrames("right"));
        animator.setDownFrame(1, ImageLoader.getInstance().getDeidaraFrames("down"));
        animator.setLeftMeleeAttackFrames(3, ImageLoader.getInstance().getKakashiFrames("meleeLeft"));
        animator.setRightMeleeAttackFrames(3, ImageLoader.getInstance().getKakashiFrames("meleeRight"));

    }

    @Override
    public void updateKunais(ArrayList<Kunai> kunais) {}

    private void attack(Player target) {
        target.kill();
        this.delete();
    }
}
