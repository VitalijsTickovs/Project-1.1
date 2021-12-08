
class bot{
    static int height =15;
    static int width = 5;
    /**
     *  
     * @param field it is the current field in which we are going to look for or position of the piece
     * @param piece the piece that we are adding to our current field 
     * @return this method returns a 3D array,containing all the possible position(with all rotations) of the piece in the current field
     */
     public static  int[][][] combination(int[][] field,char piece){     
        Field c =new Field(height,width,field);
        int p=0;
        c.AddPiece(piece);
        int number=0;
        while(c.left()){
        }
          
        while(c.right()){ 
           number++;
        }

       //2th rotation

        Field d =new Field(height,width,field);
        d.AddPiece(piece);
        int number2=0;
        while(d.left()){
        }
        d.rotate();
        while(d.right()){ 
           number2++;
        }
        //3th rotation

        Field e =new Field(height,width,field);
        e.AddPiece(piece);
        int number3=0;
        while(e.left()){
        }
        e.rotate();
        e.rotate();
        while(e.right()){ 
           number3++;
        }
        //4th rotation
        //
        Field f =new Field(height,width,field);
        f.AddPiece(piece);
        int number4=0;
        while(f.left()){
        }
        f.rotate();
        f.rotate();
        f.rotate();
        while(f.right()){ 
           number4++;
        }
        //10th rotation
        Field g =new Field(height,width,field);
        g.AddPiece(piece);
        int number5=0;
        while(g.left()){
        }
        g.rotate();
        g.rotate();
        g.rotate();
        g.rotate();
        while(g.right()){ 
           number5++;
        }
         //6th rotation
         Field h =new Field(height,width,field);
         h.AddPiece(piece);
         int number6=0;
         while(h.left()){
         }
         h.rotate();
         h.rotate();
         h.rotate();
         h.rotate();
         h.rotate();
         while(h.right()){ 
            number6++;
         }
        
         //7th rotation
        Field l =new Field(height,width,field);
        l.AddPiece(piece);
        int number7=0;
        while(l.left()){
        }
        l.rotate();
        l.rotate();
        l.rotate();
        l.rotate();
        l.rotate();
        l.rotate();
        while(l.right()){ 
           number7++;
        }
         //8th rotation
         Field x =new Field(height,width,field);
         x.AddPiece(piece);
         int number8=0;
         while(x.left()){
         }
         x.rotate();
         x.rotate();
         x.rotate();
         x.rotate();
         while(x.right()){ 
            number8++;
         }
       
        int tot_number=number+number2+number3+number4+number5+number6+number7+number8+8;
        int[][][] result=new int[tot_number][field.length][field[0].length];
        for(int i=0;i<=number;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }

            for(int j=0;j<i;j++){
                b.right();
            }
            while(b.down()){                  
                }
                for(int k=0;k<field.length;k++){
                    if(field[k][i]!=-1){
                        
                    break;
                    }
                    else
                    b.down();
                }
                
                result[p]=b.getField();
                p++;
     
        }

        for(int i=0;i<=number2;i++){                                                                                                    
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }
                
            result[p]=b.getField();
                p++;
     
          
        }
            
        for(int i=0;i<=number3;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }
            
                result[p]=b.getField();
                p++;
     
                
          
        }
              
        for(int i=0;i<=number4;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            b.rotate();
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }        
                result[p]=b.getField();
                p++;         
        }
        for(int i=0;i<=number5;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }        
                result[p]=b.getField();
                p++;         
        }
        for(int i=0;i<=number6;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }        
                result[p]=b.getField();
                p++;         
        }
        for(int i=0;i<=number7;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }        
                result[p]=b.getField();
                p++;         
        }
        for(int i=0;i<=number8;i++){
            Field b =new Field(field.length,field[0].length,field);
            b.AddPiece(piece);
            while(b.left()){
            }
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            b.rotate();
            for(int j=0;j<i;j++){
                b.right();
            }
            for(int k=0;k<field.length;k++){
                if(field[k][i]!=-1){
                    
                break;
                }
                else
                b.down();
            }        
                result[p]=b.getField();
                p++;         
        }
 
    return result;
}
/**
 * 
 * @param array current field
 * @param column the column that we are looking the height for
 * @return return the height of the column "column"
 */
 public static  int getHeight( int[][] array,int column){
    
    int height =0;
    for(int i=0;i<array.length;i++){
            if(array[i][column]!=0){
                height = array.length-i;
                return height;
        }
    }
    return height;
}
/**
 * 
 * @param array current field
 * @return it return the number of holes in the field
 */
 public static  int getHoles(int[][] array){
    int holes =0;
    for(int i=1;i<array.length;i++){
        for(int j=1;j<array[0].length;j++){
            if(array[i][j]==-1 && array[i-1][j]!=-1)
                holes++;
        }
    }
    return holes;
}
 public static  int clearLines(int[][] array){
    
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
/**
 * 
 * @param array current field
 * @return the aggregrate height of the actual field.(the sum of the height of all columns)
 */
 public static    int Aggregate_Height( int[][] array){
    int aggre_height=0;
    for(int i=0;i<array[0].length;i++){
        aggre_height+=getHeight(array, i);
    }
    return aggre_height;
}
/**
 * 
 * @param array current field
 * @return the bumpiness of the field(sum of the | height of column i -  height of column i+1 |  )
 */
 public static   int Bumpiness(int[][] array){
    int bump=0;
    for(int i=0;i<array[0].length-1;i++){ 
    }
    return bump;
}
 /**
  * @param array the field for which we are going to calculate the score
    @return the score of that "move"
  */
public static  double score(int[][] array){
    double score = 5*Aggregate_Height(array) +0.522287506868767*clearLines(array)+-1.14921408023878*getHoles(array)-0.164626498034284*Bumpiness(array);
    return score;
}
/**
 * same as the previous method but we are going to applicate this method to the letters I,L and V because the are the tallest pentominos 

 */
public static double score2(int[][] array){
double score = 25.0*Aggregate_Height(array) +0.522287506868767*clearLines(array)+-0.164626498034284*Bumpiness(array);
    return score;
}
/**
 *  difference with bestscore2: applying first method score f
 * @param array 3d array with all the possible moves
 * @return the index of best move with the highest score 
 */
 public static  int bestscore(int[][][] array){
    double best =0;
    int best_position=0;
    boolean no= true;
    for(int i=0;i<array.length;i++){
        Field c =new Field(height,width,array[i]);
        int[][] test = c.getField();
        c.checkRows();
        no=true;
        for(int j=0;j<array[0][0].length;j++){
            if(test[0][j]!=-1){
                no=false;
            }
        }
        if(no){
        int[][] arraycompare=test;
        if(score(arraycompare)>best){
            best=score(arraycompare);
            best_position=i;
        }
        }
    }
    return best_position;
}
/**
 * applying method score 2 because the letter is either 'I' or 'L' or 'V'
 * @param array
 * @return
 */
public static  int bestscore2(int[][][] array){
    double best =0;
    int best_position=0;
    boolean no= true;
    for(int i=0;i<array.length;i++){
        Field c =new Field(height,width,array[i]);
        int[][] test = c.getField();
        c.checkRows();
        no=true;
        for(int j=0;j<array[0][0].length;j++){
            if(test[0][j]!=-1){
                no=false;
            }
        }
        if(no){
        int[][] arraycompare=test;
        if(score2(arraycompare)>best){
            best=score2(arraycompare);
            best_position=i;
        }
        }
    }
    return best_position;
}
/**
 * 
 * @param field current field
 * @param piece piece that we are adding
 * @return best move that the bot can 
 */
 public static  int[][] best_field(int[][] field,char piece){
    int[][][] a =combination(field, piece);
    int array[][] = new int[field.length][field[0].length];
    if(piece=='I'||piece=='L'||piece=='W'){
        array=a[bestscore2(a)];
    }
    else{
        array=a[bestscore(a)];
    }
      return array; 

}
  
     

}