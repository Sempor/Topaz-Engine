package topaz.rendering.ui;

import java.util.ArrayList;
import java.util.Collections;
import topaz.core.Display;

public class UIManager {

    private Display display;

    private ArrayList<UIObject> uiObjects = new ArrayList<>();

    public UIManager(Display display) {
        this.display = display;
    }

    public void tick(double delta) {
        for (UIObject o : uiObjects) {
            if (o.isActive()) {
                o.tick(delta);
            }
        }
    }

    public void render() {
        for (UIObject o : uiObjects) {
            if (o.isVisible()) {
                o.render();
            }
        }
    }

    public void onMouseMove() {
        for (UIObject o : uiObjects) {
            o.onMouseMove(display);
        }
    }

    public void onMouseRelease() {
        for (UIObject o : uiObjects) {
            o.onMouseRelease();
        }
    }

    public void addObject(UIObject o) {
        uiObjects.add(o);
        sortObjects();
    }

    public void removeObject(UIObject o) {
        uiObjects.remove(o);
    }

    private void sortObjects() {
        Collections.sort(uiObjects, (UIObject o1, UIObject o2) -> ((Integer) o1.getRenderOrder()).compareTo((Integer) o2.getRenderOrder()));
    }

    public ArrayList<UIObject> getUIObjects() {
        return uiObjects;
    }

    public void setUIObjects(ArrayList<UIObject> uiObjects) {
        this.uiObjects = uiObjects;
    }
}