package topaz.rendering;

import java.nio.FloatBuffer;
import java.util.Arrays;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderBuilder;
import topaz.rendering.shaders.ShaderProgram;

public class TexturedMesh extends Mesh {

    private int vboTexID;

    private int[] textureIDs;
    private int selectedTexture = 0;

    public TexturedMesh(RenderManager renderManager, float[] vertices, short[] indices, float[] textureCoords, int[] textureIDs) {
        super(renderManager, vertices, indices);

        this.textureIDs = Arrays.copyOf(textureIDs, textureIDs.length);
        
        Shader vsTextureMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/textureMesh.vs");
        Shader fsTextureMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/textureMesh.fs");
        shaderProgram = new ShaderProgram(vsTextureMesh, fsTextureMesh);

        //Stores the texture coordinates in a buffer
        FloatBuffer textureCoordsBuffer = BufferUtils.createFloatBuffer(textureCoords.length);
        textureCoordsBuffer.put(textureCoords).flip();

        //Creates a vertex buffer object and uploads the texture coords buffer into the vertex buffer object
        vboTexID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboTexID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureCoordsBuffer, GL15.GL_STATIC_DRAW);

        //Points the buffer at location 1
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);

        storeIndices(indices);
    }

    @Override
    public void render() {
        if (visible) {
            GL20.glUseProgram(shaderProgram.getProgramID());

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureIDs[selectedTexture]);

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

    public void setSelectedTexture(int selectedTexture) {
        this.selectedTexture = selectedTexture;
    }
}