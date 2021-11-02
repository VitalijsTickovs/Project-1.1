import java.security.Principal;
import java.util.List;
import java.util.ArrayList;

import javax.lang.model.type.NullType;

public class Dancing_links{

    public ArrayList<boolean[]> solution = new ArrayList<boolean[]>();;

    private static boolean printStuff = false;




    public boolean Solving(boolean[][] A){

        
        

        
        if(A == null){
            System.out.println("Problem solved! Nothing to do");
            return true;
        }
        else{

            //boolean[][] solution = new int[A.length][A[0].length];
            int c = A[0].length-1;
            int r = 0;
            
            //choose R such that r is the first row with a "1"
            for(int ii=0;ii<A.length;ii++){
                if(A[ii][c]){
                    r=ii;

                    boolean[] potentialsolution = copyArray(A[r]);
                    if(printStuff) System.out.println("r = " + r);
                    //continue;
                

                //for(int i=0;i<A[0].length;i++){
                  //  solution[r][i]=A[r][i];
                //}
                //make a copy and delete first the column then the rows
                boolean[][] Solution2 = copyArray(A);
                if(printStuff)System.out.println(A.length +" * "+A[0].length);
                for(int j=Solution2[0].length-1;j>=0;j--){
                    if(printStuff) System.out.println("j = " + j);
                    if(Solution2.length!=0&&Solution2[r][j]){
                        
                        for(int i=Solution2.length-1;i>=0;i--){
                            
                            if(Solution2[i][j]&&i!=r){
                                
                              Solution2= deleteRow(Solution2, i);
                              if(r>i){
                                r--;
                            }
                              
                            }
                           
                        }
                        if(Solution2.length!=0){
                        Solution2=deleteColumn(Solution2, j);
                        
                        }else{
                            if(printStuff)System.out.println("small");
                        }
                        
                    }
                }
                if(Solution2.length == 1&& Solution2[0].length>0){
                    if(printStuff)System.out.println("end branch");
                    //return false;
                }else{
                if(Solution2.length==1&& Solution2[0].length==0){
                    solution.add(potentialsolution);
                    return true;
                }
                Solution2=deleteRow(Solution2, r);
                if(Solving(Solution2)){
                    solution.add(potentialsolution);
                    return true;
                }
            }


                }
            }
            return false;
            // assign to partial solution  the row r
            
        }      

    } 

    public boolean[][] deleteColumn(boolean[][] Ao,int c){
        if(printStuff)System.out.println("deleting column " + c + " for matrix of proportions: " + Ao.length + "*" + Ao[0].length);

        boolean[][] A= copyArray(Ao);
        boolean[][] B= new boolean[A.length][A[0].length-1];
            for(int i=0;i<A.length;i++){
                for(int j=0;j<c;j++){
                    B[i][j]=A[i][j];
                }
            }
            for(int i=0;i<A.length;i++){
                for(int j=c+1;j<A[0].length;j++){
                    B[i][j-1]=A[i][j];
                }
            }
            return B;
    }
    public boolean[][] deleteRow(boolean[][] A,int r){
        if(printStuff)System.out.println("deleting row " + r + "for matrix of proportions: " + A.length + "*" + A[0].length);
        boolean[][] B= new boolean[A.length-1][A[0].length];
            for(int j=0;j<A[0].length;j++){
                for(int i=0;i<r;i++){
                    B[i][j]=A[i][j];
                }
            }
            for(int j=0;j<A[0].length;j++){
                for(int i=r+1;i<A.length;i++){
                    B[i-1][j]=A[i][j];
                }
            }
            return B;
    }

    public boolean[][] copyArray(boolean[][] A){
        boolean [][] result = new boolean[A.length][A[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j]=A[i][j];
            }
        }
        return result;
    }

    public boolean[] copyArray(boolean[] A){
        boolean [] result = new boolean[A.length];

        for (int i = 0; i < result.length; i++) {
            result[i]=A[i];
            
        }
        return result;
    }
}