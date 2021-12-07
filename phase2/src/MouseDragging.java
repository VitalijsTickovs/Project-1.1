import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseDragging extends KeyInput implements MouseMotionListener, MouseListener {
    public Rectangle pauseBut = new Rectangle(20,500,100,50);

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int px = e.getX();
        int py = e.getY();
        if (RunGame.scene == RunGame.STATE.menu){
            if (px >= 310 && px <= 410) {
                if (py >= 150 && py <= 200) {
                    RunGame.playColor = Color.ORANGE;
                } else {
                    RunGame.playColor = Color.WHITE;
                }
            } else {
                RunGame.playColor = Color.WHITE;
            }
            if (px >= 310 && px <= 410) {
                if (py >= 250 && py <= 300) {
                    RunGame.helpColor = Color.ORANGE;
                } else {
                    RunGame.helpColor = Color.WHITE;
                }
            } else {
                RunGame.helpColor = Color.WHITE;
            }
            if (px >= 310 && px <= 410) {
                if (py >= 350 && py <= 400) {
                    RunGame.quitColor = Color.ORANGE;
                } else {
                    RunGame.quitColor = Color.WHITE;
                }
            } else {
                RunGame.quitColor = Color.WHITE;
            }
        }else if(RunGame.scene == RunGame.STATE.game){
            if(px >= 345 && px<=440){
                if(py>=60 && py<=85){
                    RunGame.pauseColor = Color.ORANGE;
                }else{
                    RunGame.pauseColor = Color.WHITE;
                }
            }else{
                RunGame.pauseColor = Color.WHITE;
            }
        }else if(RunGame.scene == RunGame.STATE.help){
            if(px>=0 && px<=90){
                if(py>=0 && py<=30){
                    RunGame.backColor = Color.ORANGE;
                }else{
                    RunGame.backColor = Color.WHITE;
                }
            }else{
                RunGame.backColor = Color.WHITE;
            }
        }
    }
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
            if(mx >=345 && mx<=440){
                if(my >= 60 && my<=85){
                    RunGame.pause = !RunGame.pause;
                }
            }
        }else if(RunGame.scene == RunGame.STATE.help){
            if(mx>=0 && mx<=90){
                if(my>=0 && my<=30) {
                    RunGame.scene = RunGame.STATE.menu;
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
