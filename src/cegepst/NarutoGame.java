package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class NarutoGame extends Game {

    private Player player;
    private GamePad gamePad;

    public NarutoGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Buffer buffer) {
        player.draw(buffer);
    }
}
