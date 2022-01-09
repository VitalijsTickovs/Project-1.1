package renderer.shapes.builder;

import renderer.point.Points;
import renderer.shapes.Entity;
import renderer.shapes.MyPolygon;
import renderer.shapes.TetraHedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PentominoBuilder {
    static int[][][] pentominoLayout = new int[5][8][33];

    public static Color findColor(int pentValue){
        switch(pentValue){
            case 1:{
                return Color.BLUE;
            }
            case 2:{
                return Color.RED;
            }
            case 3:{
                return Color.GREEN;
            }
            default: return Color.WHITE;
        }
    }

    //arr is the 3d array you should pass

    public static Entity createPentominos(double size, double centerX, double centerY, double centerZ, int[][][] arr){
        List<TetraHedron> tetras = new ArrayList<TetraHedron>();

        int colorCount = 0;
        for(int pentominoPiece=0; pentominoPiece< arr.length; pentominoPiece++){
            if(pentominoPiece % 5 ==0){
                if(colorCount<3) colorCount++;
                else colorCount = 1;
            }
            for(int cube=0; cube < arr[pentominoPiece].length; cube++){
                pentominoLayout[arr[pentominoPiece][cube][0]][arr[pentominoPiece][cube][1]][arr[pentominoPiece][cube][2]] = colorCount;
            }
        }

        int cubeSpacing = 2;

        for(int i=-2; i < 2; i++){
            double cubeCenterX = i * (size + cubeSpacing) + centerX;

            for(int j=-4; j < 3; j++){
                double cubeCenterY = j * (size + cubeSpacing) + centerY;

                for(int k=-16; k < 17; k++){
                    Color squareColor = findColor(pentominoLayout[i+2][j+4][k+16]);

                    double cubeCenterZ = k * (size + cubeSpacing) + centerZ;

                    Points p1 = new Points(cubeCenterX - size/2, cubeCenterY - size/2, cubeCenterZ - size/2);
                    Points p2 = new Points(cubeCenterX - size/2, cubeCenterY - size/2, cubeCenterZ + size/2);
                    Points p3 = new Points(cubeCenterX - size/2, cubeCenterY + size/2, cubeCenterZ - size/2);
                    Points p4 = new Points(cubeCenterX - size/2, cubeCenterY + size/2, cubeCenterZ + size/2);
                    Points p5 = new Points(cubeCenterX + size/2, cubeCenterY - size/2, cubeCenterZ - size/2);
                    Points p6 = new Points(cubeCenterX + size/2, cubeCenterY - size/2, cubeCenterZ + size/2);
                    Points p7 = new Points(cubeCenterX + size/2, cubeCenterY + size/2, cubeCenterZ - size/2);
                    Points p8 = new Points(cubeCenterX + size/2, cubeCenterY + size/2, cubeCenterZ + size/2);

                    MyPolygon poly1 = new MyPolygon(squareColor, p5, p6, p8, p7);
                    MyPolygon poly2 = new MyPolygon(squareColor, p2, p4, p8, p6);
                    MyPolygon poly3 = new MyPolygon(squareColor, p3, p4, p8, p7);
                    MyPolygon poly4 = new MyPolygon(squareColor, p1, p2, p6, p5);
                    MyPolygon poly5 = new MyPolygon(squareColor, p1, p2, p4, p3);
                    MyPolygon poly6 = new MyPolygon(squareColor, p1, p3, p7, p5);

                    TetraHedron tetra = new TetraHedron(poly1, poly2, poly3, poly4, poly5, poly6);
                    tetras.add(tetra);

                }
            }
        }

        return new Entity(tetras);
    }



}
