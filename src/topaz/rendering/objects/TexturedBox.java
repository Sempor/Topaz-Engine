package topaz.rendering.objects;

import org.joml.Vector3f;
import topaz.assets.AssetLoader;
import topaz.physics.PhysicsManager;
import topaz.physics.PhysicsObject;
import topaz.physics.collisions.AxisAlignedBoundingBox;
import topaz.rendering.GameObject;
import topaz.rendering.Mesh;
import topaz.rendering.ObjectManager;
import topaz.rendering.RenderManager;
import topaz.rendering.TexturedMesh;

public class TexturedBox extends Box {
    
    public TexturedBox(RenderManager renderManager, PhysicsManager physicsManager, ObjectManager objectManager, float width, float height, float depth, String... textureFilePaths) {
        this(renderManager, physicsManager, objectManager, 0, 0, 0, width, height, depth, textureFilePaths);
    }

    public TexturedBox(RenderManager renderManager, PhysicsManager physicsManager, ObjectManager objectManager, float x, float y, float z, float width, float height, float depth, String... textureFilePaths) {
        int[] textureIDs = new int[textureFilePaths.length];

        for (int i = 0; i < textureFilePaths.length; i++) {
            textureIDs[i] = AssetLoader.loadPNGTexture(textureFilePaths[i]);
        }

        float[] vertices = getVertices(x, y, z, width, height, depth);

        float[] textureCoords = new float[]{
            0, 0, //first vertex
            0, 1, //second vertex
            1, 1, //third vertex
            1, 0 //fourth vertex
        };

        Mesh mesh = new TexturedMesh(renderManager, vertices, indices, textureCoords, textureIDs);
        renderManager.add(mesh);
        AxisAlignedBoundingBox boundingBox = new AxisAlignedBoundingBox(physicsManager, objectManager, new Vector3f(x, y, z), new Vector3f(width, height, depth));
        PhysicsObject physicalObject = new PhysicsObject(boundingBox, mass);
        physicsManager.add(physicalObject);

        gameObject = new GameObject(objectManager, renderManager, physicsManager, mesh, physicalObject);
    }
}