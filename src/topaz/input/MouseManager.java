package topaz.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import topaz.core.Display;

public class MouseManager implements MouseListener, MouseMotionListener {

    private Display display;
    
    private boolean leftPressed, rightPressed;
    private float mouseSpeed;

    public MouseManager(Display display) {
        this.display = display;
        
        mouseSpeed = 0.0001f;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void setMouseSpeed(float mouseSpeed) {
        this.mouseSpeed = mouseSpeed;
    }

    public float getMouseSpeed() {
        return mouseSpeed;
    }

    public double getCursorX() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(display.getWindowID(), xBuffer, yBuffer);
        return xBuffer.get(0);
    }

    public double getCursorY() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(display.getWindowID(), xBuffer, yBuffer);
        return yBuffer.get(0);
    }

    public void setCursorLocation(double x, double y) {
        GLFW.glfwSetCursorPos(display.getWindowID(), x, y);
    }

    public void centerCursor() {
        GLFW.glfwSetCursorPos(display.getWindowID(), display.getWidth() / 2, display.getHeight() / 2);
    }

    public void makeCursorVisible(boolean isVisible) {
        if (isVisible) {
            GLFW.glfwSetInputMode(display.getWindowID(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        } else {
            GLFW.glfwSetInputMode(display.getWindowID(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
        }
    }
}
