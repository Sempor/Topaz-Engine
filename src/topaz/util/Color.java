package topaz.util;

import java.util.Random;

/* TODO:
 - getGamma() method that gets the gamma correction of a color
 */
public class Color {


    public static Color BLACK = new Color(0, 0, 0, 1);
    public static Color BLUE = new Color(0, 0, 1, 1);
    public static Color BLUE_CYAN = new Color(0, 1, 1, 1);
    public static Color BLUE_NAVY = new Color(0, 0, 0.5f, 1);
    public static Color BLUE_SKY = new Color(0.53f, 0.81f, 0.92f, 1);
    public static Color BLUE_TEAL = new Color(0, 0.5f, 0.5f, 1);
    public static Color BROWN = new Color(0.65f, 0.16f, 0.16f, 1);
    public static Color CLEAR = new Color(0, 0, 0, 0);
    public static Color GOLD = new Color(1, 0.84f, 0, 1);
    public static Color GRAY = new Color(0.5f, 0.5f, 0.5f, 1);
    public static Color GREY = new Color(0.5f, 0.5f, 0.5f, 1); //English spelling
    public static Color GREEN = new Color(0, 1, 0, 1);
    public static Color GREEN_OLIVE = new Color(0.5f, 0.5f, 0, 1);
    public static Color KHAKI = new Color(0.94f, 0.90f, 0.55f, 1);
    public static Color ORANGE = new Color(1, 0.5f, 0, 1);
    public static Color PINK = new Color(1, 0.75f, 0.80f, 1);
    public static Color PURPLE = new Color(1, 0, 1, 1);
    public static Color PURPLE_DARK = new Color(0.5f, 0, 0.5f, 1);
    public static Color RED = new Color(1, 0, 0, 1);
    public static Color RED_CRIMSON = new Color(0.86f, 0.08f, 0.24f, 1);
    public static Color RED_MAROON = new Color(0.5f, 0, 0, 1);
    public static Color SILVER = new Color(0.75f, 0.75f, 0.75f, 1);
    public static Color WHITE = new Color(1, 1, 1, 1);
    public static Color WHITE_SMOKE = new Color(0.96f, 0.96f, 0.96f, 1);
    public static Color WHITE_SNOW = new Color(1, 0.98f, 0.98f, 1);
    public static Color YELLOW = new Color(1, 1, 0, 1);


    //This can't return the color white since 1 is excluded from the random float generator.
    //Also, just by programmer's choice, this only returns opaque colors.
    public static Color getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b, 1);
    }

    public static Color addColors(Color c1, Color c2) {
        float r = c1.r + c2.r;
        float g = c1.g + c2.g;
        float b = c1.b + c2.b;
        float a = c1.a + c2.a;
        return new Color(r, g, b, a);
    }

    public static Color subtractColors(Color c1, Color c2) {
        float r = c1.r - c2.r;
        float g = c1.g - c2.g;
        float b = c1.b - c2.b;
        float a = c1.a - c2.a;
        return new Color(r, g, b, a);
    }

    public static Color multiplyColors(Color c1, Color c2) {
        float r = c1.r * c2.r;
        float g = c1.g * c2.g;
        float b = c1.b * c2.b;
        float a = c1.a * c2.a;
        return new Color(r, g, b, a);
    }

    public static Color divideColor(Color c, float d) {
        if (d == 0) {
            return Color.WHITE;
        }
        float r = c.r / d;
        float g = c.g / d;
        float b = c.b / d;
        float a = c.a / d;
        return new Color(r, g, b, a);
    }
    public float r;
    public float g;
    public float b;
    public float a;
    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public Color(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }
    public float getGrayscale() {
        return 0.3f * r + 0.59f * g + 0.11f * b;
    }
    public float getMaximumColorComponent() {
        float max = r;
        if (g > max) {
            max = g;
        }
        if (b > max) {
            max = b;
        }
        return max;
    }
    public float getMinimumColorComponent() {
        float min = r;
        if (g < min) {
            min = g;
        }
        if (b < min) {
            min = b;
        }
        return min;
    }
}
