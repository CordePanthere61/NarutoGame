package cegepst.player;

import cegepst.*;
import cegepst.engine.Sound;
import cegepst.engine.entity.CollidableRepository;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.entity.ControllableEntity;


public class Player extends ControllableEntity {



    private GamePad gamePad;
    private Hud hud;
    private int fireCooldown = 0;
    private int nbDeaths = 0;
    private boolean killed = false;
    private Log log;

    private boolean moveFreely = false;

    public Player(GamePad controller) {
        super(controller);
        this.gamePad = controller;
        this.hud = new Hud();
        setSpeed(5);
        setDimension(20,56);
        teleport(200,450);
        animator.setAnimationSpeed(4);
        loadAnimatorFrames();
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void update(World world) {
        super.update();
        super.animator.update();
        if (moveFreely && (x >= 0 || x <= 800)) {
            moveAccordingToHandler();
        }
        if (killed && animator.logAnimationFinished()) {
            teleport(x, -25);
            killed = false;
        }
        if (x <= 0) {
            x = 1;
        } else if (x >= 800) {
            x = 799;
        }
        if (y >= 600) {
            if (!killed) {
                this.kill();
            }
        }

        if (log != null) {
            log.update();
        }
        if (gamePad.isFireKeyPressed() && canFire()) {
            world.addKunais(fire());
        }
        if (gamePad.isJumpPressed()) {
            startJump();
        }

        fireCooldown--;
        if (fireCooldown <= 0) {
            fireCooldown = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(super.animator.determineWhichFrameToDraw(), x, y);
        hud.draw(buffer);
        if (log != null) {
            log.draw(buffer);
        }
        //drawHitBox(buffer);
    }

    public void setMoveFreely(boolean input) {
        moveFreely = input;
    }

    public void loadAnimatorFrames() {
        super.animator.setLeftFrames(8, ImageLoader.getInstance().getPlayerFrames("left"));
        super.animator.setRightFrames(8, ImageLoader.getInstance().getPlayerFrames("right"));
        super.animator.setIdleFrames(2, ImageLoader.getInstance().getPlayerFrames("idle"));
        super.animator.setDownFrame(1, ImageLoader.getInstance().getPlayerFrames("down"));
        super.animator.setUpFrame(1, ImageLoader.getInstance().getPlayerFrames("up"));
        super.animator.setDoubleJumpFrames(4, ImageLoader.getInstance().getPlayerFrames("doubleJump"));
        super.animator.setFireLeftFrames(7, ImageLoader.getInstance().getPlayerFrames("fireLeft"));
        super.animator.setFireRightFrames(7, ImageLoader.getInstance().getPlayerFrames("fireRight"));
        super.animator.setLogAnimationFrames(5, ImageLoader.getInstance().getPlayerFrames("log"));
    }

    @Override
    public boolean hasMoved() {
        return gamePad.isMovementKeyPressed();
    }

    public Kunai fire() {
        fireCooldown = 50;
        super.animator.fireAnimation();
        return new Kunai(this);
    }

    public void kill() {
        Sound.play("sounds/mob.wav");
        animator.logAnimation();
        log = new Log(x, y);
        hud.reduceLog();
        nbDeaths++;
        killed = true;
    }

    public boolean hasWon() {
        return x >= 799;
    }

    public boolean isAlive() {
        return nbDeaths < 3;
    }

    public boolean canFire() {
        return fireCooldown == 0 && animator.logAnimationFinished();
    }
}
