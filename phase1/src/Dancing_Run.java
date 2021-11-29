import java.util.ArrayList;

public class Dancing_Run {


    public static final int horizontalGridSize = 6;

    //for efficiency make vertical grid size the large one:)
    public static final int verticalGridSize = 5;

    public static UI ui = new UI(verticalGridSize,horizontalGridSize,50);
    
    public static final char[] input = { 'U','V','N','T','W','P','Z','Y','I'};
    //public static final char[] input = { 'I','X','L','Z','F','P','W','N','V'};
    //public static final char[] input = {'L','V','N'};
   public static void main(String[] args) {


    long firsttime = System.currentTimeMillis();

    
    int[][] field = new int[verticalGridSize][horizontalGridSize];

    for(int i = 0; i < field.length; i++)
    {
        for(int j = 0; j < field[i].length; j++)
        {
            // -1 in the state matrix corresponds to empty square
            // Any positive number identifies the ID of the pentomino
            field[i][j] = -1;
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

    boolean[][] testTable = DancingTable.MakeTable(field, input);


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

                            Dancing_links test = new Dancing_links();
                            boolean solved = false;
                            if(test.Solving(testTable)){
                                solved = true;
                            }
                            System.out.println(solved);

                            ArrayList<boolean[]> ourSolution = test.solution;


                            System.out.println();
                            //System.out.println(ourSolution.size());

                            if(solved){
                                Show(ourSolution);
                            }

                            System.out.println("time taken for algorithm in ms: " + (System.currentTimeMillis()-firsttime));
                            
   }

   public static void Show(ArrayList<boolean[]> solution){


    int[][] field = new int[verticalGridSize][horizontalGridSize];

    for(int i = 0; i < field.length; i++)
    {
        for(int j = 0; j < field[i].length; j++)
        {
            // -1 in the state matrix corresponds to empty square
            // Any positive number identifies the ID of the pentomino
            field[i][j] = -1;
        }
    }

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
    }
    System.out.println("pasue");
    */

    for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
            for (int k = 0; k < realSolution.length; k++) {
                if(realSolution[k][realSolution.length+j+i*horizontalGridSize]){
                    field[i][j]=k%12;
                }
            }
        }        
    }

    /*for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
            System.out.print(field[i][j]+ " ");
            
        }
        System.out.println();
        
    }*/


    
    ui.setState(field);


    
    
    


    




   }

  
}
