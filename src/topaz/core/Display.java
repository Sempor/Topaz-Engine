package topaz.core;

import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import topaz.rendering.RenderSettings;

public class Display {

    private static long windowID;
    private static String windowTitle;

    public static void init(String title, int width, int height) {
        windowTitle = title;
        
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
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, RenderSettings.getNumSamples());

        //Creates window
        windowID = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (windowID == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //Creates key callback that is called whenever a key event occurs
        GLFW.glfwSetKeyCallback(windowID, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });

        //Gets thread atack and pushes new frame
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(windowID, pWidth, pHeight);
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowPos(windowID, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        }

        GLFW.glfwMakeContextCurrent(windowID);

        //Sets vSync
        GLFW.glfwSwapInterval(RenderSettings.getVSync());

        //Makes window visible if it needs to be visible
        if (RenderSettings.isDisplayVisible()) {
            GLFW.glfwShowWindow(windowID);
        }
    }

    public static long getWindowID() {
        return windowID;
    }

    public static void setTitle(String newTitle) {
        GLFW.glfwSetWindowTitle(windowID, newTitle);
        windowTitle = newTitle;
    }

    public static String getTitle() {
        return windowTitle;
    }
}