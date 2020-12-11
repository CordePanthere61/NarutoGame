package cegepst;

import cegepst.enemies.Deidara;
import cegepst.enemies.Enemy;
import cegepst.enemies.Kakashi;
import cegepst.engine.entity.MovableEntity;
import cegepst.player.Player;

import java.util.ArrayList;

public class EnemyLoader {

    private ArrayList<Enemy> enemies;

    public EnemyLoader() {
        enemies = new ArrayList<>();
    }

    public void createEnemies(Player player) {
        enemies.add(new Kakashi(player, 470, 450));
        enemies.add(new Deidara(player, 1015, 450));
        enemies.add(new Kakashi(player, 1455, 450));
        enemies.add(new Deidara(player, 1785, 400));
        enemies.add(new Kakashi(player, 2405, 350));
        enemies.add(new Deidara(player, 3000,350));
        enemies.add(new Deidara(player, 3445,500));
        enemies.add(new Kakashi(player, 3900,470));
        enemies.add(new Kakashi(player, 4630,350));
        enemies.add(new Deidara(player, 5400,390));
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
