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
import topaz.rendering.ObjectManager;
import topaz.rendering.RenderManager;
import topaz.rendering.ui.UIManager;
import topaz.util.Color4f;

public class CoreEngine implements Runnable {

    private String title;
    private int width, height;
    private boolean running;
    private Thread thread;
    private CoreApp coreApp;
    private boolean printFPS = true;
    private boolean printSoftwareInformation = false;

    public CoreEngine(CoreApp coreApp, int width, int height) {
        this(coreApp, "Topaz Game Engine - An Application", width, height);
    }

    public CoreEngine(CoreApp coreApp, String title, int width, int height) {
        this.coreApp = coreApp;
        this.title = title;
        this.width = width;
        this.height = height;
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
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick(delta);
                render();
                ticks++;
                delta--;

                GLFW.glfwPollEvents();
                GLFW.glfwSwapBuffers(coreApp.display.getWindowID());
            }

            if (timer > 1000000000D) {
                if (printFPS) {
                    System.out.println("Frames per second: " + ticks);
                }
                ticks = 0;
                timer = 0;
            }

            if (GLFW.glfwWindowShouldClose(coreApp.display.getWindowID())) {
                running = false;
            }
        }

        stop();
    }

    private void init() {
        coreApp.display = new Display(title, width, height);

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glEnable(GL11.GL_CULL_FACE);

        if (printSoftwareInformation) {
            System.out.println("OS Name: " + System.getProperty("os.name"));
            System.out.println("OS Version: " + System.getProperty("os.version"));
            System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
            System.out.println("LWJGL Version: " + Version.getVersion());
        }

        coreApp.display.setBackgroundColor(Color4f.BLACK);

        coreApp.keyManager = new KeyManager(coreApp.display.getWindowID());
        coreApp.mouseManager = new MouseManager(coreApp.display.getWindowID());
        coreApp.camera = new Camera(coreApp.display, coreApp.mouseManager);
        coreApp.renderManager = new RenderManager(coreApp.display, coreApp.mouseManager, coreApp.camera);
        coreApp.physicsManager = new PhysicsManager(coreApp.display);
        coreApp.objectManager = new ObjectManager();
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

    public void setPrintFPS(boolean enabled) {
        this.printFPS = enabled;
    }

    public void setPrintSoftwareInformation(boolean enabled) {
        this.printSoftwareInformation = enabled;
    }
}
