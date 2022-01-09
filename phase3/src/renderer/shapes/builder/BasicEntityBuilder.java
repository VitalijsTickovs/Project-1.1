package renderer.shapes.builder;

import renderer.point.Points;
import renderer.shapes.Entity;
import renderer.shapes.MyPolygon;
import renderer.shapes.TetraHedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BasicEntityBuilder {
    public static Entity createCube(double size, double centerX, double centerY, double centerZ){
        Points p1 = new Points(centerX + size/2, centerY - size/2, centerZ - size/2);
        Points p2 = new Points(centerX + size/2, centerY + size/2, centerZ - size/2);
        Points p3 = new Points(centerX + size/2, centerY + size/2, centerZ + size/2);
        Points p4 = new Points(centerX + size/2, centerY - size/2, centerZ + size/2);
        Points p5 = new Points(centerX - size/2, centerY - size/2, centerZ - size/2);
        Points p6 = new Points(centerX - size/2, centerY + size/2, centerZ - size/2);
        Points p7 = new Points(centerX - size/2, centerY + size/2, centerZ + size/2);
        Points p8 = new Points(centerX - size/2, centerY - size/2, centerZ + size/2);

        TetraHedron tetra = new TetraHedron(
                new MyPolygon(Color.RED, p1, p2, p3, p4),
                new MyPolygon(Color.YELLOW, p5, p6, p7, p8),
                new MyPolygon(Color.ORANGE, p1, p2, p6, p5),
                new MyPolygon(Color.BLUE, p1, p5, p8, p4),
                new MyPolygon(Color.PINK, p2, p6, p7, p3),
                new MyPolygon(Color.GREEN, p4, p3, p7, p8));
        
        List<TetraHedron> tetras = new ArrayList<TetraHedron>();
        tetras.add(tetra);
        return new Entity(tetras);
    }
}
