package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import topaz.input.MouseManager;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderBuilder;
import topaz.rendering.shaders.ShaderProgram;

public class RenderManager {

    public static ShaderProgram colorMesh;
    public static ShaderProgram textureMesh;

    private static float fieldOfView = 45f;
    private static float nearPlane = 0.01f;
    private static float farPlane = 100f;

    private static ArrayList<Mesh> meshes = new ArrayList<>();

    private static Camera camera;

    public static void init(MouseManager mouseManager) {
        Shader vsColorMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/colorMesh.vs");
        Shader fsColorMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/colorMesh.fs");
        colorMesh = new ShaderProgram(vsColorMesh, fsColorMesh);

        Shader vsTextureMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/textureMesh.vs");
        Shader fsTextureMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/textureMesh.fs");
        textureMesh = new ShaderProgram(vsTextureMesh, fsTextureMesh);

        camera = new Camera(mouseManager);
    }

    public static void tick(MouseManager mouseManager, double delta) {
        float aspectRatio = (float) RenderSettings.getDisplayWidth() / (float) RenderSettings.getDisplayHeight();

        Matrix4f viewProjectionMatrix = new Matrix4f();
        viewProjectionMatrix.perspective((float) Math.toRadians(fieldOfView), aspectRatio, nearPlane, farPlane);
        if (camera.isFollowingMouse()) {
            viewProjectionMatrix.lookAt(camera.getLocation(), camera.getLocation().add(camera.getForward()), camera.getUp());
        }

        camera.tick(delta);
        mouseManager.centerCursor();

        for (Mesh mesh : meshes) {
            mesh.tick(viewProjectionMatrix, delta);
        }
    }

    public static void render() {
        for (Mesh mesh : meshes) {
            mesh.render();
        }
    }

    public static void addMesh(Mesh mesh) {
        meshes.add(mesh);
    }

    public static int getMVPMatrixLocation(ShaderProgram program) {
        return GL20.glGetUniformLocation(program.getProgramID(), "mvpMatrix");
    }

    public static Camera getCamera() {
        return camera;
    }

    public static float getFieldOfView() {
        return fieldOfView;
    }

    public static void setFieldOfView(float fieldOfView) {
        RenderManager.fieldOfView = fieldOfView;
    }

    public static float getNearPlane() {
        return nearPlane;
    }

    public static void setNearPlane(float nearPlane) {
        RenderManager.nearPlane = nearPlane;
    }

    public static float getFarPlane() {
        return farPlane;
    }

    public static void setFarPlane(float farPlane) {
        RenderManager.farPlane = farPlane;
    }
}