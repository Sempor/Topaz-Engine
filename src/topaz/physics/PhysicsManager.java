package topaz.physics;

import java.util.ArrayList;

public class PhysicsManager {

    private ArrayList<AxisAlignedBoundingBox> boundingBoxes = new ArrayList<>();

    public void add(AxisAlignedBoundingBox box) {
        boundingBoxes.add(box);
    }

    public ArrayList<AxisAlignedBoundingBox> getBoundingBoxes() {
        return boundingBoxes;
    }

    public void setBoundingBoxes(ArrayList<AxisAlignedBoundingBox> boundingBoxes) {
        this.boundingBoxes = boundingBoxes;
    }
}