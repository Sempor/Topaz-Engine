package topaz.test;

import topaz.core.CoreEngine;
import topaz.core.CoreApp;
import topaz.rendering.GameObject;
import topaz.rendering.meshes.Mesh_Box;

public class TestCamera extends CoreApp {

    private static int WIDTH = 1000, HEIGHT = 800;
    private float speed = 0.01f;
    private GameObject boxTextured;

    public static void main(String[] args) {
        CoreEngine core = new CoreEngine(new TestCamera(), WIDTH, HEIGHT);
        core.disable(CoreEngine.PRINT_FPS);
        core.start();
    }

    @Override
    public void init() {
        /*GameObject boxColored = new GameObject("ColoredBox");
        boxColored.setMesh(new Mesh_Box());
        boxColored.setLocation(-1, 1, -1);
        boxColored.setColor(Color.RED);
        boxColored.setVisible(true);
        rootObject.addChild(boxColored);*/

        boxTextured = new GameObject("TexturedBox");
        boxTextured.setMesh(new Mesh_Box());
        boxTextured.setLocation(1, 1, 1);
        boxTextured.setTexture("/topaz/assets/textures/testPicture.png");
        boxTextured.setVisible(true);
        rootObject.addChild(boxTextured);
    }

    @Override
    public void tick(double delta) {
        if (input.KEY_W.isPressed()) {
            camera.translate(camera.getForward().mul((float) delta).mul(speed));
        } else if (input.KEY_S.isPressed()) {
            camera.translate(camera.getBackward().mul((float) delta).mul(speed));
        }
        if (input.KEY_A.isPressed()) {
            camera.translate(camera.getLeft().mul((float) delta).mul(speed));
        } else if (input.KEY_D.isPressed()) {
            camera.translate(camera.getRight().mul((float) delta).mul(speed));
        }
    }
}
