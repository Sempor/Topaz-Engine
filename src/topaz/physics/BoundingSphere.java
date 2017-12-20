package topaz.physics;

import org.joml.Vector3f;
import topaz.rendering.GameObject;

public class BoundingSphere extends CollisionObject {

    public float radius;

    public BoundingSphere(GameObject rootObject, float radius) {
        super(rootObject);
        this.radius = radius;
    }

    //NOT SUPPORTED YET!
    @Override
    public boolean intersectsBox(BoundingBox box) {
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
