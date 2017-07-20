package topaz.game;

import org.joml.Vector3f;
import topaz.input.KeyManager;
import topaz.physics.CollisionObject;
import topaz.rendering.Camera;

public class BasicPlayer {

    private KeyManager keyManager;
    private Camera camera;

    private CollisionObject collisionObject;

    private float gravityAcceleration;
    private float jumpVelocity;
    private float moveSpeed;

    private float verticalVelocity = 0f;

    private boolean useDefaultInput = true;

    private int health;

    public BasicPlayer(KeyManager keyManager, Camera camera, CollisionObject collisionObject) {
        this.keyManager = keyManager;
        this.camera = camera;

        this.collisionObject = collisionObject;
        this.collisionObject.setCenter(camera.getLocation());
        this.collisionObject.setActive(true);

        gravityAcceleration = -0.00098f;
        jumpVelocity = 0.1f;
        moveSpeed = 0.002f;

        health = 10;
    }

    public void tick(double delta) {
        applyGravity(delta);

        if (useDefaultInput) {
            if (keyManager.KEY_W.isPressed()) {
                move(camera.getForward().mul((float) delta).mul(moveSpeed));
            } else if (keyManager.KEY_S.isPressed()) {
                move(camera.getBackward().mul((float) delta).mul(moveSpeed));
            }
            if (keyManager.KEY_A.isPressed()) {
                move(camera.getLeft().mul((float) delta).mul(moveSpeed));
            } else if (keyManager.KEY_D.isPressed()) {
                move(camera.getRight().mul((float) delta).mul(moveSpeed));
            }

            if (keyManager.KEY_SPACE.isPressed()) {
                jump();
            }
        }
    }

    private void applyGravity(double delta) {
        collisionObject.y += verticalVelocity;
        CollisionObject collidingObject = collisionObject.checkForCollisions();
        if (collidingObject != null) {
            collisionObject.y = collidingObject.y + collidingObject.getHeight();
            camera.setLocation(collisionObject.getCenter());
            verticalVelocity = 0;
        } else {
            camera.translate(0, verticalVelocity, 0);
            verticalVelocity += gravityAcceleration * delta;
        }
    }

    public void move(float dx, float dy, float dz) {
        move(new Vector3f(dx, dy, dz));
    }

    public void move(Vector3f translation) {
        //Move in x direction
        collisionObject.x += translation.x;
        CollisionObject collidingObject = collisionObject.checkForCollisions();
        if (collidingObject != null) {
            if (translation.x > 0) {
                collisionObject.x = collidingObject.x - collisionObject.getWidth();
            } else {
                collisionObject.x = collidingObject.x + collidingObject.getWidth();
            }
            camera.setLocation(collisionObject.getCenter());
        } else {
            camera.translate(translation.x, 0, 0);
        }

        //Move in z direction
        collisionObject.z += translation.z;
        collidingObject = collisionObject.checkForCollisions();
        if (collidingObject != null) {
            if (translation.z > 0) {
                collisionObject.z = collidingObject.z - collisionObject.getDepth();
            } else {
                collisionObject.z = collidingObject.z + collidingObject.getDepth();
            }
            camera.setLocation(collisionObject.getCenter());
        } else {
            camera.translate(0, 0, translation.z);
        }
    }

    public void jump() {
        if (verticalVelocity != 0) {
            return;
        }
        verticalVelocity = jumpVelocity;
    }

    public void enableDefaultInput(boolean toggle) {
        useDefaultInput = toggle;
    }

    public void setLocation(float x, float y, float z) {
        camera.setLocation(x, y, z);
        collisionObject.setCenter(camera.getLocation());
    }

    public Vector3f getLocation() {
        return collisionObject.getLocation();
    }

    public float getGravityAcceleration() {
        return gravityAcceleration;
    }

    public void setGravityAcceleration(float gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration;
    }

    public float getJumpVelocity() {
        return jumpVelocity;
    }

    public void setJumpVelocity(float jumpVelocity) {
        this.jumpVelocity = jumpVelocity;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
