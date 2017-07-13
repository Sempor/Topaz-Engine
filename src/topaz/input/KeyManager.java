package topaz.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.lwjgl.glfw.GLFW;

public class KeyManager implements KeyListener {

    /*TODO
    Add the rest of the key codes
     */
    private boolean[] keys, justPressed, cantPress;
    public boolean KEY_A, KEY_B, KEY_C, KEY_D, KEY_E, KEY_F, KEY_G, KEY_H, KEY_I,
            KEY_J, KEY_K, KEY_L, KEY_M, KEY_N, KEY_O, KEY_P, KEY_Q, KEY_R, KEY_S,
            KEY_T, KEY_U, KEY_V, KEY_W, KEY_X, KEY_Y, KEY_Z;
    public boolean KEY_1, KEY_2, KEY_3, KEY_4, KEY_5, KEY_6, KEY_7, KEY_8,
            KEY_9, KEY_0;
    public boolean KEY_SPACE, KEY_ENTER, KEY_ESCAPE;
    public boolean KEY_LEFT, KEY_RIGHT, KEY_UP, KEY_DOWN;
    public boolean KEY_TAB, KEY_CAPS, KEY_SHIFT_LEFT, KEY_SHIFT_RIGHT,
            KEY_CONTROL_LEFT, KEY_CONTROL_RIGHT, KEY_ALT_LEFT, KEY_ALT_RIGHT,
            KEY_BACKSPACE, KEY_DELETE;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick(long window) {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }
        }

        KEY_A = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A));
        KEY_B = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_B));
        KEY_C = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_C));
        KEY_D = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D));
        KEY_E = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_E));
        KEY_F = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_F));
        KEY_G = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_G));
        KEY_H = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_H));
        KEY_I = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_I));
        KEY_J = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_J));
        KEY_K = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_K));
        KEY_L = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_L));
        KEY_M = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_M));
        KEY_N = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_N));
        KEY_O = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_O));
        KEY_P = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_P));
        KEY_Q = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_Q));
        KEY_R = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_R));
        KEY_S = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S));
        KEY_T = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_T));
        KEY_U = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_U));
        KEY_V = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_V));
        KEY_W = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W));
        KEY_X = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_X));
        KEY_Y = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_Y));
        KEY_Z = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_Z));
        KEY_1 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_1));
        KEY_2 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_2));
        KEY_3 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_3));
        KEY_4 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_4));
        KEY_5 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_5));
        KEY_6 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_6));
        KEY_7 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_7));
        KEY_8 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_8));
        KEY_9 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_9));
        KEY_0 = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_0));
        KEY_SPACE = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_SPACE));
        KEY_ENTER = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ENTER));
        KEY_ESCAPE = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE));
        KEY_UP = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_UP));
        KEY_DOWN = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_DOWN));
        KEY_RIGHT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT));
        KEY_LEFT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT));
        KEY_TAB = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_TAB));
        KEY_CAPS = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_CAPS_LOCK));
        KEY_SHIFT_LEFT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT));
        KEY_SHIFT_RIGHT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT));
        KEY_CONTROL_LEFT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL));
        KEY_CONTROL_RIGHT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_CONTROL));
        KEY_ALT_LEFT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_ALT));
        KEY_ALT_RIGHT = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_ALT));
        KEY_BACKSPACE = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_BACKSPACE));
        KEY_DELETE = intToBoolean(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_DELETE));
    }

    public boolean keyJustPressed(int keyCode) {
        return justPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = false;
    }

    //Assumed that value is 0 or 1
    private boolean intToBoolean(int value) {
        return value == 1;
    }
}
