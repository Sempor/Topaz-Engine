package topaz.physics;

import org.joml.Vector3f;

public class BoundingSphere extends CollisionObject {

    public float radius;

    public BoundingSphere(PhysicsManager physicsManager, float radius) {
        super(physicsManager);

        this.radius = radius;
    }

    public BoundingSphere(PhysicsManager physicsManager, float x, float y, float z, float radius) {
        this(physicsManager, new Vector3f(x, y, z), radius);
    }

    public BoundingSphere(PhysicsManager physicsManager, Vector3f center, float radius) {
        super(physicsManager, center);

        this.radius = radius;
    }

    //NOT SUPPORTED YET!
    @Override
    public boolean intersectsBox(AxisAlignedBoundingBox box) {
        return false;
    }

    @Override
    public boolean intersectsSphere(BoundingSphere sphere) {
        Vector3f separationVector = new Vector3f(x, y, z).sub(new Vector3f(sphere.x, sphere.y, sphere.z));
        return separationVector.lengthSquared() <= Math.pow(radius + sphere.radius, 2.0);
    }

    @Override
    public boolean containsPoint(Vector3f point) {
        Vector3f separationVector = new Vector3f(x, y, z).sub(point);
        return separationVector.lengthSquared() <= Math.pow(radius, 2.0);
    }

    @Override
    public void centerOn(Vector3f location) {
        this.x = location.x;
        this.y = location.y;
        this.z = location.z;
    }
}
