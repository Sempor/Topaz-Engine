package topaz.physics;

import org.joml.Vector3f;
import topaz.rendering.GameObject;

public class BoundingSphere extends CollisionObject {

    private float radius;

    public BoundingSphere(GameObject rootObject, float radius) {
        super(rootObject);
        this.radius = radius;
    }

    @Override
    public boolean containsPoint(Vector3f point) {
        Vector3f separationVector = getCenter().sub(point.x, point.y, point.z);
        return separationVector.lengthSquared() <= radius * radius;
    }

    @Override
    public float getWidth() {
        return radius * 2f * scale.x;
    }

    @Override
    public float getHeight() {
        return radius * 2f * scale.y;
    }

    @Override
    public float getDepth() {
        return radius * 2f * scale.z;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
    
    public float getRadius() {
        return radius;
    }
}
