package topaz.physics;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.rendering.GameObject;
import topaz.rendering.Raycast;

public abstract class CollisionObject {

    private final float raycastStepSize = 0.004f;

    protected GameObject rootObject;

    public float x = 0f, y = 0f, z = 0f;
    public float scaleX = 1f, scaleY = 1f, scaleZ = 1f;

    protected boolean active;

    public CollisionObject(GameObject rootObject) {
        this.rootObject = rootObject;
    }

    public ArrayList<CollisionObject> getCollidingObjects() {
        ArrayList<CollisionObject> collidingObjects = new ArrayList<>();
        for (GameObject object : rootObject.getAllDescendants()) {
            CollisionObject collisionObject = object.getCollisionObject();
            if (collisionObject.isActive() == false || collisionObject.equals(this)) {
                continue;
            }
            if (intersects(collisionObject)) {
                collidingObjects.add(collisionObject);
            }
        }
        return collidingObjects;
    }

    public boolean intersects(CollisionObject collisionObject) {
        if (collisionObject instanceof BoundingBox) {
            return intersectsBox((BoundingBox) collisionObject);
        } else if (collisionObject instanceof BoundingSphere) {
            return intersectsSphere((BoundingSphere) collisionObject);
        }
        return false;
    }

    public abstract boolean intersectsBox(BoundingBox box);

    public abstract boolean intersectsSphere(BoundingSphere sphere);

    public abstract boolean containsPoint(float pointX, float pointY, float pointZ);

    public boolean containsPoint(Vector3f point) {
        return containsPoint(point.x, point.y, point.z);
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract float getDepth();

    public Vector3f getDimensions() {
        return new Vector3f(getWidth(), getHeight(), getDepth());
    }

    public void setCenter(float centerX, float centerY, float centerZ) {
        x = centerX - getWidth() / 2f;
        y = centerY - getHeight() / 2f;
        z = centerZ - getDepth() / 2f;
    }

    public void setCenter(Vector3f center) {
        setCenter(center.x, center.y, center.z);
    }

    public Vector3f getCenter() {
        return new Vector3f(x + getWidth() / 2f, y + getHeight() / 2f, z + getDepth() / 2f);
    }

    //Need to replace this with a shape cast
    //Returns true if there is a collision, false if not
    public boolean translateX(float dx) {
        Raycast raycast;
        if (dx >= 0) {
            raycast = new Raycast(getCenter(), new Vector3f(dx + getWidth() / 2f, 0, 0));
        } else {
            raycast = new Raycast(getCenter(), new Vector3f(dx - getWidth() / 2f, 0, 0));
        }
        raycast.addExcludedCollisionObject(this);

        CollisionObject closestCollisionObject = raycast.getClosestIntersectingCollisionObject(
                rootObject, Math.abs(dx) + getWidth() / 2f, raycastStepSize);
        if (closestCollisionObject == null) {
            x += dx;
            return false;
        } else {
            if (dx > 0) {
                x = closestCollisionObject.x - getWidth();
            } else if (dx < 0) {
                x = closestCollisionObject.x + closestCollisionObject.getWidth();
            }
            return true;
        }
    }

    public boolean translateY(float dy) {
        Raycast raycast;
        if (dy >= 0) {
            raycast = new Raycast(getCenter(), new Vector3f(0, dy + getHeight() / 2f, 0));
        } else {
            raycast = new Raycast(getCenter(), new Vector3f(0, dy - getHeight() / 2f, 0));
        }
        raycast.addExcludedCollisionObject(this);

        CollisionObject closestCollisionObject = raycast.getClosestIntersectingCollisionObject(
                rootObject, Math.abs(dy) + getHeight() / 2f, raycastStepSize);
        if (closestCollisionObject == null) {
            y += dy;
            return false;
        } else {
            if (dy > 0) {
                y = closestCollisionObject.y - getHeight();
            } else if (dy < 0) {
                y = closestCollisionObject.y + closestCollisionObject.getHeight();
            }
            return true;
        }
    }

    public boolean translateZ(float dz) {
        Raycast raycast;
        if (dz >= 0) {
            raycast = new Raycast(getCenter(), new Vector3f(0, 0, dz + getDepth() / 2f));
        } else {
            raycast = new Raycast(getCenter(), new Vector3f(0, 0, dz - getDepth() / 2f));
        }
        raycast.addExcludedCollisionObject(this);

        CollisionObject closestCollisionObject = raycast.getClosestIntersectingCollisionObject(
                rootObject, Math.abs(dz) + getDepth() / 2f, raycastStepSize);
        if (closestCollisionObject == null) {
            z += dz;
            return false;
        } else {
            if (dz > 0) {
                z = closestCollisionObject.z - getDepth();
            } else if (dz < 0) {
                z = closestCollisionObject.z + closestCollisionObject.getDepth();
            }
            return true;
        }
    }

    public void translate(float dx, float dy, float dz) {
        translateX(dx);
        translateY(dy);
        translateZ(dz);
    }

    public void translate(Vector3f translation) {
        translate(translation.x, translation.y, translation.z);
    }

    public void setLocation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setLocation(Vector3f location) {
        setLocation(location.x, location.y, location.z);
    }

    public Vector3f getLocation() {
        return new Vector3f(x, y, z);
    }

    public void scale(float dx, float dy, float dz) {
        scaleX *= dx;
        scaleY *= dy;
        scaleZ *= dz;
    }

    public void scale(Vector3f scale) {
        scale(scale.x, scale.y, scale.z);
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    public void setScale(Vector3f scale) {
        setScale(scale.x, scale.y, scale.z);
    }

    public Vector3f getScale() {
        return new Vector3f(scaleX, scaleY, scaleZ);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
