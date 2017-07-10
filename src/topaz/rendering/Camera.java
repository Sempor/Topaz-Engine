package topaz.rendering;

import org.joml.Vector3f;

public class Camera {

    public Vector3f location;
    public Vector3f rotation;

    public Camera() {
        location = new Vector3f(0, 0, -2);
        rotation = new Vector3f(0, 0, 0);
    }

    public void translate(float dx, float dy, float dz) {
        location.x += dx;
        location.y += dy;
        location.z += dz;
    }

    public void rotate(float dx, float dy, float dz) {
        rotation.x += dx;
        rotation.y += dy;
        rotation.z += dz;
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public void setLocation(float x, float y, float z) {
        location.x = x;
        location.y = y;
        location.z = z;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}
