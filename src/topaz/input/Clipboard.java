package topaz.input;

import org.lwjgl.glfw.GLFW;

public class Clipboard {

    public static String getClipboardText(long window) {
        return GLFW.glfwGetClipboardString(window);
    }

    public static void setClipboardText(long window, String text) {
        GLFW.glfwSetClipboardString(window, text);
    }
}