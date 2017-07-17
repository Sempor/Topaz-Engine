package topaz.input;

import org.lwjgl.glfw.GLFW;

public class KeyManager {

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
            KEY_PAUSE;

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

    public KeyManager() {
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
    }

    public void tick(long window) {
        //Alphabet keys
        KEY_A.tick(window);
        KEY_B.tick(window);
        KEY_C.tick(window);
        KEY_D.tick(window);
        KEY_E.tick(window);
        KEY_F.tick(window);
        KEY_G.tick(window);
        KEY_H.tick(window);
        KEY_I.tick(window);
        KEY_J.tick(window);
        KEY_K.tick(window);
        KEY_L.tick(window);
        KEY_M.tick(window);
        KEY_N.tick(window);
        KEY_O.tick(window);
        KEY_P.tick(window);
        KEY_Q.tick(window);
        KEY_R.tick(window);
        KEY_S.tick(window);
        KEY_T.tick(window);
        KEY_U.tick(window);
        KEY_V.tick(window);
        KEY_W.tick(window);
        KEY_X.tick(window);
        KEY_Y.tick(window);
        KEY_Z.tick(window);
        //Number keys
        KEY_1.tick(window);
        KEY_2.tick(window);
        KEY_3.tick(window);
        KEY_4.tick(window);
        KEY_5.tick(window);
        KEY_6.tick(window);
        KEY_7.tick(window);
        KEY_8.tick(window);
        KEY_9.tick(window);
        KEY_0.tick(window);
        //Punctuation mark keys
        KEY_APOSTROPHE.tick(window);
        KEY_COMMA.tick(window);
        KEY_MINUS.tick(window);
        KEY_PERIOD.tick(window);
        KEY_SLASH.tick(window);
        KEY_SEMICOLON.tick(window);
        KEY_EQUAL.tick(window);
        KEY_BRACKET_LEFT.tick(window);
        KEY_BRACKET_RIGHT.tick(window);
        KEY_BACKSLASH.tick(window);
        //F keys
        KEY_F1.tick(window);
        KEY_F2.tick(window);
        KEY_F3.tick(window);
        KEY_F4.tick(window);
        KEY_F5.tick(window);
        KEY_F6.tick(window);
        KEY_F7.tick(window);
        KEY_F8.tick(window);
        KEY_F9.tick(window);
        KEY_F10.tick(window);
        KEY_F11.tick(window);
        KEY_F12.tick(window);
        //Arrow keys
        KEY_UP.tick(window);
        KEY_DOWN.tick(window);
        KEY_LEFT.tick(window);
        KEY_RIGHT.tick(window);
        //Other keys
        KEY_SPACE.tick(window);
        KEY_ENTER.tick(window);
        KEY_ESCAPE.tick(window);
        KEY_TAB.tick(window);
        KEY_CAPS_LOCK.tick(window);
        KEY_SHIFT_LEFT.tick(window);
        KEY_SHIFT_RIGHT.tick(window);
        KEY_CONTROL_LEFT.tick(window);
        KEY_CONTROL_RIGHT.tick(window);
        KEY_ALT_LEFT.tick(window);
        KEY_ALT_RIGHT.tick(window);
        KEY_BACKSPACE.tick(window);
        KEY_DELETE.tick(window);
        KEY_INSERT.tick(window);
        KEY_PAGE_UP.tick(window);
        KEY_PAGE_DOWN.tick(window);
        KEY_HOME.tick(window);
        KEY_END.tick(window);
        KEY_NUM_LOCK.tick(window);
        KEY_SCROLL_LOCK.tick(window);
        KEY_PRINT_SCREEN.tick(window);
        KEY_PAUSE.tick(window);
    }
}