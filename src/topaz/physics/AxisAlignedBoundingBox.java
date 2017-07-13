package topaz.physics;

import org.joml.Vector3f;
import topaz.util.Interval;

public class AxisAlignedBoundingBox {

    public float x = 0, y = 0, z = 0; //Default values
    public float width, height, depth;

    public AxisAlignedBoundingBox(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        PhysicsManager.addBoundingBox(this);
    }

    public AxisAlignedBoundingBox(Vector3f dimensions) {
        width = dimensions.x;
        height = dimensions.y;
        depth = dimensions.z;
        PhysicsManager.addBoundingBox(this);
    }

    public AxisAlignedBoundingBox(float x, float y, float z, float width, float height, float depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        PhysicsManager.addBoundingBox(this);
    }

    public AxisAlignedBoundingBox(Vector3f location, Vector3f dimensions) {
        x = location.x;
        y = location.y;
        z = location.z;
        width = dimensions.x;
        height = dimensions.y;
        depth = dimensions.z;
        PhysicsManager.addBoundingBox(this);
    }

    public boolean checkBoundingBoxCollisions() {
        for (AxisAlignedBoundingBox box : PhysicsManager.getBoundingBoxes()) {
            if (box.equals(this)) {
                continue;
            }
            if (intersects(box)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersects(AxisAlignedBoundingBox boundingBox) {
        if (new Interval(x, x + width).overlaps(new Interval(boundingBox.x, boundingBox.x + boundingBox.width)) == false) {
            return false;
        }
        if (new Interval(y, y + height).overlaps(new Interval(boundingBox.y, boundingBox.y + boundingBox.height)) == false) {
            return false;
        }
        if (new Interval(z, z + depth).overlaps(new Interval(boundingBox.z, boundingBox.z + boundingBox.depth)) == false) {
            return false;
        }
        return true;
    }

    public void setLocation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setLocation(Vector3f location) {
        x = location.x;
        y = location.y;
        z = location.z;
    }

    public void centerOn(Vector3f location) {
        x = location.x - width / 2f;
        y = location.y - height / 2f;
        z = location.z - depth / 2f;
    }
}
