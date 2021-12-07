import java.awt.*;

public class HelpScreen extends GameScreen{                //this class will output the text for the help screen
    Font fnt = new Font("Arial", Font.BOLD, 30);

    /**
     * Constructs help screen
     * @param g Graphics parameter used by RunGame instance
     */
    public void renderHelp(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(RunGame.backColor);
        g2d.setFont(fnt);
        g2d.drawString("Back", 10,25);

        g2d.setColor(Color.white);
        g.drawImage(RunGame.rotate, 20, 50, 250, 200, null);
        g2d.drawString("To rotate a piece press " + '\u2193', 320 , 100);

        g.drawImage(RunGame.moving, 550, 310, 250, 200, null);
        g2d.drawString("To move a piece press " + '\u2190' + " or " + '\u2192', 20, 410);

        g.drawImage(RunGame.drop, 20, 550, 200, 250, null);
        g2d.drawString("To drop a piece press space bar", 320, 700);
    }
}
