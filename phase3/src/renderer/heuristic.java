import java.util.Arrays; 
import java.lang.Math;
import java.lang.reflect.Field;
public class heuristic{
    public static void sort(int[] array){
        Arrays.sort(array); 
    }
    public static int[] Sortedratio(int[] size,int[] value){
        int[] result =new int[size.length];
        for(int i=0;i<result.length;i++){
            result[i]=value[i]/size[i];
        }
        Arrays.sort(result);
        return result;
    }
    public static int[] tryfit(int max,int[] size,int[] value){
        int[] result= new int[size.length];
        int[] arratio=Sortedratio(size,value);
        int a=0;
        int b=0;
        int c=0;
        int actual_size=0;
        int a_position=0;
        int b_position=0;
        int c_position=0;
        for(int i=0;i<arratio.length;i++){
            if(arratio[i]==value[0]/size[0]){
                a_position=i;
                continue;
            }
            if(arratio[i]==value[1]/size[1]){
                b_position=i;
                continue;
            }
            if(arratio[i]==value[2]/size[2]){
                c_position=i;
                continue;
            }
        }
        while(actual_size<=max-31){
            System.out.println("Hello World"+ actual_size);
            if(a_position==0 && actual_size+size[0]<=max){
                a++;
                actual_size+=size[0];
            }
            if(b_position==0 && actual_size+size[1]<=max){
                b++;
                actual_size+=size[1];
            }
            if(c_position==0 && actual_size+size[2]<=max){
                c++;
                actual_size+=size[2];
            } 
        }
        while(actual_size<=max-31){
            if(a_position==1 && actual_size+size[0]<=max){
                a++;
                actual_size+=size[0];
            }
            if(b_position==1 && actual_size+size[1]<=max){
                b++;
                actual_size+=size[1];
            }
            if(c_position==1 && actual_size+size[2]<=max){
                c++;
                actual_size+=size[2];
            } 
        }
        while(actual_size<=max-31){
            if(a_position==2 && actual_size+size[0]<=max){
                a++;
                actual_size+=size[0];
            }
            if(b_position==2 && actual_size+size[1]<=max){
                b++;
                actual_size+=size[1];
            }
            if(c_position==2 && actual_size+size[2]<=max){
                c++;
                actual_size+=size[2];
            } 
        }
        result[0]=a;
        result[1]=b;
        result[2]=c;
        return result;
    }

// widht,height,length
    
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
            score += -Aggregate_Height(array,j)-getHoles(array)-bumpiness(array,j);
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
/** 
public static void main(String[] args){
    int[][] T={  {1,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0} };
    int[][] L={  {1,0,0,0},{1,0,0,0},{1,1,1,0},{0,0,0,0}};
    int[][] I={  {1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};      
    int[][][] field=new int[33][5][8];
    int position=0;
    int BestScore=-999999999;
     for(int kl=0;kl<130;kl++){
         position=0;
         BestScore=-999999999;
        int[][][][] result = allposibillities(field, T);
        //System.out.print(result.length+" ");
        for(int l=0;l<result.length;l++){
            if(score(result[l])>BestScore){
                BestScore=score(result[l]);
                position=l;
            }
        }
           
                
            field=result[position];
     }
     /** 
     for(int kl=0;kl<80;kl++){
        BestScore=-999999999;
        position=0;
        int[][][][] result = allposibillities(field, I);
        for(int l=0;l<result.length;l++){
            if(score(result[l])>BestScore){
                BestScore=score(result[l]);
                position=l;
            }
            }
            field=result[position];
            //System.out.print(position+" "+BestScore+" next ");
     }
     
     print(field);
    
  
    System.out.println(BestScore);
    }
*/
}