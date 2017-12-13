package topaz.test;

import topaz.core.CoreEngine;
import topaz.core.CoreApp;
import topaz.rendering.GameObject;
import topaz.rendering.meshes.Mesh_Box;
import topaz.util.Color;

public class TestCamera extends CoreApp {
    
    private static int WIDTH = 1000, HEIGHT = 800;
    private float speed = 0.01f;
    
    public static void main(String[] args) {
        CoreEngine core = new CoreEngine(new TestCamera(), WIDTH, HEIGHT);
        core.enablePrintSoftwareInformation(false);
        core.enablePrintFPS(false);
        core.start();
    }
    
    @Override
    public void init() {
        GameObject box = new GameObject("Box");
        box.setMesh(new Mesh_Box());
        box.setLocation(1, 1, 1);
        box.setColor(Color.RED);
        box.setVisible(true);
        rootObject.attachChild(box);
    }
    
    @Override
    public void tick(double delta) {
        if (keyManager.KEY_W.isPressed()) {
            camera.translate(camera.getForward().mul((float) delta).mul(speed));
        } else if (keyManager.KEY_S.isPressed()) {
            camera.translate(camera.getBackward().mul((float) delta).mul(speed));
        }
        if (keyManager.KEY_A.isPressed()) {
            camera.translate(camera.getLeft().mul((float) delta).mul(speed));
        } else if (keyManager.KEY_D.isPressed()) {
            camera.translate(camera.getRight().mul((float) delta).mul(speed));
        }
    }
}
