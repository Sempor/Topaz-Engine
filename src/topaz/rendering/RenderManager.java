package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import topaz.core.Display;
import topaz.input.MouseManager;
import topaz.rendering.shaders.ShaderProgram;

public class RenderManager {
    
    private Display display;
    private MouseManager mouseManager;
    private Camera camera;
    
    public ShaderProgram colorMesh;
    public ShaderProgram textureMesh;
    
    private float fieldOfView = 45f;
    private float nearPlane = 0.01f;
    private float farPlane = 100f;
    
    private ArrayList<Mesh> meshes = new ArrayList<>();
    
    public RenderManager(Display display, MouseManager mouseManager, Camera camera) {
        this.display = display;
        this.mouseManager = mouseManager;
        this.camera = camera;
    }
    
    public void tick(double delta) {
        float aspectRatio = (float) display.getWidth() / (float) display.getHeight();
        
        Matrix4f viewProjectionMatrix = new Matrix4f();
        viewProjectionMatrix.perspective((float) Math.toRadians(fieldOfView), aspectRatio, nearPlane, farPlane);
        if (camera.isFollowingMouse()) {
            viewProjectionMatrix.lookAt(camera.getLocation(), camera.getLocation().add(camera.getForward()), camera.getUp());
        }
        
        for (Mesh mesh : meshes) {
            mesh.tick(viewProjectionMatrix, delta);
        }
    }
    
    public void render() {
        for (Mesh mesh : meshes) {
            mesh.render();
        }
    }
    
    public void add(Mesh mesh) {
        meshes.add(mesh);
    }
    
    public void remove(Mesh mesh) {
        meshes.remove(mesh);
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
