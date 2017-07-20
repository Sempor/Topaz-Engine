package topaz.core;

import topaz.input.KeyManager;
import topaz.input.MouseManager;
import topaz.physics.PhysicsManager;
import topaz.rendering.Camera;
import topaz.rendering.ObjectManager;
import topaz.rendering.RenderManager;
import topaz.rendering.ui.UIManager;

public abstract class CoreApp {

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

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
}
