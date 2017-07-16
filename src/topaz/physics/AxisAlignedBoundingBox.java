package topaz.physics;

import org.joml.Vector3f;
import topaz.util.Interval;

public class AxisAlignedBoundingBox {

    public float x = 0, y = 0, z = 0; //Default values
    public float width, height, depth;
    private float scaleX = 1, scaleY = 1, scaleZ = 1;

    private boolean active;

    private PhysicsManager physicsManager;

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, float width, float height, float depth) {
        this(physicsManager, new Vector3f(width, height, depth));
    }

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, Vector3f dimensions) {
        width = dimensions.x;
        height = dimensions.y;
        depth = dimensions.z;

        this.physicsManager = physicsManager;
    }

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, float x, float y, float z, float width, float height, float depth) {
        this(physicsManager, new Vector3f(x, y, z), new Vector3f(width, height, depth));
    }

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, Vector3f location, Vector3f dimensions) {
        x = location.x;
        y = location.y;
        z = location.z;
        width = dimensions.x;
        height = dimensions.y;
        depth = dimensions.z;

        this.physicsManager = physicsManager;
    }

    public boolean checkBoundingBoxCollisions() {
        for (AxisAlignedBoundingBox box : physicsManager.getBoundingBoxes()) {
            if (box.equals(this)) {
                continue;
            }
            if (box.isActive() == false) {
                continue;
            }
            if (intersects(box)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersects(AxisAlignedBoundingBox boundingBox) {
        if (new Interval(x, x + width * scaleX).overlaps(
                new Interval(boundingBox.x, boundingBox.x + boundingBox.width * boundingBox.scaleX)) == false) {
            return false;
        }
        if (new Interval(y, y + height * scaleY).overlaps(
                new Interval(boundingBox.y, boundingBox.y + boundingBox.height * boundingBox.scaleY)) == false) {
            return false;
        }
        if (new Interval(z, z + depth * scaleZ).overlaps(
                new Interval(boundingBox.z, boundingBox.z + boundingBox.depth * boundingBox.scaleZ)) == false) {
            return false;
        }
        return true;
    }

    public void translate(float dx, float dy, float dz) {
        translate(new Vector3f(dx, dy, dz));
    }

    public void translate(Vector3f translation) {
        x += translation.x;
        y += translation.y;
        z += translation.z;
    }

    public void setLocation(float dx, float dy, float dz) {
        setLocation(new Vector3f(dx, dy, dz));
    }

    public void setLocation(Vector3f location) {
        x = location.x;
        y = location.y;
        z = location.z;
    }

    public void scale(float dx, float dy, float dz) {
        scale(new Vector3f(dx, dy, dz));
    }

    public void scale(Vector3f scale) {
        scaleX *= scale.x;
        scaleY *= scale.y;
        scaleZ *= scale.z;
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        setScale(new Vector3f(scaleX, scaleY, scaleZ));
    }

    public void setScale(Vector3f scale) {
        scaleX = scale.x;
        scaleY = scale.y;
        scaleZ = scale.z;
    }

    public void centerOn(Vector3f location) {
        x = location.x - width * scaleX / 2f;
        y = location.y - height * scaleY / 2f;
        z = location.z - depth * scaleZ / 2f;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
