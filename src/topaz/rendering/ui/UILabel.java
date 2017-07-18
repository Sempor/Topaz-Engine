package topaz.rendering.ui;

public class UILabel extends UIObject {

    private String text;

    public UILabel(int x, int y, int width, int height, int renderOrder, String text) {
        super(x, y, width, height, renderOrder);
        this.text = text;
    }

    @Override
    public void tick(double delta) {
    }

    //Need to render text on the screen still
    @Override
    public void render() {
    }

    @Override
    public void onClick() {
    }
}
