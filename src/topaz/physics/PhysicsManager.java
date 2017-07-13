package topaz.physics;

import java.util.ArrayList;

public class PhysicsManager {

    private static ArrayList<AxisAlignedBoundingBox> boundingBoxes = new ArrayList<>();

    public static void addBoundingBox(AxisAlignedBoundingBox box) {
        boundingBoxes.add(box);
    }

    public static ArrayList<AxisAlignedBoundingBox> getBoundingBoxes() {
        return boundingBoxes;
    }

    public static void setBoundingBoxes(ArrayList<AxisAlignedBoundingBox> boundingBoxes) {
        PhysicsManager.boundingBoxes = boundingBoxes;
    }
}
