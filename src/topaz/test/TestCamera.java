package topaz.test;

import topaz.assets.Texture;
import topaz.core.CoreEngine;
import topaz.core.CoreUser;
import topaz.rendering.objects.Box;
import topaz.util.Color4f;

public class TestCamera extends CoreUser {

    private static String TITLE = "GameEngine Test";
    private static int WIDTH = 1000, HEIGHT = 800;
    private float speed = 0.01f;

    public static void main(String[] args) {
        CoreEngine core = new CoreEngine(new TestCamera(), TITLE, WIDTH, HEIGHT);
        core.setPrintVersionData(false);
        core.setPrintFramesPerSecond(false);
        core.start();
    }

    @Override
    public void init() {
        Box box1 = new Box(1, 1, 1);
        box1.setTextures(new Texture("/topaz/assets/textures/testPicture.png"));
        box1.setLocation(0, 0, 0);
        box1.generate();

        Box box2 = new Box(1, 1, 1);
        box2.setColor(Color4f.CYAN);
        box2.setLocation(-2, 0, 0);
        box2.generate();
    }

    @Override
    public void tick(double delta) {
        if (keyManager.KEY_W) {
            camera.translate(camera.getForward().mul((float) delta).mul(speed));
        } else if (keyManager.KEY_S) {
            camera.translate(camera.getForward().mul((float) delta).mul(speed).negate());
        }
        if (keyManager.KEY_A) {
            camera.translate(camera.getRight().mul((float) delta).mul(speed).negate());
        } else if (keyManager.KEY_D) {
            camera.translate(camera.getRight().mul((float) delta).mul(speed));
        }
    }
}
