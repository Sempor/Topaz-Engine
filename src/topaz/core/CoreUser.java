package topaz.core;

import topaz.input.KeyManager;
import topaz.input.MouseManager;
import topaz.physics.PhysicsManager;
import topaz.rendering.Camera;
import topaz.rendering.RenderManager;

public abstract class CoreUser {

    public Display display;

    public RenderManager renderManager;
    public PhysicsManager physicsManager;
    public Camera camera;

    public KeyManager keyManager;
    public MouseManager mouseManager;

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
     * @param camera
     * @param keyManager
     * @param mouseManager
     */
    protected void setUp(Display display, RenderManager renderManager, PhysicsManager physicsManager,
            Camera camera, KeyManager keyManager, MouseManager mouseManager) {
        this.display = display;
        this.renderManager = renderManager;
        this.physicsManager = physicsManager;
        this.camera = camera;
        this.keyManager = keyManager;
        this.mouseManager = mouseManager;
    }
}
