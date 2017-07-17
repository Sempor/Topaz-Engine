package topaz.physics;

import java.util.ArrayList;

public class PhysicsManager {

    private ArrayList<CollisionObject> collisionObjects = new ArrayList<>();

    public void add(CollisionObject collisionObject) {
        collisionObjects.add(collisionObject);
    }

    public void remove(CollisionObject collisionObject) {
        collisionObjects.remove(collisionObject);
    }

    public ArrayList<CollisionObject> getCollisionObjects() {
        return collisionObjects;
    }

    public void setCollisionObjects(ArrayList<CollisionObject> collisionObjects) {
        this.collisionObjects = collisionObjects;
    }
}
