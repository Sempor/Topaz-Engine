package topaz.core;

import topaz.input.KeyManager;
import topaz.input.MouseManager;
import topaz.physics.PhysicsManager;
import topaz.rendering.Camera;
import topaz.rendering.ObjectManager;
import topaz.rendering.RenderManager;
import topaz.rendering.ui.UIManager;

public abstract class CoreUser {

    public Display display;

    public RenderManager renderManager;
    public PhysicsManager physicsManager;
    public ObjectManager objectManager;
    public UIManager uiManager;
    public Camera camera;

    public KeyManager keyManager;
    public MouseManager mouseManager;

    private boolean paused = false;

    public abstract void init();

    public abstract void tick(double delta);

    public void render() {
    }

    /**
     * This method is used for internal engine purposes. It is NOT meant to be
     * called by users.
     *
     * @param display
     * @param renderManager
     * @param physicsManager
     * @param objectManager
     * @param uiManager
     * @param camera
     * @param keyManager
     * @param mouseManager
     */
    protected void setUp(Display display, RenderManager renderManager, PhysicsManager physicsManager,
            ObjectManager objectManager, UIManager uiManager, Camera camera, KeyManager keyManager, MouseManager mouseManager) {
        this.display = display;
        this.renderManager = renderManager;
        this.physicsManager = physicsManager;
        this.objectManager = objectManager;
        this.uiManager = uiManager;
        this.camera = camera;
        this.keyManager = keyManager;
        this.mouseManager = mouseManager;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
}
