package topaz.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebUtil {
    
    public static void openWebPage(String webpage) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(webpage));
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(WebUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
