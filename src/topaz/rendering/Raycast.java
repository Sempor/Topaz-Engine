package topaz.rendering;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.physics.PhysicsManager;
import topaz.physics.PhysicsObject;
import topaz.physics.collisions.CollisionObject;
import topaz.util.Ray;

public class Raycast {

    private Ray ray;
    private Vector3f endPoint;
    private ArrayList<GameObject> excludedGameObjects = new ArrayList<>();
    private ArrayList<CollisionObject> excludedCollisionObjects = new ArrayList<>();

    public Raycast(Vector3f startPoint, Vector3f direction) {
        ray = new Ray();
        ray.setStartPoint(startPoint);
        ray.setDirection(direction);
    }

    public ArrayList<GameObject> getIntersectingObjects(RenderManager renderManager, float distance, float step) {
        ArrayList<GameObject> intersectingObjects = new ArrayList<>();

        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (GameObject gameObject : renderManager.getGameObjects()) {
                if (excludedGameObjects.contains(gameObject)) {
                    continue;
                }
                if (gameObject.getPhysicsObject().getCollisionObject().containsPoint(point)) {
                    intersectingObjects.add(gameObject);
                }
            }
        }
        return intersectingObjects;
    }

    public GameObject getClosestIntersectingObject(RenderManager renderManager, float distance, float step) {
        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (GameObject gameObject : renderManager.getGameObjects()) {
                if (excludedGameObjects.contains(gameObject)) {
                    continue;
                }
                if (gameObject.getPhysicsObject().getCollisionObject().containsPoint(point)) {
                    return gameObject;
                }
            }
        }
        return null;
    }

    public ArrayList<CollisionObject> getIntersectingCollisionObjects(PhysicsManager physicsManager, float distance, float step) {
        ArrayList<CollisionObject> intersectingObjects = new ArrayList<>();

        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (PhysicsObject physicsObject : physicsManager.getPhysicsObjects()) {
                CollisionObject collisionObject = physicsObject.getCollisionObject();
                if (excludedCollisionObjects.contains(collisionObject)) {
                    continue;
                }
                if (collisionObject.containsPoint(point)) {
                    intersectingObjects.add(collisionObject);
                }
            }
        }
        return intersectingObjects;
    }

    public CollisionObject getClosestIntersectingCollisionObject(PhysicsManager physicsManager, float distance, float step) {
        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (PhysicsObject physicsObject : physicsManager.getPhysicsObjects()) {
                CollisionObject collisionObject = physicsObject.getCollisionObject();
                if (excludedCollisionObjects.contains(collisionObject)) {
                    continue;
                }
                if (collisionObject.containsPoint(point)) {
                    return collisionObject;
                }
            }
        }
        return null;
    }

    public void addExcludedGameObject(GameObject gameObject) {
        excludedGameObjects.add(gameObject);
    }

    public void addExcludedCollisionObject(CollisionObject collisionObject) {
        excludedCollisionObjects.add(collisionObject);
    }

    public Ray getRay() {
        return ray;
    }

    public void setRay(Ray ray) {
        this.ray = ray;
    }

    public Vector3f getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Vector3f endPoint) {
        this.endPoint = endPoint;
    }
}
