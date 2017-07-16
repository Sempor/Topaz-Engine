package topaz.rendering;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import topaz.rendering.shaders.ShaderProgram;

public class Mesh {

    private RenderManager renderManager;

    private FloatBuffer matrix44Buffer;

    protected int vaoID;
    private int vboVertID;
    private int eboID;

    protected int numIndices;

    protected ShaderProgram shaderProgram;

    private Vector3f location = new Vector3f(0, 0, 0);
    private Vector3f rotation = new Vector3f(0, 0, 0);
    private Vector3f scale = new Vector3f(1, 1, 1);

    protected boolean visible;

    public Mesh(RenderManager renderManager, float[] vertices, short[] indices) {
        this.renderManager = renderManager;
        numIndices = indices.length;

        matrix44Buffer = BufferUtils.createFloatBuffer(16);

        //Creates a vertex array object
        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);

        //Stores the vertices in a buffer
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        //Creates a vertex buffer object and uploads the vertices buffer into the vertex buffer object
        vboVertID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);

        //Points the buffer at location 0
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
    }

    protected void storeIndices(short[] indices) {
        //Stores the indices in a buffer
        ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        //Creates an element buffer object and uploads the indices buffer into the element buffer object
        eboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);

        GL30.glBindVertexArray(0);
    }

    public void tick(Matrix4f viewProjectionMatrix, double delta) {
        Matrix4f modelMatrix = new Matrix4f();

        modelMatrix.scale(scale);
        modelMatrix.translate(location);
        modelMatrix.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
        modelMatrix.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        modelMatrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));

        Matrix4f modelViewProjectionMatrix = new Matrix4f(viewProjectionMatrix).mul(modelMatrix);

        //Uploads modelViewProjectionMatrix to the uniform variable
        GL20.glUseProgram(shaderProgram.getProgramID());
        store(modelViewProjectionMatrix, matrix44Buffer);
        matrix44Buffer.flip();
        GL20.glUniformMatrix4fv(GL20.glGetUniformLocation(shaderProgram.getProgramID(), "mvpMatrix"), false, matrix44Buffer);
        GL20.glUseProgram(0);
    }

    /* TEMPORARY - DO NOT KEEP - LWJGL CODE */
    private Matrix4f store(Matrix4f matrix, FloatBuffer buf) {
        buf.put(matrix.m00());
        buf.put(matrix.m01());
        buf.put(matrix.m02());
        buf.put(matrix.m03());
        buf.put(matrix.m10());
        buf.put(matrix.m11());
        buf.put(matrix.m12());
        buf.put(matrix.m13());
        buf.put(matrix.m20());
        buf.put(matrix.m21());
        buf.put(matrix.m22());
        buf.put(matrix.m23());
        buf.put(matrix.m30());
        buf.put(matrix.m31());
        buf.put(matrix.m32());
        buf.put(matrix.m33());
        return matrix;
    }

    public void render() {
        if (visible) {
            GL20.glUseProgram(shaderProgram.getProgramID());

            GL30.glBindVertexArray(vaoID);
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            GL11.glDrawElements(GL11.GL_TRIANGLES, numIndices, GL11.GL_UNSIGNED_SHORT, 0);

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);

            GL20.glUseProgram(0);
        }
    }

    public void translate(Vector3f translation) {
        location.x += translation.x;
        location.y += translation.y;
        location.z += translation.z;
    }

    public void translate(float dx, float dy, float dz) {
        translate(new Vector3f(dx, dy, dz));
    }

    public void rotate(Vector3f rotation) {
        this.rotation.x += rotation.x;
        this.rotation.y += rotation.y;
        this.rotation.z += rotation.z;
    }

    public void rotate(float dx, float dy, float dz) {
        rotate(new Vector3f(dx, dy, dz));
    }

    public void scale(Vector3f scale) {
        this.scale.x += scale.x;
        this.scale.y += scale.y;
        this.scale.z += scale.z;
    }

    public void scale(float dx, float dy, float dz) {
        scale(new Vector3f(dx, dy, dz));
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(float x, float y, float z) {
        location.x = x;
        location.y = y;
        location.z = z;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(float x, float y, float z) {
        scale.x = x;
        scale.y = y;
        scale.z = z;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
