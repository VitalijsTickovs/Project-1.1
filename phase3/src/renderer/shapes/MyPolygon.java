package renderer.shapes;

import renderer.point.Converter;
import renderer.point.Points;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MyPolygon {
    private Points[] points;
    private Color color;

    public MyPolygon(Color color, Points... points){
        this.color = color;
        this.points = new Points[points.length];
        for(int i=0; i < points.length; i++){
            Points p = points[i];
            this.points[i] = new Points(p.x, p.y, p.z);
        }
    }

    public MyPolygon(Points... points){
        this.color = Color.WHITE;
        this.points = new Points[points.length];
        for(int i=0; i < points.length; i++){
            Points p = points[i];
            this.points[i] = new Points(p.x, p.y, p.z);
        }
    }

    public void render(Graphics g){
        Polygon poly = new Polygon();
        for(int i=0; i < this.points.length; i++){
            Point p = Converter.convertPoint(this.points[i]);
            poly.addPoint(p.x, p.y);
        }
        g.setColor(this.color);
        g.fillPolygon(poly);
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(Points p: this.points){
            Converter.rotateAxisX(p, CW, xDegrees);
            Converter.rotateAxisY(p, CW, yDegrees);
            Converter.rotateAxisZ(p, CW, zDegrees);
        }

    }

    public void setColor(Color color){
        this.color = color;
    }

    public double getAVGX(){
        double sum=0;
        for(Points p: this.points){
            sum += p.x;
        }
        return sum/ this.points.length;
    }

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
