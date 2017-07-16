package topaz.rendering;

import org.joml.Vector3f;
import topaz.core.Display;
import topaz.input.MouseManager;

public class Camera {

    private Display display;
    private MouseManager mouseManager;

    private Vector3f location;
    private Vector3f forward;
    private Vector3f right;
    private Vector3f up;

    private float horizontalAngle = 0f;
    private float verticalAngle = 0f;

    private boolean followingMouse;

    public Camera(Display display, MouseManager mouseManager) {
        this.display = display;
        this.mouseManager = mouseManager;

        location = new Vector3f(0, 0, -2);
        forward = new Vector3f(0, 0, 0);
        right = new Vector3f(0, 0, 0);
        up = new Vector3f(0, 1, 0);
    }

    public void tick(double delta) {
        horizontalAngle += mouseManager.getMouseSpeed() * delta * ((float) display.getWidth() / 2f - (float) mouseManager.getCursorX());
        verticalAngle += mouseManager.getMouseSpeed() * delta * ((float) display.getHeight() / 2f - (float) mouseManager.getCursorY());

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
        translate(new Vector3f(dx, dy, dz));
    }

    public void translateX(float dx) {
        location.x += dx;
    }

    public void translateY(float dy) {
        location.y += dy;
    }

    public void translateZ(float dz) {
        location.z += dz;
    }

    public void translate(Vector3f translation) {
        location.add(translation);
    }

    public Vector3f getLocation() {
        return new Vector3f(location);
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }

    public float getZ() {
        return location.z;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public void setLocation(float x, float y, float z) {
        setLocation(new Vector3f(x, y, z));
    }

    public void setX(float x) {
        location.x = x;
    }

    public void setY(float y) {
        location.y = y;
    }

    public void setZ(float z) {
        location.z = z;
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