package topaz.game;

import org.joml.Vector3f;
import topaz.input.KeyManager;
import topaz.physics.AxisAlignedBoundingBox;
import topaz.rendering.Camera;

public class BasicPlayer {

    private KeyManager keyManager;
    private Camera camera;

    private AxisAlignedBoundingBox boundingBox;

    private float gravityAcceleration;
    private float jumpVelocity;
    private float moveSpeed;

    private float verticalVelocity = 0f;

    private boolean useDefaultInput = true;

    public BasicPlayer(KeyManager keyManager, Camera camera, AxisAlignedBoundingBox boundingBox) {
        this.keyManager = keyManager;
        this.camera = camera;

        this.boundingBox = boundingBox;
        this.boundingBox.centerOn(camera.getLocation());
        this.boundingBox.setActive(true);

        gravityAcceleration = -0.0003f;
        jumpVelocity = 0.06f;
        moveSpeed = 0.003f;
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
        boundingBox.y += verticalVelocity;
        if (boundingBox.checkBoundingBoxCollisions()) {
            boundingBox.centerOn(camera.getLocation());
            verticalVelocity = 0;
        } else {
            camera.translateY(verticalVelocity);
            verticalVelocity += gravityAcceleration * delta;
        }
    }

    public void move(float dx, float dy, float dz) {
        move(new Vector3f(dx, dy, dz));
    }

    public void move(Vector3f translation) {
        //Move in x direction
        boundingBox.x += translation.x;
        if (boundingBox.checkBoundingBoxCollisions()) {
            boundingBox.centerOn(camera.getLocation());
        } else {
            camera.translateX(translation.x);
        }

        //Move in z direction
        boundingBox.z += translation.z;
        if (boundingBox.checkBoundingBoxCollisions()) {
            boundingBox.centerOn(camera.getLocation());
        } else {
            camera.translateZ(translation.z);
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
        boundingBox.centerOn(camera.getLocation());
    }

    public Vector3f getLocation() {
        return camera.getLocation();
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
}
