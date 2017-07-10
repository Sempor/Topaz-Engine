package topaz.core;

import org.lwjgl.Version;
import topaz.input.KeyManager;
import topaz.input.MouseManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import topaz.rendering.RenderManager;

public class CoreSystem implements Runnable {

    private String title;
    private int width, height;

    private boolean running;
    private Thread thread;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private CoreUser coreUser;

    private boolean printFramesPerSecond = true;
    private boolean printVersionData = false;

    public CoreSystem(CoreUser coreUser, String title, int width, int height) {
        this.coreUser = coreUser;
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

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
                GLFW.glfwSwapBuffers(Display.getWindow());
            }

            if (timer > 1000000000D) {
                if (printFramesPerSecond) {
                    System.out.println("Frames per second: " + ticks);
                }
                ticks = 0;
                timer = 0;
            }

            if (GLFW.glfwWindowShouldClose(Display.getWindow())) {
                running = false;
            }
        }

        stop();
    }

    public void init() {
        Display.init(title, width, height);

        GL.createCapabilities();

        if (printVersionData) {
            System.out.println("OS Name: " + System.getProperty("os.name"));
            System.out.println("OS Version: " + System.getProperty("os.version"));
            System.out.println("LWJGL Version: " + Version.getVersion());
            System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
        }

        //Sets screen clear color to black
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        RenderManager.init();

        coreUser.setUp(RenderManager.getCamera(), keyManager, mouseManager);
        coreUser.init();
    }

    public void tick(double delta) {
        RenderManager.tick(delta);
        
        keyManager.tick(Display.getWindow());

        coreUser.tick(delta);
    }

    public void render() {
        //Clears screen / framebuffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        RenderManager.render();

        coreUser.render();
    }

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

    public void setPrintFramesPerSecond(boolean printFramesPerSecond) {
        this.printFramesPerSecond = printFramesPerSecond;
    }

    public void setPrintVersionData(boolean printVersionData) {
        this.printVersionData = printVersionData;
    }
}
