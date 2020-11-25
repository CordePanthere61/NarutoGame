package cegepst;


import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import cegepst.engine.graphics.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World extends ControllableEntity {

    private static final String MAP_PATH = "images/narutomap.png";
    private Image background;
    private ArrayList<Blockade> blockades;

    public World(MovementController controller) {
        super(controller);
        setSpeed(5);
        teleport(0,0);
        super.setGravityEnabled(false);
        loadBackground();
        blockades = new ArrayList<>();
        Blockade grassBlockade = new Blockade();
        grassBlockade.setDimension(1600, 10);
        grassBlockade.teleport(0, 560);
        blockades.add(grassBlockade);
    }

    @Override
    public void update() {
        super.update();
        moveOppositeToHandler();
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, x, y);
        for (Blockade blockade : blockades) {
            blockade.draw(buffer);
        }
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
