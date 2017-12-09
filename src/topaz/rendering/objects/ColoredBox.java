package topaz.rendering.objects;

import topaz.physics.PhysicsObject;
import topaz.physics.collisions.BoundingBox;
import topaz.rendering.ColoredMesh;
import topaz.rendering.GameObject;
import topaz.rendering.Mesh;
import topaz.rendering.ObjectManager;
import topaz.util.Color;

public class ColoredBox extends Box {

    public ColoredBox(ObjectManager objectManager, float width, float height, float depth, Color color) {
        this(objectManager, 0, 0, 0, width, height, depth, color);
    }

    public ColoredBox(ObjectManager objectManager, float x, float y, float z, float width, float height, float depth, Color color) {
        float[] vertices = getVertices(x, y, z, width, height, depth);

        float[] colors = new float[]{
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a
        };

        Mesh mesh = new ColoredMesh(objectManager.getRenderManager(), vertices, indices, colors);
        objectManager.getRenderManager().add(mesh);

        BoundingBox boundingBox = new BoundingBox(objectManager.getPhysicsManager(), width, height, depth);
        boundingBox.setLocation(x, y, z);
        PhysicsObject physicalObject = new PhysicsObject(boundingBox, mass);
        objectManager.getPhysicsManager().add(physicalObject);

        gameObject = new GameObject(objectManager, mesh, physicalObject);
    }

    public void setColor(Color color) {
        float[] colors = new float[]{
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a,
            color.r, color.g, color.b, color.a
        };
        ((ColoredMesh) gameObject.getMesh()).updateColorData(colors);
    }
}