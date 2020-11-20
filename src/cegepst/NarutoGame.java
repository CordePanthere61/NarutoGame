package cegepst;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.player.Player;

import java.util.ArrayList;

public class NarutoGame extends Game {

    private Player player;
    private GamePad gamePad;
    private ArrayList<Brick> bricks;
    private Enemy enemy;

    public NarutoGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        bricks = new ArrayList<>();
        enemy = new Enemy(player);
    }

    @Override
    public void initialize() {
        ImageLoader.getInstance();
        bricks.add(new Brick(0, 400, 800, 20));
        bricks.add(new Brick(200, 250, 400, 20));
    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        player.update();
        enemy.update();
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        player.draw(buffer);
        enemy.draw(buffer);
        for (Brick brick : bricks) {
            brick.draw(buffer);
        }
    }
}
