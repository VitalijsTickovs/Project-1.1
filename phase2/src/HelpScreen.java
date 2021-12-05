import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreen{                //this class will output the text for the help screen
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    Font fnt = new Font("Arial", Font.BOLD, 30);

    Rectangle back = new Rectangle(0, 10, 40, 30);

    public void render(Graphics g) {
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



        //timer.start();
    }
}
