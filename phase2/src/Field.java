

public class Field extends pieceBag{


    private static int[][] field;
    public static int h;
    public static int w;

    public static int pieceID;
    public static int pieceMutation;
    public static int yPiece;
    public static int xPiece;
    public static int[][] pieceLayout;

    /**
     * Constructor for bot
     * @param height of the field
     * @param width of the field
     * @param fieldd
     */
    public Field(int height, int width,int[][] fieldd){
        h = height;
        w = width;
        field=fieldd;
        pieceID = -1;
        pieceMutation = 0;
        yPiece = 0;
        xPiece = 0;
    }


    /**
     * Constructor for the game
     * @param height of the field to be initiated
     * @param width of the field to be initiated
     */
    public Field(int height, int width){
        h = height;
        w = width;
        field = new int[height][width];
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++){
            	field[i][j] = -1;
            }
        }
        pieceID = -1;
        pieceMutation = 0;
        yPiece = 0;
        xPiece = 0;
    }

    public Field(){}

    /**
     * Adds piece that is given to the field
     * @param piece - piece that has to be added
     * @return - returns false if piece can't be added (aka game over), otherwise, returns true and adds the piece
     */
    public boolean AddPiece(char piece){
        int newPieceID = characterToID(piece);
        int[][] newPiece = data[newPieceID][0];

        //checking if can be added (can be made more lenient by trying to rotate/move piece left or right)
        for(int i=0;i<newPiece.length;i++){
            for(int j=0;j<newPiece[0].length;j++){
                if(newPiece[i][j]==1&&field[i][j+w/2-newPiece[0].length/2]!=-1){
                    return false;
                }
            }
        }
        pieceLayout = newPiece;
        pieceID = newPieceID;
        pieceMutation = 0;
        yPiece = 0;
        xPiece = w/2-newPiece[0].length/2;

        return true;
    }

    /**
     * Method for moving active piece left
     * @return FALSE, if piece can't be moved left anymore
     */
    public static boolean left(){
        if(pieceID>-1){
            boolean possible = true;

            //if the piece is at the leftmost border
            if(xPiece<=0){
                possible = false;
            } 

            //if a square that the piece would move to is occupied
            for(int i = 0;i<pieceLayout.length;i++){
                for(int j = 0;j<pieceLayout[0].length;j++){
                    if(possible&&pieceLayout[i][j]==1&&field[yPiece+i][xPiece+j-1]!=-1){
                        possible = false;
                    }
                }
            }

            if(possible){
                xPiece--;
            }
            return possible;

        }
        return false;
    }

    /**
     * Method for moving active piece right
     * @return FALSE, if piece can't be moved right anymore
     */
    public static boolean right(){
        if(pieceID>-1){
            //pieceLayout = PentominoDatabase.data[pieceID][pieceMutation];

            boolean possible = true;

            //if the piece is at the rightmost border
            if(xPiece+pieceLayout[0].length>=w){
                possible = false;
            } 

            //if a square that the piece would move to is occupied
            for(int i = 0;i<pieceLayout.length;i++){
                for(int j = 0;j<pieceLayout[0].length;j++){
                    if(possible&&pieceLayout[i][j]==1&&field[yPiece+i][xPiece+j+1]!=-1){
                        possible = false;
                    }
                }
            }

            if(possible){
                xPiece++;
            }
            return possible;
        }
        return false;
    }


    /**
     * Method for moving piece down
     * @return FALSE, if piece can't be moved down anymore
     */
    public static boolean down(){
        if(pieceID>-1){
            boolean possible = true;

            //if the piece is at the rightmost border
            if(yPiece+pieceLayout.length>=h){
                possible = false;
            }

            //if a square that the piece would move to is occupied
            for(int i = 0;i<pieceLayout.length;i++){
                for(int j = 0;j<pieceLayout[0].length;j++){
                    if(possible&&pieceLayout[i][j]==1&&field[yPiece+i+1][xPiece+j]!=-1){
                        possible = false;
                    }
                }
            }

            if(possible){
                yPiece++;
            }
            return possible;
        }
        return false;
    }

    //method to go down multiple times
    public static void down(int n){
        for (int i = 0; i < n; i++) {
            down();
        }
    }


    /**
     * Method for rotating piece
     * @return FALSE, if piece can't be rotated anymore
     */
    public static boolean rotate(){
        if(pieceID!=-1){
            boolean possible = true;
            int[][] newLayout = PentominoDatabase.data[pieceID][(pieceMutation+1)%PentominoDatabase.data[pieceID].length];



            //check if the rotated piece collides with an edge

            if(xPiece+newLayout[0].length>w||yPiece+newLayout.length>h){
                possible=false;
            }
            //check if the rotated piece collides with any piece on the field 
            for (int i = 0; i < newLayout.length &&possible; i++) {
                for (int j = 0; j < newLayout[0].length; j++) {
                    if(possible&&newLayout[i][j]==1&&field[i+yPiece][j+xPiece]>=0){
                        possible = false;
                    }
                }
                
            }

            if(possible){
                pieceLayout = newLayout;
                pieceMutation = (pieceMutation+1)%PentominoDatabase.data[pieceID].length;
            }


            return possible;
        }
        return false;
    }


    /**
     * Rotate method that tries several x positions so the rotation can be more "satisfying"
     * @return FALSE, if piece can't be rotated from any other position
     */
    public static boolean lenientRotate(){

        int deltaX = 0;
        if(rotate()){
            return true;
        }
        for(int i = 1;i < pieceLayout.length;i++){
        if(left()){
            deltaX++;
        }
        if(rotate()){
            return true;
        }
        }
        for (int i = 0; i < deltaX; i++) {
            right();
        }
          

        return false;

    }

    /**
     * Method for "cementing" active piece on the board. we assume it is possible
     */
    public void setPiece(){
        if(pieceID!=-1){    
            for (int i = 0; i < pieceLayout.length; i++) {
                for (int j = 0; j < pieceLayout[0].length; j++) {
                    if(pieceLayout[i][j]==1){
                        field[yPiece+i][xPiece+j]=pieceID;
                    }   
                }
            }
            pieceID=-1;
        }

    }


    /**
     * Checks if there are any full rows, and removes them (and shifts everything above down)
     * @return the number of removed rows, for scoring purposes
     */
    public int checkRows(){
        int nOfClears = 0;
        for (int i = h-1; i >=0; i--) {
            boolean full = true;
            for (int j = 0; j < w; j++) {
                if(full&&field[i][j]==-1){
                    full = false;
                }
            }

            if(full){
                nOfClears++;
                for (int k = i-1; k>=0; k--) {
                    for (int j = 0; j < w; j++) {
                        field[k+1][j]=field[k][j];
                    }
                }

                for (int j = 0; j < w; j++) {
                    field[0][j] = -1;
                }
                i++;

            }


        }
        return nOfClears;
    }


    public static int[][] getField(){

        if(pieceID==-1){
            return field;
        }

        int[][] result = new int[h][w];
        
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = field[i][j];

                if(i>=yPiece&&i<yPiece+pieceLayout.length&&j>=xPiece&&j<xPiece+pieceLayout[0].length){
                    
                    if(pieceLayout[i-yPiece][j-xPiece]==1){
                        result[i][j]=pieceID;
                    }
                }
            }            
        }

        return result;


        
    }
    public void ResetPiece(){
        pieceLayout = PentominoDatabase.data[pieceID][0];
        pieceMutation = 0;
        yPiece = 0;
        xPiece = w/2-pieceLayout[0].length/2;
    }




}
