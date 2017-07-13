package topaz.rendering;

import topaz.physics.AxisAlignedBoundingBox;

public class GameObject {

    private Mesh mesh;

    private AxisAlignedBoundingBox boundingBox;

    public GameObject() {
    }

    //Currently shallow copies, maybe make deep copy later
    public GameObject(Mesh mesh) {
        this.mesh = mesh;
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
