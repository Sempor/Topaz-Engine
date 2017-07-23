package topaz.physics.collisions;

import org.joml.Vector3f;
import topaz.physics.PhysicsManager;

public class BoundingSphere extends CollisionObject {

    public float radius;

    public BoundingSphere(PhysicsManager physicsManager, float radius) {
        super(physicsManager);
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
        return separationVector.lengthSquared() <= (radius + sphere.radius) * (radius + sphere.radius);
    }

    @Override
    public boolean containsPoint(float pointX, float pointY, float pointZ) {
        Vector3f separationVector = getCenter().sub(pointX, pointY, pointZ);
        return separationVector.lengthSquared() <= radius * radius;
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
