package topaz.core;

import org.lwjgl.glfw.GLFW;

public class Input {

    private long windowID;

    public MouseButton MOUSE_BUTTON_LEFT, MOUSE_BUTTON_RIGHT, MOUSE_BUTTON_MIDDLE;
    private float mouseSpeed;
    private double scrollX, scrollY;
    private boolean scrollUp, scrollDown, scrollLeft, scrollRight;

    public KeyButton KEY_A, //Alphabet keys
            KEY_B,
            KEY_C,
            KEY_D,
            KEY_E,
            KEY_F,
            KEY_G,
            KEY_H,
            KEY_I,
            KEY_J,
            KEY_K,
            KEY_L,
            KEY_M,
            KEY_N,
            KEY_O,
            KEY_P,
            KEY_Q,
            KEY_R,
            KEY_S,
            KEY_T,
            KEY_U,
            KEY_V,
            KEY_W,
            KEY_X,
            KEY_Y,
            KEY_Z,
            KEY_1, //Number keys
            KEY_2,
            KEY_3,
            KEY_4,
            KEY_5,
            KEY_6,
            KEY_7,
            KEY_8,
            KEY_9,
            KEY_0,
            KEY_LEFT, //Arrow keys
            KEY_RIGHT,
            KEY_UP,
            KEY_DOWN,
            KEY_APOSTROPHE, //Punctuation mark keys
            KEY_COMMA,
            KEY_MINUS,
            KEY_PERIOD,
            KEY_SLASH,
            KEY_SEMICOLON,
            KEY_EQUAL,
            KEY_BRACKET_LEFT,
            KEY_BRACKET_RIGHT,
            KEY_BACKSLASH,
            KEY_F1, //F keys
            KEY_F2,
            KEY_F3,
            KEY_F4,
            KEY_F5,
            KEY_F6,
            KEY_F7,
            KEY_F8,
            KEY_F9,
            KEY_F10,
            KEY_F11,
            KEY_F12,
            KEY_SPACE, //Other keys
            KEY_ENTER,
            KEY_ESCAPE,
            KEY_TAB,
            KEY_CAPS_LOCK,
            KEY_SHIFT_LEFT,
            KEY_SHIFT_RIGHT,
            KEY_CONTROL_LEFT,
            KEY_CONTROL_RIGHT,
            KEY_ALT_LEFT,
            KEY_ALT_RIGHT,
            KEY_BACKSPACE,
            KEY_DELETE,
            KEY_INSERT,
            KEY_PAGE_UP,
            KEY_PAGE_DOWN,
            KEY_HOME,
            KEY_END,
            KEY_NUM_LOCK,
            KEY_SCROLL_LOCK,
            KEY_PRINT_SCREEN,
            KEY_PAUSE,
            KEY_KEYPAD_0, //Keypad keys
            KEY_KEYPAD_1,
            KEY_KEYPAD_2,
            KEY_KEYPAD_3,
            KEY_KEYPAD_4,
            KEY_KEYPAD_5,
            KEY_KEYPAD_6,
            KEY_KEYPAD_7,
            KEY_KEYPAD_8,
            KEY_KEYPAD_9,
            KEY_KEYPAD_DECIMAL,
            KEY_KEYPAD_DIVIDE,
            KEY_KEYPAD_MULTIPLY,
            KEY_KEYPAD_SUBTRACT,
            KEY_KEYPAD_ADD,
            KEY_KEYPAD_ENTER,
            KEY_KEYPAD_EQUAL;


    public Input(long windowID) {
        this.windowID = windowID;

        //Alphabet keys
        KEY_A = new KeyButton(GLFW.GLFW_KEY_A);
        KEY_B = new KeyButton(GLFW.GLFW_KEY_B);
        KEY_C = new KeyButton(GLFW.GLFW_KEY_C);
        KEY_D = new KeyButton(GLFW.GLFW_KEY_D);
        KEY_E = new KeyButton(GLFW.GLFW_KEY_E);
        KEY_F = new KeyButton(GLFW.GLFW_KEY_F);
        KEY_G = new KeyButton(GLFW.GLFW_KEY_G);
        KEY_H = new KeyButton(GLFW.GLFW_KEY_H);
        KEY_I = new KeyButton(GLFW.GLFW_KEY_I);
        KEY_J = new KeyButton(GLFW.GLFW_KEY_J);
        KEY_K = new KeyButton(GLFW.GLFW_KEY_K);
        KEY_L = new KeyButton(GLFW.GLFW_KEY_L);
        KEY_M = new KeyButton(GLFW.GLFW_KEY_M);
        KEY_N = new KeyButton(GLFW.GLFW_KEY_N);
        KEY_O = new KeyButton(GLFW.GLFW_KEY_O);
        KEY_P = new KeyButton(GLFW.GLFW_KEY_P);
        KEY_Q = new KeyButton(GLFW.GLFW_KEY_Q);
        KEY_R = new KeyButton(GLFW.GLFW_KEY_R);
        KEY_S = new KeyButton(GLFW.GLFW_KEY_S);
        KEY_T = new KeyButton(GLFW.GLFW_KEY_T);
        KEY_U = new KeyButton(GLFW.GLFW_KEY_U);
        KEY_V = new KeyButton(GLFW.GLFW_KEY_V);
        KEY_W = new KeyButton(GLFW.GLFW_KEY_W);
        KEY_X = new KeyButton(GLFW.GLFW_KEY_X);
        KEY_Y = new KeyButton(GLFW.GLFW_KEY_Y);
        KEY_Z = new KeyButton(GLFW.GLFW_KEY_Z);
        //Number keys
        KEY_1 = new KeyButton(GLFW.GLFW_KEY_1);
        KEY_2 = new KeyButton(GLFW.GLFW_KEY_2);
        KEY_3 = new KeyButton(GLFW.GLFW_KEY_3);
        KEY_4 = new KeyButton(GLFW.GLFW_KEY_4);
        KEY_5 = new KeyButton(GLFW.GLFW_KEY_5);
        KEY_6 = new KeyButton(GLFW.GLFW_KEY_6);
        KEY_7 = new KeyButton(GLFW.GLFW_KEY_7);
        KEY_8 = new KeyButton(GLFW.GLFW_KEY_8);
        KEY_9 = new KeyButton(GLFW.GLFW_KEY_9);
        KEY_0 = new KeyButton(GLFW.GLFW_KEY_0);
        //Punctuation mark keys
        KEY_APOSTROPHE = new KeyButton(GLFW.GLFW_KEY_APOSTROPHE);
        KEY_COMMA = new KeyButton(GLFW.GLFW_KEY_COMMA);
        KEY_MINUS = new KeyButton(GLFW.GLFW_KEY_MINUS);
        KEY_PERIOD = new KeyButton(GLFW.GLFW_KEY_PERIOD);
        KEY_SLASH = new KeyButton(GLFW.GLFW_KEY_SLASH);
        KEY_SEMICOLON = new KeyButton(GLFW.GLFW_KEY_SEMICOLON);
        KEY_EQUAL = new KeyButton(GLFW.GLFW_KEY_EQUAL);
        KEY_BRACKET_LEFT = new KeyButton(GLFW.GLFW_KEY_LEFT_BRACKET);
        KEY_BRACKET_RIGHT = new KeyButton(GLFW.GLFW_KEY_RIGHT_BRACKET);
        KEY_BACKSLASH = new KeyButton(GLFW.GLFW_KEY_BACKSLASH);
        //F keys
        KEY_F1 = new KeyButton(GLFW.GLFW_KEY_F1);
        KEY_F2 = new KeyButton(GLFW.GLFW_KEY_F2);
        KEY_F3 = new KeyButton(GLFW.GLFW_KEY_F3);
        KEY_F4 = new KeyButton(GLFW.GLFW_KEY_F4);
        KEY_F5 = new KeyButton(GLFW.GLFW_KEY_F5);
        KEY_F6 = new KeyButton(GLFW.GLFW_KEY_F6);
        KEY_F7 = new KeyButton(GLFW.GLFW_KEY_F7);
        KEY_F8 = new KeyButton(GLFW.GLFW_KEY_F8);
        KEY_F9 = new KeyButton(GLFW.GLFW_KEY_F9);
        KEY_F10 = new KeyButton(GLFW.GLFW_KEY_F10);
        KEY_F11 = new KeyButton(GLFW.GLFW_KEY_F11);
        KEY_F12 = new KeyButton(GLFW.GLFW_KEY_F12);
        //Arrow keys
        KEY_LEFT = new KeyButton(GLFW.GLFW_KEY_LEFT);
        KEY_RIGHT = new KeyButton(GLFW.GLFW_KEY_RIGHT);
        KEY_UP = new KeyButton(GLFW.GLFW_KEY_UP);
        KEY_DOWN = new KeyButton(GLFW.GLFW_KEY_DOWN);
        //Other keys
        KEY_SPACE = new KeyButton(GLFW.GLFW_KEY_SPACE);
        KEY_ENTER = new KeyButton(GLFW.GLFW_KEY_ENTER);
        KEY_ESCAPE = new KeyButton(GLFW.GLFW_KEY_ESCAPE);
        KEY_TAB = new KeyButton(GLFW.GLFW_KEY_TAB);
        KEY_CAPS_LOCK = new KeyButton(GLFW.GLFW_KEY_CAPS_LOCK);
        KEY_SHIFT_LEFT = new KeyButton(GLFW.GLFW_KEY_LEFT_SHIFT);
        KEY_SHIFT_RIGHT = new KeyButton(GLFW.GLFW_KEY_RIGHT_SHIFT);
        KEY_CONTROL_LEFT = new KeyButton(GLFW.GLFW_KEY_LEFT_CONTROL);
        KEY_CONTROL_RIGHT = new KeyButton(GLFW.GLFW_KEY_RIGHT_CONTROL);
        KEY_ALT_LEFT = new KeyButton(GLFW.GLFW_KEY_LEFT_ALT);
        KEY_ALT_RIGHT = new KeyButton(GLFW.GLFW_KEY_RIGHT_ALT);
        KEY_BACKSPACE = new KeyButton(GLFW.GLFW_KEY_BACKSPACE);
        KEY_DELETE = new KeyButton(GLFW.GLFW_KEY_DELETE);
        KEY_INSERT = new KeyButton(GLFW.GLFW_KEY_INSERT);
        KEY_PAGE_UP = new KeyButton(GLFW.GLFW_KEY_PAGE_UP);
        KEY_PAGE_DOWN = new KeyButton(GLFW.GLFW_KEY_PAGE_DOWN);
        KEY_HOME = new KeyButton(GLFW.GLFW_KEY_HOME);
        KEY_END = new KeyButton(GLFW.GLFW_KEY_END);
        KEY_NUM_LOCK = new KeyButton(GLFW.GLFW_KEY_NUM_LOCK);
        KEY_SCROLL_LOCK = new KeyButton(GLFW.GLFW_KEY_SCROLL_LOCK);
        KEY_PRINT_SCREEN = new KeyButton(GLFW.GLFW_KEY_PRINT_SCREEN);
        KEY_PAUSE = new KeyButton(GLFW.GLFW_KEY_PAUSE);
        //Keypad keys
        KEY_KEYPAD_0 = new KeyButton(GLFW.GLFW_KEY_KP_0);
        KEY_KEYPAD_1 = new KeyButton(GLFW.GLFW_KEY_KP_1);
        KEY_KEYPAD_2 = new KeyButton(GLFW.GLFW_KEY_KP_2);
        KEY_KEYPAD_3 = new KeyButton(GLFW.GLFW_KEY_KP_3);
        KEY_KEYPAD_4 = new KeyButton(GLFW.GLFW_KEY_KP_4);
        KEY_KEYPAD_5 = new KeyButton(GLFW.GLFW_KEY_KP_5);
        KEY_KEYPAD_6 = new KeyButton(GLFW.GLFW_KEY_KP_6);
        KEY_KEYPAD_7 = new KeyButton(GLFW.GLFW_KEY_KP_7);
        KEY_KEYPAD_8 = new KeyButton(GLFW.GLFW_KEY_KP_8);
        KEY_KEYPAD_9 = new KeyButton(GLFW.GLFW_KEY_KP_9);
        KEY_KEYPAD_DECIMAL = new KeyButton(GLFW.GLFW_KEY_KP_DECIMAL);
        KEY_KEYPAD_DIVIDE = new KeyButton(GLFW.GLFW_KEY_KP_DIVIDE);
        KEY_KEYPAD_MULTIPLY = new KeyButton(GLFW.GLFW_KEY_KP_MULTIPLY);
        KEY_KEYPAD_SUBTRACT = new KeyButton(GLFW.GLFW_KEY_KP_SUBTRACT);
        KEY_KEYPAD_ADD = new KeyButton(GLFW.GLFW_KEY_KP_ADD);
        KEY_KEYPAD_ENTER = new KeyButton(GLFW.GLFW_KEY_KP_ENTER);
        KEY_KEYPAD_EQUAL = new KeyButton(GLFW.GLFW_KEY_KP_EQUAL);

        //Creates key callback that is called whenever a key event occurs
        GLFW.glfwSetKeyCallback(windowID, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });

        mouseSpeed = 0.002f;

        MOUSE_BUTTON_LEFT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_LEFT);
        MOUSE_BUTTON_RIGHT = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_RIGHT);
        MOUSE_BUTTON_MIDDLE = new MouseButton(GLFW.GLFW_MOUSE_BUTTON_MIDDLE);

        //Creates scroll callback that is called whenever a scroll event occurs
        GLFW.glfwSetScrollCallback(windowID, (window, xoffset, yoffset) -> {
            scrollX = xoffset;
            scrollY = yoffset;
        });
    }

    public void tick() {
        //Alphabet keys
        KEY_A.tick(windowID);
        KEY_B.tick(windowID);
        KEY_C.tick(windowID);
        KEY_D.tick(windowID);
        KEY_E.tick(windowID);
        KEY_F.tick(windowID);
        KEY_G.tick(windowID);
        KEY_H.tick(windowID);
        KEY_I.tick(windowID);
        KEY_J.tick(windowID);
        KEY_K.tick(windowID);
        KEY_L.tick(windowID);
        KEY_M.tick(windowID);
        KEY_N.tick(windowID);
        KEY_O.tick(windowID);
        KEY_P.tick(windowID);
        KEY_Q.tick(windowID);
        KEY_R.tick(windowID);
        KEY_S.tick(windowID);
        KEY_T.tick(windowID);
        KEY_U.tick(windowID);
        KEY_V.tick(windowID);
        KEY_W.tick(windowID);
        KEY_X.tick(windowID);
        KEY_Y.tick(windowID);
        KEY_Z.tick(windowID);
        //Number keys
        KEY_1.tick(windowID);
        KEY_2.tick(windowID);
        KEY_3.tick(windowID);
        KEY_4.tick(windowID);
        KEY_5.tick(windowID);
        KEY_6.tick(windowID);
        KEY_7.tick(windowID);
        KEY_8.tick(windowID);
        KEY_9.tick(windowID);
        KEY_0.tick(windowID);
        //Punctuation mark keys
        KEY_APOSTROPHE.tick(windowID);
        KEY_COMMA.tick(windowID);
        KEY_MINUS.tick(windowID);
        KEY_PERIOD.tick(windowID);
        KEY_SLASH.tick(windowID);
        KEY_SEMICOLON.tick(windowID);
        KEY_EQUAL.tick(windowID);
        KEY_BRACKET_LEFT.tick(windowID);
        KEY_BRACKET_RIGHT.tick(windowID);
        KEY_BACKSLASH.tick(windowID);
        //F keys
        KEY_F1.tick(windowID);
        KEY_F2.tick(windowID);
        KEY_F3.tick(windowID);
        KEY_F4.tick(windowID);
        KEY_F5.tick(windowID);
        KEY_F6.tick(windowID);
        KEY_F7.tick(windowID);
        KEY_F8.tick(windowID);
        KEY_F9.tick(windowID);
        KEY_F10.tick(windowID);
        KEY_F11.tick(windowID);
        KEY_F12.tick(windowID);
        //Arrow keys
        KEY_UP.tick(windowID);
        KEY_DOWN.tick(windowID);
        KEY_LEFT.tick(windowID);
        KEY_RIGHT.tick(windowID);
        //Other keys
        KEY_SPACE.tick(windowID);
        KEY_ENTER.tick(windowID);
        KEY_ESCAPE.tick(windowID);
        KEY_TAB.tick(windowID);
        KEY_CAPS_LOCK.tick(windowID);
        KEY_SHIFT_LEFT.tick(windowID);
        KEY_SHIFT_RIGHT.tick(windowID);
        KEY_CONTROL_LEFT.tick(windowID);
        KEY_CONTROL_RIGHT.tick(windowID);
        KEY_ALT_LEFT.tick(windowID);
        KEY_ALT_RIGHT.tick(windowID);
        KEY_BACKSPACE.tick(windowID);
        KEY_DELETE.tick(windowID);
        KEY_INSERT.tick(windowID);
        KEY_PAGE_UP.tick(windowID);
        KEY_PAGE_DOWN.tick(windowID);
        KEY_HOME.tick(windowID);
        KEY_END.tick(windowID);
        KEY_NUM_LOCK.tick(windowID);
        KEY_SCROLL_LOCK.tick(windowID);
        KEY_PRINT_SCREEN.tick(windowID);
        KEY_PAUSE.tick(windowID);
        //Keypad keys
        KEY_KEYPAD_0.tick(windowID);
        KEY_KEYPAD_1.tick(windowID);
        KEY_KEYPAD_2.tick(windowID);
        KEY_KEYPAD_3.tick(windowID);
        KEY_KEYPAD_4.tick(windowID);
        KEY_KEYPAD_5.tick(windowID);
        KEY_KEYPAD_6.tick(windowID);
        KEY_KEYPAD_7.tick(windowID);
        KEY_KEYPAD_8.tick(windowID);
        KEY_KEYPAD_9.tick(windowID);
        KEY_KEYPAD_DECIMAL.tick(windowID);
        KEY_KEYPAD_DIVIDE.tick(windowID);
        KEY_KEYPAD_MULTIPLY.tick(windowID);
        KEY_KEYPAD_SUBTRACT.tick(windowID);
        KEY_KEYPAD_ADD.tick(windowID);
        KEY_KEYPAD_ENTER.tick(windowID);
        KEY_KEYPAD_EQUAL.tick(windowID);

        MOUSE_BUTTON_LEFT.tick(windowID);
        MOUSE_BUTTON_RIGHT.tick(windowID);
        MOUSE_BUTTON_MIDDLE.tick(windowID);

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
    public class KeyButton {
        
        private int id;
        private boolean pressed;
        private int ticksPressed;
        private boolean justPressed;
        private boolean justReleased;
        
        public KeyButton(int id) {
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
            pressed = (GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, id));
            
            if (pressed) {
                justPressed = (ticksPressed == 0);
                ticksPressed++;
            } else {
                justReleased = (ticksPressed > 0);
                ticksPressed = 0;
            }
        }
    }
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
}