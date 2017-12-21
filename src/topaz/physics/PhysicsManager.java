package topaz.physics;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.core.Display;
import topaz.math.collections.Interval;
import topaz.rendering.GameObject;

public class PhysicsManager {

    private Display display;
    private GameObject rootObject;

    public PhysicsManager(Display display, GameObject rootObject) {
        this.display = display;
        this.rootObject = rootObject;
    }

    public void tick(double delta) {
        for (GameObject object : rootObject.getAllDescendants()) {
            object.tick(delta, display);
        }
    }

    public ArrayList<CollisionObject> getCollidingObjects(CollisionObject collisionObject) {
        ArrayList<CollisionObject> collidingObjects = new ArrayList<>();
        for (GameObject object : rootObject.getAllDescendants()) {
            CollisionObject otherCollisionObject = object.getCollisionObject();
            if (otherCollisionObject.isActive() == false || otherCollisionObject.equals(this)) {
                continue;
            }
            if (areObjectsIntersecting(collisionObject, otherCollisionObject)) {
                collidingObjects.add(otherCollisionObject);
            }
        }
        return collidingObjects;
    }

    public boolean areObjectsIntersecting(CollisionObject c1, CollisionObject c2) {
        if (c1 instanceof BoundingBox) {
            if (c2 instanceof BoundingBox) {
                return areBoxBoxIntersecting((BoundingBox) c1, (BoundingBox) c2);
            } else if (c2 instanceof BoundingSphere) {
                return areBoxSphereIntersecting((BoundingBox) c1, (BoundingSphere) c2);
            }
        } else if (c1 instanceof BoundingSphere) {
            if (c2 instanceof BoundingBox) {
                return areBoxSphereIntersecting((BoundingBox) c2, (BoundingSphere) c1);
            } else if (c2 instanceof BoundingSphere) {
                return areSphereSphereIntersecting((BoundingSphere) c1, (BoundingSphere) c2);
            }
        }
        return false;
    }

    public boolean areBoxBoxIntersecting(BoundingBox b1, BoundingBox b2) {
        if (new Interval(b1.getLocation().x, b1.getLocation().x + b1.getWidth() * b1.getScale().x).overlaps(
                new Interval(b2.getLocation().x, b2.getLocation().x + b2.getWidth() * b2.getScale().x)) == false) {
            return false;
        }
        if (new Interval(b1.getLocation().y, b1.getLocation().y + b1.getHeight() * b1.getScale().y).overlaps(
                new Interval(b2.getLocation().y, b2.getLocation().y + b2.getHeight() * b2.getScale().y)) == false) {
            return false;
        }
        if (new Interval(b1.getLocation().z, b1.getLocation().z + b1.getDepth() * b1.getScale().z).overlaps(
                new Interval(b2.getLocation().z, b2.getLocation().z + b2.getDepth() * b2.getScale().z))) {
            return false;
        }
        return true;
    }

    //NOT SUPPORTED YET!!!!
    public boolean areBoxSphereIntersecting(BoundingBox b, BoundingSphere s) {
        return false;
    }

    public boolean areSphereSphereIntersecting(BoundingSphere s1, BoundingSphere s2) {
        Vector3f separationVector = s1.getCenter().sub(s2.getCenter());
        return separationVector.lengthSquared() <= (s1.getRadius() + s2.getRadius()) * (s1.getRadius() + s2.getRadius());
    }
}
