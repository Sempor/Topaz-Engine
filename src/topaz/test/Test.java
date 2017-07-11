package topaz.test;

import topaz.assets.Texture;
import topaz.core.CoreSystem;
import topaz.core.CoreUser;
import topaz.rendering.objects.Box;
import topaz.util.Color4f;

public class Test extends CoreUser {

    private static String TITLE = "GameEngine Test";
    private static int WIDTH = 640, HEIGHT = 480;
    private Box box1, box2;

    public static void main(String[] args) {
        CoreSystem core = new CoreSystem(new Test(), TITLE, WIDTH, HEIGHT);
        core.setPrintVersionData(false);
        core.setPrintFramesPerSecond(false);
        core.start();
    }

    @Override
    public void init() {
        box1 = new Box(1, 1, 1);
        box1.setTextures(new Texture("/topaz/assets/textures/testPicture.png"));
        box1.setLocation(2, 0, 0);
        box1.generate();
        
        box2 = new Box(1, 1, 1);
        box2.setColor(Color4f.CYAN);
        box2.setLocation(-2, 0, 0);
        box2.generate();
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
            camera.rotate(0, 0.5f, 0);
        }
    }
}
