package renderer.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entity {
    private List<TetraHedron> tetrahedrons;
    private MyPolygon[] polygons;

    public Entity(List<TetraHedron> tetrahedrons){
        this.tetrahedrons = tetrahedrons;
        List<MyPolygon> tempList = new ArrayList<MyPolygon>();
        for(TetraHedron tetra : this.tetrahedrons){
            tempList.addAll(Arrays.asList(tetra.getPolygons()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    public void render(Graphics g){
        for(MyPolygon p : this.polygons){
            p.render(g);
        }
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(TetraHedron p: this.tetrahedrons){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons(){
        MyPolygon.sortPolygons(this.polygons);
    }
}
