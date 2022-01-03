
import java.util.ArrayList;



public class DancingTable3D {
    //boolean[][] bigTable;


    //for the boxes
    public static boolean[][] MakeTable(int[][][] emptyField, int[] input){


        int[] Adims = {2,2,4};
        int[] Bdims = {2,3,4};
        int[] Cdims = {3,3,3};

        int Amutations = 3;
        int Bmutations = 6;
        int Cmutations = 1;


        boolean[][] bigTable;
        int height = emptyField.length;
        int width = emptyField[0].length;
        int length = emptyField[0][0].length;
        int n = input[0]+input[1]+input[2];


        ArrayList<boolean[]> tempTable = new ArrayList<boolean[]>();
        
        for(int s = 0;s<n;s++){
            
            int[] Shape;
            int ShapeMutations;
            if(s<input[0]){
                Shape = Adims;
                ShapeMutations = Amutations;
            }else{
                if(s<input[1]+input[0]) {
                    Shape = Bdims;
                    ShapeMutations = Bmutations;
                }else{
                    Shape = Cdims;
                    ShapeMutations = Cmutations;
                }
            }



            for (int mutation = 0;mutation < ShapeMutations;mutation++) {
                //for each possible placement

                //(all the +mutations and %s are just fun ways to swap the dimensions around for each mutation)
                for(int i = 0;i <height-Shape[(0 + mutation + mutation/3)%3]+1;i++){
                    for(int j = 0; j <width-Shape[(1 + mutation - mutation/3)%3]+1;j++){
                        for(int k = 0; k < length-Shape[(2 + mutation)%3]+1;k++){

                            boolean[] newrow = new boolean[n+height*width*length];

                            newrow[s]=true;
                            //for each cell that the piece will cover becomes true (a 1) in the table
                            for(int ii=0;ii<Shape[(0 + mutation + mutation/3)%3];ii++){
                                for(int jj=0;jj<Shape[(1 + mutation - mutation/3)%3];jj++){
                                    for(int kk=0;kk<Shape[(2 + mutation)%3];kk++){
                                        newrow[n+length*width*(i+ii)+ length*(j+jj) + k+kk]= true;
                                    }

                                }
                            }

                            tempTable.add(newrow);
                        }

                    }
                }
            }
            

        }





        bigTable = new boolean[tempTable.size()][n+height*width*length];
        bigTable =  tempTable.toArray(bigTable);

        return bigTable;





    }

}


///hsgciuyeycve