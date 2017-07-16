package topaz.rendering;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderBuilder;
import topaz.rendering.shaders.ShaderProgram;

public class ColoredMesh extends Mesh {

    private int vboColID;
    
    public ColoredMesh(RenderManager renderManager, float[] vertices, short[] indices, float[] colors) {
        super(renderManager, vertices, indices);
        
        Shader vsColorMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/colorMesh.vs");
        Shader fsColorMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/colorMesh.fs");
        shaderProgram = new ShaderProgram(vsColorMesh, fsColorMesh);
        
        //Stores the colors in a buffer
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();
        
        //Creates a vertex buffer object and uploads the colors buffer into the vertex buffer object
        vboColID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboColID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_STATIC_DRAW);

        //Points the buffer at location 1
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 0, 0);
        
        storeIndices(indices);
    }
}
