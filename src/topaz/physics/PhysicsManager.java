package topaz.physics;

import topaz.core.Display;
import topaz.rendering.GameObject;

public class PhysicsManager {

    private Display display;
    private GameObject rootObject;

    public PhysicsManager(Display display, GameObject rootObject) {
        this.display = display;
        this.rootObject = rootObject;
    }

    public void tick(double delta) {
        double elapsedSeconds = delta / (double) display.getFPS();
        for (GameObject object : rootObject.getAllDescendants()) {
            object.tick(elapsedSeconds);
        }
    }
}
