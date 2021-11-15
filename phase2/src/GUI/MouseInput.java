package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener{
    public Menu ob = new Menu();
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Menu.state.equals(Menu.STATE.menu)) {
            int mx = e.getX();
            int my = e.getY();

        /* public Rectangle playBut = new Rectangle( 310,150,100,50);
    public Rectangle helpBut = new Rectangle(Menu.height*Menu.scale/2 +60,250,100,50);
    public Rectangle quitBut = new Rectangle(Menu.height*Menu.scale/2 +60,350,100,50);*/

            if (mx >= 310 && mx <= 460) {
                if (my >= 150 && my <= 200) {
                    Menu.state = Menu.STATE.game;
                    //Menu.playColor=Color.YELLOW;
                } else if (my >= 250 && my <= 300) {
                    Menu.state = Menu.STATE.help;
                } else if (my >= 350 && my <= 400) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /*public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }*/
}
