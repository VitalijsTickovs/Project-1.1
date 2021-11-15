package GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage image;
    public BufferedImage loadBufferedImage(String path) throws IOException {
            image = ImageIO.read(GameScreen.class.getResourceAsStream(path));
            return image;
    }
}
