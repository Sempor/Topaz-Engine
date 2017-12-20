package topaz.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import topaz.assets.AssetLoader;

public class Shader {

    private String filePath;
    private int shaderType;
    private int shaderID;

    public Shader(String filePath, int shaderType) throws Exception {
        this.filePath = filePath;
        this.shaderType = shaderType;

        //Creates shader, sets shader source, and compiles shader
        shaderID = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(shaderID, AssetLoader.loadFileAsString(filePath));
        GL20.glCompileShader(shaderID);

        //Error check
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw new Exception();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public int getShaderType() {
        return shaderType;
    }

    public int getShaderID() {
        return shaderID;
    }
}
