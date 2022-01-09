import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

    

        int[][][] e = new int[8][5][33];

        int[] input = {1,1,1};


        int[][][] x = DancingRun3D.getSolution(100,100,true);
        
        



        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print("{");
                for (int j2 = 0; j2 < x[i][j].length; j2++) {
                    System.out.print(x[i][j][j2] + ", ");
                }
                System.out.print("} ");
            }
            System.out.println();
        }



        }
}