package topaz.rendering.meshes;

import topaz.rendering.Mesh;

public class Mesh_Box extends Mesh {

    public Mesh_Box() {
        super(new float[]{
            0, 0, 1, //Bottom left, front
            1, 0, 1, //Bottom right, front
            1, 1, 1, //Top right, front
            0, 1, 1, //Top left, front
            0, 0, 0, //Bottom left, back
            1, 0, 0, //Bottom right, back
            1, 1, 0, //Top right, back
            0, 1, 0 //Top left, back
        }, new short[]{
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
        });
    }
}
