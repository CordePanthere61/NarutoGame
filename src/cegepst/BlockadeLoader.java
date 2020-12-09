package cegepst;

import cegepst.engine.entity.MovableEntity;

import java.util.ArrayList;

public class BlockadeLoader {

    private ArrayList<Blockade> blockades;
    private int blockadeHeight = 20;

    public BlockadeLoader() {
        blockades = new ArrayList<>();
        createBlockades();
    }

    private void createBlockades() {
        blockades.add(new Blockade(0, 534, 506, blockadeHeight));       //1
        blockades.add(new Blockade(614, 534, 884, blockadeHeight));     //2
        blockades.add(new Blockade(1606, 470, 244, blockadeHeight));    //3
        blockades.add(new Blockade(1990, 406, 436, blockadeHeight));    //4
        blockades.add(new Blockade(2598, 406, 436, blockadeHeight));    //5
        blockades.add(new Blockade(3142, 566, 340, blockadeHeight));    //6
        blockades.add(new Blockade(3590, 534, 596, blockadeHeight));    //7
        blockades.add(new Blockade(4326, 438, 500, blockadeHeight));    //8
        blockades.add(new Blockade(4870, 470, 692, blockadeHeight));    //9
        blockades.add(new Blockade(5702, 501, 314, blockadeHeight));    //10
    }

    public void addBlockades(ArrayList<MovableEntity> entities) {
        for (Blockade blockade : blockades) {
            entities.add(blockade);
        }
    }
}
