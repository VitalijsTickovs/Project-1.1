package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class GameMenu{

    Font Fnt = new Font("Arial", Font.BOLD, 30);
    Font focusedFnt = new Font("Arial", Font.BOLD, 40);
    public Rectangle playBut = new Rectangle((Menu.height*Menu.scale/2) +60,150,100,50);
    public Rectangle helpBut = new Rectangle(Menu.height*Menu.scale/2 +60,250,100,50);
    public Rectangle quitBut = new Rectangle(Menu.height*Menu.scale/2 +60,350,100,50);

    public void render(Graphics g){
        g.setFont(Fnt);
        if(Menu.playColor.equals(Color.ORANGE))g.setFont(focusedFnt);
        g.setColor(Menu.playColor);
        g.drawString("Play", playBut.x + 20, playBut.y + 30);

        g.setFont(Fnt);
        if(Menu.helpColor.equals(Color.ORANGE))g.setFont(focusedFnt);
        g.setColor(Menu.helpColor);
        g.drawString("Help", helpBut.x + 20, helpBut.y + 30);

        g.setFont(Fnt);
        if(Menu.quitColor.equals(Color.ORANGE))g.setFont(focusedFnt);
        g.setColor(Menu.quitColor);
        g.drawString("Quit", quitBut.x + 20, quitBut.y + 30);

    }
}
