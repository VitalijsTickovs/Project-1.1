package renderer.shapes;

import renderer.point.Converter;
import renderer.point.Points;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MyPolygon {
    private Points[] points;
    private Color color;

    /**
     * Method to initialize polygon object
     * @param color - color of the face
     * @param points - each point of the cube face
     */
    public MyPolygon(Color color, Points... points){
        this.color = color;
        this.points = new Points[points.length];
        for(int i=0; i < points.length; i++){
            Points p = points[i];
            this.points[i] = new Points(p.x, p.y, p.z);         //Creating new points for the cube, specified by the points array
        }
    }

    /**
     * Method to display each face
     * @param g
     */
    public void render(Graphics g){
        Polygon poly = new Polygon();
        for(int i=0; i < this.points.length; i++){
            Point p = Converter.convertPoint(this.points[i]);
            poly.addPoint(p.x, p.y);                            //adding points to the frame
        }
        g.setColor(this.color);
        g.fillPolygon(poly);                                    //filling inside the points
    }
    /**
     * Rotation method of the shape
     * @param CW - if it is rotated clockwise
     * @param xDegrees - how many degrees on x-axis
     * @param yDegrees - how many degrees on y-axis
     * @param zDegrees - how many degrees on z-axis
     */
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(Points p: this.points){
            Converter.rotateAxisX(p, CW, xDegrees);
            Converter.rotateAxisY(p, CW, yDegrees);
            Converter.rotateAxisZ(p, CW, zDegrees);
        }
    }

    /**
     * Method to sort values of List variable
     * @return average x position
     */
    public double getAVGX(){
        double sum=0;
        for(Points p: this.points){
            sum += p.x;
        }
        return sum/ this.points.length;
    }

    /**
     * Method to sort each face, so it is recognized by the user
     * @param polygons - array of cube faces
     * @return sorted array of faces
     */
    public static MyPolygon[] sortPolygons(MyPolygon[] polygons){
        List<MyPolygon> polygonList = new ArrayList<MyPolygon>();

        for(MyPolygon p: polygons){
            polygonList.add(p);
        }

        Collections.sort(polygonList, new Comparator<MyPolygon>(){
            @Override
            public int compare(MyPolygon p1, MyPolygon p2){
                double p1AVGX = p1.getAVGX(); double p2AVGX = p2.getAVGX();
                double diff = p2AVGX - p1AVGX;
                if(diff == 0 ) return 0;
                return diff<0 ? 1: -1;
            }
        });

        for(int i=0; i<polygons.length; i++){
            polygons[i] = polygonList.get(i);
        }
        return polygons;
    }
}
