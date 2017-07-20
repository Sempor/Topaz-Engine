package topaz.physics;

import topaz.physics.collisions.CollisionObject;
import org.joml.Vector3f;

public class PhysicsObject {

    private CollisionObject collisionObject;
    private Vector3f netForce;
    private Vector3f velocity;
    private float mass;

    private float gravityAcceleration = -0.00098f;
    private boolean gravityEnabled;
    
    private float timeDamper;

    public PhysicsObject(CollisionObject collisionObject, float mass) {
        this.collisionObject = collisionObject;
        this.mass = mass;

        netForce = new Vector3f(0, 0, 0);
        velocity = new Vector3f(0, 0, 0);
        timeDamper = 5f;
    }

    public void tick(double delta) {
        delta = delta / timeDamper;
        //Updates velocity vector
        Vector3f acceleration = new Vector3f(netForce).div(mass);
        Vector3f dv = new Vector3f(acceleration).mul((float) delta);
        velocity.add(dv);

        //Updates position - adjusts for collisions
        boolean collision = collisionObject.translatePhysicallyX(velocity.x * (float) delta);
        if (collision) {
            velocity.x = 0;
        }
        collision = collisionObject.translatePhysicallyY(velocity.y * (float) delta);
        if (collision) {
            velocity.y = 0;
        }
        collision = collisionObject.translatePhysicallyZ(velocity.z * (float) delta);
        if (collision) {
            velocity.z = 0;
        }
    }

    public void applyForce(Vector3f force) {
        netForce.add(force);
    }

    public void setGravityEnabled(boolean enabled) {
        if (enabled && gravityEnabled == false) {
            applyForce(new Vector3f(0, gravityAcceleration * mass, 0));
            gravityEnabled = true;
        } else if (enabled == false && gravityEnabled == true) {
            applyForce(new Vector3f(0, -gravityAcceleration * mass, 0));
            gravityEnabled = false;
        }
    }

    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    public CollisionObject getCollisionObject() {
        return collisionObject;
    }

    public void setCollisionObject(CollisionObject collisionObject) {
        this.collisionObject = collisionObject;
    }

    public Vector3f getNetForce() {
        return netForce;
    }

    public void setNetForce(Vector3f netForce) {
        this.netForce = netForce;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public Vector3f getLocation() {
        return collisionObject.getLocation();
    }

    public void setLocation(Vector3f location) {
        collisionObject.setLocation(location);
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }

    public float getGravityAcceleration() {
        return gravityAcceleration;
    }

    public void setGravityAcceleration(float gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration;
    }

    public float getTimeDamper() {
        return timeDamper;
    }

    public void setTimeDamper(float timeDamper) {
        this.timeDamper = timeDamper;
    }
}
