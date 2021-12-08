import java.lang.*;

class Bot_heuristic{
    public  int getHeight( int[][] array,int column){
    
        int height =0;
        for(int i=0;i<array.length;i++){
                if(array[i][column]!=0){
                    height = 4-i;
                    return height;
            }
        }
        return height;
    }
    public int getHoles(int[][] array){
        int holes =0;
        for(int i=1;i<array.length;i++){
            for(int j=1;j<array[0].length;j++){
                if(array[i][j]==-1 && array[i-1][j]!=-1)
                    holes++;
            }
        }
        return holes;
    }
    public int clearLines(){
        int array[][]= new int[10][10];
        int width=0;
        int lines=0;
        for(int i=1;i<array.length;i++){
            int number =0;
            for(int j=1;j<array[0].length;j++){
                if(array[i][j]!=-1){
                    number++;
                }
            }
            if(number ==width){
                lines++;
            }
        }
        return lines;
    }
    public   int Aggregate_Height( int[][] array){
        int aggre_height=0;
        for(int i=0;i<array[0].length;i++){
            aggre_height+=getHeight(array, i);
        }
        return aggre_height;
    }
    
    public  int Bumpiness(int[][] array){
        int bump=0;
        for(int i=0;i<array[0].length-1;i++){
            System.out.println(bump);
        }
        return bump;
    }
    /** 
    public int score(int[] array){
         int score = -0.510066*Aggregate_Height(array) +0.760666*clearLines()-0.35663*getHoles(array)-0.184483*Bumpiness(array);
         return score;
    }
    */
    public int scoreHeur(int[][] array){
        int score = Aggregate_Height(array) +clearLines()-getHoles(array)-Bumpiness(array);
        return score;
   }
    /** 
    public static  void main(String[] args){
        int[][] array= {{0,0,0,0} ,
                        {0,0,1,0} ,
                        {1,0,1,1} ,
                        {1,1,1,1}};
        System.out.println(getHeight(array,2));
        System.out.println(Bumpiness(array));                
    }
    */
}