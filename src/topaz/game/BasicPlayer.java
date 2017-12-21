package topaz.game;

import org.joml.Vector3f;
import topaz.core.Display;
import topaz.core.Input;
import topaz.physics.CollisionObject;
import topaz.rendering.Camera;
import topaz.rendering.GameObject;

public class BasicPlayer extends GameObject {

    public static final int DEFAULT_KEY_CONTROLS = 100;

    private Input input;
    private Camera camera;

    private float jumpVelocity = 0.1f;
    private float moveSpeed = 10f;
    private Vector3f addedVelocity = new Vector3f(0, 0, 0);
    private boolean defaultKeyControls;

    public BasicPlayer(Input input, Camera camera, CollisionObject collisionObject) {
        super("BasicPlayer");
        this.input = input;
        this.camera = camera;

        super.setLocation(new Vector3f(camera.getLocation()).sub(collisionObject.getDimensions().div(2)));
        super.setCollisionObject(collisionObject);
        super.setGravityAcceleration(-0.016f);
        super.enable(GameObject.ENABLE_GRAVITY);
        super.getCollisionObject().setActive(true);
        super.setVisible(true);
    }

    @Override
    public void tick(double delta, Display display) {
        if (defaultKeyControls) {
            addVelocity(addedVelocity.negate());
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

            addVelocity(addedVelocity);
        }

        super.tick(delta, display);

        camera.setLocation(collisionObject.getCenter());
    }

    public void move(Vector3f translation) {
        addedVelocity.add(translation.x, 0, translation.z);
    }

    public void jump() {
        if (velocity.y != 0) {
            return;
        }
        velocity.y = jumpVelocity;
    }

    @Override
    public void enable(int property) {
        super.enable(property);
        switch (property) {
            case DEFAULT_KEY_CONTROLS:
                defaultKeyControls = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void disable(int property) {
        super.disable(property);
        switch (property) {
            case DEFAULT_KEY_CONTROLS:
                defaultKeyControls = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void setLocation(Vector3f location) {
        super.setLocation(location);
        camera.setLocation(collisionObject.getCenter());
    }
    
    @Override
    public void setLocation(float x, float y, float z) {
        super.setLocation(new Vector3f(x, y, z));
        camera.setLocation(collisionObject.getCenter());
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
