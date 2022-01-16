package renderer.shapes;

import renderer.mouseInput.Mouse;
import renderer.point.Converter;
import renderer.shapes.AlgorithmType.AlgorithmsTypes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entity;
    private int initX, initY;

    public EntityManager(){
        this.entity = new ArrayList<Entity>();
    }

    /**
     * Initialization of the shape's array
     * @param arr - the array that algorithm outputted
     * @param algorithm - algorithm type
     */
    public void init(int[][][] arr, AlgorithmsTypes algorithm){
        this.entity.add(PentominoBuilder.createPentominos(10, 0, 0, 0, arr, algorithm));
    }

    /**
     * Updates the shape based on mouse input
     * @param mouse - object of Mouse type
     */
    public void update(Mouse mouse){
        int x = mouse.getX();
        int y = mouse.getY();
        if(mouse.getButton() == 1){                                 //if left click was clicked
            int xDif = x - initX;
            int yDif = y - initY;

            this.rotate(true, 0, -yDif, -xDif);
        }

        if(mouse.getButton() == 3){                                //if right click was clicked
            int xDif = x - initX;
            this.rotate(true, -xDif, 0, 0);
        }

        if(mouse.scrollingUp()){                                   //scaling of the shape if scrolled up
            Converter.zoomIn();
        }else if(mouse.scrollingDown()){                           //scaling of the shape if scrolled down
            Converter.zoomOut();
        }
        mouse.resetScroll();                                        //reset of the variable in mouse object
        initX = x;
        initY = y;
    }

    /**
     * Display each object of Entity
     * @param g
     */
    public void render(Graphics g){
        for(Entity p: this.entity){
            p.render(g);
        }
    }

    /**
     * Rotation method of the shape
     * @param CW - if it is rotated clockwise
     * @param xDegrees - how many degrees on x-axis
     * @param yDegrees - how many degrees on y-axis
     * @param zDegrees - how many degrees on z-axis
     */
    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(Entity p: this.entity){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
    }
}
