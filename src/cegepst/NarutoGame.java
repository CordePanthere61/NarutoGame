package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class NarutoGame extends Game {

    private Player player;
    private GamePad gamePad;
    private Brick brick;
    private Brick brick2;

    public NarutoGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        brick = new Brick(100, 400);
        brick2 = new Brick(250, 450);
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
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        player.draw(buffer);
        brick.draw(buffer);
        brick2.draw(buffer);
    }
}
