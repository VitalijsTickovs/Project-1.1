package renderer.shapes;

import renderer.mouseInput.Mouse;
import renderer.point.Converter;
import renderer.shapes.builder.PentominoBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entity;
    private int initX, initY;

    public EntityManager(){
        this.entity = new ArrayList<Entity>();
    }

    public void init(){
        this.entity.add(PentominoBuilder.createPentominos(10, 0, 0, 0));
    }


    public void update(Mouse mouse){
        int x = mouse.getX();
        int y = mouse.getY();
        if(mouse.getButton() == 1){
            int xDif = x - initX;
            int yDif = y - initY;

            this.rotate(true, 0, -yDif, -xDif);
        }

        if(mouse.getButton() == 3){
            int xDif = x - initX;

            this.rotate(true, -xDif, 0, 0);
        }

        if(mouse.scrollingUp()){
            Converter.zoomIn();
        }else if(mouse.scrollingDown()){
            Converter.zoomOut();
        }
        mouse.resetScroll();
        initX = x;
        initY = y;
    }

    public void render(Graphics g){
        for(Entity p: this.entity){
            p.render(g);
        }
    }

    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(Entity p: this.entity){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
    }
}
