package topaz.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import topaz.core.Display;

public class Screenshot {

    public static final int BBP = 4;

    public enum SaveFormat {
        PNG, JPG, GIF, BMP, WBMP
    }

    public static void takeScreenshot(Display display, File saveFile, SaveFormat saveFormat) {
        //Gets screen data and puts it in a byte buffer
        GL11.glReadBuffer(GL11.GL_FRONT);
        ByteBuffer buffer = BufferUtils.createByteBuffer(display.getWidth() * display.getHeight() * BBP);
        GL11.glReadPixels(0, 0, display.getWidth(), display.getHeight(), GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        //Copies screen data from byte buffer into pixel data of buffered image
        BufferedImage image = new BufferedImage(display.getWidth(), display.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < display.getWidth(); x++) {
            for (int y = 0; y < display.getHeight(); y++) {
                int i = (x + (display.getWidth() * y)) * BBP;
                int r = buffer.get(i) & 0xFF;
                int g = buffer.get(i + 1) & 0xFF;
                int b = buffer.get(i + 2) & 0xFF;
                image.setRGB(x, display.getHeight() - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
            }
        }

        //Saves screenshot to saveFile
        try {
            switch (saveFormat) {
                case PNG:
                    ImageIO.write(image, "PNG", saveFile);
                    break;
                case JPG:
                    ImageIO.write(image, "JPG", saveFile);
                    break;
                case GIF:
                    ImageIO.write(image, "GIF", saveFile);
                    break;
                case BMP:
                    ImageIO.write(image, "BMP", saveFile);
                    break;
                case WBMP:
                    ImageIO.write(image, "WBMP", saveFile);
                    break;
                default:
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Screenshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}