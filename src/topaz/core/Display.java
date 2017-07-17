package topaz.core;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import topaz.util.Color4f;

public class Display {

    public static final int NUM_SAMPLES = 4;

    private long windowID;
    private String title;
    private boolean visible = true;
    private int vSync = 1;

    public Display(String title, int width, int height) {
        this.title = title;

        //Creates error callback to print error messages in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        //Initializes GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, NUM_SAMPLES);

        windowID = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (windowID == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //Gets thread atack and pushes new frame
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(windowID, pWidth, pHeight);
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowPos(windowID, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        }

        GLFW.glfwMakeContextCurrent(windowID);

        GLFW.glfwSwapInterval(vSync);

        if (visible) {
            GLFW.glfwShowWindow(windowID);
        }
    }

    public long getWindowID() {
        return windowID;
    }

    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(windowID, title);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSize(int width, int height) {
        GLFW.glfwSetWindowSize(windowID, width, height);
    }

    public void setWidth(int width) {
        GLFW.glfwSetWindowSize(windowID, width, getHeight());
    }

    public void setHeight(int height) {
        GLFW.glfwSetWindowSize(windowID, getWidth(), height);
    }

    public int getWidth() {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(windowID, widthBuffer, heightBuffer);
        return widthBuffer.get(0);
    }

    public int getHeight() {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(windowID, widthBuffer, heightBuffer);
        return heightBuffer.get(0);
    }

    public void setBackgroundColor(Color4f backgroundColor) {
        setBackgroundColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
    }

    public void setBackgroundColor(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            GLFW.glfwShowWindow(windowID);
        } else {
            GLFW.glfwShowWindow(0);
        }
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void enableZBuffer(boolean toggle) {
        if (toggle) {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }

    public void enableMultisampling(boolean toggle) {
        if (toggle) {
            GL11.glEnable(GL13.GL_MULTISAMPLE);
        } else {
            GL11.glDisable(GL13.GL_MULTISAMPLE);
        }
    }

    public void enableFaceCulling(boolean toggle) {
        if (toggle) {
            GL11.glEnable(GL11.GL_CULL_FACE);
        } else {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
    }

    public void setVSync(int vSync) {
        this.vSync = vSync;
        GLFW.glfwSwapInterval(vSync);
    }

    public int getVSync() {
        return vSync;
    }

    public double getCursorX() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(windowID, xBuffer, yBuffer);
        return xBuffer.get(0);
    }

    public double getCursorY() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(windowID, xBuffer, yBuffer);
        return yBuffer.get(0);
    }

    public void setCursorLocation(double x, double y) {
        GLFW.glfwSetCursorPos(windowID, x, y);
    }

    public void centerCursor() {
        GLFW.glfwSetCursorPos(windowID, getWidth() / 2, getHeight() / 2);
    }

    public void makeCursorVisible(boolean isVisible) {
        if (isVisible) {
            GLFW.glfwSetInputMode(windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        } else {
            GLFW.glfwSetInputMode(windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
        }
    }
}
