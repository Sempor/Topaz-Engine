package topaz.physics;

import topaz.physics.collisions.CollisionObject;
import org.joml.Vector3f;

public class PhysicsObject {

    public static float GRAVITY_ACCELERATION = -9.8f;

    private CollisionObject collisionObject;
    private Vector3f netForce;
    private Vector3f linearVelocity;
    private float mass;

    private float accelerationFromGravity;
    private boolean gravityEnabled;

    private boolean sleeping;

    public PhysicsObject(CollisionObject collisionObject, float mass) {
        this.collisionObject = collisionObject;
        this.mass = mass;

        netForce = new Vector3f(0, 0, 0);
        linearVelocity = new Vector3f(0, 0, 0);
        accelerationFromGravity = GRAVITY_ACCELERATION;
    }

    public void tick(double elapsedSeconds) {
        if (!sleeping) {
            //Updates linearVelocity vector
            Vector3f acceleration = new Vector3f(netForce).div(mass);
            Vector3f dv = new Vector3f(acceleration).mul((float) elapsedSeconds);
            linearVelocity.add(dv);

            //Updates position - adjusts for collisions
            boolean collision = collisionObject.translateX(linearVelocity.x * (float) elapsedSeconds);
            if (collision) {
                linearVelocity.x = 0;
            }
            collision = collisionObject.translateY(linearVelocity.y * (float) elapsedSeconds);
            if (collision) {
                linearVelocity.y = 0;
            }
            collision = collisionObject.translateZ(linearVelocity.z * (float) elapsedSeconds);
            if (collision) {
                linearVelocity.z = 0;
            }

            if (linearVelocity.lengthSquared() == 0) {
                sleeping = true;
            }
        }
    }

    public void setGravityEnabled(boolean enabled) {
        if (enabled && !gravityEnabled) {
            netForce.add(new Vector3f(0, accelerationFromGravity * mass, 0));
        } else if (!enabled && gravityEnabled) {
            netForce.add(new Vector3f(0, -accelerationFromGravity * mass, 0));
        }
        gravityEnabled = enabled;
        sleeping = false;
    }

    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    public CollisionObject getCollisionObject() {
        sleeping = false;
        return collisionObject;
    }

    public void setCollisionObject(CollisionObject collisionObject) {
        this.collisionObject = collisionObject;
        sleeping = false;
    }

    public Vector3f getNetForce() {
        sleeping = false;
        return netForce;
    }

    public void setNetForce(Vector3f netForce) {
        this.netForce = netForce;
        sleeping = false;
    }

    public float getMass() {
        sleeping = false;
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        sleeping = false;
    }

    public Vector3f getLocation() {
        sleeping = false;
        return collisionObject.getLocation();
    }

    public void setLocation(Vector3f location) {
        setLocation(location.x, location.y, location.z);
    }

    public void setLocation(float x, float y, float z) {
        collisionObject.setLocation(x, y, z);
        sleeping = false;
    }

    public Vector3f getCenter() {
        sleeping = false;
        return collisionObject.getCenter();
    }

    public void setCenter(Vector3f center) {
        setCenter(center.x, center.y, center.z);
    }

    public void setCenter(float x, float y, float z) {
        collisionObject.setCenter(x, y, z);
        sleeping = false;
    }

    public Vector3f getScale() {
        sleeping = false;
        return collisionObject.getScale();
    }

    public void setScale(Vector3f scale) {
        collisionObject.setScale(scale);
        sleeping = false;
    }

    public Vector3f getLinearVelocity() {
        sleeping = false;
        return linearVelocity;
    }

    public void setLinearVelocity(Vector3f linearVelocity) {
        this.linearVelocity = linearVelocity;
        sleeping = false;
    }

    public void addLinearVelocity(Vector3f addedLinearVelocity) {
        linearVelocity.add(addedLinearVelocity);
        sleeping = false;
    }

    public Vector3f getMomentum() {
        return new Vector3f(linearVelocity).mul(mass);
    }

    public float getKineticEnergy() {
        return mass * linearVelocity.lengthSquared() / 2f;
    }

    public float getAccelerationFromGravity() {
        return accelerationFromGravity;
    }

    public void setAccelerationFromGravity(float acceleration) {
        if (gravityEnabled) {
            netForce.add(new Vector3f(0, -this.accelerationFromGravity * mass, 0));
            netForce.add(new Vector3f(0, acceleration * mass, 0));
        }

        this.accelerationFromGravity = acceleration;
        sleeping = false;
    }

    public void setActive(boolean active) {
        sleeping = false;
        collisionObject.setActive(active);
    }

    public boolean isActive() {
        return collisionObject.isActive();
    }
}
