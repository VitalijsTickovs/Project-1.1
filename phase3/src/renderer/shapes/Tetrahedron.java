package renderer.shapes;

import java.awt.*;

public class Tetrahedron {
    private MyPolygon[] poly;
    private Color color;

    /**
     * Initializing method for Tetrahedron object
     * @param poly - array of polygons
     */
    public Tetrahedron(MyPolygon... poly){
        this.color = Color.WHITE;
        this.poly = poly;
        this.sortPolygons();
    }

    /**
     * @return array of polygons of this class
     */
    public MyPolygon[] getPolygons(){
        return this.poly;
    }

    /**
     * Rotation method of the shape
     * @param CW - if it is rotated clockwise
     * @param xDegrees - how many degrees on x-axis
     * @param yDegrees - how many degrees on y-axis
     * @param zDegrees - how many degrees on z-axis
     */
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPolygon p: this.poly){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    /**
     * Method to sort each face of the cube
     */
    private void sortPolygons(){
        MyPolygon.sortPolygons(this.poly);
    }
}
