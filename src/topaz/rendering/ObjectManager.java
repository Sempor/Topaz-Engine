package topaz.rendering;

import java.util.ArrayList;

public class ObjectManager {

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

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
}
