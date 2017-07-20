package topaz.physics;

import java.util.ArrayList;

public class PhysicsManager {

    private ArrayList<PhysicsObject> physicalObjects = new ArrayList<>();

    public void tick(double delta) {
        for (PhysicsObject p : physicalObjects) {
            p.tick(delta);
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
