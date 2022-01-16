package renderer.shapes;

import renderer.point.Points;
import renderer.shapes.AlgorithmType.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PentominoBuilder {
    static int[][][] pentominoLayout = new int[5][8][33];       //layout of the cubes

    /**
     * Method that will color the separate pentomino
     * @param pentValue - value of the cell
     * @return Color type variable
     */
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

    /**
     * Method responsible for creation of the shape
     * @param size  - size of the separate cubes
     * @param centerX - initial x value coordinate
     * @param centerY - initial y value coordinate
     * @param centerZ - initial z value coordinate
     * @param arr - array outputted from algorithms
     * @param alg - which algorithm is used
     * @return shape that will be siplayed
     */
    public static Entity createPentominos(double size, double centerX, double centerY, double centerZ, int[][][] arr, AlgorithmsTypes alg){
        List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();                            //initializing the list of cubes

        if(alg == AlgorithmsTypes.HeuristicAlgorithm) heuristicAlgorithm(arr);    //decode every cell value depending on the algorithm
        else dancingLinksAlgorithm(arr);

        int cubeSpacing = 2;
        for(int i=-2; i < 3; i++) {                                               //assigning cube location on each position
            double cubeCenterX = i * (size + cubeSpacing) + centerX;

            for (int j = -4; j < 4; j++) {
                double cubeCenterY = j * (size + cubeSpacing) + centerY;

                for (int k = -16; k < 17; k++) {
                    Color squareColor = findColor(pentominoLayout[i + 2][j + 4][k + 16]);       //seeing what color the cube should be drawn

                    double cubeCenterZ = k * (size + cubeSpacing) + centerZ;

                    Points p1 = new Points(cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2);
                    Points p2 = new Points(cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2);
                    Points p3 = new Points(cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2);
                    Points p4 = new Points(cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2);
                    Points p5 = new Points(cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2);
                    Points p6 = new Points(cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2);
                    Points p7 = new Points(cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2);
                    Points p8 = new Points(cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2);

                    MyPolygon poly1 = new MyPolygon(squareColor, p5, p6, p8, p7);
                    MyPolygon poly2 = new MyPolygon(squareColor, p2, p4, p8, p6);
                    MyPolygon poly3 = new MyPolygon(squareColor, p3, p4, p8, p7);
                    MyPolygon poly4 = new MyPolygon(squareColor, p1, p2, p6, p5);
                    MyPolygon poly5 = new MyPolygon(squareColor, p1, p2, p4, p3);
                    MyPolygon poly6 = new MyPolygon(squareColor, p1, p3, p7, p5);

                    Tetrahedron tetra = new Tetrahedron(poly1, poly2, poly3, poly4, poly5, poly6);
                    tetras.add(tetra);                                                                          //adding each cube to the list

                }
            }
        }
        return new Entity(tetras);                                                                              //returning finished shape layout
    }

    /**
     * Method to decode Dancing links algorithm output
     * @param arr
     */
    public static void dancingLinksAlgorithm(int [][][] arr){
        int colorCount = 0;
        for(int pentPiece=0; pentPiece < arr.length; pentPiece++){
            if(pentPiece % 5 ==0){
                if(colorCount<3) colorCount++;
                else colorCount = 1;
            }
            for(int cube=0; cube < arr[pentPiece].length; cube++) {
                pentominoLayout[arr[pentPiece][cube][0]][arr[pentPiece][cube][1]][arr[pentPiece][cube][2]] = colorCount;
            }
        }
    }

    /**
     * Method to decode heuristic algorithm output
     * @param arr
     */
    public static void heuristicAlgorithm(int[][][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                for(int k=0; k<arr[i][j].length; k++){
                    pentominoLayout[j][k][i] = arr[i][j][k];
                }
            }
        }
    }
}
