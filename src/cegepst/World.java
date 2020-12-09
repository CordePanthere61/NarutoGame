package cegepst;

import cegepst.enemies.Deidara;
import cegepst.enemies.Enemy;
import cegepst.enemies.Kakashi;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class World extends ControllableEntity {

    private Image background;
    private boolean touchedBound;
    private Blockade ground;
    private Player player;
    private BlockadeLoader blockadeLoader;
    private EnemyLoader enemyLoader;
    private ArrayList<MovableEntity> entities;
    private ArrayList<Enemy> enemies;
    private ArrayList<MovableEntity> removedEntities;
    public ArrayList<Kunai> kunais;
    private ArrayList<Kunai> deletedKunais;

    public World(MovementController controller, Player player) {
        super(controller);
        super.disableGravity();
        super.setSpeed(5);
        super.setDimension(6016, 608);
        super.teleport(0,0);
        this.player = player;
        entities = new ArrayList<>();
        enemies = new ArrayList<>();
        removedEntities = new ArrayList<>();
        kunais = new ArrayList<>();
        deletedKunais = new ArrayList<>();
        loadBackground();
        loadBlockades();
        loadEnemies();
        //ground  = new Blockade(0, 560, width, 20);
    }

    @Override
    public void update() {
        super.update();
        moveEntities();
        updateKunais();
        updateEnemies();

    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(background, x, y);
        for (MovableEntity entity : entities) {
            entity.draw(buffer);
        }

        for (Kunai kunai : kunais) {
            kunai.draw(buffer);
        }
        //ground.draw(buffer);
    }

    public void updateKunais() {
        for (Kunai kunai : kunais) {
            kunai.update();
            kunai.deleteEnemiesHit(this);
            kunai.killPlayerIfHits(player);
            if (kunai.isDeleted()) {
                deletedKunais.add(kunai);
            }
        }
        kunais.removeAll(deletedKunais);
        deletedKunais.clear();
    }

    public void updateEnemies() {
        for (MovableEntity entity : entities) {
            entity.update();
            if (entity.isDeleted()) {
                removedEntities.add(entity);
            }
        }
        for (Enemy entity : enemies) {
            entity.update();
            entity.updateKunais(kunais);
            if (entity.isDeleted()) {
                removedEntities.add(entity);
            }
        }
        if (removedEntities.size() != 0 ) {
            entities.removeAll(removedEntities);
            enemies.removeAll(removedEntities);
            removedEntities.clear();
        }
    }

    public void moveEntities() {
        if (!touchedBound && !playerHitsSomething()) {
            moveOppositeToHandler();
            if (this.hasMoved()) {
                for (MovableEntity entity : entities) {
                    entity.setSpeed(speed);
                    entity.move(this.getDirection());
                }
                for (Enemy enemy : enemies) {
                    enemy.setSpeed(1);
                    if (enemy.getType().equals("melee")) {
                        enemy.move(this.getDirection());
                    }
                }
            } else {
                for (MovableEntity entity : entities) {
                    entity.resetSpeed();
                }
            }
        }
    }

    public void isTouchingBound() {
        touchedBound = true;
    }

    public void isNotTouchingBound() {
        touchedBound = false;
    }

    public void addKunais(Kunai kunai) {
        kunais.add(kunai);
    }

    private void loadBackground() {
        background = ImageLoader.getInstance().getMapImage();
    }

    private void loadEnemies() {
        enemyLoader = new EnemyLoader();
        enemyLoader.createEnemies(player);
        enemyLoader.addEnemiesAsEnemies(enemies);
        enemyLoader.addEnemiesAsEntities(entities);
    }

    private void loadBlockades() {
        blockadeLoader = new BlockadeLoader();
        blockadeLoader.addBlockades(entities);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    private boolean playerHitsSomething() {
        for (MovableEntity entity : entities) {
            if (player.collisionBoundIntersectWith(entity)) {
                return true;
            }
        }
        return false;
    }

}
