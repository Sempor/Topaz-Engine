package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import topaz.assets.AssetLoader;
import topaz.core.Display;
import topaz.physics.CollisionObject;
import topaz.util.Color;

public class GameObject {

    public static final int ENABLE_COLLISIONS = 100;
    public static final int ENABLE_GRAVITY = 101;

    //Basic properties
    protected String name;
    protected ArrayList<GameObject> children = new ArrayList<>();
    protected Vector3f location = new Vector3f(0, 0, 0);
    protected Vector3f rotation = new Vector3f(0, 0, 0);
    protected Vector3f scale = new Vector3f(1, 1, 1);
    protected int textureID = -1;
    //Rendering
    protected Mesh mesh;
    protected boolean visible;
    //Physics
    protected Vector3f velocity = new Vector3f(0, 0, 0);
    protected Vector3f acceleration = new Vector3f(0, 0, 0);
    protected CollisionObject collisionObject;
    protected boolean collisionsEnabled;
    protected boolean gravityEnabled;
    protected float gravityAcceleration = -98f;
    //Lighting
    protected Vector3f ambientLightIntensity = new Vector3f(0.1f, 0.1f, 0.1f);

    public GameObject() {
    }

    public GameObject(String name) {
        this.name = name;
    }

    public void tick(double delta, Display display) {
        double elapsedSeconds = delta / (double) display.getFPS();
        velocity.add(new Vector3f(acceleration).mul((float) elapsedSeconds));

        if (collisionObject != null) {
            boolean collision = collisionObject.translateX(velocity.x * (float) elapsedSeconds);
            if (collision) {
                velocity.x = 0;
            }
            collision = collisionObject.translateY(velocity.y * (float) elapsedSeconds);
            if (collision) {
                velocity.y = 0;
            }
            collision = collisionObject.translateZ(velocity.z * (float) elapsedSeconds);
            if (collision) {
                velocity.z = 0;
            }
        }
    }

    public void addChild(GameObject child) {
        if (child != null) {
            children.add(child);
        }
    }

    public void addChildren(GameObject... childs) {
        for (GameObject child : childs) {
            if (child != null) {
                children.add(child);
            }
        }
    }

    public void removeChild(GameObject child) {
        if (child != null) {
            children.remove(child);
        }
    }

    public void removeChild(String name) {
        if (name != null) {
            for (GameObject child : children) {
                if (name.equals(child.getName())) {
                    children.remove(child);
                }
            }
        }
    }

    public void removeChildren(GameObject... childs) {
        for (GameObject child : childs) {
            if (child != null) {
                children.remove(child);
            }
        }
    }

    public void setColor(Color color) {
        float[] colors = new float[mesh.getVertices().length * 4 / 3];
        for (int i = 0; i < colors.length; i++) {
            switch (i % 4) {
                case 0:
                    colors[i] = color.r;
                    break;
                case 1:
                    colors[i] = color.g;
                    break;
                case 2:
                    colors[i] = color.b;
                    break;
                case 3:
                    colors[i] = color.a;
                    break;
                default:
                    break;
            }
        }
        mesh.setColor(colors);
    }

    public void setTexture(String textureFilePath) {
        textureID = AssetLoader.loadPNGTexture(textureFilePath);
    }

    public void translate(Vector3f translation) {
        location.add(translation);

        if (collisionObject != null) {
            collisionObject.translate(translation);
        }

        for (int i = 0; i < children.size(); i++) {
            children.get(i).translate(translation);
        }
    }

    public void translate(float dx, float dy, float dz) {
        translate(new Vector3f(dx, dy, dz));
    }

    public void rotate(Vector3f rotation) {
        this.rotation.add(rotation);

        for (int i = 0; i < children.size(); i++) {
            children.get(i).rotate(rotation);
        }
    }

    //Bounding box doesn't work that well with rotations
    public void rotate(float dx, float dy, float dz) {
        rotate(new Vector3f(dx, dy, dz));
    }

    public void scale(Vector3f scale) {
        this.scale.add(scale);

        if (collisionObject != null) {
            collisionObject.scale(scale);
        }

        for (int i = 0; i < children.size(); i++) {
            children.get(i).scale(scale);
        }
    }

    public void scale(float dx, float dy, float dz) {
        scale(new Vector3f(dx, dy, dz));
    }

    public void setLocation(Vector3f location) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getLocation().sub(location));
            children.get(i).setLocation(location);
            children.get(i).translate(separationVector);
        }

        this.location = new Vector3f(location);

        if (collisionObject != null) {
            collisionObject.setLocation(new Vector3f(location));
        }
    }

    public void setLocation(float x, float y, float z) {
        setLocation(new Vector3f(x, y, z));
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setRotation(Vector3f rotation) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getRotation().sub(rotation));
            children.get(i).setRotation(rotation);
            children.get(i).rotate(separationVector);
        }

        this.rotation = new Vector3f(rotation);
    }

    //Bounding box doesn't work that well with rotations
    public void setRotation(float rotateX, float rotateY, float rotateZ) {
        setRotation(new Vector3f(rotateX, rotateY, rotateZ));
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setScale(Vector3f scale) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getScale().sub(scale));
            children.get(i).setScale(scale);
            children.get(i).scale(separationVector);
        }

        this.scale = new Vector3f(scale);

        if (collisionObject != null) {
            collisionObject.setScale(new Vector3f(scale));
        }
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        setScale(new Vector3f(scaleX, scaleY, scaleZ));
    }

    public Vector3f getScale() {
        return scale;
    }

    public void enable(int property) {
        switch (property) {
            case ENABLE_COLLISIONS:
                collisionsEnabled = true;
                if (collisionObject != null) {
                    collisionObject.setActive(true);
                }
                break;
            case ENABLE_GRAVITY:
                if (!gravityEnabled) {
                    acceleration.add(0, gravityAcceleration, 0);
                    gravityEnabled = true;
                }
                break;
            default:
                break;
        }
    }

    public void disable(int property) {
        switch (property) {
            case ENABLE_COLLISIONS:
                collisionsEnabled = false;
                if (collisionObject != null) {
                    collisionObject.setActive(false);
                }
                break;
            case ENABLE_GRAVITY:
                if (gravityEnabled) {
                    acceleration.add(0, -gravityAcceleration, 0);
                    gravityEnabled = false;
                }
                break;
            default:
                break;
        }
    }

    public float getGravityAcceleration() {
        return gravityAcceleration;
    }

    public void setGravityAcceleration(float gravityAcceleration) {
        if (gravityEnabled) {
            acceleration.add(0, -this.gravityAcceleration, 0);
            acceleration.add(0, gravityAcceleration, 0);
        }
        this.gravityAcceleration = gravityAcceleration;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public CollisionObject getCollisionObject() {
        return collisionObject;
    }

    public void setCollisionObject(CollisionObject collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionObject.setLocation(location);
        this.collisionObject.setScale(scale);
        if (collisionsEnabled) {
            collisionObject.setActive(true);
        }
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3f velocity) {
        this.velocity = new Vector3f(velocity);
    }

    public void addVelocity(Vector3f velocity) {
        this.velocity.add(new Vector3f(velocity));
    }

    public Vector3f getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3f acceleration) {
        this.acceleration = new Vector3f(acceleration);
    }

    public void addAcceleration(Vector3f acceleration) {
        this.acceleration.add(new Vector3f(acceleration));
    }

    public int getTexture() {
        return textureID;
    }

    protected Matrix4f getModelMatrix() {
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.scale(scale);
        modelMatrix.translate(location);
        modelMatrix.rotate((float) Math.toRadians(rotation.z), 0, 0, 1);
        modelMatrix.rotate((float) Math.toRadians(rotation.y), 0, 1, 0);
        modelMatrix.rotate((float) Math.toRadians(rotation.x), 1, 0, 0);
        return modelMatrix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GameObject> getChildren() {
        return children;
    }

    public ArrayList<GameObject> getAllDescendants() {
        ArrayList<GameObject> descendants = new ArrayList<>();
        for (GameObject child : children) {
            descendants.add(child);
            descendants.addAll(child.getAllDescendants());
        }
        return descendants;
    }
}
