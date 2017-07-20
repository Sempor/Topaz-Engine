package topaz.physics.collisions;

import org.joml.Vector3f;
import topaz.physics.PhysicsManager;
import topaz.util.Interval;

public class AxisAlignedBoundingBox extends CollisionObject {

    public float width, height, depth;

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, float width, float height, float depth) {
        this(physicsManager, new Vector3f(width, height, depth));
    }

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, Vector3f dimensions) {
        super(physicsManager);
        width = dimensions.x;
        height = dimensions.y;
        depth = dimensions.z;
    }

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, float x, float y, float z, float width, float height, float depth) {
        this(physicsManager, new Vector3f(x, y, z), new Vector3f(width, height, depth));
    }

    public AxisAlignedBoundingBox(PhysicsManager physicsManager, Vector3f location, Vector3f dimensions) {
        super(physicsManager, location);
        width = dimensions.x;
        height = dimensions.y;
        depth = dimensions.z;
    }

    @Override
    public boolean intersectsBox(AxisAlignedBoundingBox box) {
        if (new Interval(x, x + width * scaleX).overlaps(new Interval(box.x, box.x + box.width * box.scaleX)) == false) {
            return false;
        }
        if (new Interval(y, y + height * scaleY).overlaps(new Interval(box.y, box.y + box.height * box.scaleY)) == false) {
            return false;
        }
        if (new Interval(z, z + depth * scaleZ).overlaps(new Interval(box.z, box.z + box.depth * box.scaleZ)) == false) {
            return false;
        }
        return true;
    }

    //NOT SUPPORTED YET!
    @Override
    public boolean intersectsSphere(BoundingSphere sphere) {
        return false;
    }

    @Override
    public boolean containsPoint(Vector3f point) {
        if (point.x < x || point.x > x + width * scaleX) {
            return false;
        }
        if (point.y < y || point.y > y + height * scaleY) {
            return false;
        }
        if (point.z < z || point.z > z + depth * scaleZ) {
            return false;
        }
        return true;
    }

    @Override
    public float getWidth() {
        return width * scaleX;
    }

    @Override
    public float getHeight() {
        return height * scaleY;
    }

    @Override
    public float getDepth() {
        return depth * scaleZ;
    }
}
