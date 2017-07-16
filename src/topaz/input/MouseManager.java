package topaz.input;

import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import topaz.core.Display;

public class MouseManager {

    private Display display;

    public boolean BUTTON_LEFT, BUTTON_RIGHT, BUTTON_MIDDLE;

    private float mouseSpeed;

    public MouseManager(Display display) {
        this.display = display;

        mouseSpeed = 0.0001f;
    }

    public void tick(long window) {
        BUTTON_LEFT = (GLFW.GLFW_PRESS == GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_LEFT));
        BUTTON_RIGHT = (GLFW.GLFW_PRESS == GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_RIGHT));
        BUTTON_MIDDLE = (GLFW.GLFW_PRESS == GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_MIDDLE));
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