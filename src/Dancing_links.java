import javax.lang.model.type.NullType;

public class Dancing_links{
    public int[][] Solving(int[][] A){
        
        if(A == null){
            System.out.println("Problem solved! Nothing to do");
            return A;
        }
        else{
            int[][] solution = new int[A.length][A[0].length];
            int c = 0;
            int r = -1;
            
            //choose R such that r is the first row with a "1"
            for(int i=0;i<A.length;i++){
                if(A[i][c]==1){
                    r=i;
                    continue;
                }
            }
            // assign to partial solution  the row r
            for(int i=0;i<A[0].length;i++){
                solution[r][i]=A[r][i];
            }
            //make a copy and delete first the column then the rows

            for(int j=0;j<A.length;j++){
                if(A[r][j]==1){
                    int[][] Solution2=deleteColumn(A, j);
                    for(int i=0;i<Solution2.length;i++){
                        
                        if(Solution2[i][j]==1){
                          Solution3= deleteRow(Solution2, i);
                          
                        }
                       
                    }
                    
                }
            }
        }      

    } 
    public int[][] deleteColumn(int[][] A,int c){
        int[][] B= new int[A.length][A[0].length-1];
            for(int i=0;i<A.length;i++){
                for(int j=0;j<c;j++){
                    B[i][j]=A[i][j];
                }
            }
            for(int i=0;i<A.length;i++){
                for(int j=c+1;j<A[0].length;j++){
                    B[i][j]=A[i][j];
                }
            }
            return B;
    }
    public int[][] deleteRow(int[][] A,int r){
        int[][] B= new int[A.length][A[0].length-1];
            for(int j=0;j<r;j++){
                for(int i=0;i<A.length;i++){
                    B[i][j]=A[i][j];
                }
            }
            for(int j=r+1;j<A[0].length;j++){
                for(int i=0;i<A.length;i++){
                    B[i][j]=A[i][j];
                }
            }
            return B;
    }
}