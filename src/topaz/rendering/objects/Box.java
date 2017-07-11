package topaz.rendering.objects;

import topaz.assets.AssetLoader;
import topaz.assets.Texture;
import topaz.rendering.Mesh;
import topaz.util.Color4f;

public class Box {

    private Mesh mesh;

    private float[] vertices;

    private Color4f color = Color4f.RED;

    private float[] textureCoords;
    private int[] textureIDs = null;

    private float x = 0;
    private float y = 0;
    private float z = 0;

    private float width = 1;
    private float height = 1;
    private float depth = 1;

    private boolean meshCreated = false;

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

    public Box() {
    }

    public Box(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public Box(float x, float y, float z, float width, float height, float depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void setColor(Color4f color) {
        this.color = color;
    }

    public Color4f getColor() {
        return color;
    }

    public void setTextures(Texture... textures) {
        textureIDs = new int[textures.length];

        for (int i = 0; i < textures.length; i++) {
            textureIDs[i] = AssetLoader.loadPNGTexture(textures[i].getFilePath());
        }
    }

    public void generate() {
        vertices = new float[]{
            x, y, z + depth, //Bottom left, front
            x + width, y, z + depth, //Bottom right, front
            x + width, y + height, z + depth, //Top right, front
            x, y + height, z + depth, //Top left, front
            x, y, z, //Bottom left, back
            x + width, y, z, //Bottom right, back
            x + width, y + height, z, //Top right, back
            x, y + height, z //Top left, back
        };

        if (textureIDs == null) {
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
            mesh = new Mesh(vertices, indices, colors);
        } else {
            textureCoords = new float[]{
                0, 0, //first vertex
                0, 1, //second vertex
                1, 1, //third vertex
                1, 0 //fourth vertex
            };

            mesh = new Mesh(vertices, indices, textureCoords, textureIDs);
        }

        meshCreated = true;
    }

    public void translate(float x, float y, float z) {
        if (meshCreated) {
            mesh.translate(x, y, z);
        } else {
            this.x += x;
            this.y += y;
            this.z += z;
        }
    }

    public void rotate(float x, float y, float z) {
        if (meshCreated) {
            mesh.rotate(x, y, z);
        } else {
            this.x += x;
            this.y += y;
            this.z += z;
        }
    }

    public void scale(float x, float y, float z) {
        if (meshCreated) {
            mesh.scale(x, y, z);
        } else {
            this.x += x;
            this.y += y;
            this.z += z;
        }
    }

    public void setLocation(float x, float y, float z) {
        if (meshCreated) {
            mesh.setLocation(x, y, z);
        } else {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public void setRotation(float x, float y, float z) {
        if (meshCreated) {
            mesh.setRotation(x, y, z);
        } else {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public void setScale(float x, float y, float z) {
        if (meshCreated) {
            mesh.setScale(x, y, z);
        } else {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
