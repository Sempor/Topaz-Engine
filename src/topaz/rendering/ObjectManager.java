package topaz.rendering;

import java.util.ArrayList;
import topaz.physics.PhysicsManager;

public class ObjectManager {

    private RenderManager renderManager;
    private PhysicsManager physicsManager;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public ObjectManager(RenderManager renderManager, PhysicsManager physicsManager) {
        this.renderManager = renderManager;
        this.physicsManager = physicsManager;
    }

    public void add(GameObject gameObject) {
        if (gameObject == null) {
            return;
        }
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        if (gameObject == null) {
            return;
        }
        gameObjects.remove(gameObject);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public RenderManager getRenderManager() {
        return renderManager;
    }

    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }
}