public class test {
    public static void main(String[] args) {

        int[][][] field = new int[4][3][3];

        int[] input = {2,1,1};


        boolean[][] testTable = DancingTable3D.MakeTable(field, input);





        for(int i = 0;i < testTable.length;i++){
            for(int j = 0; j <testTable[0].length;j++){
             if(testTable[i][j]){
                 System.out.print("1 ");
             }else{
                    System.out.print("0 ");
             }
            
            }
            System.out.println();
            System.out.println();
        }
    }
}
