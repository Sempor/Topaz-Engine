package topaz.physics.collisions;

import org.joml.Vector3f;
import topaz.physics.PhysicsManager;

public class BoundingSphere extends CollisionObject {

    public float radius;

    public BoundingSphere(PhysicsManager physicsManager, float radius) {
        super(physicsManager);
        this.radius = radius;
    }

    public BoundingSphere(PhysicsManager physicsManager, float x, float y, float z, float radius) {
        this(physicsManager, new Vector3f(x, y, z), radius);
    }

    public BoundingSphere(PhysicsManager physicsManager, Vector3f location, float radius) {
        super(physicsManager, location);
        this.radius = radius;
    }

    //NOT SUPPORTED YET!
    @Override
    public boolean intersectsBox(AxisAlignedBoundingBox box) {
        return false;
    }

    @Override
    public boolean intersectsSphere(BoundingSphere sphere) {
        Vector3f separationVector = getCenter().sub(sphere.getCenter());
        return separationVector.lengthSquared() <= Math.pow(radius + sphere.radius, 2.0);
    }

    @Override
    public boolean containsPoint(Vector3f point) {
        Vector3f separationVector = getCenter().sub(point);
        return separationVector.lengthSquared() <= Math.pow(radius, 2.0);
    }

    @Override
    public float getWidth() {
        return radius * 2f * scaleX;
    }

    @Override
    public float getHeight() {
        return radius * 2f * scaleY;
    }

    @Override
    public float getDepth() {
        return radius * 2f * scaleZ;
    }
}
