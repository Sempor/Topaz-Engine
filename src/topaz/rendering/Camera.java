package topaz.rendering;

import org.joml.Vector3f;
import topaz.core.Display;
import topaz.input.MouseManager;
import topaz.physics.collisions.Raycast;

public class Camera {

    private Display display;
    private MouseManager mouseManager;

    public float x, y, z;

    private Vector3f forward;
    private Vector3f right;
    private Vector3f up;

    private float horizontalAngle = 0f;
    private float verticalAngle = 0f;

    private boolean followingMouse;

    public Camera(Display display, MouseManager mouseManager) {
        this.display = display;
        this.mouseManager = mouseManager;

        forward = new Vector3f(0, 0, 0);
        right = new Vector3f(0, 0, 0);
        up = new Vector3f(0, 1, 0);
        
        followingMouse = true;
    }

    public void tick(double delta) {
        horizontalAngle += mouseManager.getMouseSpeed() * delta * ((float) display.getWidth() / 2f - (float) display.getCursorX());
        verticalAngle += mouseManager.getMouseSpeed() * delta * ((float) display.getHeight() / 2f - (float) display.getCursorY());

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
        x += dx;
        y += dy;
        z += dz;
    }

    public void translate(Vector3f translation) {
        translate(translation.x, translation.y, translation.z);
    }

    public Vector3f getLocation() {
        return new Vector3f(x, y, z);
    }

    public void setLocation(Vector3f location) {
        setLocation(location.x, location.y, location.z);
    }

    public void setLocation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f getForward() {
        return new Vector3f(forward);
    }

    public Vector3f getBackward() {
        return new Vector3f(forward).negate();
    }

    public Vector3f getRight() {
        return new Vector3f(right);
    }

    public Vector3f getLeft() {
        return new Vector3f(right).negate();
    }

    public Vector3f getUp() {
        return new Vector3f(up);
    }

    public Vector3f getDown() {
        return new Vector3f(up).negate();
    }

    public boolean isFollowingMouse() {
        return followingMouse;
    }

    public void setFollowingMouse(boolean followingMouse) {
        this.followingMouse = followingMouse;
    }
}
