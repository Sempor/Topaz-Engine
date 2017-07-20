package topaz.game;

import org.joml.Vector3f;
import topaz.input.KeyManager;
import topaz.physics.PhysicalObject;
import topaz.rendering.Camera;

public class BasicPlayer {

    private KeyManager keyManager;
    private Camera camera;

    private PhysicalObject physicalObject;
    private float jumpVelocity;
    private float moveSpeed;

    private boolean useDefaultInput = true;

    private int health;

    public BasicPlayer(KeyManager keyManager, Camera camera, PhysicalObject physicalObject) {
        this.keyManager = keyManager;
        this.camera = camera;

        this.physicalObject = physicalObject;
        this.physicalObject.getCollisionObject().setCenter(camera.getLocation());
        this.physicalObject.getCollisionObject().setActive(true);
        this.physicalObject.setGravityEnabled(true);

        jumpVelocity = 0.03f;
        moveSpeed = 0.002f;

        health = 10;
    }

    public void tick(double delta) {
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

        camera.setLocation(physicalObject.getCollisionObject().getCenter());
    }

    public void move(float dx, float dy, float dz) {
        move(new Vector3f(dx, dy, dz));
    }

    public void move(Vector3f translation) {
        physicalObject.getCollisionObject().translatePhysicallyX(translation.x);
        physicalObject.getCollisionObject().translatePhysicallyZ(translation.z);
    }

    public void jump() {
        if (physicalObject.getVelocity().y != 0) {
            return;
        }
        physicalObject.setVelocity(new Vector3f(0, jumpVelocity, 0));
    }

    public void enableDefaultInput(boolean toggle) {
        useDefaultInput = toggle;
    }

    public void setLocation(float x, float y, float z) {
        physicalObject.getCollisionObject().setLocation(x, y, z);
        camera.setLocation(physicalObject.getCollisionObject().getCenter());
    }

    public Vector3f getLocation() {
        return physicalObject.getCollisionObject().getLocation();
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
