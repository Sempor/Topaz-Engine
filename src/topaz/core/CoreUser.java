package topaz.core;

import topaz.input.KeyManager;
import topaz.input.MouseManager;
import topaz.rendering.Camera;

public abstract class CoreUser {

    public KeyManager keyManager;
    public MouseManager mouseManager;
    public Camera camera;

    public abstract void init();

    public abstract void tick(double delta);

    public void render() {
    }

    /**
     * This method is used for internal engine purposes. It is NOT meant to be
     * called by users.
     *
     * @param camera
     * @param keyManager
     * @param mouseManager
     */
    protected void setUp(Camera camera, KeyManager keyManager, MouseManager mouseManager) {
        this.camera = camera;
        this.keyManager = keyManager;
        this.mouseManager = mouseManager;
    }
}
