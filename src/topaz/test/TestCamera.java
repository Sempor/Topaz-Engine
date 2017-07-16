package topaz.test;

import topaz.core.CoreEngine;
import topaz.core.CoreUser;
import topaz.rendering.GameObject;
import topaz.rendering.objects.Box;
import topaz.util.Color4f;

public class TestCamera extends CoreUser {

    private static int WIDTH = 1000, HEIGHT = 800;
    private float speed = 0.01f;

    public static void main(String[] args) {
        CoreEngine core = new CoreEngine(new TestCamera(), WIDTH, HEIGHT);
        core.enablePrintVersionData(false);
        core.enablePrintFramesPerSecond(false);
        core.start();
    }

    @Override
    public void init() {
        GameObject box1 = new Box(renderManager, physicsManager, 1, 1, 1, Color4f.RED).getGameObject();
        box1.setLocation(0, 0, 0);
        box1.setVisible(true);
        objectManager.add(box1);

        GameObject box2 = new Box(renderManager, physicsManager, 1, 1, 1, Color4f.CYAN).getGameObject();
        box2.setLocation(-2, 0, 0);
        box2.setVisible(true);
        objectManager.add(box2);
    }

    @Override
    public void tick(double delta) {
        if (keyManager.KEY_W) {
            camera.translate(camera.getForward().mul((float) delta).mul(speed));
        } else if (keyManager.KEY_S) {
            camera.translate(camera.getBackward().mul((float) delta).mul(speed));
        }
        if (keyManager.KEY_A) {
            camera.translate(camera.getLeft().mul((float) delta).mul(speed));
        } else if (keyManager.KEY_D) {
            camera.translate(camera.getRight().mul((float) delta).mul(speed));
        }
    }
}
