//import java.io.ObjectInputStream.GetField;

class all_position{
    public int h;
    public int w;

    public int pieceID;
    public int pieceMutation;
    public int yPiece;
    public int xPiece;
    public int[][] pieceLayout;
     public static  int[][][] combination(int[][] field,char piece){
        
        Field c =new Field(7,5,field);
        int p=0;
        c.AddPiece(piece);
        int number=0;
        while(c.left()){
        }
          
        while(c.right()){ 
           number++;
        }

       //2th rotation

        Field d =new Field(7,5,field);
        d.AddPiece(piece);
        int number2=0;
        while(d.left()){
        }
        d.rotate();
        while(d.right()){ 
           number2++;
        }
        //3th rotation

        Field e =new Field(7,5,field);
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
        Field f =new Field(7,5,field);
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
       
        int tot_number=number+number2+number3+number4+4;
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
                b.setPiece();
                result[p]=b.getField();
                p++;
     
                
          
        }
 
    return result;
}
 public static  int getHeight( int[][] array,int column){
    
    int height =0;
    for(int i=0;i<array.length;i++){
            if(array[i][column]!=0){
                height = 4-i;
                return height;
        }
    }
    return height;
}
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
 public static  int clearLines(){
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
 public static    int Aggregate_Height( int[][] array){
    int aggre_height=0;
    for(int i=0;i<array[0].length;i++){
        aggre_height+=getHeight(array, i);
    }
    return aggre_height;
}

 public static   int Bumpiness(int[][] array){
    int bump=0;
    for(int i=0;i<array[0].length-1;i++){ 
    }
    return bump;
}
 
 public static  int score(int[][] array){
     int score = 2000*Aggregate_Height(array) +15*clearLines()-50*getHoles(array)-5*Bumpiness(array);
     return score;
}

/** 
 public static  int score(int[][] array){
    int score = Aggregate_Height(array) +clearLines()-getHoles(array)-Bumpiness(array);
    return score;
}
*/
 public static  int bestscore(int[][][] array){
    int best =0;
    int best_position=0;
    boolean no= true;
    for(int i=0;i<array.length;i++){
        no=true;
        for(int j=0;j<array[0][0].length;j++){
            if(array[i][0][j]!=-1){
                no=false;
            }
        }
        if(no){
        int[][] arraycompare=array[i];
        if(score(arraycompare)>best){
            best=score(arraycompare);
            best_position=i;
        }
        }
    }
    return best_position;
}
 public static  int[][] best_field(int[][] field,char piece){
    int[][][] a =combination(field, piece);
    System.out.print(bestscore(a)+ " ");
        int array[][]=a[bestscore(a)];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                System.out.print(array[i][j]+" ");
            }
        System.out.println();
        }
      return a[bestscore(a)]; 

}

     public static  void main(String[] args){
       int[][] field={{-1,-1,-1,-1,-1},
                      {-1,-1,-1,-1,-1},
                      {0,-1,-1,-1,0},
                      {0,-1,-1,-1,0},
                      {0,0,-1,-1, 0},
                      { 0, 0,-1,-1,0},
                      { 0, 0,-1,-1,-1}
                    };
                      /**             
       int[][][] a =combination(field, 'Y');
     /** 
        for(int k=0;k<a.length;k++){
            System.out.println("matrix "+k);
            for(int i=0;i<field.length;i++){
                for(int j=0;j<field[0].length;j++){
                    System.out.print(a[k][i][j]+" ");
                }
            System.out.println();
            }
            System.out.println();
            System.out.println();
        }
      
        System.out.print(bestscore(a)+ " ");
        int array[][]=a[bestscore(a)];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                System.out.print(array[i][j]+" ");
            }
        System.out.println();
        }
        */
        int[][] b = best_field(field, 'Y');
    }
    

}