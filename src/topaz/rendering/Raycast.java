package topaz.rendering;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.math.collections.Ray;
import topaz.physics.CollisionObject;

public class Raycast {

    private GameObject rootObject;
    
    private Ray ray;
    private Vector3f endPoint;
    private ArrayList<GameObject> excludedGameObjects = new ArrayList<>();
    private ArrayList<CollisionObject> excludedCollisionObjects = new ArrayList<>();

    public Raycast(GameObject rootObject, Vector3f startPoint, Vector3f direction) {
        this.rootObject = rootObject;
        
        ray = new Ray();
        ray.setStartPoint(startPoint);
        ray.setDirection(direction);
    }

    public ArrayList<GameObject> getIntersectingObjects(float distance, float step) {
        ArrayList<GameObject> intersectingObjects = new ArrayList<>();

        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (GameObject object : rootObject.getAllDescendants()) {
                if (excludedGameObjects.contains(object)) {
                    continue;
                }
                if (object.getCollisionObject().containsPoint(point)) {
                    intersectingObjects.add(object);
                }
            }
        }
        return intersectingObjects;
    }

    public GameObject getClosestIntersectingObject(float distance, float step) {
        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (GameObject object : rootObject.getAllDescendants()) {
                if (excludedGameObjects.contains(object)) {
                    continue;
                }
                if (object.getCollisionObject().containsPoint(point)) {
                    return object;
                }
            }
        }
        return null;
    }

    public ArrayList<CollisionObject> getIntersectingCollisionObjects(float distance, float step) {
        ArrayList<CollisionObject> intersectingObjects = new ArrayList<>();

        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (GameObject object : rootObject.getAllDescendants()) {
                CollisionObject collisionObject = object.getCollisionObject();
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

    public CollisionObject getClosestIntersectingCollisionObject(float distance, float step) {
        for (float i = 0; i <= distance; i += step) {
            Vector3f point = ray.getPointOnRay(i);

            for (GameObject object : rootObject.getAllDescendants()) {
                CollisionObject collisionObject = object.getCollisionObject();
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
