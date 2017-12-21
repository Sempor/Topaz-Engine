package topaz.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {

    private int programID;

    public ShaderProgram(Shader... shaders) {
        programID = GL20.glCreateProgram();
        if (programID == 0) {
            System.exit(-1);
        }
        
        for (Shader shader : shaders) {
            GL20.glAttachShader(programID, shader.getShaderID());
        }

        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.exit(-1);
        }
        
        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            System.exit(-1);
        }
    }

    public int getProgramID() {
        return programID;
    }
}
