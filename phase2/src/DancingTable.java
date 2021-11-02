
import java.util.ArrayList;

public class DancingTable {
    //boolean[][] bigTable;

    public static boolean[][] MakeTable(int[][] emptyField, char[] input){
        boolean[][] bigTable;
        int height = emptyField.length;
        int width = emptyField[0].length;
        int n = input.length;


        ArrayList<boolean[]> tempTable = new ArrayList<boolean[]>();
        
        for(int s = 0;s<input.length;s++){
            char shape = input[s]; 
            int shapeid = characterToID(shape);
            for (int[][] shapeLayout : PentominoDatabase.data[shapeid]) {
                for(int i = 0;i <height-shapeLayout.length+1;i++){
                    for(int j = 0; j <width-shapeLayout[0].length+1;j++){

                    boolean[] newrow = new boolean[n+height*width];

                    newrow[s]=true;

                    for(int ii=0;ii<shapeLayout.length;ii++){
                        for(int jj=0;jj<shapeLayout[0].length;jj++){
                            if(shapeLayout[ii][jj]>0){
                                newrow[n+width*(i+ii)+j+jj]= true;
                            }

                        }
                    }

                    tempTable.add(newrow);


                    }
                }
            }
            

        }
        bigTable = new boolean[tempTable.size()][n+height*width];
        bigTable =  tempTable.toArray(bigTable);

        return bigTable;





    }

    public static int characterToID(char character) {
    	int pentID = -1; 
    	if (character == 'X') {
    		pentID = 0;
    	} else if (character == 'I') {
    		pentID = 1;
    	} else if (character == 'Z') {
    		pentID = 2;
    	} else if (character == 'T') {
    		pentID = 3;
    	} else if (character == 'U') {
    		pentID = 4;
     	} else if (character == 'V') {
     		pentID = 5;
     	} else if (character == 'W') {
     		pentID = 6;
     	} else if (character == 'Y') {
     		pentID = 7;
    	} else if (character == 'L') {
    		pentID = 8;
    	} else if (character == 'P') {
    		pentID = 9;
    	} else if (character == 'N') {
    		pentID = 10;
    	} else if (character == 'F') {
    		pentID = 11;
    	} 
    	return pentID;
    }
}


///hsgciuyeycve