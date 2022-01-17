package dancingLinks;


import java.util.ArrayList;



public class DancingTable3DUnlimited {
    //boolean[][] bigTable;


    /**
     * Method to make boolean tables for dancing links assuming we have unlimited of each piece in input
     * @param emptyField emty 3D space to be filled
     * @param input array determining how many of each box (input[0] = number of A boxes etc)
     * @return table that represents each possible placement of each box, that, when solved with dancing links returns a solution
     */
    public static boolean[][] MakeTable(int[][][] emptyField, int[] input){
        


        int[] Adims = {2,2,4}; //16
        int[] Bdims = {2,3,4}; //24
        int[] Cdims = {3,3,3}; //27



        //55*B
        	
        //making a 1 unit large box to fill all the empty spaces
        

        int Amutations = 3;
        int Bmutations = 6;
        int Cmutations = 1;

        


        //making a new input that adds the D boxes
        //int emptySpace = emptyField.length*emptyField[0].length*emptyField[0][0].length-(firstInput[0]*16+firstInput[1]*24+firstInput[2]*27);


        //int[] input = {firstInput[0],firstInput[1],firstInput[2],emptySpace};

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

                            boolean[] newrow = new boolean[height*width*length];

                            //newrow[s]=true;
                            //for each cell that the piece will cover becomes true (a 1) in the table
                            for(int ii=0;ii<Shape[(0 + mutation + mutation/3)%3];ii++){
                                for(int jj=0;jj<Shape[(1 + mutation - mutation/3)%3];jj++){
                                    for(int kk=0;kk<Shape[(2 + mutation)%3];kk++){
                                        newrow[length*width*(i+ii)+ length*(j+jj) + k+kk]= true;
                                    }

                                }
                            }

                            tempTable.add(newrow);
                        }

                    }
                }
            }
            System.out.println("number of rows : " + tempTable.size() + "  " + ShapeMutations);
            

        }





        bigTable = new boolean[tempTable.size()][n+height*width*length];
        bigTable =  tempTable.toArray(bigTable);

        return bigTable;





    }

    public static boolean[][] MakeTable(int[][][] emptyField, int[] input, boolean thing){


        boolean[][] bigTable;
        int height = emptyField.length;
        int width = emptyField[0].length;
        int length = emptyField[0][0].length;

        
        int n = input[0]+input[1]+input[2];


        int[] ids = {8,9,3};//ids of the L, P and T pieces respectively
        


        ArrayList<boolean[]> tempTable = new ArrayList<boolean[]>();
        
        for(int s = 0;s<n;s++){
            System.out.println("number of rows : " + tempTable.size());
             
            int shapeid = ids[s];

            for(int hs = 0;hs<height;hs++){
            
            for (int[][] shapeLayout : PentominoDatabase.data[shapeid]) {
                for(int i = 0;i <width-shapeLayout.length+1;i++){
                    for(int j = 0; j <length-shapeLayout[0].length+1;j++){

                    boolean[] newrow = new boolean[height*width*length];

                    //newrow[s]=true;

                    for(int ii=0;ii<shapeLayout.length;ii++){
                        for(int jj=0;jj<shapeLayout[0].length;jj++){
                            if(shapeLayout[ii][jj]>0){
                                newrow[length*width*(hs)+length*(i+ii)+j+jj]= true;
                            }

                        }
                    }

                    tempTable.add(newrow);


                    }
                }
            }
            }
            

            for(int ws = 0;ws<width;ws++){
            
                for (int[][] shapeLayout : PentominoDatabase.data[shapeid]) {
                    for(int i = 0;i <height-shapeLayout.length+1;i++){
                        for(int j = 0; j <length-shapeLayout[0].length+1;j++){
    
                        boolean[] newrow = new boolean[height*width*length];
    
                        //newrow[s]=true;
    
                        for(int ii=0;ii<shapeLayout.length;ii++){
                            for(int jj=0;jj<shapeLayout[0].length;jj++){
                                if(shapeLayout[ii][jj]>0){
                                    newrow[length*width*(i+ii)+length*(ws)+j+jj]= true;
                                }
    
                            }
                        }
    
                        tempTable.add(newrow);
    
    
                        }
                    }
                }
            }
            
            for(int ls = 0;ls<width;ls++){
            
                for (int[][] shapeLayout : PentominoDatabase.data[shapeid]) {
                    for(int i = 0;i <height-shapeLayout.length+1;i++){
                        for(int j = 0; j <width-shapeLayout[0].length+1;j++){
    
                        boolean[] newrow = new boolean[height*width*length];
    
                        //newrow[s]=true;
    
                        for(int ii=0;ii<shapeLayout.length;ii++){
                            for(int jj=0;jj<shapeLayout[0].length;jj++){
                                if(shapeLayout[ii][jj]>0){
                                    newrow[length*width*(i+ii)+length*(j+jj)+ls]= true;
                                }
    
                            }
                        }
    
                        tempTable.add(newrow);
    
    
                        }
                    }
                }
            }
            System.out.println("number of rows : " + tempTable.size());
            

        }
        bigTable = new boolean[tempTable.size()][n+height*width];
        bigTable =  tempTable.toArray(bigTable);

        return bigTable;







    }

}

