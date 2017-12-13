package topaz.core;

import org.lwjgl.Version;
import topaz.input.KeyManager;
import topaz.input.MouseManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import topaz.physics.PhysicsManager;
import topaz.rendering.Camera;
import topaz.rendering.RenderManager;
import topaz.rendering.ui.UIManager;
import topaz.util.Color;

public class CoreEngine implements Runnable {

    public static final String DEFAULT_DISPLAY_TITLE = "Topaz Game Engine - An Application";

    //Display properties
    private String displayTitle;
    private int displayWidth, displayHeight;
    //Internal engine variables
    private boolean running;
    private Thread thread;
    private CoreApp coreApp;
    //Print
    private boolean printFPS = true;
    private boolean printSoftwareInformation = false;

    public CoreEngine(CoreApp coreApp, int displayWidth, int displayHeight) {
        this(coreApp, DEFAULT_DISPLAY_TITLE, displayWidth, displayHeight);
    }

    public CoreEngine(CoreApp coreApp, String displayTitle, int displayWidth, int displayHeight) {
        this.coreApp = coreApp;
        this.displayTitle = displayTitle;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
    }

    /**
     * Starts the game.
     */
    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();

        double nsPerTick = 1_000_000_000D / coreApp.display.getFPS();
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();
        long timeElapsed = 0;
        int ticksElapsed = 0;

        while (running) {
            currentTime = System.nanoTime();
            timeElapsed += currentTime - lastTime;
            delta += (currentTime - lastTime) / nsPerTick;
            lastTime = currentTime;

            if (delta >= 1) {
                tick(delta);
                render();
                ticksElapsed++;
                delta -= ((long) delta);

                GLFW.glfwPollEvents();
                GLFW.glfwSwapBuffers(coreApp.display.getWindowID());
            }

            if (timeElapsed > 1000000000D) {
                if (printFPS) {
                    System.out.println("Frames per second: " + ticksElapsed);
                }
                ticksElapsed = 0;
                timeElapsed = 0;
            }

            if (GLFW.glfwWindowShouldClose(coreApp.display.getWindowID())) {
                running = false;
            }
        }

        stop();
    }

    private void init() {
        coreApp.display = new Display(displayTitle, displayWidth, displayHeight);

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glEnable(GL11.GL_CULL_FACE);

        if (printSoftwareInformation) {
            System.out.println("Operating System: " + System.getProperty("os.name")
                    + " , Version: " + System.getProperty("os.version"));
            System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
            System.out.println("LWJGL Version: " + Version.getVersion());
        }

        coreApp.display.setBackgroundColor(Color.BLACK);

        coreApp.keyManager = new KeyManager(coreApp.display.getWindowID());
        coreApp.mouseManager = new MouseManager(coreApp.display.getWindowID());
        coreApp.camera = new Camera(coreApp.display, coreApp.mouseManager);
        coreApp.renderManager = new RenderManager(coreApp.display, coreApp.camera);
        coreApp.physicsManager = new PhysicsManager(coreApp.display);
        coreApp.uiManager = new UIManager(coreApp.display);

        coreApp.init();
    }

    private void tick(double delta) {
        coreApp.physicsManager.tick(delta);
        coreApp.renderManager.tick(delta);
        coreApp.camera.tick(delta);
        coreApp.display.setCursorLocation(coreApp.display.getWidth() / 2, coreApp.display.getHeight() / 2);
        coreApp.uiManager.tick(delta);
        coreApp.keyManager.tick();
        coreApp.mouseManager.tick();

        if (!coreApp.isPaused()) {
            coreApp.tick(delta);
        }
    }

    private void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        coreApp.renderManager.render();
        coreApp.uiManager.render();

        coreApp.render();
    }

    /**
     * Stops the game.
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enablePrintFPS(boolean enabled) {
        this.printFPS = enabled;
    }

    public void enablePrintSoftwareInformation(boolean enabled) {
        this.printSoftwareInformation = enabled;
    }
}
