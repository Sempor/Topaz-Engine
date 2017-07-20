package topaz.physics;

import java.util.ArrayList;
import topaz.core.Display;

public class PhysicsManager {

    private Display display;

    private ArrayList<PhysicsObject> physicalObjects = new ArrayList<>();

    public PhysicsManager(Display display) {
        this.display = display;
    }

    public void tick(double delta) {
        double elapsedSeconds = delta / (double) display.getFPS();
        for (PhysicsObject p : physicalObjects) {
            p.tick(elapsedSeconds);
        }
    }

    public void add(PhysicsObject physicalObject) {
        physicalObjects.add(physicalObject);
    }

    public void remove(PhysicsObject physicalObject) {
        physicalObjects.remove(physicalObject);
    }

    public ArrayList<PhysicsObject> getPhysicalObjects() {
        return physicalObjects;
    }

    public void setPhysicalObjects(ArrayList<PhysicsObject> physicalObjects) {
        this.physicalObjects = physicalObjects;
    }
}
