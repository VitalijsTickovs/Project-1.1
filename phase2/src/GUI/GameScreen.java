package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameScreen{
    public Rectangle playArea = new Rectangle((RunGame.height*4/2) +100,100,25,15*5);
    public Dimension gameScreen = new Dimension(RunGame.height * 4, RunGame.width * RunGame.scale);
    int size = 30;

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        //g2d.draw(playArea);
        g2d.setColor(Color.WHITE);


        for (int i = 0; i <= RunGame.pentLoc[i].length; i++)
        {
            g2d.drawLine((i * size) + 300, 100, (i * size) + 300, RunGame.pentLoc.length * size +100);
        }
        for (int i = 0; i <= RunGame.pentLoc.length; i++)
        {
            g2d.drawLine(300, i * size +100, (RunGame.pentLoc[0].length * size)+300, i * size +100);
        }

        //draw blocks
        for (int i = 0; i < RunGame.pentLoc[i].length; i++)
        {
            for (int j = 0; j < RunGame.pentLoc.length; j++)
            {
                g2d.setColor(GetColorOfID(RunGame.pentLoc[j][i]));
                g2d.fill(new Rectangle2D.Double(i * size + 301, j * size + 101, size - 1, size - 1));
            }
        }
    }
    private Color GetColorOfID(int i)
    {
        if(i==0) {return Color.BLUE;}
        else if(i==1) {return Color.ORANGE;}
        else if(i==2) {return Color.CYAN;}
        else if(i==3) {return Color.GREEN;}
        else if(i==4) {return Color.MAGENTA;}
        else if(i==5) {return Color.PINK;}
        else if(i==6) {return Color.RED;}
        else if(i==7) {return Color.YELLOW;}
        else if(i==8) {return new Color(0, 0, 0);}
        else if(i==9) {return new Color(0, 0, 100);}
        else if(i==10) {return new Color(100, 0,0);}
        else if(i==11) {return new Color(0, 100, 0);}
        else {return Color.LIGHT_GRAY;}
    }
}
