package topaz.rendering;

import topaz.physics.AxisAlignedBoundingBox;
import topaz.physics.PhysicsManager;

public class GameObject {

    private Mesh mesh;
    private AxisAlignedBoundingBox boundingBox;

    //Currently shallow copies, maybe make deep copy later
    public GameObject(Mesh mesh, AxisAlignedBoundingBox boundingBox) {
        this.mesh = mesh;
        this.boundingBox = boundingBox;
    }

    public void removeFromAllManagers(ObjectManager objectManager, RenderManager renderManager, PhysicsManager physicsManager) {
        objectManager.remove(this);
        renderManager.remove(mesh);
        physicsManager.remove(boundingBox);
    }

    public void translate(float x, float y, float z) {
        mesh.translate(x, y, z);
        boundingBox.translate(x, y, z);
    }

    //Bounding box doesn't work that well with rotations
    public void rotate(float x, float y, float z) {
        mesh.rotate(x, y, z);
    }

    public void scale(float x, float y, float z) {
        mesh.scale(x, y, z);
        boundingBox.scale(x, y, z);
    }

    public void setLocation(float x, float y, float z) {
        mesh.setLocation(x, y, z);
        boundingBox.setLocation(x, y, z);
    }

    //Bounding box doesn't work that well with rotations
    public void setRotation(float x, float y, float z) {
        mesh.setRotation(x, y, z);
    }

    public void setScale(float x, float y, float z) {
        mesh.setScale(x, y, z);
        boundingBox.setScale(x, y, z);
    }

    public void enableCollisions(boolean collisions) {
        boundingBox.setActive(collisions);
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

    public AxisAlignedBoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(AxisAlignedBoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }
}
