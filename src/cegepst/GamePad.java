package cegepst;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int fireKey = KeyEvent.VK_SPACE;
    private int rightKey = KeyEvent.VK_D;
    private int leftKey = KeyEvent.VK_A;

    public GamePad() {
        super.bindKey(quitKey);
        super.bindKey(fireKey);
        super.setRightKey(rightKey);
        super.setLeftKey(leftKey);
        RenderingEngine.getInstance().addInputListener(this);
    }

    public boolean isFirePressed() {
        return super.isKeyPressed(fireKey);
    }

    public boolean isQuitPressed() {
        return super.isKeyPressed(quitKey);
    }
}
