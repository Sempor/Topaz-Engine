package topaz.core;

import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class Display {

    private static long window;
    private static String title;
    private static int width, height;

    public static void init(String displayTitle, int displayWidth, int displayHeight) {
        title = displayTitle;
        width = displayWidth;
        height = displayHeight;

        //Creates error callback to print error messages in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        //Initializes GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        //Configures GLFW
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        //Creates window
        window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //Creates key callback that is called whenever a key event occurs
        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });

        //Gets thread atack and pushes new frame
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        }

        GLFW.glfwMakeContextCurrent(window);

        //Enables v-sync
        GLFW.glfwSwapInterval(1);

        //Makes window visible
        GLFW.glfwShowWindow(window);
    }

    public static long getWindow() {
        return window;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
