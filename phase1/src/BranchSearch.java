import java.util.Arrays;
public class BranchSearch {
    public static final int horizontalGridSize = 5;
    public static final int verticalGridSize = 8;
    
    public static final char[] input = { 'L','F','X','U','V','N','T','W','P','Z','Y','I'};
    
    
    //Static UI class to display the board
    public static UI ui = new UI(horizontalGridSize, verticalGridSize, 50);

	/**
	 * Helper function which starts a recursive search algorithm
	 */
    public static void search(){
        // Initialize an empty board
        int[][] field = new int[horizontalGridSize][verticalGridSize];
		removeLetter(field,'i');

		//This is branching algorithm
		if(!searchBranching(field, input, 0)){
			System.out.println("The solution is not found");	//In case searchBranching is false, it will output that there is no solution
		}														//If it was in base case, it would output this text for every branch, where there is no soulution
    }
	/**
	 * This function converts the letters from the input to the ID's for 
	 * @param character
	 * @return shape ID
	 */
    public static int characterToID(char character) {
    	int shapeID = -1; 
    	if (character == 'X') {
    		shapeID = 0;
    	} else if (character == 'I') {
    		shapeID = 1;
    	} else if (character == 'Z') {
    		shapeID = 2;
    	} else if (character == 'T') {
    		shapeID = 3;
    	} else if (character == 'U') {
    		shapeID = 4;
     	} else if (character == 'V') {
			shapeID = 5;
     	} else if (character == 'W') {
			shapeID = 6;
     	} else if (character == 'Y') {
			shapeID = 7;
    	} else if (character == 'L') {
    		shapeID = 8;
    	} else if (character == 'P') {
    		shapeID = 9;
    	} else if (character == 'N') {
    		shapeID = 10;
    	} else if (character == 'F') {
    		shapeID = 11;
    	} 
    	return shapeID;
    }

	/**
	 * This method checks if the field matrix is full
	 * @param field
	 * @return true, if the matrix is full || false, if the matrix still has a spot to fill in 
	 */
    public static boolean isFull(int[][] field){
		boolean isFull = true;
		for(int i=0;i<field.length;i++){
			for(int j=0;j<field[0].length;j++){
				if(field[i][j] == -1) isFull = false;
			}
		}
		return isFull;
	}

	/**
	 * this method adds the shape to the field matrix, changing the values of it
	 * @param field - the matrix to which we have to add the shape
	 * @param piece - the shape matrix
	 * @param pieceID - the ID of the letter
	 * @param x - the horixontal position where to place the shape in field matrix
	 * @param y - the vertical position where to place the shape in field matrix
	 */
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

	/**
	 * This removes the current letter of the branch, to try another position, if possible
	 * @code user == 'i' is to initialize the table at the start
	 * @param field
	 * @param user
	 * @return field, which is removed from 
	 */
	public static int[][] removeLetter(int[][] field, char user){
		for(int i = 0; i < field.length; i++)
        {
            for(int j = 0; j < field[i].length; j++)
            {
                // -1 in the state matrix corresponds to empty square
                // Any positive number identifies the ID of the pentomino
            	if(field[i][j] == characterToID(user) || user == 'i') field[i][j] = -1;
            }
        }
		return field;
	}

	/**
	 * This is the branching method, which is recursive, non-deterministic, depth-first, backtracking algorithm
	 * @param field is matrix to fill in with pentominoes
	 * @param userInput letters that are inputted by the user
	 * @param mutation keeps track on which mutation we are
	 * @return if true returns visual representation of the filled table || returns a text where that there is no solution
	 */
    public static boolean searchBranching(int[][] field, char[] userInput, int mutation){
		if(isFull(field)){									//base case 1, where the shapes are in our matrix
			ui.setState(field);
			System.out.println("The solution is found");
			return true;
		}else if(userInput.length ==0){						//base case 2, where the there is no characters left
			return false;
		}else{
			//making a copy of existing matrix
			int[][] newField = Arrays.copyOfRange(field, 0, field.length);						
			//find userInput[0] char from database
			int pieceID = characterToID(userInput[0]);
			int[][] pieceToPlace = PentominoDatabase.data[pieceID][mutation];
			//check if the shape with current mutation is fitting the matrix
			for(int i=0; i< (newField.length-pieceToPlace.length)+1;i++){
				for(int j=0;j<(newField[i].length-pieceToPlace[0].length)+1;j++){
					boolean placeFound = true;
					//seeing if the matrix is filled with something else
					for(int k=0; k<pieceToPlace.length; k++){
						for(int n=0;n<pieceToPlace[k].length;n++){
							if(newField[k+i][n+j]>-1 && pieceToPlace[k][n]!=0 && placeFound){
								placeFound = false;
							}
						}
					}
					//if the shape can be placed in the matrix, it adds it and tries to fit next shape
					if(placeFound){
						addPiece(newField, pieceToPlace, pieceID, i,j);
						if(searchBranching(newField, Arrays.copyOfRange(userInput, 1, userInput.length), 0)){
							return true;
						}
					}
					//if it is returned false, then remove that shape, to try another position
					newField = removeLetter(field, userInput[0]);

				}
			}
			//try the different mutation for the shape
			if((mutation<7) && (mutation < PentominoDatabase.data[pieceID].length-1)){
				return searchBranching(field, userInput, mutation+1);
			}else{
				ui.setState(field);
				return false;	//if the mutations are finished it will return false
			}
		}
	}
	//The main method is needed to run the algorithm
	public static void main(String[] args)
    {
		long startTime = System.currentTimeMillis();
        search();
		long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
    }
}