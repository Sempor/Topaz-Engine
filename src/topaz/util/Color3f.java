package topaz.util;

import java.util.Random;

public class Color3f {

    public float r, g, b;

    //Rainbow colors
    public static Color3f RED = new Color3f(1, 0, 0);
    public static Color3f ORANGE = new Color3f(1, 0.5f, 0);
    public static Color3f YELLOW = new Color3f(1, 1, 0);
    public static Color3f GREEN = new Color3f(0, 1, 0);
    public static Color3f BLUE = new Color3f(0, 0, 1);
    public static Color3f PURPLE = new Color3f(1, 0, 1);

    //Black-white colors
    public static Color3f WHITE = new Color3f(1, 1, 1);
    public static Color3f BLACK = new Color3f(0, 0, 0);
    public static Color3f GRAY = new Color3f(0.5f, 0.5f, 0.5f);
    public static Color3f SILVER = new Color3f(0.75f, 0.75f, 0.75f);
    public static Color3f WHITE_SNOW = new Color3f(1, 0.98f, 0.98f);
    public static Color3f WHITE_SMOKE = new Color3f(0.96f, 0.96f, 0.96f);

    //Even more colors
    public static Color3f CYAN = new Color3f(0, 1, 1);
    public static Color3f OLIVE = new Color3f(0.5f, 0.5f, 0);
    public static Color3f NAVY_BLUE = new Color3f(0, 0, 0.5f);
    public static Color3f MAROON = new Color3f(0.5f, 0, 0);
    public static Color3f DARK_PURPLE = new Color3f(0.5f, 0, 0.5f);
    public static Color3f PINK = new Color3f(1, 0.75f, 0.80f);
    public static Color3f SKY_BLUE = new Color3f(0.53f, 0.81f, 0.92f);
    public static Color3f KHAKI = new Color3f(0.94f, 0.90f, 0.55f);
    public static Color3f GOLD = new Color3f(1, 0.84f, 0);
    public static Color3f CRIMSON = new Color3f(0.86f, 0.08f, 0.24f);
    public static Color3f BROWN = new Color3f(0.65f, 0.16f, 0.16f);
    public static Color3f TEAL = new Color3f(0, 0.5f, 0.5f);

    public Color3f(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color3f(Color3f color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
    }

    //This can't return the color white since 1 is excluded from the random float generator.
    public static Color3f getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color3f(r, g, b);
    }
}