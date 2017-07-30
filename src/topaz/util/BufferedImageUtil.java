package topaz.util;

import java.awt.image.BufferedImage;

public class BufferedImageUtil {
    
    public static BufferedImage getImageCopy(BufferedImage image) {
        return new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
    }

    public static BufferedImage makeColorTransparent(BufferedImage image, Color3f color) {
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

    public static BufferedImage flipHorizontally(BufferedImage image) {
        BufferedImage imageCopy = getImageCopy(image);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageCopy.setRGB(x, y, image.getRGB(image.getWidth() - 1 - x, y));
            }
        }
        return imageCopy;
    }

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