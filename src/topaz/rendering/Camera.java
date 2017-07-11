package topaz.rendering;

import org.joml.Vector3f;
import topaz.input.MouseManager;

public class Camera {

    private MouseManager mouseManager;

    private Vector3f location;
    private Vector3f forward;
    private Vector3f right;
    private Vector3f up;

    private float horizontalAngle = 0f;
    private float verticalAngle = 0;

    public Camera(MouseManager mouseManager) {
        this.mouseManager = mouseManager;

        location = new Vector3f(0, 0, -2);
        forward = new Vector3f(0, 0, 0);
        right = new Vector3f(0, 0, 0);
        up = new Vector3f(0, 1, 0);
    }

    public void tick(double delta) {
        horizontalAngle += mouseManager.getMouseSpeed() * delta * ((float) RenderSettings.getDisplayWidth() / 2f - (float) mouseManager.getCursorX());
        verticalAngle += mouseManager.getMouseSpeed() * delta * ((float) RenderSettings.getDisplayHeight() / 2f - (float) mouseManager.getCursorY());

        forward = new Vector3f((float) (Math.cos(verticalAngle) * Math.sin(horizontalAngle)),
                (float) Math.sin(verticalAngle),
                (float) (Math.cos(verticalAngle) * Math.cos(horizontalAngle)));

        right = new Vector3f((float) Math.sin(horizontalAngle - Math.PI / 2f),
                0,
                (float) Math.cos(horizontalAngle - Math.PI / 2f));
        
        //Computes cross product of forward and right, stores cross product in up
        Vector3f rightCopy = new Vector3f(right);
        rightCopy.cross(forward, up);
    }

    public void translate(float dx, float dy, float dz) {
        location.x += dx;
        location.y += dy;
        location.z += dz;
    }
    
    public void translate(Vector3f translation) {
        location.add(translation);
    }

    public Vector3f getLocation() {
        return new Vector3f(location);
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public void setLocation(float x, float y, float z) {
        location.x = x;
        location.y = y;
        location.z = z;
    }

    public Vector3f getForward() {
        return new Vector3f(forward);
    }

    public Vector3f getRight() {
        return new Vector3f(right);
    }

    public Vector3f getUp() {
        return new Vector3f(up);
    }
}