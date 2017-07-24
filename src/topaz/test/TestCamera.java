package topaz.test;

import topaz.core.CoreEngine;
import topaz.core.CoreApp;
import topaz.rendering.GameObject;
import topaz.rendering.objects.ColoredBox;
import topaz.util.Color4f;

public class TestCamera extends CoreApp {

    private static int WIDTH = 1000, HEIGHT = 800;
    private float speed = 0.01f;
    private ColoredBox box1, box2;

    public static void main(String[] args) {
        CoreEngine core = new CoreEngine(new TestCamera(), WIDTH, HEIGHT);
        core.setPrintSoftwareInformation(false);
        core.setPrintFPS(false);
        core.start();
    }

    @Override
    public void init() {
        box1 = new ColoredBox(objectManager, 1, 1, 1, Color4f.RED);
        box1.getGameObject().setLocation(0, 0, 0);
        box1.getGameObject().setVisible(true);
        objectManager.add(box1.getGameObject());

        box2 = new ColoredBox(objectManager, 1, 1, 1, Color4f.CYAN);
        box2.getGameObject().setLocation(-2, 0, 0);
        box2.getGameObject().setVisible(true);
        objectManager.add(box2.getGameObject());
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
        if (keyManager.KEY_C.isPressed()) {
            box1.setColor(Color4f.YELLOW);
        }
    }
}
