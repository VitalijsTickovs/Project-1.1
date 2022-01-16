package renderer;
import java.lang.Math;
public class heuristic{
    public static  int getHeight( int[][][] array,int width,int length){
        int height =0;
        for(int i=0;i<array[0].length;i++){
                if(array[length][i][width]!=0){
                    height++;
            }
        }
        return height;
    }
    public static  int getHoles(int[][][] array){
        int holes =0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                for(int k=0;k<array[0][0].length;k++){
                    if(array[i][j][k]==0)
                        holes++;
                }
            }
        }
        return holes;
    }
    public static int Aggregate_Height( int[][][] array,int length){
        int aggre_height=0;
        for(int i=0;i<array[0].length;i++){
            aggre_height+=getHeight(array, i,length);
        }
        return aggre_height;
    }
    public static int bumpiness(int[][][] array,int length){
        int bump=0;
        for(int i=0;i<array[0].length-1;i++){
            bump+=Math.abs(getHeight(array, i, length)-getHeight(array, i+1, length));
        }
        return bump;
    }
    public static int score(int[][][] array){
        int score =0;
        for(int j=0;j<array.length;j++){
            score += -Aggregate_Height(array,j)-bumpiness(array,j);
        }
        return score;
   }
   public static int[][][] add(int[][][] array,int[][] piece,int length){
    int[][][] result = array;
    int[][][] base = new int[array.length][array[0].length][array[0][0].length];
    reset(base,array);
    boolean  test = true;
    for(int i=0;i<piece.length;i++){
        for(int j=0;j<piece[0].length;j++){
            if(array[length][i][j]!=0 && piece[i][j]!=0){ 
                test=false;
                break;
            }
            else
                array[length][i][j]+=piece[i][j];
            
        }
        if(!test)
            break;
    }
    if(test){
        reset(result,array);    
    }
    return result;
   }
   public static int[][][] addWidth(int[][][] array,int[][] piece,int width){
    int[][][] result = array;
    int[][][] base = new int[array.length][array[0].length][array[0][0].length];
    reset(base,array);
    boolean test = true;
    for(int i=0;i<piece.length;i++){
        for(int j=0;j<piece[0].length;j++){
            if(array[i][j][width]!=0 && piece[i][j]!=0){
                test=false;
                break;
            }
            else
                array[i][j][width]+=piece[i][j];
        }
        if(!test)
            break;
    }
    if(test)
        reset(result,array);
    
    return result;
   }
   public static boolean equal(int[][][] array,int[][][] matrice){
    for(int i=0;i<array.length;i++){
        for(int j=0;j<array[0].length;j++){
            for(int k=0;k<array[0][0].length;k++){
                if(array[i][j][k]!=matrice[i][j][k])
                    return false;
            }
        }
    }
    return true;
   }
   public static int[][][][] allposibillities(int[][][] field,int[][] piece){
       int[][][][] result = new int[500][field.length][field[0].length][field[0][0].length];
       int[][][] basefield = new int[field.length][field[0].length][field[0][0].length];
       reset(basefield,field);
       int[][]  basepiece= piece;
       int position=0;
         for(int i=0;i<field.length;i++){
            reset(field,basefield);
            int[][][] a= add(field,piece,i);
            if(!equal(basefield,a)){
                reset(result[position],a);
                position++;
            }

        for(int j=0;j<3;j++){
            piece=rotation(piece);
            
                reset(field,basefield);
                a= add(field,piece,i);
                if(!equal(a, basefield)){    
                    reset(result[position],a);
                    position++;
                }
            
        }
    }
        reset(piece,basepiece);
        piece= flip(piece);
         piece=rotation(piece);
        for(int i=0;i<field[0][0].length;i++){
            reset(field,basefield);
            int[][][] a= addWidth(field,piece,i);
            if(!equal(basefield,a)){
                reset(result[position],a);
                position++;
            }
   
        for(int j=0;j<3;j++){
            piece=rotation(piece);
            
                reset(field,basefield);
                a= addWidth(field,piece,i);
                if(!equal(a, basefield)){    
                    reset(result[position],a);
                    position++;
                }
            
        }
        
    } 
    int[][][][] finalresult = new int[position][33][5][8];
    int finalposition=0;
    for(int i=0;i<position;i++){
        if(!equal(result[i], basefield)){
            finalresult[finalposition]=result[i];      
                finalposition++;
        }
    }
     return finalresult;       
   }
   public static void print(int[][][] field){
    for(int p=0;p<field.length;p++){   
        for(int j=0;j<field[0].length;j++){
            for(int k=0;k<field[0][0].length;k++){
                System.out.print(field[p][j][k]+" ");
            }
        }
        System.out.println();
    } 
   }
   public static void reset(int[][][] field,int[][][] basefield){
    for(int p=0;p<field.length;p++){   
        for(int j=0;j<field[0].length;j++){
            for(int k=0;k<field[0][0].length;k++){
                field[p][j][k]=basefield[p][j][k];
            }
        }
    }
   }
   public static void reset(int[][] field,int[][] basefield){
    for(int p=0;p<field.length;p++){   
        for(int j=0;j<field[0].length;j++){
                field[p][j]=basefield[p][j];
            
        }
    }
   }
public static int[][] rotation(int[][] letter){
    for(int i=0;i<letter.length;i++) {  
        for(int j=i;j<letter.length;j++) {  
            int temp = letter[i][j];  
            letter[i][j] = letter[j][i];  
            letter[j][i] = temp;  
        }  
    }  
    for(int i=0;i<letter.length;i++) {      
        int low = 0, high = letter.length-1;  
        while(low < high) {  
            int temp = letter[i][low];  
            letter[i][low] = letter[i][high];  
            letter[i][high] = temp;  
            low++;  
            high--;  
        }  
    }  
    return letter;
} 
public static int[][] flip(int[][] theArray){
    for (int i = 0; i < (theArray.length / 2); i++) {
        int[] temp = theArray[i];
        theArray[i] = theArray[theArray.length - i - 1];
        theArray[theArray.length - i - 1] = temp;
    }
    return theArray;
}
public static int[][][] takebest12(int[][][][] array,int[][][][] matrix,int num1,int num2){
    int position1=0;
    int BestScore1=-999999999;
    int[][][] result1=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int[][][] result2=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int position2=0;
    int BestScore2=-999999999;
    for(int l=0;l<array.length;l++){
        if(score(array[l])>BestScore1){
            BestScore1=score(array[l]);
            position1=l;
        }
    }
    result1=array[position1];
    for(int l=0;l<matrix.length;l++){
        if(score(matrix[l])>BestScore2){
            BestScore2=score(matrix[l]);
            position2=l;
        }
    }
    result2=matrix[position2];
    if(BestScore1>BestScore2){
        numberOfL++;
        return result1;
    }
    numberOfT++;
    return result2;    
}
public static int[][][] takebest13(int[][][][] array,int[][][][] matrix,int num1,int num2){
    int position1=0;
    int BestScore1=-999999999;
    int[][][] result1=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int[][][] result2=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int position2=0;
    int BestScore2=-999999999;
    for(int l=0;l<array.length;l++){
        if(score(array[l])>BestScore1){
            BestScore1=score(array[l]);
            position1=l;
        }
    }
    result1=array[position1];
    for(int l=0;l<matrix.length;l++){
        if(score(matrix[l])>BestScore2){
            BestScore2=score(matrix[l]);
            position2=l;
        }
    }
    result2=matrix[position2];
    if(BestScore1>BestScore2){
        
        numberOfL++;

        return result1;
    } 
    else
        numberOfP++;
    return result2;    

}
public static int[][][] takebest23(int[][][][] array,int[][][][] matrix,int num1,int num2){
    int position1=0;
    int BestScore1=-999999999;
    int[][][] result1=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int[][][] result2=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int position2=0;
    int BestScore2=-999999999;
    for(int l=0;l<array.length;l++){
        if(score(array[l])>BestScore1){
            BestScore1=score(array[l]);
            position1=l;
        }
    }
    result1=array[position1];
    for(int l=0;l<matrix.length;l++){
        if(score(matrix[l])>BestScore2){
            BestScore2=score(matrix[l]);
            position2=l;
        }
    }
    result2=matrix[position2];
    if(BestScore1>BestScore2){
        numberOfT++;
        return result1;
    }
    else
        numberOfP++;
    return result2;    

}
public static int[][][] takebest(int[][][][] array,int[][][][] matrix,int[][][][] list){
    int position1=0;
    int BestScore1=-999999999;
    int[][][] result1=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int[][][] result2=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int[][][] result3=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int[][][] Fresult=new int[array[0].length][array[0][0].length][array[0][0][0].length];
    int position2=0;
    int BestScore2=-999999999;
    int position3=0;
    int BestScore3=-999999999;
    for(int l=0;l<array.length;l++){
        if(score(array[l])>BestScore1){
            BestScore1=score(array[l]);
            position1=l;
        }
    }
    result1=array[position1];
    for(int l=0;l<matrix.length;l++){
        if(score(matrix[l])>BestScore2){
            BestScore2=score(matrix[l]);
            position2=l;
        }
    }
    result2=matrix[position2];
    for(int l=0;l<list.length;l++){
        if(score(list[l])>BestScore3){
            BestScore3=score(list[l]);
            position3=l;
        }
    }
    result3=list[position3];
    if(BestScore1>=BestScore2){
        if(BestScore1>=BestScore3){
            numberOfL++;
            return result1;
        }
        else{
            numberOfP++;
            return result3;
        }
    }
    else if(BestScore2>BestScore1){
        if(BestScore2>=BestScore3){
            numberOfT++;
            return result2;
        }
        else{
            numberOfP++;
            return result3;
        }
    }  
    return Fresult;
}
public static int numberOfL=0;
public static int numberOfT=0;
public static int numberOfP=0;
public static int value(int a,int b,int c){
    return a*numberOfL+b*numberOfT+c*numberOfP;
} 
/** 
public static int[][][] finaresult(int a,int b,int c){
    int[][] T={  {1,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0} };
    int[][] L={  {2,0,0,0},{2,0,0,0},{2,2,2,0},{0,0,0,0}};
    int[][] I={  {3,0,0,0,0},{3,0,0,0,0},{3,0,0,0,0},{3,0,0,0,0},{3,0,0,0,0}};
    int[][][] field=new int[33][5][8];
    for(int kl=0;kl<a+b+c;kl++){
        int[][][][] result1 = allposibillities(field, L);
        int[][][][] result2 = allposibillities(field, T);
        int[][][][] result3 = allposibillities(field, I);
        if(result1.length==0 && result2.length==0 && result3.length==0  ){
            System.out.println("oh-oh,too much "+kl);
            break;
         }
        else if(result1.length==0 && result2.length==0 && numberOfP<=c){
            field=takebest(result3, result3,numberOfP,numberOfP);
            System.out.println(numberOfP);
         }
         else if(result1.length==0 && result3.length==0 && numberOfT<=b){
            field=takebest(result2, result2,numberOfT,numberOfT);
            System.out.println(numberOfT);
         }
         else if(result2.length==0 && result3.length==0 &&  numberOfL<=a){
            field=takebest(result1, result1,numberOfL,numberOfL);
         }
        else if(result1.length==0 && numberOfT<=b && numberOfP<=c){
           field=takebest(result2, result3,numberOfT,numberOfP);
        }
        else if(result2.length==0 &&  numberOfL<=a && numberOfP<=c){
            field=takebest(result1, result3,numberOfL,numberOfT);
        }
        else if(result3.length==0 &&  numberOfL<=a && numberOfT<=b){
            field=takebest(result1, result2,numberOfL,numberOfP);
        }
        else if( numberOfL<=a && numberOfT<=b && numberOfP<=c){    
            field=takebest(result1, result2, result3);
        }  
     }
     return field;
  }
  */
  public static int[][][] finaresult(int a,int b,int c){
    int[][] T={  {1,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0} };
    int[][] L={  {2,0,0,0},{2,0,0,0},{2,2,2,0},{0,0,0,0}};
    int[][] P={  {3,3,0,0,0},{3,3,0,0,0},{3,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    int[][][] field=new int[33][5][8];
    for(int kl=0;kl<a+b+c;kl++){
        int[][][][] result1 = allposibillities(field, L);
        int[][][][] result2 = allposibillities(field, T);
        int[][][][] result3 = allposibillities(field, P);
        System.out.println(numberOfL+" "+numberOfT+" "+numberOfP);
        if(result1.length==0 && result2.length==0 && result3.length==0  ){
            System.out.println("oh-oh,too much "+kl);
            break;
         }
         if(numberOfL<=a && numberOfT<=b && numberOfP<=c){
            if(result1.length==0 && result2.length==0)
                field=takebest13(result3, result3,numberOfP,numberOfP);
            else if(result1.length==0 && result3.length==0)
                field=takebest12(result2, result2,numberOfT,numberOfT);
            else if(result2.length==0 && result3.length==0)
                field=takebest12(result1, result1,numberOfL,numberOfL);
            else if(result1.length==0)
                field=takebest23(result2, result3,numberOfT,numberOfP);
            else if(result2.length==0)
                field=takebest13(result1, result3,numberOfL,numberOfP);
            else if(result3.length==0)
                field=takebest12(result1, result2,numberOfL,numberOfT);
            else 
                field=takebest(result1, result2, result3);
            System.out.println(numberOfL+" "+numberOfT+" "+numberOfP);
         }
         else if(numberOfL>=a && numberOfT>=b && numberOfP>=c){
            return field;
        }
         else if(numberOfL>=a && numberOfT>=b ){
            if(result3.length==0){
                return field;
            }
            else
                field=takebest13(result3, result3,numberOfP,numberOfP);
        }
        else if(numberOfL>=a && numberOfP>=c ){
            if(result2.length==0){
                return field;
            }
            else
                field=takebest12(result2, result2,numberOfT,numberOfT);
        }
        else if(numberOfT>=b && numberOfP>=c ){
            if(result1.length==0){
                return field;
            }
            else
                field=takebest12(result1, result1,numberOfL,numberOfL);
        }
        if(numberOfL>=a){
            if(result2.length==0 && result3.length==0)
                return field;
            else if(result2.length==0){
                field=takebest13(result3, result3,numberOfP,numberOfP);
            }
            else if(result3.length==0)
                field=takebest12(result2, result2,numberOfT,numberOfT);
            else
                field=takebest23(result2, result3,numberOfT,numberOfP);
        }
        else if(numberOfT>=b){
            if(result1.length==0 && result3.length==0)
                return field;
            else if(result1.length==0){
                field=takebest13(result3, result3,numberOfP,numberOfP);
            }
            else if(result3.length==0){
                field=takebest12(result1, result1,numberOfL,numberOfL);
            }
            else
                field=takebest13(result1, result3,numberOfL,numberOfP);
        }
        else if(numberOfP>=c){
            if(result1.length==0 && result2.length==0)
                return field;
            else if(result1.length==0){
                field=takebest12(result2, result2,numberOfT,numberOfT);
            }
            else if(result2.length==0){
                field=takebest12(result1, result1,numberOfL,numberOfL);
            }
            else
                field=takebest13(result1, result3,numberOfL,numberOfP);
        }
        
    }
    return field;
}
        /** 
        else if(result1.length==0 && result2.length==0 && numberOfP<=c){
            field=takebest(result3, result3,numberOfP,numberOfP);
            System.out.println(numberOfP);
         }
         else if(result1.length==0 && result3.length==0 && numberOfT<=b){
            field=takebest(result2, result2,numberOfT,numberOfT);
            System.out.println(numberOfT);
         }
         else if(result2.length==0 && result3.length==0 &&  numberOfL<=a){
            field=takebest(result1, result1,numberOfL,numberOfL);
         }
        else if(result1.length==0 && numberOfT<=b && numberOfP<=c){
           field=takebest(result2, result3,numberOfT,numberOfP);
        }
        else if(result2.length==0 &&  numberOfL<=a && numberOfP<=c){
            field=takebest(result1, result3,numberOfL,numberOfT);
        }
        else if(result3.length==0 &&  numberOfL<=a && numberOfT<=b){
            field=takebest(result1, result2,numberOfL,numberOfP);
        }
        else if( numberOfL<=a && numberOfT<=b && numberOfP<=c){    
            field=takebest(result1, result2, result3);
        }  
     }
     */

}