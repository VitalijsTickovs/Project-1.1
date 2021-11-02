import java.util.Arrays;
public class BranchSearch {
    public static final int horizontalGridSize = 5;
    public static final int verticalGridSize = 3;
    
    public static final char[] input = { 'L','P','V'};
    
    //Static UI class to display the board
    public static UI ui = new UI(horizontalGridSize, verticalGridSize, 50);

	/**
	 * Helper function which starts a basic search algorithm
	 */
    public static void search(){
        // Initialize an empty board
        int[][] field = new int[horizontalGridSize][verticalGridSize];

        for(int i = 0; i < field.length; i++)
        {
            for(int j = 0; j < field[i].length; j++)
            {
                // -1 in the state matrix corresponds to empty square
                // Any positive number identifies the ID of the pentomino
            	field[i][j] = -1;
            }
        }
        //Start the basic search
        //basicSearch(field);
		searchBranching(field, input, 0);
    }

    public static int characterToID(char character) {
    	int pentID = -1; 
    	if (character == 'X') {
    		pentID = 0;
    	} else if (character == 'I') {
    		pentID = 1;
    	} else if (character == 'Z') {
    		pentID = 2;
    	} else if (character == 'T') {
    		pentID = 3;
    	} else if (character == 'U') {
    		pentID = 4;
     	} else if (character == 'V') {
     		pentID = 5;
     	} else if (character == 'W') {
     		pentID = 6;
     	} else if (character == 'Y') {
     		pentID = 7;
    	} else if (character == 'L') {
    		pentID = 8;
    	} else if (character == 'P') {
    		pentID = 9;
    	} else if (character == 'N') {
    		pentID = 10;
    	} else if (character == 'F') {
    		pentID = 11;
    	} 
    	return pentID;
    }

    public static boolean isFull(int[][] field){
		boolean isFull = true;
		for(int i=0;i<field.length;i++){
			for(int j=0;j<field[0].length;j++){
				if(field[i][j] != 1) isFull = false;
			}
		}
		return isFull;
	}

    public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y)
    {
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1)
                {
                    // Add the ID of the pentomino to the board if the pentomino occupies this square
                    field[x + i][y + j] = pieceID;
                }
            }
        }
    }

    public static void printTable(int[][] field){
        for(int i=0; i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean searchBranching(int[][] field, char[] userInput, int mutation){
		if(isFull(field)){									//base case 1, where the shapes are in our matrix
			ui.setState(field); 
			System.out.println("The solution is found");
			return true;
		}else if(userInput.length ==0){						//base case 2, where the there is no characters left
			System.out.println("The solution is not found");
			return false;
		}else{
			//make a copy of existing matrix
			int[][] newField = Arrays.copyOfRange(field, 0, field.length);							
			//find userInput[0] char from database
			int pieceID = characterToID(userInput[0]);
			int[][] pieceToPlace = PentominoDatabase.data[pieceID][mutation];
			ui.setState(field); 
			//check if the shape with current mutation is fitting the matrix
			for(int i=0; i< (newField.length-pieceToPlace.length)+1;i++){
				for(int j=0;j<(newField[0].length-pieceToPlace[0].length)+1;j++){
					boolean placeFound = true;
					
					//seeing if the matrix is filled with something else
                    //printTable(field);
					for(int k=0; k<pieceToPlace.length; k++){
						for(int n=0;n<pieceToPlace[k].length;n++){
							if(newField[k+i][n+j]>-1  && placeFound){
								placeFound = false;
							}
						}
					}
					if(placeFound){
						addPiece(newField, pieceToPlace, pieceID, i,j);
						return searchBranching(newField, Arrays.copyOfRange(userInput, 1, userInput.length), 0);
					}
				}
			}
            System.out.println(pieceToPlace[pieceID].length + " " + pieceID);
			if(mutation<7 && pieceToPlace[pieceID].length>= mutation){
				return searchBranching(field, userInput, mutation+1);
			}else{
				return false;
			}
		}
	}
    public static void main(String[] args)
    {
        search();
    }
}
