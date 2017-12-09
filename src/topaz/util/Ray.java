package topaz.util;

import org.joml.Vector3f;

public class Ray {

    private Vector3f startPoint;
    private Vector3f direction;

    public Ray() {
    }

    public Ray(Vector3f startPoint, Vector3f direction) {
        this.startPoint = new Vector3f(startPoint);
        this.direction = new Vector3f(direction);
    }

    public Vector3f getPointOnRay(float distanceFromStartPoint) {
        Vector3f normDirection = new Vector3f(direction).normalize();
        return new Vector3f(startPoint).add(normDirection.mul(distanceFromStartPoint));
    }

    public Vector3f getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector3f startPoint) {
        this.startPoint = new Vector3f(startPoint);
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = new Vector3f(direction);
    }
}
