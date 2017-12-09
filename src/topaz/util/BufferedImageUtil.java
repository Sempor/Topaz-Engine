package topaz.util;

import java.awt.image.BufferedImage;

public class BufferedImageUtil {

    /**
     * Makes and returns a copy of the image entered
     *
     * @param image
     * @return
     */
    public static BufferedImage getImageCopy(BufferedImage image) {
        return new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
    }

    /**
     * Accepts an image and a color from the user. Sets all pixels of that color
     * to be transparent. Returns the modified image.
     *
     * @param image
     * @param color
     * @return
     */
    public static BufferedImage makeColorTransparent(BufferedImage image, Color color) {
        BufferedImage imageCopy = getImageCopy(image);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);
                int red = (pixelColor & 0x00ff0000) >> 16;
                int green = (pixelColor & 0x0000ff00) >> 8;
                int blue = (pixelColor & 0x000000ff);
                if (red == color.r && green == color.g && blue == color.b) {
                    imageCopy.setRGB(x, y, 0x00000000);
                }
            }
        }
        return imageCopy;
    }

    /**
     * Returns a grayscale copy of the image entered.
     *
     * @param image
     * @param color
     * @return
     */
    public static BufferedImage getGrayscale(BufferedImage image) {
        BufferedImage imageCopy = getImageCopy(image);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);
                float red = ((pixelColor & 0x00ff0000) >> 16) / 255;
                float green = ((pixelColor & 0x0000ff00) >> 8) / 255;
                float blue = ((pixelColor & 0x000000ff)) / 255;
                Color color = new Color(red, green, blue, 0);
                imageCopy.setRGB(x, y, (int) (color.getGrayscale() * 255f));
            }
        }
        return imageCopy;
    }

    /**
     * Flips the image over the y-axis. Returns the modified image.
     *
     * @param image
     * @return
     */
    public static BufferedImage flipHorizontally(BufferedImage image) {
        BufferedImage imageCopy = getImageCopy(image);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageCopy.setRGB(x, y, image.getRGB(image.getWidth() - 1 - x, y));
            }
        }
        return imageCopy;
    }

    /**
     * Flips the image over the x-axis. Returns the modified image.
     *
     * @param image
     * @return
     */
    public static BufferedImage flipVertically(BufferedImage image) {
        BufferedImage imageCopy = getImageCopy(image);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageCopy.setRGB(x, y, image.getRGB(x, image.getHeight() - 1 - y));
            }
        }
        return imageCopy;
    }
}
