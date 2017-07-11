package topaz.rendering;

import org.joml.Vector3f;

public class Camera {

    public Vector3f location;
    public Vector3f rotation;
    public Vector3f scale;

    public Camera() {
        location = new Vector3f(0, 0, -2);
        rotation = new Vector3f(0, 0, 0);
        scale = new Vector3f(1, 1, 1);
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

    public void scale(float dx, float dy, float dz) {
        scale.x += dx;
        scale.y += dy;
        scale.z += dz;
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

    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setScale(float x, float y, float z) {
        scale.x = x;
        scale.y = y;
        scale.z = z;
    }
}