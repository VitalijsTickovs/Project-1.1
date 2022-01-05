import java.util.ArrayList;

public class DancingRun3D {


   public static void main(String[] args) {


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
       
    
    /*boolean[][] testTable = {{false, false, true, false, true, true, false},
                                {true, false,false,true,false,false,true},
                                {false, true, true,false,false, true,false },
                                {true, false, false,true,false,false,false},
                                {false, true,false,false,false,false, true},
                                {false,false,false,true,true,false, true} 
                            };*/

    //boolean[][] testTable = {{true, true,false},{false, true,true}};

    boolean[][] testTable = DancingTable3DUnlimited.MakeTable(field, input);

    if(testTable.length==0){
        System.out.println("impossible (not enough physical space)");
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

    DancingListOpt test = new DancingListOpt();
    boolean solved = false;
    if(test.search(0,TableToList.ToList(testTable))){
        solved = true;
    }
    System.out.println(solved);

    ArrayList<Square> Os = test.maxOs;

    System.out.println("time taken for dancing links: " + (System.currentTimeMillis()-firsttime));
    
    System.out.println("number of pieces (maybe): " + Os.size());
    System.out.println();
    for (Square i : Os) {
        System.out.print(i.C.N + " ");
        Square j = i.R;
        int count = 1;
        while(!j.equals(i)){
            count++;
            System.out.print(j.C.N + " ");
            j = j.R;
        }

        if(count == 16){
            System.out.print("  A");
        }else{
            if (count == 24) {
                System.out.print("  B");;
            } else {
                System.out.println("  C");
            }
        }
        System.out.println(); 

    }
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

  
}
