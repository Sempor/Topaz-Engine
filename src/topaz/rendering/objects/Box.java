package topaz.rendering.objects;

import topaz.rendering.GameObject;

public abstract class Box {

    protected GameObject gameObject;
    protected float mass = 1f;

    protected short[] indices = new short[]{
        // front
        0, 1, 2,
        2, 3, 0,
        // top
        1, 5, 6,
        6, 2, 1,
        // back
        7, 6, 5,
        5, 4, 7,
        // bottom
        4, 0, 3,
        3, 7, 4,
        // left
        4, 5, 1,
        1, 0, 4,
        // right
        3, 2, 6,
        6, 7, 3
    };

    protected float[] getVertices(float x, float y, float z, float width, float height, float depth) {
        return new float[]{
            x, y, z + depth, //Bottom left, front
            x + width, y, z + depth, //Bottom right, front
            x + width, y + height, z + depth, //Top right, front
            x, y + height, z + depth, //Top left, front
            x, y, z, //Bottom left, back
            x + width, y, z, //Bottom right, back
            x + width, y + height, z, //Top right, back
            x, y + height, z //Top left, back
        };
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
