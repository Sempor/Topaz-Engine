package topaz.physics;

import org.joml.Vector3f;

public abstract class CollisionObject {

    protected PhysicsManager physicsManager;

    public float x = 0f, y = 0f, z = 0f; //Default values
    public float scaleX = 1f, scaleY = 1f, scaleZ = 1f;

    protected boolean active;

    public CollisionObject(PhysicsManager physicsManager) {
        this.physicsManager = physicsManager;
    }

    public CollisionObject(PhysicsManager physicsManager, float x, float y, float z) {
        this(physicsManager);

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CollisionObject(PhysicsManager physicsManager, Vector3f location) {
        this(physicsManager, location.x, location.y, location.z);
    }

    public CollisionObject checkForCollisions() {
        for (CollisionObject collisionObject : physicsManager.getCollisionObjects()) {
            if (collisionObject.equals(this)) {
                continue;
            }
            if (collisionObject.isActive() == false) {
                continue;
            }
            if (intersects(collisionObject)) {
                return collisionObject;
            }
        }
        return null;
    }

    public boolean intersects(CollisionObject collisionObject) {
        if (collisionObject instanceof AxisAlignedBoundingBox) {
            return intersectsBox((AxisAlignedBoundingBox) collisionObject);
        } else if (collisionObject instanceof BoundingSphere) {
            return intersectsSphere((BoundingSphere) collisionObject);
        }
        return false;
    }

    public abstract boolean intersectsBox(AxisAlignedBoundingBox box);

    public abstract boolean intersectsSphere(BoundingSphere sphere);

    public abstract boolean containsPoint(Vector3f point);

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract float getDepth();

    public void setCenter(Vector3f center) {
        x = center.x - getWidth() / 2f;
        y = center.y - getHeight() / 2f;
        z = center.z - getDepth() / 2f;
    }

    public Vector3f getCenter() {
        return new Vector3f(x + getWidth() / 2f, y + getHeight() / 2f, z + getDepth() / 2f);
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

    public Vector3f getLocation() {
        return new Vector3f(x, y, z);
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
