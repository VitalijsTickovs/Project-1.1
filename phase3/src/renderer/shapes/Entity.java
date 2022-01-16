package renderer.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entity {
    private List<Tetrahedron> tetrahedra;
    private MyPolygon[] polygons;

    /**
     * Initialization method of Entity class
     * @param tetrahedra - list with cubes
     */
    public Entity(List<Tetrahedron> tetrahedra){
        this.tetrahedra = tetrahedra;
        List<MyPolygon> tempList = new ArrayList<MyPolygon>();
        for(Tetrahedron tetra : this.tetrahedra){
            tempList.addAll(Arrays.asList(tetra.getPolygons()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    /**
     * Output the cubes
     * @param g
     */
    public void render(Graphics g){
        for(MyPolygon p : this.polygons){
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
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(Tetrahedron p: this.tetrahedra){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();                            //sorting each face of the cubes
    }

    /**
     * Method to sort cubes, so each face would be seen
     */
    private void sortPolygons(){
        MyPolygon.sortPolygons(this.polygons);
    }
}
