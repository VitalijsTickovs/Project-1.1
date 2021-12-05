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
        Field c =new Field(25,8,field);
        int p=0;
        c.AddPiece(piece);
        int number=0;
        while(c.left()){
        }
          
        while(c.right()){ 
           number++;
        }

       //2th rotation

        Field d =new Field(25,8,field);
        d.AddPiece(piece);
        int number2=0;
        while(d.left()){
        }
        d.rotate();
        while(d.right()){ 
           number2++;
        }
        //3th rotation

        Field e =new Field(25,8,field);
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
        Field f =new Field(25,8,field);
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
        //5th rotation
        Field g =new Field(25,8,field);
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
         Field h =new Field(25,8,field);
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
        Field l =new Field(25,8,field);
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
         Field x =new Field(25,8,field);
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
 /** 
 public static  double score(int[][] array){
     double score = 1*Aggregate_Height(array) + 1*clearLines(array)-0.2*getHoles(array)-0.164626498034284*Bumpiness(array);
     return score;
}
*/
 
 public static  double score(int[][] array){
    double score = 6*Aggregate_Height(array) +0.522287506868767*clearLines(array)+-1.4921408023878*getHoles(array)-0.164626498034284*Bumpiness(array);
    return score;
}
public static double score2(int[][] array){
double score = -30.0*Aggregate_Height(array) +0.522287506868767*clearLines(array)+-0.164626498034284*Bumpiness(array);
    return score;
}
 public static  int bestscore(int[][][] array){
    double best =0;
    int best_position=0;
    boolean no= true;
    for(int i=0;i<array.length;i++){
        Field c =new Field(25,8,array[i]);
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
public static  int bestscore2(int[][][] array){
    double best =0;
    int best_position=0;
    boolean no= true;
    for(int i=0;i<array.length;i++){
        Field c =new Field(25,8,array[i]);
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
 public static  int[][] best_field(int[][] field,char piece){
    int[][][] a =combination(field, piece);
    if(piece=='I'||piece=='L'||piece=='V'||piece=='Y'){
        int array[][]=a[bestscore2(a)];
    }
    else{
        int array[][]=a[bestscore(a)];
    }
      return a[bestscore(a)]; 

}
  
     public static void main (String[] args){
         /** 
       int[][] field={{-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
       {-1,-1,-1,-1,-1,-1,-1,-1},
                      {0,-1,-1,-1,0,-1,-1,-1},
                      {0,-1,-1,-1,0,0,0,0},
                      {0,0,-1,-1, 0,0,0,0},
                      { 0, 0,-1,-1,0,0,0,0},
                      { 0, 0,-1,-1,0,0,0,0}
                    };
                    Field a= new Field(7, 5,field);
                    //System.out.println(a.checkRows());
                    int[][] b =best_field(field, 'I');
                    for(int i=0;i<b.length;i++){
                        for(int j=0;j<b[0].length;j++){
                            System.out.print(b[i][j]+" ");
                        }
                    System.out.println();
                    }
                    */
                    int[] x ={1,2,3};
                    int[] y ={1,2,3};
                    boolean test =true;
                    for(int i=0;i<x.length;i++){
                        if(x[i]!=y[i])
                            test=false;
                    }
                    System.out.println(test);
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
       
        int[][] b = best_field(field, 'Y');
    }
    **/
}

}