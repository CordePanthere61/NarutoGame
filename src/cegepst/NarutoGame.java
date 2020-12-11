package cegepst;

import cegepst.enemies.Enemy;
import cegepst.engine.Sound;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.player.Player;

public class NarutoGame extends Game {

    private Player player;
    private GamePad gamePad;
    private Camera camera;
    private World world;
    private Enemy enemy;


    public NarutoGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        world = new World(gamePad, player);
        camera = new Camera(gamePad, world, player);

    }

    @Override
    public void initialize() {
        ImageLoader.getInstance();
        Sound.play("music/theme.wav");
    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        player.update(world);
        world.update();
        camera.update();

        if (gamePad.isQuitPressed() || !player.isAlive() || player.hasWon()) {
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        player.draw(buffer);
        camera.draw(buffer);
    }
}
