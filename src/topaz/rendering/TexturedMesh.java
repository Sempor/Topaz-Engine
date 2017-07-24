package topaz.rendering;

import java.nio.FloatBuffer;
import java.util.Arrays;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class TexturedMesh extends Mesh {

    private int vboTexID;

    private int[] textureIDs;
    private int selector;

    public TexturedMesh(RenderManager renderManager, float[] vertices, short[] indices, float[] textureCoords, int[] textureIDs) {
        super(renderManager, vertices, indices);
        this.textureIDs = Arrays.copyOf(textureIDs, textureIDs.length);
        shaderProgram = renderManager.getTexturedMeshShaderProgram();

        FloatBuffer textureCoordsBuffer = BufferUtils.createFloatBuffer(textureCoords.length);
        textureCoordsBuffer.put(textureCoords).flip();
        
        GL30.glBindVertexArray(vaoID);

        vboTexID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboTexID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureCoordsBuffer, GL15.GL_STATIC_DRAW); //Uploads texture coords data into the array buffer of the vao
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0); //Points buffer at location 1 of the array buffer of the vao

        GL30.glBindVertexArray(0);
    }

    public int getSelectedTexture() {
        return textureIDs[selector];
    }

    public void setSelectedTexture(int selector) {
        this.selector = selector;
    }
}
