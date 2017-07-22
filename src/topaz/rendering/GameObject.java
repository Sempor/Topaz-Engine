package topaz.rendering;

import java.util.ArrayList;
import org.joml.Vector3f;
import topaz.physics.PhysicsObject;
import topaz.physics.PhysicsManager;

public class GameObject {

    private ObjectManager objectManager;
    private RenderManager renderManager;
    private PhysicsManager physicsManager;

    private Mesh mesh;

    private PhysicsObject physicsObject;
    private boolean collisionsEnabled;

    private ArrayList<GameObject> children = new ArrayList<>();

    public GameObject(ObjectManager objectManager, RenderManager renderManager, PhysicsManager physicsManager, Mesh mesh) {
        this.objectManager = objectManager;
        this.renderManager = renderManager;
        this.physicsManager = physicsManager;

        this.mesh = mesh;
    }

    //Currently shallow copies, maybe make deep copy later
    public GameObject(ObjectManager objectManager, RenderManager renderManager, PhysicsManager physicsManager,
            Mesh mesh, PhysicsObject physicsObject) {
        this(objectManager, renderManager, physicsManager, mesh);

        this.physicsObject = physicsObject;
    }

    public void removeFromAllManagers() {
        objectManager.remove(this);
        renderManager.remove(mesh);
        physicsManager.remove(physicsObject);
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

    public void translate(Vector3f translation) {
        translate(translation.x, translation.y, translation.z);
    }

    public void translate(float dx, float dy, float dz) {
        mesh.translate(dx, dy, dz);
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
        mesh.rotate(dx, dy, dz);

        for (int i = 0; i < children.size(); i++) {
            children.get(i).rotate(dx, dy, dz);
        }
    }

    public void scale(Vector3f scale) {
        scale(scale.x, scale.y, scale.z);
    }

    public void scale(float dx, float dy, float dz) {
        mesh.scale(dx, dy, dz);
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
            Vector3f separationVector = new Vector3f(children.get(i).getLocation().sub(mesh.getLocation()));
            children.get(i).setLocation(x, y, z);
            children.get(i).translate(separationVector);
        }

        mesh.setLocation(x, y, z);
        if (physicsObject != null) {
            physicsObject.setLocation(new Vector3f(x, y, z));
        }
    }

    public Vector3f getLocation() {
        return mesh.getLocation();
    }

    public void setRotation(Vector3f rotation) {
        setRotation(rotation.x, rotation.y, rotation.z);
    }

    //Bounding box doesn't work that well with rotations
    public void setRotation(float x, float y, float z) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getRotation().sub(mesh.getRotation()));
            children.get(i).setRotation(x, y, z);
            children.get(i).rotate(separationVector);
        }

        mesh.setRotation(x, y, z);
    }

    public Vector3f getRotation() {
        return mesh.getRotation();
    }

    public void setScale(Vector3f scale) {
        setScale(scale.x, scale.y, scale.z);
    }

    public void setScale(float x, float y, float z) {
        for (int i = 0; i < children.size(); i++) {
            Vector3f separationVector = new Vector3f(children.get(i).getScale().sub(mesh.getScale()));
            children.get(i).setScale(x, y, z);
            children.get(i).scale(separationVector);
        }

        mesh.setScale(x, y, z);
        if (physicsObject != null) {
            physicsObject.getCollisionObject().setScale(x, y, z);
        }
    }

    public Vector3f getScale() {
        return mesh.getScale();
    }

    public void setCollisionsEnabled(boolean enabled) {
        this.collisionsEnabled = enabled;
        if (physicsObject != null) {
            physicsObject.getCollisionObject().setActive(enabled);
        }
    }

    public boolean isVisible() {
        return mesh.isVisible();
    }

    public void setVisible(boolean visible) {
        mesh.setVisible(visible);
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
        this.physicsObject.setLocation(mesh.getLocation());
        this.physicsObject.setScale(mesh.getScale());
        if (collisionsEnabled) {
            physicsObject.getCollisionObject().setActive(true);
        }
    }
}
