package topaz.util;

public class Color4f {

    public float r, g, b, a;
    
    //Rainbow colors
    public static Color4f RED = new Color4f(1, 0, 0, 1);
    public static Color4f ORANGE = new Color4f(1, 0.5f, 0, 1);
    public static Color4f YELLOW = new Color4f(1, 1, 0, 1);
    public static Color4f GREEN = new Color4f(0, 1, 0, 1);
    public static Color4f BLUE = new Color4f(0, 0, 1, 1);
    public static Color4f PURPLE = new Color4f(1, 0, 1, 1);
    
    //Black-white colors
    public static Color4f WHITE = new Color4f(1, 1, 1, 1);
    public static Color4f BLACK = new Color4f(0, 0, 0, 1);
    public static Color4f GRAY = new Color4f(0.5f, 0.5f, 0.5f, 1);
    
    //Even more colors
    public static Color4f CYAN = new Color4f(0, 1, 1, 1);
    public static Color4f OLIVE = new Color4f(0.5f, 0.5f, 0, 1);
    public static Color4f SILVER = new Color4f(0.75f, 0.75f, 0.75f, 1);
    public static Color4f NAVY = new Color4f(0, 0, 0.5f, 1);
    public static Color4f MAROON = new Color4f(0.5f, 0, 0, 1);
    public static Color4f DARK_PURPLE = new Color4f(0.5f, 0, 0.5f, 1);

    public Color4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}