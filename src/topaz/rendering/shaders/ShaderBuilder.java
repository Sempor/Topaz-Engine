package topaz.rendering.shaders;

import org.lwjgl.opengl.GL20;

public class ShaderBuilder {

    public static Shader createVertexShader(String filePath) {
        try {
            return new Shader(filePath, GL20.GL_VERTEX_SHADER);
        } catch (Exception e) {
            System.out.println("ERROR: Failed to create vertex shader");
            System.exit(-1);
        }
        return null;
    }

    public static Shader createFragmentShader(String filePath) {
        try {
            return new Shader(filePath, GL20.GL_FRAGMENT_SHADER);
        } catch (Exception e) {
            System.out.println("ERROR: Failed to create fragment shader");
            System.exit(-1);
        }
        return null;
    }
}
