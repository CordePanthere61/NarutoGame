package cegepst;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.player.Camera;
import cegepst.player.Player;

import java.util.ArrayList;

public class NarutoGame extends Game {

    private Player player;
    private GamePad gamePad;
    private Camera camera;
    private World world;
    private Enemy enemy;
    private ArrayList<Kunai> kunais;

    public NarutoGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);

        world = new World(gamePad);
        camera = new Camera(gamePad, world, player);
        kunais = new ArrayList<>();
        //enemy = new Enemy(player);
    }

    @Override
    public void initialize() {
        ImageLoader.getInstance();
    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        player.update();
        world.update();
        camera.update();
        if (gamePad.isFireKeyPressed() && player.canFire()) {
            kunais.add(player.fire());
        }
        for (Kunai kunai : kunais) {
            kunai.update();
        }
        //enemy.update();
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        player.draw(buffer);
        camera.draw(buffer);
        for (Kunai kunai : kunais) {
            kunai.draw(buffer);
        }
        //enemy.draw(buffer);
    }
}
