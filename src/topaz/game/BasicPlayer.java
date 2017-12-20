package topaz.game;

import org.joml.Vector3f;
import topaz.core.Input;
import topaz.physics.CollisionObject;
import topaz.rendering.Camera;
import topaz.rendering.GameObject;

public class BasicPlayer {

    public static final int DEFAULT_KEY_CONTROLS = 100;

    private Input input;
    private Camera camera;

    private GameObject playerObject;
    private float jumpVelocity = 0.1f;
    private float moveSpeed = 10f;
    private Vector3f addedVelocity = new Vector3f(0, 0, 0);
    private boolean defaultKeyControls;

    public BasicPlayer(Input input, Camera camera, GameObject rootObject, CollisionObject collisionObject) {
        this.input = input;
        this.camera = camera;

        playerObject = new GameObject("BasicPlayer");
        playerObject.setLocation(new Vector3f(camera.getLocation()).sub(collisionObject.getDimensions().div(2)));
        playerObject.setCollisionObject(collisionObject);
        playerObject.setGravityAcceleration(-0.016f);
        playerObject.enable(GameObject.ENABLE_GRAVITY);
        playerObject.getCollisionObject().setActive(true);
        playerObject.setVisible(true);
        rootObject.addChild(playerObject);
    }

    public void tick(double delta) {
        if (defaultKeyControls) {
            playerObject.setVelocity(addedVelocity.negate());
            addedVelocity = new Vector3f(0, 0, 0);

            if (input.KEY_W.isPressed()) {
                move(camera.getForward().mul((float) delta).mul(moveSpeed));
            } else if (input.KEY_S.isPressed()) {
                move(camera.getBackward().mul((float) delta).mul(moveSpeed));
            }
            if (input.KEY_A.isPressed()) {
                move(camera.getLeft().mul((float) delta).mul(moveSpeed));
            } else if (input.KEY_D.isPressed()) {
                move(camera.getRight().mul((float) delta).mul(moveSpeed));
            }
            if (input.KEY_SPACE.isPressed()) {
                jump();
            }

            playerObject.addVelocity(addedVelocity);
        }

        camera.setLocation(playerObject.getCollisionObject().getCenter());
    }

    public void move(Vector3f translation) {
        addedVelocity.add(translation.x, 0, translation.z);
    }

    public void jump() {
        if (playerObject.getVelocity().y != 0) {
            return;
        }
        playerObject.getVelocity().y = jumpVelocity;
    }

    public void enable(int property) {
        switch (property) {
            case DEFAULT_KEY_CONTROLS:
                defaultKeyControls = true;
                break;
            default:
                break;
        }
    }

    public void disable(int property) {
        switch (property) {
            case DEFAULT_KEY_CONTROLS:
                defaultKeyControls = false;
                break;
            default:
                break;
        }
    }

    public void setLocation(float x, float y, float z) {
        playerObject.setLocation(x, y, z);
        camera.setLocation(playerObject.getCollisionObject().getCenter());
    }

    public Vector3f getLocation() {
        return playerObject.getLocation();
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
