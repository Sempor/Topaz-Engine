package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import topaz.assets.AssetLoader;
import topaz.physics.PhysicsObject;
import topaz.util.Color;

public class GameObject {

    private String name;
    private Vector3f location = new Vector3f(0, 0, 0);
    private Vector3f rotation = new Vector3f(0, 0, 0);
    private Vector3f scale = new Vector3f(1, 1, 1);
    private int[] textureIDs;
    private int selector;
    private boolean visible = true;

    private Mesh mesh;

    private PhysicsObject physicsObject;
    private boolean collisionsEnabled;

    private Vector3f ambientLightIntensity = new Vector3f(0.1f, 0.1f, 0.1f);

    private ArrayList<GameObject> children = new ArrayList<>();

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
    }

    //Currently shallow copies, maybe make deep copy later
    public GameObject(Mesh mesh, PhysicsObject physicsObject) {
        this(mesh);
        this.physicsObject = physicsObject;
    }

    public GameObject(String name, Mesh mesh) {
        this.name = name;
        this.mesh = mesh;
    }

    //Currently shallow copies, maybe make deep copy later
    public GameObject(String name, Mesh mesh, PhysicsObject physicsObject) {
        this(name, mesh);
        this.physicsObject = physicsObject;
    }

    public void attachChild(GameObject child) {
        if (child == null) {
            return;
        }
        children.add(child);
    }

    public void removeChild(GameObject child) {
        if (child == null) {
            return;
        }
        children.remove(child);
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

    public void setTextures(String... textureFilePaths) {
        textureIDs = new int[textureFilePaths.length];

        for (int i = 0; i < textureFilePaths.length; i++) {
            textureIDs[i] = AssetLoader.loadPNGTexture(textureFilePaths[i]);
        }
    }

    public void translate(Vector3f translation) {
        location.add(translation);

        if (physicsObject != null) {
            physicsObject.getCollisionObject().translate(translation);
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

        if (physicsObject != null) {
            physicsObject.getCollisionObject().scale(scale);
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

        if (physicsObject != null) {
            physicsObject.setLocation(new Vector3f(location));
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

        if (physicsObject != null) {
            physicsObject.getCollisionObject().setScale(new Vector3f(scale));
        }
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        setScale(new Vector3f(scaleX, scaleY, scaleZ));
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setCollisionsEnabled(boolean enabled) {
        this.collisionsEnabled = enabled;
        if (physicsObject != null) {
            physicsObject.getCollisionObject().setActive(enabled);
        }
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

    public PhysicsObject getPhysicsObject() {
        return physicsObject;
    }

    public void setPhysicsObject(PhysicsObject physicsObject) {
        this.physicsObject = physicsObject;
        this.physicsObject.setLocation(location);
        this.physicsObject.setScale(scale);
        if (collisionsEnabled) {
            physicsObject.getCollisionObject().setActive(true);
        }
    }

    public boolean setSelectedTexture(int selector) {
        if (selector < textureIDs.length && selector >= 0 && textureIDs != null) {
            this.selector = selector;
            return true;
        }
        return false;
    }

    public int getSelectedTexture() {
        if (textureIDs != null) {
            return textureIDs[selector];
        } else {
            return -1;
        }
    }

    public Matrix4f getModelMatrix() {
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
}
