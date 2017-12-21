package topaz.physics;

import org.joml.Vector3f;
import topaz.rendering.GameObject;

public class BoundingBox extends CollisionObject {

    private float width, height, depth;

    public BoundingBox(GameObject rootObject, float width, float height, float depth) {
        super(rootObject);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public BoundingBox(GameObject rootObject, Vector3f dimensions) {
        this(rootObject, dimensions.x, dimensions.y, dimensions.z);
    }

    @Override
    public boolean containsPoint(Vector3f point) {
        return point.x >= location.x && point.x <= location.x + getWidth()
                && point.y >= location.y && point.y <= location.y + getHeight()
                && point.z >= location.z && point.z <= location.z + getDepth();
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    @Override
    public float getWidth() {
        return width * scale.x;
    }

    @Override
    public float getHeight() {
        return height * scale.y;
    }

    @Override
    public float getDepth() {
        return depth * scale.z;
    }
}
