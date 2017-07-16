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
import topaz.util.Color4f;

public class CoreEngine implements Runnable {

    private String title;
    private int width, height;
    private Display display;

    private boolean running;
    private Thread thread;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private RenderManager renderManager;
    private PhysicsManager physicsManager;
    private Camera camera;

    private CoreUser coreUser;

    private boolean printFramesPerSecond = true;
    private boolean printVersionData = false;

    public CoreEngine(CoreUser coreUser, int width, int height) {
        this(coreUser, "Topaz Application", width, height);
    }

    public CoreEngine(CoreUser coreUser, String title, int width, int height) {
        this.coreUser = coreUser;
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

        int fps = 60;
        double nsPerTick = 1000000000D / fps;
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

                //Polls for window events
                GLFW.glfwPollEvents();

                //Swaps color buffers
                GLFW.glfwSwapBuffers(display.getWindowID());
            }

            if (timer > 1000000000D) {
                if (printFramesPerSecond) {
                    System.out.println("Frames per second: " + ticks);
                }
                ticks = 0;
                timer = 0;
            }

            if (GLFW.glfwWindowShouldClose(display.getWindowID())) {
                running = false;
            }
        }

        stop();
    }

    public void init() {
        display = new Display(title, width, height);

        GL.createCapabilities();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glEnable(GL11.GL_CULL_FACE);

        if (printVersionData) {
            System.out.println("OS Name: " + System.getProperty("os.name"));
            System.out.println("OS Version: " + System.getProperty("os.version"));
            System.out.println("LWJGL Version: " + Version.getVersion());
            System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
        }

        keyManager = new KeyManager();
        mouseManager = new MouseManager(display);
        mouseManager.makeCursorVisible(false);

        display.setBackgroundColor(Color4f.BLACK);
        camera = new Camera(display, mouseManager);
        camera.setFollowingMouse(true);
        renderManager = new RenderManager(display, mouseManager, camera);
        physicsManager = new PhysicsManager();

        coreUser.setUp(display, renderManager, physicsManager, camera, keyManager, mouseManager);
        coreUser.init();
    }

    public void tick(double delta) {
        renderManager.tick(delta);
        camera.tick(delta);
        mouseManager.centerCursor();

        keyManager.tick(display.getWindowID());

        coreUser.tick(delta);
    }

    public void render() {
        //Clears frame-buffer and z-buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        renderManager.render();

        coreUser.render();
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

    public void enablePrintFramesPerSecond(boolean toggle) {
        this.printFramesPerSecond = toggle;
    }

    public void enablePrintVersionData(boolean toggle) {
        this.printVersionData = toggle;
    }
}
