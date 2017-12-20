package topaz.core;

import topaz.physics.PhysicsManager;
import topaz.rendering.Camera;
import topaz.rendering.GameObject;
import topaz.rendering.RenderManager;
import topaz.rendering.ui.UIManager;

public abstract class CoreApp {

    public Display display;
    public RenderManager renderManager;
    public PhysicsManager physicsManager;
    public UIManager uiManager;
    public Camera camera;
    public GameObject rootObject;
    public Input input;

    private boolean paused;
    
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
