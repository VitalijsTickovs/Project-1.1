import java.awt.*;

public class GameMenu{
    Font Fnt = new Font("Arial", Font.BOLD, 30);            //Init the font
    Font focusedFnt = new Font("Arial", Font.BOLD, 40);

    public Rectangle playBut = new Rectangle((RunGame.height*RunGame.scale/2) +60,150,100,50);          //Areas of buttons
    public Rectangle helpBut = new Rectangle(RunGame.height*RunGame.scale/2 +60,250,100,50);
    public Rectangle quitBut = new Rectangle(RunGame.height*RunGame.scale/2 +60,350,100,50);

    /**
     * Constructs menu scene
     * @param g Graphics parameter used by RunGame instance
     */
    public void renderMenu(Graphics g){
        g.setFont(Fnt);
        if(RunGame.playColor.equals(Color.ORANGE)) g.setFont(focusedFnt);                    //displaying playbutton by its color
        g.setColor(RunGame.playColor);
        g.drawString("Play", playBut.x + 20, playBut.y + 30);

        g.setFont(Fnt);
        if(RunGame.helpColor.equals(Color.ORANGE)) g.setFont(focusedFnt);                    //displaying helpbutton by its color
        g.setColor(RunGame.helpColor);
        g.drawString("Help", helpBut.x + 20, helpBut.y + 30);

        g.setFont(Fnt);
        if(RunGame.quitColor.equals(Color.ORANGE)) g.setFont(focusedFnt);                    //displaying quitbutton by its color
        g.setColor(RunGame.quitColor);
        g.drawString("Quit", quitBut.x + 20, quitBut.y + 30);

    }
}
