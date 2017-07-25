package topaz.rendering;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import topaz.rendering.shaders.ShaderProgram;

public abstract class Mesh {

    private RenderManager renderManager;

    protected int vaoID;
    private int eboID;
    private int verticesVboID;

    private float[] vertices;
    private short[] indices;

    protected ShaderProgram shaderProgram;
    private FloatBuffer mvpMatrixBuffer;

    private float x, y, z;
    private float rotateX, rotateY, rotateZ;
    private float scaleX = 1f, scaleY = 1f, scaleZ = 1f;

    protected boolean visible;

    public Mesh(RenderManager renderManager, float[] vertices, short[] indices) {
        this.renderManager = renderManager;
        this.vertices = Arrays.copyOf(vertices, vertices.length);
        this.indices = Arrays.copyOf(indices, indices.length);
        mvpMatrixBuffer = BufferUtils.createFloatBuffer(16);

        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        vaoID = GL30.glGenVertexArrays(); //Creates vao (vertex array object)
        GL30.glBindVertexArray(vaoID);

        verticesVboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesVboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW); //Uploads vertex data into the array buffer of the vao
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0); //Points buffer at location 0 of the array buffer in the vao

        eboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW); //Uploads index data into the element array buffer of the vao

        GL30.glBindVertexArray(0);
    }

    public Mesh(Mesh mesh) {
        this(mesh.renderManager, mesh.vertices, mesh.indices);
        this.x = mesh.x;
        this.y = mesh.y;
        this.z = mesh.z;
        this.rotateX = mesh.rotateX;
        this.rotateY = mesh.rotateY;
        this.rotateZ = mesh.rotateZ;
        this.scaleX = mesh.scaleX;
        this.scaleY = mesh.scaleY;
        this.scaleZ = mesh.scaleZ;
        this.visible = mesh.visible;
    }

    public void tick(double delta, Matrix4f viewProjectionMatrix) {
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.scale(scaleX, scaleY, scaleZ);
        modelMatrix.translate(x, y, z);
        modelMatrix.rotate((float) Math.toRadians(rotateZ), 0, 0, 1);
        modelMatrix.rotate((float) Math.toRadians(rotateY), 0, 1, 0);
        modelMatrix.rotate((float) Math.toRadians(rotateX), 1, 0, 0);

        Matrix4f modelViewProjectionMatrix = new Matrix4f(viewProjectionMatrix).mul(modelMatrix);
        RenderingUtils.storeMatrixInBuffer(mvpMatrixBuffer, modelViewProjectionMatrix);
        mvpMatrixBuffer.flip();
    }

    public void render() {
        GL20.glUniformMatrix4fv(GL20.glGetUniformLocation(shaderProgram.getProgramID(), "mvpMatrix"), false, mvpMatrixBuffer); //Uploads mvpMatrix to the uniform variable

        if (visible) {
            if (this instanceof TexturedMesh) {
                GL13.glActiveTexture(GL13.GL_TEXTURE0);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((TexturedMesh) this).getSelectedTexture());
            }

            GL30.glBindVertexArray(vaoID);
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            GL11.glDrawElements(GL11.GL_TRIANGLES, indices.length, GL11.GL_UNSIGNED_SHORT, 0);

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);
        }
    }

    public void translate(Vector3f translation) {
        translate(translation.x, translation.y, translation.z);
    }

    public void translate(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void rotate(Vector3f rotation) {
        rotate(rotation.x, rotation.y, rotation.z);
    }

    public void rotate(float dx, float dy, float dz) {
        rotateX += dx;
        rotateY += dy;
        rotateZ += dz;
    }

    public void scale(Vector3f scale) {
        scale(scale.x, scale.y, scale.z);
    }

    public void scale(float dx, float dy, float dz) {
        scaleX += dx;
        scaleY += dy;
        scaleZ += dz;
    }

    public Vector3f getLocation() {
        return new Vector3f(x, y, z);
    }

    public void setLocation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setLocation(Vector3f location) {
        setLocation(location.x, location.y, location.z);
    }

    public Vector3f getRotation() {
        return new Vector3f(rotateX, rotateY, rotateZ);
    }

    public void setRotation(float rotateX, float rotateY, float rotateZ) {
        this.rotateX = rotateX;
        this.rotateY = rotateY;
        this.rotateZ = rotateZ;
    }

    public void setRotation(Vector3f rotation) {
        setRotation(rotation.x, rotation.y, rotation.z);
    }

    public Vector3f getScale() {
        return new Vector3f(scaleX, scaleY, scaleZ);
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    public void setScale(Vector3f scale) {
        setScale(scale.x, scale.y, scale.z);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }
}
