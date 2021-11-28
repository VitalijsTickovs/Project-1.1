import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        if (RunGame.scene.equals(RunGame.STATE.menu)) {
            int mx = e.getX();
            int my = e.getY();

            if (mx >= 310 && mx <= 460) {
                if (my >= 150 && my <= 200) {
                    RunGame.scene = RunGame.STATE.game;
                    //RunGame.playColor=Color.YELLOW;
                } else if (my >= 250 && my <= 300) {
                    RunGame.scene = RunGame.STATE.help;
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
}
