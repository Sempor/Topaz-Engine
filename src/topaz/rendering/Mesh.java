package topaz.rendering;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Mesh {

    private float[] vertices;
    private short[] indices;

    private int vaoID;
    private int colorsVboID;
    private int textureCoordsVboID;

    private FloatBuffer mvpMatrixBuffer;

    public Mesh(float[] vertices, short[] indices) {
        this.vertices = Arrays.copyOf(vertices, vertices.length);
        this.indices = Arrays.copyOf(indices, indices.length);

        mvpMatrixBuffer = BufferUtils.createFloatBuffer(16);

        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        vaoID = GL30.glGenVertexArrays(); //Creates vao (vertex array object)
        GL30.glBindVertexArray(vaoID);

        int verticesVboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesVboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW); //Uploads vertex data into the array buffer of the vao
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0); //Points buffer at location 0 of the array buffer in the vao

        int eboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW); //Uploads index data into the element array buffer of the vao

        //Colors
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(vertices.length * 4 / 3);
        colorsVboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsVboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_DYNAMIC_DRAW); //Uploads color data into the array buffer of the vao
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 0, 0); //Points buffer at location 1 of the array buffer of the vao

        //Texture coords
        float[] textureCoords = new float[]{
            0, 0, //first vertex
            0, 1, //second vertex
            1, 1, //third vertex
            1, 0 //fourth vertex
        };
        FloatBuffer textureCoordsBuffer = BufferUtils.createFloatBuffer(textureCoords.length);
        textureCoordsBuffer.put(textureCoords).flip();
        textureCoordsVboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, textureCoordsVboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureCoordsBuffer, GL15.GL_STATIC_DRAW); //Uploads texture coords data into the array buffer of the vao
        GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 0, 0); //Points buffer at location 2 of the array buffer of the vao

        GL30.glBindVertexArray(0);
    }

    public Mesh(Mesh mesh) {
        this(mesh.vertices, mesh.indices);
    }

    protected void tick(double delta, Matrix4f modelViewProjectionMatrix) {
        storeMatrixInBuffer(mvpMatrixBuffer, modelViewProjectionMatrix);
        mvpMatrixBuffer.flip();
    }

    protected void render(ShaderProgram[] shaderPrograms, int texture, boolean visible) {
        if (texture != -1) {
            GL20.glUseProgram(shaderPrograms[1].getProgramID());
            GL20.glUniformMatrix4fv(GL20.glGetUniformLocation(shaderPrograms[1].getProgramID(), "mvpMatrix"), false, mvpMatrixBuffer); //Uploads mvpMatrix to the uniform variable
            if (visible) {
                GL13.glActiveTexture(GL13.GL_TEXTURE0);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);

                GL30.glBindVertexArray(vaoID);
                GL20.glEnableVertexAttribArray(0);
                GL20.glEnableVertexAttribArray(2);

                GL11.glDrawElements(GL11.GL_TRIANGLES, indices.length, GL11.GL_UNSIGNED_SHORT, 0);

                GL20.glDisableVertexAttribArray(0);
                GL20.glDisableVertexAttribArray(2);
                GL30.glBindVertexArray(0);
            }
        } else {
            GL20.glUseProgram(shaderPrograms[0].getProgramID());
            GL20.glUniformMatrix4fv(GL20.glGetUniformLocation(shaderPrograms[0].getProgramID(), "mvpMatrix"), false, mvpMatrixBuffer); //Uploads mvpMatrix to the uniform variable
            if (visible) {
                GL30.glBindVertexArray(vaoID);
                GL20.glEnableVertexAttribArray(0);
                GL20.glEnableVertexAttribArray(1);

                GL11.glDrawElements(GL11.GL_TRIANGLES, indices.length, GL11.GL_UNSIGNED_SHORT, 0);

                GL20.glDisableVertexAttribArray(0);
                GL20.glDisableVertexAttribArray(1);
                GL30.glBindVertexArray(0);
            }
        }
        GL20.glUseProgram(0);
    }

    private Matrix4f storeMatrixInBuffer(FloatBuffer matrixBuffer, Matrix4f matrix) {
        matrixBuffer.put(matrix.m00());
        matrixBuffer.put(matrix.m01());
        matrixBuffer.put(matrix.m02());
        matrixBuffer.put(matrix.m03());
        matrixBuffer.put(matrix.m10());
        matrixBuffer.put(matrix.m11());
        matrixBuffer.put(matrix.m12());
        matrixBuffer.put(matrix.m13());
        matrixBuffer.put(matrix.m20());
        matrixBuffer.put(matrix.m21());
        matrixBuffer.put(matrix.m22());
        matrixBuffer.put(matrix.m23());
        matrixBuffer.put(matrix.m30());
        matrixBuffer.put(matrix.m31());
        matrixBuffer.put(matrix.m32());
        matrixBuffer.put(matrix.m33());
        return matrix;
    }

    //Need to fix up this code. Map buffer data instead of using glBufferData because
    //glBufferData creates more memory.
    public void setColor(float[] colors) {
        //Stores the colors in a buffer
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();

        GL30.glBindVertexArray(vaoID);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorsVboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_DYNAMIC_DRAW); //Update location 1 of the array buffer of the vao

        GL30.glBindVertexArray(0);
    }

    //Need to fix up this code. Map buffer data instead of using glBufferData because
    //glBufferData creates more memory.
    public void setTextureCoords(float[] textureCoords) {
        //Stores the texture coords in a buffer
        FloatBuffer textureCoordsBuffer = BufferUtils.createFloatBuffer(textureCoords.length);
        textureCoordsBuffer.put(textureCoords).flip();

        GL30.glBindVertexArray(vaoID);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, textureCoordsVboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureCoordsBuffer, GL15.GL_STATIC_DRAW); //Update location 2 of the array buffer of the vao

        GL30.glBindVertexArray(0);
    }

    public float[] getVertices() {
        return vertices;
    }

    public short[] getIndices() {
        return indices;
    }
}
