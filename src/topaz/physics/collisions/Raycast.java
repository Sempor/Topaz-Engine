package topaz.physics.collisions;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.rendering.GameObject;
import topaz.rendering.ObjectManager;

public class Raycast {

    private Vector3f startPoint;
    private Vector3f endPoint;

    public Raycast(Vector3f startPoint, Vector3f endPoint) {
        this.startPoint = new Vector3f(startPoint);
        this.endPoint = new Vector3f(endPoint);
    }

    public ArrayList<GameObject> getClosestIntersectingObjects(ObjectManager objectManager, float step, CollisionObject... excludedCollisionObjects) {
        ArrayList<GameObject> closestIntersectingObjects = new ArrayList<>();
        Vector3f deltaVector = new Vector3f(endPoint).sub(startPoint);
        boolean closestObjectsFound = false;

        float i = 0f;
        while (i <= 1f) {
            Vector3f point = new Vector3f(startPoint).add(new Vector3f(deltaVector).mul(i));

            objectIterator:
            for (int j = 0; j < objectManager.getGameObjects().size(); j++) {
                CollisionObject collisionObject = objectManager.getGameObjects().get(j).getPhysicalObject().getCollisionObject();
                for (CollisionObject c : excludedCollisionObjects) {
                    if (c.equals(collisionObject)) {
                        continue objectIterator;
                    }
                }
                if (collisionObject.containsPoint(point)) {
                    closestIntersectingObjects.add(objectManager.getGameObjects().get(j));
                    closestObjectsFound = true;
                }
            }

            if (closestObjectsFound) {
                return closestIntersectingObjects;
            }

            i += step;
        }
        return null;
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
}
