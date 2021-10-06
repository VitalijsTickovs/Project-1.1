public class test {


    public static final int horizontalGridSize = 5;
    public static final int verticalGridSize = 10;
    
    public static final char[] input = { 'L','F','I','X','U','V','N','T','W','P'};
   public static void main(String[] args) {


    long firsttime = System.currentTimeMillis();

    
    int[][] field = new int[horizontalGridSize][verticalGridSize];

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

                            Dancing_links test = new Dancing_links();
                            System.out.println(test.Solving(testTable));
                            System.out.println("time taken in ms: " + (System.currentTimeMillis()-firsttime));
                            
   }

  
}
