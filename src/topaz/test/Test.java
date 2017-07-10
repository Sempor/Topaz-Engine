package topaz.test;

import topaz.assets.Texture;
import topaz.core.CoreSystem;
import topaz.core.CoreUser;
import topaz.rendering.objects.Box;
import topaz.util.Color4f;

public class Test extends CoreUser {

    private static String TITLE = "GameEngine Test";
    private static int WIDTH = 640, HEIGHT = 480;
    private Box box;

    public static void main(String[] args) {
        CoreSystem core = new CoreSystem(new Test(), TITLE, WIDTH, HEIGHT);
        core.setPrintVersionData(false);
        core.setPrintFramesPerSecond(false);
        core.start();
    }

    @Override
    public void init() {
        box = new Box(-0.5f, -0.5f, -0.5f, 1, 1, 1);
        box.setColor(Color4f.CYAN);
        box.setTextures(new Texture("/topaz/assets/textures/testPicture.png"));
        box.generate();
    }

    @Override
    public void tick(double delta) {
        if (keyManager.KEY_W) {
            camera.translate(0, 0, 0.05f);
        }
        if (keyManager.KEY_A) {
            camera.translate(0.05f, 0, 0);
        }
        if (keyManager.KEY_S) {
            camera.translate(0, 0, -0.05f);
        }
        if (keyManager.KEY_D) {
            camera.translate(-0.05f, 0, 0);
        }
        if (keyManager.KEY_Q) {
            camera.translate(0, -0.05f, 0);
        }
        if (keyManager.KEY_E) {
            camera.translate(0, 0.05f, 0);
        }
        if (keyManager.KEY_R) {
            box.rotate(0, 0.5f, 0);
        }
    }
}
