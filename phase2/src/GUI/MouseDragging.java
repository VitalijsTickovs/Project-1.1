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
        if (Menu.state.equals(Menu.STATE.menu)) {
            int px = e.getX();
            int py = e.getY();
            if (px >= 310 && px <= 410) {
                if (py >= 150 && py <= 200) {
                    Menu.playColor = Color.ORANGE;
                } else {
                    Menu.playColor = Color.WHITE;
                }
            } else {
                Menu.playColor = Color.WHITE;
            }
            if (px >= 310 && px <= 410) {
                if (py >= 250 && py <= 300) {
                    Menu.helpColor = Color.ORANGE;
                } else {
                    Menu.helpColor = Color.WHITE;
                }
            } else {
                Menu.helpColor = Color.WHITE;
            }
            if (px >= 310 && px <= 410) {
                if (py >= 350 && py <= 400) {
                    Menu.quitColor = Color.ORANGE;
                } else {
                    Menu.quitColor = Color.WHITE;
                }
            } else {
                Menu.quitColor = Color.WHITE;
            }
        }
    }
}
