package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseDragging implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (RunGame.state.equals(RunGame.STATE.menu)) {
            int px = e.getX();
            int py = e.getY();
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
        }
    }
}
