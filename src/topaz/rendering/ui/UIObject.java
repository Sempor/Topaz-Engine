package topaz.rendering.ui;

import java.awt.Rectangle;
import topaz.core.Display;

public abstract class UIObject {

    protected int x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected int renderOrder;
    protected float mouseX, mouseY;
    protected boolean hovering = false;
    protected boolean active = true;
    protected boolean visible = false;

    public UIObject(int x, int y, int width, int height, int renderOrder) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.renderOrder = renderOrder;
        bounds = new Rectangle(x, y, width, height);
    }

    public abstract void tick(double delta);

    public abstract void render();

    public abstract void onClick();

    public void onMouseMove(Display display) {
        mouseX = (float) display.getCursorX();
        mouseY = (float) display.getCursorY();
        hovering = bounds.contains(mouseX, mouseY);
    }

    public void onMouseRelease() {
        if (hovering) {
            onClick();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        bounds = new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        bounds = new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        bounds = new Rectangle(x, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isHovering() {
        return hovering;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getRenderOrder() {
        return renderOrder;
    }

    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }
}
