package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import topaz.input.MouseManager;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderBuilder;
import topaz.rendering.shaders.ShaderProgram;

public class RenderManager {

    public ShaderProgram colorMesh;
    public ShaderProgram textureMesh;

    private float fieldOfView = 45f;
    private float nearPlane = 0.01f;
    private float farPlane = 100f;

    private ArrayList<Mesh> meshes = new ArrayList<>();

    public RenderManager(MouseManager mouseManager) {
        Shader vsColorMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/colorMesh.vs");
        Shader fsColorMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/colorMesh.fs");
        colorMesh = new ShaderProgram(vsColorMesh, fsColorMesh);

        Shader vsTextureMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/textureMesh.vs");
        Shader fsTextureMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/textureMesh.fs");
        textureMesh = new ShaderProgram(vsTextureMesh, fsTextureMesh);
    }

    public void tick(Camera camera, MouseManager mouseManager, double delta) {
        float aspectRatio = (float) RenderSettings.getDisplayWidth() / (float) RenderSettings.getDisplayHeight();

        Matrix4f viewProjectionMatrix = new Matrix4f();
        viewProjectionMatrix.perspective((float) Math.toRadians(fieldOfView), aspectRatio, nearPlane, farPlane);
        if (camera.isFollowingMouse()) {
            viewProjectionMatrix.lookAt(camera.getLocation(), camera.getLocation().add(camera.getForward()), camera.getUp());
        }

        for (Mesh mesh : meshes) {
            mesh.tick(this, viewProjectionMatrix, delta);
        }
    }

    public void render() {
        for (Mesh mesh : meshes) {
            mesh.render(this);
        }
    }

    public void addMesh(Mesh mesh) {
        meshes.add(mesh);
    }

    public int getMVPMatrixLocation(ShaderProgram program) {
        return GL20.glGetUniformLocation(program.getProgramID(), "mvpMatrix");
    }

    public float getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public float getNearPlane() {
        return nearPlane;
    }

    public void setNearPlane(float nearPlane) {
        this.nearPlane = nearPlane;
    }

    public float getFarPlane() {
        return farPlane;
    }

    public void setFarPlane(float farPlane) {
        this.farPlane = farPlane;
    }
}
