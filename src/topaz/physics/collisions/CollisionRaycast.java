package topaz.physics.collisions;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.physics.PhysicsManager;
import topaz.physics.PhysicsObject;

public class CollisionRaycast {

    private PhysicsManager physicsManager;

    private Vector3f startPoint;
    private Vector3f endPoint;
    private float step;
    private ArrayList<CollisionObject> excludedCollisionObjects = new ArrayList<>();

    public CollisionRaycast(PhysicsManager physicsManager, Vector3f startPoint, Vector3f endPoint, float step) {
        this.physicsManager = physicsManager;
        this.startPoint = new Vector3f(startPoint);
        this.endPoint = new Vector3f(endPoint);
        this.step = step;
    }
    
    public ArrayList<CollisionObject> getIntersectingCollisionObjects() {
        ArrayList<CollisionObject> intersectingObjects = new ArrayList<>();
        Vector3f separationVector = new Vector3f(endPoint).sub(startPoint);

        for (float i = 0; i <= 1f; i += step) {
            Vector3f point = new Vector3f(startPoint).add(new Vector3f(separationVector).mul(i));

            for (PhysicsObject physicsObject : physicsManager.getPhysicsObjects()) {
                CollisionObject collisionObject = physicsObject.getCollisionObject();
                if (excludedCollisionObjects.contains(collisionObject)) {
                    break;
                }
                if (collisionObject.containsPoint(point)) {
                    intersectingObjects.add(collisionObject);
                }
            }
        }
        return intersectingObjects;
    }

    public ArrayList<CollisionObject> getClosestIntersectingCollisionObjects() {
        ArrayList<CollisionObject> intersectingObjects = new ArrayList<>();
        Vector3f separationVector = new Vector3f(endPoint).sub(startPoint);
        boolean intersectingObjectFound = false;

        for (float i = 0; i <= 1f; i += step) {
            Vector3f point = new Vector3f(startPoint).add(new Vector3f(separationVector).mul(i));

            for (PhysicsObject physicsObject : physicsManager.getPhysicsObjects()) {
                CollisionObject collisionObject = physicsObject.getCollisionObject();
                if (excludedCollisionObjects.contains(collisionObject)) {
                    break;
                }
                if (collisionObject.containsPoint(point)) {
                    intersectingObjects.add(collisionObject);
                    intersectingObjectFound = true;
                }
            }

            if (intersectingObjectFound) {
                break;
            }
        }
        return intersectingObjects;
    }

    public void addExcludedCollisionObject(CollisionObject collisionObject) {
        excludedCollisionObjects.add(collisionObject);
    }

    public Vector3f getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector3f startPoint) {
        this.startPoint = startPoint;
    }

    public Vector3f getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Vector3f endPoint) {
        this.endPoint = endPoint;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }
}
