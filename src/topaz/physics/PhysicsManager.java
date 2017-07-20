package topaz.physics;

import java.util.ArrayList;

public class PhysicsManager {

    private ArrayList<PhysicalObject> physicalObjects = new ArrayList<>();

    public void tick(double delta) {
        for (PhysicalObject p : physicalObjects) {
            p.tick(delta);
        }
    }

    public void add(PhysicalObject physicalObject) {
        physicalObjects.add(physicalObject);
    }

    public void remove(PhysicalObject physicalObject) {
        physicalObjects.remove(physicalObject);
    }

    public ArrayList<PhysicalObject> getPhysicalObjects() {
        return physicalObjects;
    }

    public void setPhysicalObjects(ArrayList<PhysicalObject> physicalObjects) {
        this.physicalObjects = physicalObjects;
    }
}
