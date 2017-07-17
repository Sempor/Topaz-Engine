package topaz.web;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebBrowser {
    
    public static void goToWebPage(String webpage) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(webpage));
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(WebBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
