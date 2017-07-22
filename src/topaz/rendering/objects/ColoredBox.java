package topaz.rendering.objects;

import org.joml.Vector3f;
import topaz.physics.PhysicsManager;
import topaz.physics.PhysicsObject;
import topaz.physics.collisions.AxisAlignedBoundingBox;
import topaz.rendering.ColoredMesh;
import topaz.rendering.GameObject;
import topaz.rendering.Mesh;
import topaz.rendering.ObjectManager;
import topaz.rendering.RenderManager;
import topaz.util.Color4f;

public class ColoredBox extends Box {
    
    public ColoredBox(RenderManager renderManager, PhysicsManager physicsManager, ObjectManager objectManager, float width, float height, float depth, Color4f color) {
        this(renderManager, physicsManager, objectManager, 0, 0, 0, width, height, depth, color);
    }

    public ColoredBox(RenderManager renderManager, PhysicsManager physicsManager, ObjectManager objectManager, float x, float y, float z, float width, float height, float depth, Color4f color) {
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

        Mesh mesh = new ColoredMesh(renderManager, vertices, indices, colors);
        renderManager.add(mesh);
        AxisAlignedBoundingBox boundingBox = new AxisAlignedBoundingBox(physicsManager, objectManager, new Vector3f(x, y, z), new Vector3f(width, height, depth));
        PhysicsObject physicalObject = new PhysicsObject(boundingBox, mass);
        physicsManager.add(physicalObject);

        gameObject = new GameObject(objectManager, renderManager, physicsManager, mesh, physicalObject);
    }
}