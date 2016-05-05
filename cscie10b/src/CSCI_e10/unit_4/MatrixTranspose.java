package CSCI_e10.unit_4;



import java.util.Arrays;

/**
 * 
 * @author M Bret Blackford   ID:20849347
 *  CSCI E-10a  Fall 2015
 *  Unit 4 Problem Set  question [11]
 *
 */
public class MatrixTranspose {

	/**
	 * Method will transpose a 2D (n x n) array
	 * @param inArray
	 * @return
	 */
	public static int[][] transpose(int[][] inArray){
		
		int rows = inArray.length;
		int columns = inArray[0].length;
		
		int[][] transposedArray = new int[columns][rows];
		
		for ( int row = 0; row < rows; row++ ){
			
			for( int column = 0; column < columns; column++ ) {
			
			transposedArray[column][row] = inArray[row][column];
			}
		}
		
		//below for debugging
		//System.out.println("           array: " + Arrays.deepToString(inArray));
		//System.out.println("transposed array: " + Arrays.deepToString(transposedArray));
		
		return transposedArray;
	}
	
	/**
	 * Helper method to print the matrix in a nice grid 
	 * (as opposed to using Arrays.deepToString(inArray))
	 * @param inArray
	 */
	public static void printMatrix(int[][] inArray){
        
		System.out.println();
		
		for(int outter=0 ; outter<inArray.length ; outter++)
        {
            for(int inner=0 ; inner<inArray[0].length ; inner++)
            {
                System.out.print(inArray[outter][inner] + " ");
            }

            System.out.println();
        }
    } 
	
	
	public static void main(String[] args) {

		int[][] array1 = { {3,4,5}, {6,1,8},{9,5,7} };
		int[][] array2 = { {3,4}, {6,1},{9,5}, {7,7} };
		
		int[][] array3 = { {3,6,9}, {4,1,5},{5,8,7} };
		int[][] array4 = { {3,4,5}, {6,1,7},{9,5,2}, {7,7,1} };
		
		
		int[][] tArray1 = transpose(array1);
		int[][] tArray2 = transpose(array2);
		int[][] tArray3 = transpose(array3);
		int[][] tArray4 = transpose(array4);
		
		printMatrix(array1);
		printMatrix(tArray1);
		
		printMatrix(array2);
		printMatrix(tArray2);

		printMatrix(array3);
		printMatrix(tArray3);
		
		printMatrix(array4);
		printMatrix(tArray4);
	}

}
