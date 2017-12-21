package topaz.assets;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

public class AssetLoader {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(AssetLoader.class.getResource(path));
        } catch (IOException e) {
            System.exit(-1);
        }
        return null;
    }

    public static int loadPNGTexture(String path) {
        try {
            //Opens stream to PNG texture file and links decoder to stream
            InputStream stream = AssetLoader.class.getResourceAsStream(path);
            PNGDecoder decoder = new PNGDecoder(stream);

            //Gets width and height of PNG texture
            int width = decoder.getWidth();
            int height = decoder.getHeight();

            //Puts PNG file data in a buffer
            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * width * height);
            decoder.decode(buffer, 4 * width, Format.RGBA);
            buffer.flip();

            stream.close();

            //Creates and binds a texture object
            int textureID = GL11.glGenTextures();
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

            //Aligns RGB bytes to each other, sets each component to be 1 byte
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            //Uploads texture data
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0,
                    GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

            //Generates mipmaps for scaling purposes
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

            //Sets up ST coordinate system
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

            //Sets up protocol for when texture is scaled
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);

            return textureID;

        } catch (IOException e) {
            System.exit(-1);
        }

        return -1;
    }

    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, AssetLoader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            System.exit(-1);
        }
        return null;
    }

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();
        InputStream in = AssetLoader.class.getResourceAsStream(path);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }

            br.close();
        } catch (IOException e) {
        }
        return builder.toString();
    }
}
