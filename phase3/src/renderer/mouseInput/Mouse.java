package renderer.mouseInput;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int mouseX = -1;                                //initial values for mouse position and buttons
    private int mouseY = -1;
    private int mouseBut = -1;
    private int scroll = -1;

    public int getX(){
        return this.mouseX;
    }               //accessor method for x coordinate of the mouse

    public int getY(){
        return this.mouseY;
    }               //accessor method for y coordinate of the mouse

    public int getButton(){
        return this.mouseBut;
    }        //accessor method for which mouse button was pressed

    public boolean scrollingUp(){
        return this.scroll < 0;
    }   //if the user scrolls up

    public boolean scrollingDown(){ return this.scroll > 0;}    //if the user scrolls down

    public void resetScroll(){
        this.scroll = 0;
    }               //reset the scroll value

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {this.mouseBut = e.getButton();} // returns the specific pressed mouse button

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseBut = -1;
    }   //sets the mouse button to initial value

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {                        //returns the repositioned mouse coordinates
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) { this.scroll = e.getWheelRotation();} //returns the mouse scroll direction
}
