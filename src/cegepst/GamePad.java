package cegepst;

import cegepst.engine.graphics.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int rightKey = KeyEvent.VK_D;
    private int leftKey = KeyEvent.VK_A;
    private int jumpKey = KeyEvent.VK_SPACE;

    public GamePad() {
        super.bindKey(quitKey);
        super.bindKey(jumpKey);
        super.setRightKey(rightKey);
        super.setLeftKey(leftKey);
        RenderingEngine.getInstance().addInputListener(this);
    }

    @Override
    public boolean isLeftPressed() {
        return super.isLeftPressed();
    }

    @Override
    public boolean isRightPressed() {
        return super.isRightPressed();
    }

    public boolean isJumpPressed() {
        return super.isKeyPressed(jumpKey);
    }

    public boolean isQuitPressed() {
        return super.isKeyPressed(quitKey);
    }
}
