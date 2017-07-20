package topaz.physics.collisions;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.physics.PhysicsObject;
import topaz.physics.PhysicsManager;
import topaz.rendering.GameObject;
import topaz.rendering.ObjectManager;

public abstract class CollisionObject {

    protected ObjectManager objectManager;
    protected PhysicsManager physicsManager;

    public float x = 0f, y = 0f, z = 0f; //Default values
    public float scaleX = 1f, scaleY = 1f, scaleZ = 1f;

    protected boolean active;

    public CollisionObject(PhysicsManager physicsManager, ObjectManager objectManager) {
        this.physicsManager = physicsManager;
        this.objectManager = objectManager;
    }

    public CollisionObject(PhysicsManager physicsManager, ObjectManager objectManager, float x, float y, float z) {
        this(physicsManager, objectManager);

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CollisionObject(PhysicsManager physicsManager, ObjectManager objectManager, Vector3f location) {
        this(physicsManager, objectManager, location.x, location.y, location.z);
    }

    public CollisionObject getCollidingObject() {
        for (PhysicsObject physicalObject : physicsManager.getPhysicalObjects()) {
            CollisionObject collisionObject = physicalObject.getCollisionObject();
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

    //Need to replace this with a shape cast
    //Returns true if there is a collision, false if not
    public boolean translatePhysicallyX(float dx) {
        Raycast raycast;
        if (dx >= 0) {
            raycast = new Raycast(getCenter(), getCenter().add(dx + getWidth() / 2f, 0, 0));
        } else {
            raycast = new Raycast(getCenter(), getCenter().add(dx - getWidth() / 2f, 0, 0));
        }
        ArrayList<GameObject> closestIntersectingObjects = raycast.getClosestIntersectingObjects(objectManager, 0.01f, this);
        if (closestIntersectingObjects != null) {
            CollisionObject collidingObject = closestIntersectingObjects.get(0).getPhysicalObject().getCollisionObject();
            if (dx > 0) {
                x = collidingObject.x - getWidth();
            } else if (dx < 0) {
                x = collidingObject.x + collidingObject.getWidth();
            }
            return true;
        } else {
            x += dx;
            return false;
        }
    }

    public boolean translatePhysicallyY(float dy) {
        Raycast raycast;
        if (dy >= 0) {
            raycast = new Raycast(getCenter(), getCenter().add(0, dy + getHeight() / 2f, 0));
        } else {
            raycast = new Raycast(getCenter(), getCenter().add(0, dy - getHeight() / 2f, 0));
        }
        ArrayList<GameObject> closestIntersectingObjects = raycast.getClosestIntersectingObjects(objectManager, 0.01f, this);
        if (closestIntersectingObjects != null) {
            CollisionObject collidingObject = closestIntersectingObjects.get(0).getPhysicalObject().getCollisionObject();
            if (dy > 0) {
                y = collidingObject.y - getHeight();
            } else if (dy < 0) {
                y = collidingObject.y + collidingObject.getHeight();
            }
            return true;
        } else {
            y += dy;
            return false;
        }
    }

    public boolean translatePhysicallyZ(float dz) {
        Raycast raycast;
        if (dz >= 0) {
            raycast = new Raycast(getCenter(), getCenter().add(0, 0, dz + getDepth() / 2f));
        } else {
            raycast = new Raycast(getCenter(), getCenter().add(0, 0, dz - getDepth() / 2f));
        }
        ArrayList<GameObject> closestIntersectingObjects = raycast.getClosestIntersectingObjects(objectManager, 0.01f, this);
        if (closestIntersectingObjects != null) {
            CollisionObject collidingObject = closestIntersectingObjects.get(0).getPhysicalObject().getCollisionObject();
            if (dz > 0) {
                z = collidingObject.z - getDepth();
            } else if (dz < 0) {
                z = collidingObject.z + collidingObject.getDepth();
            }
            return true;
        } else {
            z += dz;
            return false;
        }
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
