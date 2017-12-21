package topaz.physics;

import org.joml.Vector3f;
import topaz.rendering.GameObject;
import topaz.rendering.Raycast;

public abstract class CollisionObject {

    protected GameObject rootObject;

    protected Vector3f location = new Vector3f(0, 0, 0);
    protected Vector3f scale = new Vector3f(1, 1, 1);
    protected boolean active;
    private float raycastStepSize = 0.004f;

    public CollisionObject(GameObject rootObject) {
        this.rootObject = rootObject;
    }

    public abstract boolean containsPoint(Vector3f point);

    public boolean containsPoint(float x, float y, float z) {
        return containsPoint(new Vector3f(x, y, z));
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract float getDepth();

    public Vector3f getDimensions() {
        return new Vector3f(getWidth(), getHeight(), getDepth());
    }

    public void setCenter(float x, float y, float z) {
        setCenter(new Vector3f(x, y, z));
    }

    public void setCenter(Vector3f center) {
        location = new Vector3f(center).sub(getWidth() / 2, getHeight() / 2, getDepth() / 2);
    }

    public Vector3f getCenter() {
        return new Vector3f(location).add(getWidth() / 2, getHeight() / 2, getDepth() / 2);
    }

    //Need to replace this with a shape cast
    //Returns true if there is a collision, false if not
    public boolean translateX(float dx) {
        Raycast raycast;
        if (dx >= 0) {
            raycast = new Raycast(rootObject, getCenter(), new Vector3f(dx + getWidth() / 2f, 0, 0));
        } else {
            raycast = new Raycast(rootObject, getCenter(), new Vector3f(dx - getWidth() / 2f, 0, 0));
        }
        raycast.addExcludedCollisionObject(this);

        CollisionObject closestCollisionObject = raycast.getClosestIntersectingCollisionObject(
                Math.abs(dx) + getWidth() / 2f, raycastStepSize);
        if (closestCollisionObject == null) {
            location.x += dx;
            return false;
        } else {
            if (dx > 0) {
                location.x = closestCollisionObject.getLocation().x - getWidth();
            } else if (dx < 0) {
                location.x = closestCollisionObject.getLocation().x + closestCollisionObject.getWidth();
            }
            return true;
        }
    }

    public boolean translateY(float dy) {
        Raycast raycast;
        if (dy >= 0) {
            raycast = new Raycast(rootObject, getCenter(), new Vector3f(0, dy + getHeight() / 2f, 0));
        } else {
            raycast = new Raycast(rootObject, getCenter(), new Vector3f(0, dy - getHeight() / 2f, 0));
        }
        raycast.addExcludedCollisionObject(this);

        CollisionObject closestCollisionObject = raycast.getClosestIntersectingCollisionObject(
                Math.abs(dy) + getHeight() / 2f, raycastStepSize);
        if (closestCollisionObject == null) {
            location.y += dy;
            return false;
        } else {
            if (dy > 0) {
                location.y = closestCollisionObject.getLocation().y - getHeight();
            } else if (dy < 0) {
                location.y = closestCollisionObject.getLocation().y + closestCollisionObject.getHeight();
            }
            return true;
        }
    }

    public boolean translateZ(float dz) {
        Raycast raycast;
        if (dz >= 0) {
            raycast = new Raycast(rootObject, getCenter(), new Vector3f(0, 0, dz + getDepth() / 2f));
        } else {
            raycast = new Raycast(rootObject, getCenter(), new Vector3f(0, 0, dz - getDepth() / 2f));
        }
        raycast.addExcludedCollisionObject(this);

        CollisionObject closestCollisionObject = raycast.getClosestIntersectingCollisionObject(
                Math.abs(dz) + getDepth() / 2f, raycastStepSize);
        if (closestCollisionObject == null) {
            location.z += dz;
            return false;
        } else {
            if (dz > 0) {
                location.z = closestCollisionObject.getLocation().z - getDepth();
            } else if (dz < 0) {
                location.z = closestCollisionObject.getLocation().z + closestCollisionObject.getDepth();
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
        setLocation(new Vector3f(x, y, z));
    }

    public void setLocation(Vector3f location) {
        this.location = new Vector3f(location);
    }

    public Vector3f getLocation() {
        return location;
    }

    public void scale(float dx, float dy, float dz) {
        scale(new Vector3f(dx, dy, dz));
    }

    public void scale(Vector3f scale) {
        this.scale.mul(new Vector3f(scale));
    }

    public void setScale(float x, float y, float z) {
        setScale(new Vector3f(x, y, z));
    }

    public void setScale(Vector3f scale) {
        this.scale = new Vector3f(scale);
    }

    public Vector3f getScale() {
        return scale;
    }

    public float getRaycastStepSize() {
        return raycastStepSize;
    }

    public void setRaycastStepSize(float raycastStepSize) {
        this.raycastStepSize = raycastStepSize;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
