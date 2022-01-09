/**
 * @author Department of Data Science and Knowledge Engineering (DKE)
 * @version 2022.0
 */

 import java.util.Arrays;
import java.util.Random;

/**
 * This class includes the methods to support the search of a solution.
 */
public class Search
{
    public static final int horizontalGridSize = 5;
    public static final int verticalGridSize = 3;
    
    public static final char[] input = { 'L','P','V'};
    
    //Static UI class to display the board
    public static UI ui = new UI(horizontalGridSize, verticalGridSize, 50);

	/**
	 * Helper function which starts a basic search algorithm
	 */
    public static void search()
    {
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
	
	/**
	 * Get as input the character representation of a pentomino and translate it into its corresponding numerical value (ID)
	 * @param character a character representating a pentomino
	 * @return	the corresponding ID (numerical value)
	 */
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
	
	/**
	 * Basic implementation of a search algorithm. It is not a bruto force algorithms (it does not check all the posssible combinations)
	 * but randomly takes possible combinations and positions to find a possible solution.
	 * The solution is not necessarily the most efficient one
	 * This algorithm can be very time-consuming
	 * @param field a matrix representing the board to be fulfilled with pentominoes
	 */
    private static void basicSearch(int[][] field){
    	Random random = new Random();
    	boolean solutionFound = false;
    	
    	while (!solutionFound) {
    		solutionFound = true;
    		
    		//Empty board again to find a solution
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					field[i][j] = -1;
				}
			}
    		
    		//Put all pentominoes with random rotation/flipping on a random position on the board
    		for (int i = 0; i < input.length; i++) {
    			
    			//Choose a pentomino and randomly rotate/flip it
    			int pentID = characterToID(input[i]);
    			int mutation = random.nextInt(PentominoDatabase.data[pentID].length);
    			int[][] pieceToPlace = PentominoDatabase.data[pentID][mutation];
    		
    			//Randomly generate a position to put the pentomino on the board
    			int x;
    			int y;
    			if (horizontalGridSize < pieceToPlace.length) {
    				//this particular rotation of the piece is too long for the field
    				x=-1;
    			} else if (horizontalGridSize == pieceToPlace.length) {
    				//this particular rotation of the piece fits perfectly into the width of the field
    				x = 0;
    			} else {
    				//there are multiple possibilities where to place the piece without leaving the field
    				x = random.nextInt(horizontalGridSize-pieceToPlace.length+1);
    			}

    			if (verticalGridSize < pieceToPlace[0].length) {
    				//this particular rotation of the piece is too high for the field
    				y=-1;
    			} else if (verticalGridSize == pieceToPlace[0].length) {
    				//this particular rotation of the piece fits perfectly into the height of the field
    				y = 0;
    			} else {
    				//there are multiple possibilities where to place the piece without leaving the field
    				y = random.nextInt(verticalGridSize-pieceToPlace[0].length+1);
    			}
    		
    			//If there is a possibility to place the piece on the field, do it
    			if (x >= 0 && y >= 0) {
	    			addPiece(field, pieceToPlace, pentID, x, y);
	    		} 
    		}
    		//Check whether complete field is filled
    		//
			for(int i = 0;i < field.length;i++){
				for(int j = 0;j < field[0].length;j++){
					if(field[i][j]<0&&solutionFound){
						solutionFound = false;
					}
				}
			}

    		//
    		// TODO: To be implemented
    		//
    		//
    		

    		
    		if (solutionFound) {
    			//display the field
    			ui.setState(field); 
    			System.out.println("Solution found");
    			break;
    		}
    	}
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

	public static void printtable(int[][]field){
		for(int i=0;i<field.length;i++){
			for(int j=0;j<field[0].length; j++){
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
					printtable(newField);
					System.out.println();
					
					//seeing if the matrix is filled with something else
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
			if(mutation<7 && pieceToPlace[pieceID].length>= mutation){
				return searchBranching(field, userInput, mutation+1);
			}else{
				return false;
			}
		}
	}

    
	/**
	 * Adds a pentomino to the position on the field (overriding current board at that position)
	 * @param field a matrix representing the board to be fulfilled with pentominoes
	 * @param piece a matrix representing the pentomino to be placed in the board
	 * @param pieceID ID of the relevant pentomino
	 * @param x x position of the pentomino
	 * @param y y position of the pentomino
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
	 * Main function. Needs to be executed to start the basic search algorithm
	 */
    public static void main(String[] args)
    {
        search();
    }
}