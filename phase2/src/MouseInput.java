import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (RunGame.scene == (RunGame.STATE.menu)) {
            if (mx >= 310 && mx <= 460) {
                if (my >= 150 && my <= 200) {
                    RunGame.scene = RunGame.STATE.game;
                } else if (my >= 250 && my <= 300) {
                    RunGame.scene = RunGame.STATE.help;
                } else if (my >= 350 && my <= 400) {
                    System.exit(0);
                }
            }
        }else if(RunGame.scene == RunGame.STATE.game){
            if(mx >=20 && mx<=120){
                if(my >= 460 && my<=520){
                    RunGame.pause = !RunGame.pause;
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
}
