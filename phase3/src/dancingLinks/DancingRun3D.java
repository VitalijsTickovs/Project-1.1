package dancingLinks;

import java.util.ArrayList;
import java.util.Random;


public class DancingRun3D {

    /**
	 * find the highest scoring placement after a set of tries using a mixture of monte carlo and dancing links
	 * @param timeGiven the time allowed for the dancing links to search per try (in ms)
     * @param tries the number of tries with randomly generated move prioritues to try searching for states with high scores
     * @param boxes boolean that determines wether we try boxes, or pentominos (true for boxes, false for pentominos)
	 * @return	a 3d int array with each layer representing the coordinates filled in by a piece
	 */
   public static int[][][] getSolution(int timeGiven,int tries,boolean boxes) {
    

    int[] counts = {0,0,0};

    int[] input = {1,1,1};

    int height = 8;
    int width = 5;
    int length = 33;
    

    long firsttime = System.currentTimeMillis();
    int[][][] field = new int[height][width][length];

    for(int i = 0; i < height; i++)
    {
        for(int j = 0; j < width; j++)
        {   
            for(int k = 0; k < length; k++){
            // -1 in the state matrix corresponds to empty square
            // Any positive number identifies the number of the piece
            field[i][j][k] = -1;
            }
        }
    }
       
    boolean[][] testTable;
    if(boxes){
    testTable = DancingTable3DUnlimited.MakeTable(field, input);
    }else{
    testTable = DancingTable3DUnlimited.MakeTable(field, input,true);
    }

    if(testTable.length==0){
        System.out.println("impossible (not enough physical space)");
        int[][][] r = {{{}}};
        return r;



    }else{

    System.out.println(testTable.length);
    
    /*for(int i = 0;i < testTable.length;i++){
        for(int j = 0; j <testTable[0].length;j++){
            if(testTable[i][j]){
                System.out.print("1 ");
            }else{
                System.out.print("0 ");
            }
            
            }
        System.out.println();
        }*/
    System.out.println("time taken for making of the table in ms: " + (System.currentTimeMillis()-firsttime));

    int[] maximums = new int[1320];
    boolean solved = false;
    ArrayList<Square> solution = new ArrayList<>();
    int score = 0;
    


    for (int i = 0; i < tries ; i++) {
        DancingListOpt test = new DancingListOpt(TableToList.ToList(testTable));    
        
        if(test.search(0,timeGiven,System.currentTimeMillis(),boxes)){
            solved = true;
        }
        System.out.println("try " + i + ", perfect solution found: " + solved);
        maximums[test.maxScore]++;
        
        if(test.maxScore > score){
            solution.clear();
            for (Square square : test.maxOs) {
                solution.add(square);
            }
            score = test.maxScore;
            
        }

        
        shuffle(testTable);

    }   

    System.out.println("time taken for dancing links: " + (System.currentTimeMillis()-firsttime));
    
    System.out.println("number of pieces : " + solution.size());
    System.out.println("best score : " + score);
    System.out.println();

    /*for (int i = 0; i < maximums.length; i++) {
        if(maximums[i]>0){
            System.out.println(i + ": " + maximums[i]);
        }
    }*/

    
   
   
   
   
   
    boolean[] cells = new boolean[1320];
    ArrayList<int[][]> solutionArrays = new ArrayList<>();

    for (Square i : solution) {
        if(!boxes){
            if(i.row<13968){
                counts[0]++;
            }else{
                if(i.row<30088){
                    counts[1]++;
                }else{
                    counts[2]++;
                }
            }
        }
        
        
        int ii = Integer.parseInt(i.C.N)-1;
        //System.out.print("("+ (ii%165)/33 + ", " + ii/165 + ", " + (ii%165)%33 + ") ");
        //System.out.print(ii + " ");
        if(!cells[ii]){
            cells[ii] = true;
        }else{
            System.out.println("duplicate at cell " + (ii+1));
        }
        
        int count = 1;
        for(Square k = i.R;!k.equals(i);k=k.R){
            count++;
        }
    
        if(count == 16){
            counts[0]++;
        }else{
            if(count == 24){
                counts[1]++;

            }else{
                if(count == 27){
                    counts[2]++;
                }
            }
        }

        int[][] piece = new int[count][3];
        piece[0][0] = (ii%165)/33;
        piece[0][1] = ii/165;
        piece[0][2] = (ii%165)%33;
    

        Square j = i.R;
        int index = 0;
        while(!j.equals(i)){
            index++;
            int jj = Integer.parseInt(j.C.N)-1;
            //System.out.print("("+ (jj%165)/33 + ", " + jj/165 + ", " + (jj%165)%33 + ") ");
            //System.out.print(jj + " ");
            if(!cells[jj]){
                cells[jj] = true;

                piece[index][0] = (jj%165)/33;
                piece[index][1] = jj/165;
                piece[index][2] = (jj%165)%33;

            }else{
                System.out.println("duplicate at cell " + (jj+1));
            }

            j = j.R;
        }


        solutionArrays.add(piece);

        





        /*if(count == 16){
            System.out.print("  A");
        }else{
            if (count == 24) {
                System.out.print("  B");;
            } else {
                System.out.print("  C");
            }
        }
        System.out.println(); */

        

    }

    System.out.println("P/A pieces used : " + counts[0]);
    System.out.println("L/B pieces used : " + counts[1]);
    System.out.println("T/C pieces used : " + counts[2]);
    boolean done = true;
        for (int k = 0; k < cells.length; k++) {
            if(!cells[k]){
                done = false;
            }
        }

    System.out.println("max found completely fills the space: "  + done);  
        System.out.println("array size: " + solutionArrays.size());
    int[][][] result = new int[solutionArrays.size()][][];
        for (int k = 0; k < solutionArrays.size(); k++) {
            result[k] = solutionArrays.get(k);
        }
        //result = solutionArrays.toArray(result);
    return result;



    
    












    }


                            
                            
   }

   public static void Show(ArrayList<boolean[]> solution){



    //System.out.println(solution.size());
    boolean[][] realSolution= new boolean[solution.size()][solution.get(solution.size()-1).length];


    for (int i = 0; i <  solution.size(); i++) {
        int offset = 0;
        for (int j = 0; j < realSolution[0].length; j++) {
            boolean flag = false;
            for(int k = 0;k<i;k++){
                if(realSolution[k][j]){
                    flag = true;
                }
            }
            if(flag){
                realSolution[i][j]=false;
            }else{
                    realSolution[i][j]=solution.get(solution.size()-1-i)[offset];
                    offset++;
            }
        }
    }

    /*for(int i = 0;i < realSolution.length;i++){
        for(int j = 0; j <realSolution[0].length;j++){
            if(realSolution[i][j]){
                System.out.print("1 ");
            }else{
                System.out.print("0 ");
            }
            
        }
        System.out.println();
    }*/
    System.out.println(realSolution.length);
    

    /*for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
            for (int k = 0; k < realSolution.length; k++) {
                if(realSolution[k][realSolution.length+j+i*horizontalGridSize]){
                    field[i][j]=k%12;
                }
            }
        }        
    }

    for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
            System.out.print(field[i][j]+ " ");
            
        }
        System.out.println();
        
    }*/


    
    //ui.setState(field);


    
    
    


    




   }


   public static void shuffle(boolean[][] a){
    int n;
    Random r = new Random();

    for(int i = a.length-1;i >=1;i--){
        n = r.nextInt(i+1);
        if(n!=i){
            boolean[] tempRow;
            tempRow = a[n];
            a[n]= a[i];
            a[i] = tempRow;
        }
    }


   }

  
}
