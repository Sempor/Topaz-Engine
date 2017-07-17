package topaz.input;

import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import topaz.core.Display;

public class MouseManager {

    private Display display;

    public MouseButton BUTTON_LEFT, BUTTON_RIGHT, BUTTON_MIDDLE;

    private float mouseSpeed;

    public class MouseButton {

        private int id;
        private boolean pressed;
        private int ticksPressed;
        private boolean justPressed;
        private boolean justReleased;

        public MouseButton(int id) {
            this.id = id;
        }

        public boolean isPressed() {
            return pressed;
        }

        public boolean isJustPressed() {
            return justPressed;
        }

        public boolean isJustReleased() {
            return justReleased;
        }

        public int getTicksPressed() {
            return ticksPressed;
        }

        public void tick(long window) {
            pressed = (GLFW.GLFW_PRESS == GLFW.glfwGetMouseButton(window, id));

            if (pressed) {
                justPressed = (ticksPressed == 0);
                ticksPressed++;
            } else {
                justReleased = (ticksPressed > 0);
                ticksPressed = 0;
            }
        }
    }

    public MouseManager(Display display) {
        this.display = display;

        mouseSpeed = 0.0001f;

        BUTTON_LEFT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_LEFT);
        BUTTON_RIGHT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_RIGHT);
        BUTTON_MIDDLE = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_MIDDLE);
    }

    public void tick(long window) {
        BUTTON_LEFT.tick(window);
        BUTTON_RIGHT.tick(window);
        BUTTON_MIDDLE.tick(window);
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
