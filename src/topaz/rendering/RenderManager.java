package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderBuilder;
import topaz.rendering.shaders.ShaderProgram;
import topaz.util.MathUtil;

public class RenderManager {

    public static ShaderProgram colorMesh;
    public static ShaderProgram textureMesh;

    private static float fieldOfView = 60f;
    private static float nearPlane = 0.1f;
    private static float farPlane = 100f;

    private static Matrix4f projectionMatrix;
    private static Matrix4f viewMatrix;

    private static ArrayList<Mesh> meshes = new ArrayList<>();

    private static Camera camera;

    public static void init() {
        //Create colorMesh shader program
        Shader vsColorMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/colorMesh.vs");
        Shader fsColorMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/colorMesh.fs");
        colorMesh = new ShaderProgram(vsColorMesh, fsColorMesh);

        //Creates textureMesh shader program
        Shader vsTextureMesh = ShaderBuilder.createVertexShader("/topaz/assets/shaders/textureMesh.vs");
        Shader fsTextureMesh = ShaderBuilder.createFragmentShader("/topaz/assets/shaders/textureMesh.fs");
        textureMesh = new ShaderProgram(vsTextureMesh, fsTextureMesh);

        //Create matrices
        createProjectionMatrix();
        viewMatrix = new Matrix4f();

        //Create camera
        camera = new Camera();
    }

    public static void tick(double delta) {
        //Reset view matrix
        viewMatrix = new Matrix4f();

        //Scale, translate, and rotate camera
        viewMatrix.scale(camera.getScale());
        viewMatrix.translate(camera.getLocation());
        viewMatrix.rotate((float) Math.toRadians(camera.getRotation().z), new Vector3f(0, 0, 1));
        viewMatrix.rotate((float) Math.toRadians(camera.getRotation().y), new Vector3f(0, 1, 0));
        viewMatrix.rotate((float) Math.toRadians(camera.getRotation().x), new Vector3f(1, 0, 0));

        //Tick meshes
        for (Mesh mesh : meshes) {
            mesh.tick(delta);
        }
    }

    public static void render() {
        //Render meshes
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

    public static Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public static Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public static Camera getCamera() {
        return camera;
    }

    private static void createProjectionMatrix() {
        projectionMatrix = new Matrix4f();
        float aspectRatio = (float) RenderSettings.getDisplayWidth() / (float) RenderSettings.getDisplayHeight();

        float y_scale = MathUtil.coTangent((float) Math.toRadians(fieldOfView / 2f));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = farPlane - nearPlane;

        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((farPlane + nearPlane) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * nearPlane * farPlane) / frustum_length));
        projectionMatrix.m33(0);
    }
}
