package topaz.rendering;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import topaz.core.Display;
import topaz.input.MouseManager;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderProgram;

public class RenderManager {

    private Display display;
    private MouseManager mouseManager;
    private Camera camera;

    private float fieldOfView;
    private float nearPlane;
    private float farPlane;

    private ArrayList<ColoredMesh> coloredMeshes = new ArrayList<>();
    private ArrayList<TexturedMesh> texturedMeshes = new ArrayList<>();

    private ShaderProgram coloredMeshShaderProgram;
    private ShaderProgram texturedMeshShaderProgram;

    public RenderManager(Display display, MouseManager mouseManager, Camera camera) {
        this.display = display;
        this.mouseManager = mouseManager;
        this.camera = camera;

        fieldOfView = 45f;
        nearPlane = 0.01f;
        farPlane = 100f;

        setUpShaders();
    }

    public void tick(double delta) {
        float aspectRatio = (float) display.getWidth() / (float) display.getHeight();

        Matrix4f viewProjectionMatrix = new Matrix4f();
        viewProjectionMatrix.perspective((float) Math.toRadians(fieldOfView), aspectRatio, nearPlane, farPlane);
        if (camera.isFollowingMouse()) {
            viewProjectionMatrix.lookAt(camera.getLocation(), camera.getLocation().add(camera.getForward()), camera.getUp());
        }

        for (ColoredMesh mesh : coloredMeshes) {
            mesh.tick(delta, viewProjectionMatrix);
        }
        for (TexturedMesh mesh : texturedMeshes) {
            mesh.tick(delta, viewProjectionMatrix);
        }
    }

    public void render() {
        GL20.glUseProgram(coloredMeshShaderProgram.getProgramID());
        for (ColoredMesh mesh : coloredMeshes) {
            mesh.render();
        }
        GL20.glUseProgram(0);

        GL20.glUseProgram(texturedMeshShaderProgram.getProgramID());
        for (TexturedMesh mesh : texturedMeshes) {
            mesh.render();
        }
        GL20.glUseProgram(0);
    }

    private void setUpShaders() {
        try {
            Shader vsColorMesh = new Shader("/topaz/assets/shaders/colorMesh.vs", GL20.GL_VERTEX_SHADER);
            Shader fsColorMesh = new Shader("/topaz/assets/shaders/colorMesh.fs", GL20.GL_FRAGMENT_SHADER);
            coloredMeshShaderProgram = new ShaderProgram(vsColorMesh, fsColorMesh);

            Shader vsTextureMesh = new Shader("/topaz/assets/shaders/textureMesh.vs", GL20.GL_VERTEX_SHADER);
            Shader fsTextureMesh = new Shader("/topaz/assets/shaders/textureMesh.fs", GL20.GL_FRAGMENT_SHADER);
            texturedMeshShaderProgram = new ShaderProgram(vsTextureMesh, fsTextureMesh);
        } catch (Exception ex) {
            Logger.getLogger(TexturedMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Mesh mesh) {
        if (mesh == null) {
            System.out.println("ERROR: Mesh is null!");
            return;
        }
        if (mesh instanceof ColoredMesh) {
            coloredMeshes.add((ColoredMesh) mesh);
        } else if (mesh instanceof TexturedMesh) {
            texturedMeshes.add((TexturedMesh) mesh);
        } else {
            System.out.println("ERROR: Mesh type is not supported!");
        }
    }

    public void remove(Mesh mesh) {
        if (mesh == null) {
            System.out.println("ERROR: Mesh is null!");
            return;
        }
        if (mesh instanceof ColoredMesh) {
            coloredMeshes.remove((ColoredMesh) mesh);
        } else if (mesh instanceof TexturedMesh) {
            texturedMeshes.remove((TexturedMesh) mesh);
        } else {
            System.out.println("ERROR: Mesh type is not supported!");
        }
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

    public ShaderProgram getColoredMeshShaderProgram() {
        return coloredMeshShaderProgram;
    }

    public ShaderProgram getTexturedMeshShaderProgram() {
        return texturedMeshShaderProgram;
    }
}
