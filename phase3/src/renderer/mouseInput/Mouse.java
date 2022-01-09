package renderer.mouseInput;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseBut = -1;
    private int scroll = -1;

    public int getX(){
        return this.mouseX;
    }

    public int getY(){
        return this.mouseY;
    }

    public int getButton(){
        return this.mouseBut;
    }

    public boolean scrollingUp(){
        return this.scroll < 0;
    }

    public boolean scrollingDown(){
        return this.scroll > 0;
    }

    public void resetScroll(){
        this.scroll = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseBut = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseBut = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.scroll = e.getWheelRotation();
    }
}
