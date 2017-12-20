package topaz.physics;

import org.joml.Vector3f;
import topaz.rendering.GameObject;
import topaz.math.collections.Interval;

public class BoundingBox extends CollisionObject {

    public float width, height, depth;

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
    public boolean intersectsBox(BoundingBox box) {
        return new Interval(x, x + width * scaleX).overlaps(new Interval(box.x, box.x + box.width * box.scaleX))
                && new Interval(y, y + height * scaleY).overlaps(new Interval(box.y, box.y + box.height * box.scaleY))
                && new Interval(z, z + depth * scaleZ).overlaps(new Interval(box.z, box.z + box.depth * box.scaleZ));
    }

    //NOT SUPPORTED YET!
    @Override
    public boolean intersectsSphere(BoundingSphere sphere) {
        return false;
    }

    @Override
    public boolean containsPoint(float pointX, float pointY, float pointZ) {
        return pointX >= x && pointX <= x + getWidth()
                && pointY >= y && pointY <= y + getHeight()
                && pointZ >= z && pointZ <= z + getDepth();
    }

    @Override
    public float getWidth() {
        return width * scaleX;
    }

    @Override
    public float getHeight() {
        return height * scaleY;
    }

    @Override
    public float getDepth() {
        return depth * scaleZ;
    }
}
