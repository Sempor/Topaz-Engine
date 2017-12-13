package topaz.rendering;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import topaz.core.Display;
import topaz.rendering.shaders.Shader;
import topaz.rendering.shaders.ShaderProgram;

public class RenderManager {

    private Display display;
    private Camera camera;

    private float fieldOfView = 45f;
    private float nearPlane = 0.01f;
    private float farPlane = 100f;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private ShaderProgram[] shaderPrograms;

    public RenderManager(Display display, Camera camera) {
        this.display = display;
        this.camera = camera;

        try {
            shaderPrograms = new ShaderProgram[2];
            shaderPrograms[0] = new ShaderProgram(new Shader("/topaz/assets/shaders/colorMesh.vs", GL20.GL_VERTEX_SHADER),
                    new Shader("/topaz/assets/shaders/colorMesh.fs", GL20.GL_FRAGMENT_SHADER));
            shaderPrograms[1] = new ShaderProgram(new Shader("/topaz/assets/shaders/textureMesh.vs", GL20.GL_VERTEX_SHADER),
                    new Shader("/topaz/assets/shaders/textureMesh.fs", GL20.GL_FRAGMENT_SHADER));
        } catch (Exception ex) {
            Logger.getLogger(Mesh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tick(double delta) {
        Matrix4f viewProjectionMatrix = new Matrix4f();
        viewProjectionMatrix.perspective((float) Math.toRadians(fieldOfView), display.getAspectRatio(), nearPlane, farPlane);
        if (camera.isFollowingMouse()) {
            viewProjectionMatrix.lookAt(camera.getLocation(), camera.getLocation().add(camera.getForward()), camera.getUp());
        }

        for (GameObject gameObject : gameObjects) {
            Matrix4f modelViewProjectionMatrix = new Matrix4f(viewProjectionMatrix).mul(gameObject.getModelMatrix());
            gameObject.getMesh().tick(delta, modelViewProjectionMatrix);
        }
    }

    public void render() {
        GL20.glUseProgram(shaderPrograms[0].getProgramID());
        for (GameObject gameObject : gameObjects) {
            gameObject.getMesh().render(gameObject.getName(), shaderPrograms, gameObject.getSelectedTexture(), gameObject.isVisible());
        }
        GL20.glUseProgram(0);
    }

    public void add(GameObject gameObject) {
        if (gameObject != null) {
            gameObjects.add(gameObject);
        }
    }

    public void remove(GameObject gameObject) {
        if (gameObject != null) {
            gameObjects.remove(gameObject);
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
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
