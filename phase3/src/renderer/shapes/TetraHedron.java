package renderer.shapes;

import renderer.point.Converter;
import renderer.point.Points;

import java.awt.*;

public class TetraHedron {
    private MyPolygon[] poly;
    private Color color;

    public TetraHedron(Color color, MyPolygon... poly){
        this.color = color;
        this.poly = poly;
        this.setPolygonColor();
        this.sortPolygons();
    }

    public TetraHedron(MyPolygon... poly){
        this.color = Color.WHITE;
        this.poly = poly;
        this.sortPolygons();
    }

    public void render(Graphics g){
        for(MyPolygon i: this.poly){
            i.render(g);
        }
    }

    public MyPolygon[] getPolygons(){
        return this.poly;
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPolygon p: this.poly){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons(){
        MyPolygon.sortPolygons(this.poly);
    }

    private void setPolygonColor(){
        for(MyPolygon i: this.poly){
            i.setColor(this.color);
        }
    }
}
