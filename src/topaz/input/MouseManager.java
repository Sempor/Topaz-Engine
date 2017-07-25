package topaz.input;

import org.lwjgl.glfw.GLFW;

public class MouseManager {

    public MouseButton BUTTON_LEFT, BUTTON_RIGHT, BUTTON_MIDDLE;
    
    private long windowID;
    private float mouseSpeed;
    private double scrollX, scrollY;
    private boolean scrollUp, scrollDown, scrollLeft, scrollRight;

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

    public MouseManager(long windowID) {
        this.windowID = windowID;
        
        mouseSpeed = 0.0002f;

        BUTTON_LEFT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_LEFT);
        BUTTON_RIGHT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_RIGHT);
        BUTTON_MIDDLE = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_MIDDLE);

        //Creates scroll callback that is called whenever a scroll event occurs
        GLFW.glfwSetScrollCallback(windowID, (window, xoffset, yoffset) -> {
            scrollX = xoffset;
            scrollY = yoffset;
        });
    }

    public void tick() {
        BUTTON_LEFT.tick(windowID);
        BUTTON_RIGHT.tick(windowID);
        BUTTON_MIDDLE.tick(windowID);

        if (scrollX > 0) {
            scrollRight = true;
            scrollX = 0;
        } else if (scrollX < 0) {
            scrollLeft = true;
            scrollX = 0;
        }

        if (scrollY > 0) {
            scrollUp = true;
            scrollY = 0;
        } else if (scrollY < 0) {
            scrollDown = true;
            scrollY = 0;
        }
    }

    public void setMouseSpeed(float mouseSpeed) {
        this.mouseSpeed = mouseSpeed;
    }

    public float getMouseSpeed() {
        return mouseSpeed;
    }

    public boolean scrollUpEvent() {
        return scrollUp;
    }

    public boolean scrollDownEvent() {
        return scrollDown;
    }

    public boolean scrollLeftEvent() {
        return scrollLeft;
    }

    public boolean scrollRightEvent() {
        return scrollRight;
    }
}