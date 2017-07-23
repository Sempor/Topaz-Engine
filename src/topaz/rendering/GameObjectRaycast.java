package topaz.rendering;

import java.util.ArrayList;
import org.joml.Vector3f;

public class GameObjectRaycast {

    private ObjectManager objectManager;

    private Vector3f startPoint;
    private Vector3f endPoint;
    private float step;
    private ArrayList<GameObject> excludedGameObjects = new ArrayList<>();

    public GameObjectRaycast(ObjectManager objectManager, Vector3f startPoint, Vector3f endPoint, float step) {
        this.objectManager = objectManager;
        this.startPoint = new Vector3f(startPoint);
        this.endPoint = new Vector3f(endPoint);
        this.step = step;
    }

    public ArrayList<GameObject> getIntersectingGameObjects() {
        ArrayList<GameObject> intersectingObjects = new ArrayList<>();
        Vector3f separationVector = new Vector3f(endPoint).sub(startPoint);

        for (float i = 0; i <= 1f; i += step) {
            Vector3f point = new Vector3f(startPoint).add(new Vector3f(separationVector).mul(i));

            for (GameObject gameObject : objectManager.getGameObjects()) {
                if (excludedGameObjects.contains(gameObject)) {
                    break;
                }
                if (gameObject.getPhysicsObject().getCollisionObject().containsPoint(point)) {
                    intersectingObjects.add(gameObject);
                }
            }
        }
        return intersectingObjects;
    }

    public ArrayList<GameObject> getClosestIntersectingGameObjects() {
        ArrayList<GameObject> intersectingObjects = new ArrayList<>();
        Vector3f separationVector = new Vector3f(endPoint).sub(startPoint);
        boolean intersectingObjectFound = false;

        for (float i = 0; i <= 1f; i += step) {
            Vector3f point = new Vector3f(startPoint).add(new Vector3f(separationVector).mul(i));

            for (GameObject gameObject : objectManager.getGameObjects()) {
                if (excludedGameObjects.contains(gameObject)) {
                    break;
                }
                if (gameObject.getPhysicsObject().getCollisionObject().containsPoint(point)) {
                    intersectingObjects.add(gameObject);
                    intersectingObjectFound = true;
                }
            }

            if (intersectingObjectFound) {
                break;
            }
        }
        return intersectingObjects;
    }

    public void addExcludedGameObject(GameObject gameObject) {
        excludedGameObjects.add(gameObject);
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
