package topaz.rendering.objects;

import org.joml.Vector3f;
import topaz.assets.AssetLoader;
import topaz.physics.AxisAlignedBoundingBox;
import topaz.physics.PhysicsManager;
import topaz.rendering.ColoredMesh;
import topaz.rendering.GameObject;
import topaz.rendering.Mesh;
import topaz.rendering.RenderManager;
import topaz.rendering.TexturedMesh;
import topaz.util.Color4f;

public class Box {

    private GameObject gameObject;

    private short[] indices = new short[]{
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

    public Box(RenderManager renderManager, PhysicsManager physicsManager, float width, float height, float depth, Color4f color) {
        this(renderManager, physicsManager, 0, 0, 0, width, height, depth, color);
    }

    public Box(RenderManager renderManager, PhysicsManager physicsManager, float x, float y, float z, float width, float height, float depth, Color4f color) {
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
        AxisAlignedBoundingBox boundingBox = new AxisAlignedBoundingBox(physicsManager, new Vector3f(x, y, z), new Vector3f(width, height, depth));
        physicsManager.add(boundingBox);

        gameObject = new GameObject(mesh, boundingBox);
    }

    public Box(RenderManager renderManager, PhysicsManager physicsManager, float width, float height, float depth, String... textureFilePaths) {
        this(renderManager, physicsManager, 0, 0, 0, width, height, depth, textureFilePaths);
    }

    public Box(RenderManager renderManager, PhysicsManager physicsManager, float x, float y, float z, float width, float height, float depth, String... textureFilePaths) {
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
        AxisAlignedBoundingBox boundingBox = new AxisAlignedBoundingBox(physicsManager, new Vector3f(x, y, z), new Vector3f(width, height, depth));
        physicsManager.add(boundingBox);

        gameObject = new GameObject(mesh, boundingBox);
    }

    private float[] getVertices(float x, float y, float z, float width, float height, float depth) {
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
