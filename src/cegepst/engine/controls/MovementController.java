package cegepst.engine.controls;

import java.awt.event.KeyEvent;

public class MovementController extends Controller {

    private int rightKey = KeyEvent.VK_RIGHT;
    private int leftKey = KeyEvent.VK_LEFT;

    public MovementController() {
        int[] pressedKeys = {rightKey, leftKey};
        bindKeys(pressedKeys);
    }

    public boolean isLeftPressed() {
        return super.isKeyPressed(leftKey);
    }

    public boolean isRightPressed() {
        return super.isKeyPressed(rightKey);
    }

    public boolean isMoving() {
        return isLeftPressed() || isRightPressed();
    }

    public void setRightKey(int rightKey) {
        super.removeKey(this.rightKey);
        super.bindKey(rightKey);
        this.rightKey = rightKey;
    }

    public void setLeftKey(int leftKey) {
        super.removeKey(this.leftKey);
        super.bindKey(leftKey);
        this.leftKey = leftKey;
    }
}
