import javax.swing.*;
import java.awt.*;

public class GameOver {
    private Font fnt = new Font("Arial", Font.BOLD, 100);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(fnt);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Game Over", (RunGame.height*RunGame.scale)/2 -150, (RunGame.width*RunGame.scale)/2);
    }
}
