package topaz.rendering;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ColoredMesh extends Mesh {

    private int vboColID;

    public ColoredMesh(RenderManager renderManager, float[] vertices, short[] indices, float[] colors) {
        super(renderManager, vertices, indices);
        shaderProgram = renderManager.getColoredMeshShaderProgram();

        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();
        
        GL30.glBindVertexArray(vaoID);

        vboColID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboColID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_DYNAMIC_DRAW); //Uploads color data into the array buffer of the vao
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 0, 0); //Points buffer at location 1 of the array buffer of the vao

        GL30.glBindVertexArray(0);
    }

    public void updateColorData(float[] colors) {
        //Stores the colors in a buffer
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();
        
        GL30.glBindVertexArray(vaoID);
        
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 1, colorsBuffer); //Update location 1 of the array buffer of the vao
        
        GL30.glBindVertexArray(0);
    }
}
