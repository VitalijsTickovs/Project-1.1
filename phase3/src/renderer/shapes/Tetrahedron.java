package renderer.shapes;

import java.awt.*;

public class Tetrahedron {
    private MyPolygon[] poly;
    private Color color;

    public Tetrahedron(MyPolygon... poly){
        this.color = Color.WHITE;
        this.poly = poly;
        this.sortPolygons();
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
}
