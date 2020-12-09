package cegepst;

import cegepst.enemies.Deidara;
import cegepst.enemies.Enemy;
import cegepst.enemies.Kakashi;
import cegepst.engine.entity.MovableEntity;
import cegepst.engine.entity.StaticEntity;
import cegepst.player.Player;

import java.util.ArrayList;

public class EnemyLoader {

    private ArrayList<Enemy> enemies;
    private int height = 20;

    public EnemyLoader() {
        enemies = new ArrayList<>();
    }

    public void createEnemies(Player player) {
        enemies.add(new Kakashi(player, 470, 450));             //first platform kakashi
        enemies.add(new Deidara(player, 1015, 450));
        enemies.add(new Kakashi(player, 1455, 450));
        enemies.add(new Deidara(player, 1785, 430));
        enemies.add(new Kakashi(player, 2205, 350));
        enemies.add(new Kakashi(player, 2405, 350));
        enemies.add(new Deidara(player, 2820, 350));
        enemies.add(new Kakashi(player, 3015, 350));

    }

    public void addEnemiesAsEnemies(ArrayList<Enemy> entities) {
        for (Enemy enemy : enemies) {
            entities.add(enemy);
        }
    }

    public void addEnemiesAsEntities(ArrayList<MovableEntity> entities) {
        for (Enemy enemy : enemies) {
            entities.add(enemy);
        }
    }
}
