package topaz.physics;

import java.util.ArrayList;
import topaz.core.Display;

public class PhysicsManager {

    private Display display;

    private ArrayList<PhysicsObject> physicsObjects = new ArrayList<>();

    public PhysicsManager(Display display) {
        this.display = display;
    }

    public void tick(double delta) {
        double elapsedSeconds = delta / (double) display.getFPS();
        for (PhysicsObject p : physicsObjects) {
            p.tick(elapsedSeconds);
        }
    }

    public void add(PhysicsObject physicsObject) {
        if (physicsObject == null) {
            return;
        }
        physicsObjects.add(physicsObject);
    }

    public void remove(PhysicsObject physicsObject) {
        if (physicsObject == null) {
            return;
        }
        physicsObjects.remove(physicsObject);
    }

    public ArrayList<PhysicsObject> getPhysicsObjects() {
        return physicsObjects;
    }

    public void setPhysicsObjects(ArrayList<PhysicsObject> physicsObjects) {
        this.physicsObjects = physicsObjects;
    }
}
