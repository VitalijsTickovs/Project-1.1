import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {          //this class will be loading the image by the specified file name in GUI folder
    private BufferedImage image;
    public BufferedImage loadBufferedImage(String path) throws IOException {
            image = ImageIO.read(GameScreen.class.getResourceAsStream(path));
            return image;
    }
}
