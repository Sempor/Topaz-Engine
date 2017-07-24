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
    public static Color4f SILVER = new Color4f(0.75f, 0.75f, 0.75f, 1);
    public static Color4f WHITE_SNOW = new Color4f(1, 0.98f, 0.98f, 1);
    public static Color4f WHITE_SMOKE = new Color4f(0.96f, 0.96f, 0.96f, 1);
    
    //Even more colors
    public static Color4f CYAN = new Color4f(0, 1, 1, 1);
    public static Color4f OLIVE = new Color4f(0.5f, 0.5f, 0, 1);
    public static Color4f NAVY_BLUE = new Color4f(0, 0, 0.5f, 1);
    public static Color4f MAROON = new Color4f(0.5f, 0, 0, 1);
    public static Color4f DARK_PURPLE = new Color4f(0.5f, 0, 0.5f, 1);
    public static Color4f PINK = new Color4f(1, 0.75f, 0.80f, 1);
    public static Color4f SKY_BLUE = new Color4f(0.53f, 0.81f, 0.92f, 1);
    public static Color4f KHAKI = new Color4f(0.94f, 0.90f, 0.55f, 1);
    public static Color4f GOLD = new Color4f(1, 0.84f, 0, 1);
    public static Color4f CRIMSON = new Color4f(0.86f, 0.08f, 0.24f, 1);
    public static Color4f BROWN = new Color4f(0.65f, 0.16f, 0.16f, 1);
    public static Color4f TEAL = new Color4f(0, 0.5f, 0.5f, 1);

    public Color4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}