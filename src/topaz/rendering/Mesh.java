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

public class Mesh {

    private int vaoID;
    private int vboVertID;
    private int vboColID;
    private int vboTexID;
    private int eboID;

    private int numIndices;

    private Vector3f location;
    private Vector3f rotation;
    private Vector3f scale;

    private Matrix4f modelMatrix;

    private FloatBuffer matrix44Buffer = null;

    private int[] textureIDs;
    private int textureSelector = 0;

    private MeshType meshType;

    public enum MeshType {
        COLOR_MESH, TEXTURE_MESH
    }

    public Mesh(float[] vertices, short[] indices, float[] colors) {
        meshType = MeshType.COLOR_MESH;

        numIndices = indices.length;

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

        //Stores the colors in a buffer
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();

        //Creates a vertex buffer object and uploads the colors buffer into the vertex buffer object
        vboColID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboColID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_STATIC_DRAW);

        //Points the buffer at location 1
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 0, 0);

        //Stores the indices in a buffer
        ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        //Creates an element buffer object and uploads the indices buffer into the element buffer object
        eboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);

        GL30.glBindVertexArray(0);

        createModelMatrix();

        // Create a FloatBuffer with the proper size to store our matrices later
        matrix44Buffer = BufferUtils.createFloatBuffer(16);

        RenderManager.addMesh(this);
    }

    public Mesh(float[] vertices, short[] indices, float[] textureCoords, int[] textureIDs) {
        meshType = MeshType.TEXTURE_MESH;

        numIndices = indices.length;

        this.textureIDs = Arrays.copyOf(textureIDs, textureIDs.length);

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

        //Stores the texture coordinates in a buffer
        FloatBuffer textureCoordsBuffer = BufferUtils.createFloatBuffer(textureCoords.length);
        textureCoordsBuffer.put(textureCoords).flip();

        //Creates a vertex buffer object and uploads the texture coords buffer into the vertex buffer object
        vboTexID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboTexID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureCoordsBuffer, GL15.GL_STATIC_DRAW);

        //Points the buffer at location 1
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);

        //Stores the indices in a buffer
        ShortBuffer indicesBuffer = BufferUtils.createShortBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        //Creates an element buffer object and uploads the indices buffer into the element buffer object
        eboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);

        GL30.glBindVertexArray(0);

        createModelMatrix();

        // Create a FloatBuffer with the proper size to store our matrices later
        matrix44Buffer = BufferUtils.createFloatBuffer(16);

        RenderManager.addMesh(this);
    }

    public void tick(Matrix4f viewProjectionMatrix, double delta) {
        //Reset model matrix
        modelMatrix = new Matrix4f();

        // Scale, translate, and rotate model
        modelMatrix.scale(scale);
        modelMatrix.translate(location);
        modelMatrix.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
        modelMatrix.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        modelMatrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));

        //Calculate model-view-projection matrix
        Matrix4f mvp = new Matrix4f(viewProjectionMatrix);
        mvp = mvp.mul(modelMatrix);

        // Upload mvp matrix to the uniform variable
        GL20.glUseProgram(getShaderProgram(meshType).getProgramID());
        store(mvp, matrix44Buffer);
        matrix44Buffer.flip();
        GL20.glUniformMatrix4fv(RenderManager.getMVPMatrixLocation(getShaderProgram(meshType)),
                false, matrix44Buffer);
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
        //Binds the program
        GL20.glUseProgram(getShaderProgram(meshType).getProgramID());

        //Binds the texture (if there is one)
        if (meshType == MeshType.TEXTURE_MESH) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureIDs[textureSelector]);
        }

        //Binds the vertex array object
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        //Draws the mesh
        GL11.glDrawElements(GL11.GL_TRIANGLES, numIndices, GL11.GL_UNSIGNED_SHORT, 0);

        //Unbinds the vertex array object
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);

        //Unbinds the program
        GL20.glUseProgram(0);
    }

    public void translate(Vector3f translation) {
        location.x += translation.x;
        location.y += translation.y;
        location.z += translation.z;
    }

    public void translate(float translateX, float translateY, float translateZ) {
        location.x += translateX;
        location.y += translateY;
        location.z += translateZ;
    }

    public void rotate(Vector3f rotation) {
        this.rotation.x += rotation.x;
        this.rotation.y += rotation.y;
        this.rotation.z += rotation.z;
    }

    public void rotate(float rotateX, float rotateY, float rotateZ) {
        rotation.x += rotateX;
        rotation.y += rotateY;
        rotation.z += rotateZ;
    }

    public void scale(Vector3f scale) {
        this.scale.x += scale.x;
        this.scale.y += scale.y;
        this.scale.z += scale.z;
    }

    public void scale(float scaleX, float scaleY, float scaleZ) {
        scale.x += scaleX;
        scale.y += scaleY;
        scale.z += scaleZ;
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

    public void setTextureSelector(int textureSelector) {
        this.textureSelector = textureSelector;
    }

    private void createModelMatrix() {
        modelMatrix = new Matrix4f();

        //Sets default rotation, scale, and position
        location = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
        scale = new Vector3f(1, 1, 1);
    }

    private ShaderProgram getShaderProgram(MeshType meshType) {
        if (meshType == MeshType.COLOR_MESH) {
            return RenderManager.colorMesh;
        } else if (meshType == MeshType.TEXTURE_MESH) {
            return RenderManager.textureMesh;
        }
        return null;
    }
}
