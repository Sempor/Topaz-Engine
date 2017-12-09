package topaz.rendering.objects;

import topaz.assets.AssetLoader;
import topaz.physics.PhysicsObject;
import topaz.physics.collisions.BoundingBox;
import topaz.rendering.GameObject;
import topaz.rendering.Mesh;
import topaz.rendering.ObjectManager;
import topaz.rendering.TexturedMesh;

public class TexturedBox extends Box {

    public TexturedBox(ObjectManager objectManager, float width, float height, float depth, String... textureFilePaths) {
        this(objectManager, 0, 0, 0, width, height, depth, textureFilePaths);
    }

    public TexturedBox(ObjectManager objectManager, float x, float y, float z, float width, float height, float depth, String... textureFilePaths) {
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

        Mesh mesh = new TexturedMesh(objectManager.getRenderManager(), vertices, indices, textureCoords, textureIDs);
        objectManager.getRenderManager().add(mesh);

        BoundingBox boundingBox = new BoundingBox(objectManager.getPhysicsManager(), width, height, depth);
        boundingBox.setLocation(x, y, z);
        PhysicsObject physicalObject = new PhysicsObject(boundingBox, mass);
        objectManager.getPhysicsManager().add(physicalObject);

        gameObject = new GameObject(objectManager, mesh, physicalObject);
    }
}
