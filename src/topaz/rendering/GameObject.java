package topaz.rendering;

import java.util.ArrayList;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import topaz.assets.AssetLoader;
import topaz.physics.PhysicsObject;
import topaz.util.Color;

public class GameObject {

    private String name;
    private float x, y, z;
    private float rotateX, rotateY, rotateZ;
    private float scaleX = 1f, scaleY = 1f, scaleZ = 1f;
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
        translate(translation.x, translation.y, translation.z);
    }

    public void translate(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;

        if (physicsObject != null) {
            physicsObject.getCollisionObject().translate(dx, dy, dz);
        }

        for (int i = 0; i < children.size(); i++) {
            children.get(i).translate(dx, dy, dz);
        }
    }

    public void rotate(Vector3f rotation) {
        rotate(rotation.x, rotation.y, rotation.z);
    }

    //Bounding box doesn't work that well with rotations
    public void rotate(float dx, float dy, float dz) {
        rotateX += dx;
        rotateY += dy;
        rotateZ += dz;

        for (int i = 0; i < children.size(); i++) {
            children.get(i).rotate(dx, dy, dz);
        }
    }

    public void scale(Vector3f scale) {
        scale(scale.x, scale.y, scale.z);
    }

    public void scale(float dx, float dy, float dz) {
        scaleX += dx;
        scaleY += dy;
        scaleZ += dz;

        if (physicsObject != null) {
            physicsObject.getCollisionObject().scale(dx, dy, dz);
        }

        for (int i = 0; i < children.size(); i++) {
            children.get(i).scale(dx, dy, dz);
        }
    }

    public void setLocation(Vector3f location) {
        setLocation(location.x, location.y, location.z);
    }

    public void setLocation(float x, float y, float z) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getLocation().sub(x, y, z));
            children.get(i).setLocation(x, y, z);
            children.get(i).translate(separationVector);
        }

        this.x = x;
        this.y = y;
        this.z = z;

        if (physicsObject != null) {
            physicsObject.setLocation(new Vector3f(x, y, z));
        }
    }

    public Vector3f getLocation() {
        return new Vector3f(x, y, z);
    }

    public void setRotation(Vector3f rotation) {
        setRotation(rotation.x, rotation.y, rotation.z);
    }

    //Bounding box doesn't work that well with rotations
    public void setRotation(float rotateX, float rotateY, float rotateZ) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getRotation().sub(rotateX, rotateY, rotateZ));
            children.get(i).setRotation(rotateX, rotateY, rotateZ);
            children.get(i).rotate(separationVector);
        }

        this.rotateX = rotateX;
        this.rotateY = rotateY;
        this.rotateZ = rotateZ;
    }

    public Vector3f getRotation() {
        return new Vector3f(rotateX, rotateY, rotateZ);
    }

    public void setScale(Vector3f scale) {
        setScale(scale.x, scale.y, scale.z);
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getScale().sub(scaleX, scaleY, scaleZ));
            children.get(i).setScale(scaleX, scaleY, scaleZ);
            children.get(i).scale(separationVector);
        }

        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;

        if (physicsObject != null) {
            physicsObject.getCollisionObject().setScale(scaleX, scaleY, scaleZ);
        }
    }

    public Vector3f getScale() {
        return new Vector3f(scaleX, scaleY, scaleZ);
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
        this.physicsObject.setLocation(x, y, z);
        this.physicsObject.setScale(new Vector3f(scaleX, scaleY, scaleZ));
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
        modelMatrix.scale(scaleX, scaleY, scaleZ);
        modelMatrix.translate(x, y, z);
        modelMatrix.rotate((float) Math.toRadians(rotateZ), 0, 0, 1);
        modelMatrix.rotate((float) Math.toRadians(rotateY), 0, 1, 0);
        modelMatrix.rotate((float) Math.toRadians(rotateX), 1, 0, 0);
        return modelMatrix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
