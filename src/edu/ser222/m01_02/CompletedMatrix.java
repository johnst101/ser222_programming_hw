package edu.ser222.m01_02;

/**
 * An implementation of the Matrix ADT. Provides four basic operations over an immutable type.
 * 
 * Last updated 01/11/2024.
 * 
 * @author Tyler Johnson, Ruben Acuna
 * @version 1.0
 */

public class CompletedMatrix implements Matrix {
	private int[][] newMatrix;
	private int rows;
	private int cols;
	
    public CompletedMatrix(int[][] matrix) {    	
        // exception
        if (matrix == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null.");
        }
        if (matrix.length == 0) {
            this.newMatrix = new int[0][0];
            this.rows = 0;
            this.cols = 0;
            return;
        }
        
        // construction
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.newMatrix = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
        	for (int j = 0; j < this.cols; j++) {
        		this.newMatrix[i][j] = matrix[i][j];
        	}
        }
    }

    @Override
    public int getElement(int y, int x) {
        return this.newMatrix[y][x];
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getColumns() {
        return this.cols;
    }

    @Override
    public Matrix scale(int scalar) {
        int[][] copyMatrix = this.newMatrix.clone();
        int[][] returnArr = new int[copyMatrix.length][copyMatrix[0].length];

        // scale
        for (int i = 0; i < copyMatrix.length; i++) {
        	for (int j = 0; j < copyMatrix[0].length; j++) {
        		returnArr[i][j] = scalar * copyMatrix[i][j];
        	}
        }

        // return
        Matrix returnMatrix = new CompletedMatrix(returnArr);
        return returnMatrix;
    }

    @Override
    public Matrix plus(Matrix other) {
        int[][] otherCopy = new int[other.getRows()][other.getColumns()]; 
        for (int i = 0; i < otherCopy.length; i++) {
            for (int j = 0; j < otherCopy[0].length; j++) {
            	otherCopy[i][j] = other.getElement(i, j);
            }
        }
        int[][] thisCopy = this.newMatrix.clone();        

        // exceptions
        if (otherCopy == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null.");
        }
        if (thisCopy.length == 0) {
        	Matrix thisReturnMatrix = new CompletedMatrix(thisCopy);
        	return thisReturnMatrix;
        }
        if (otherCopy.length != thisCopy.length || otherCopy[0].length != thisCopy.length) {
            throw new java.lang.RuntimeException("Both matrices must have the same dimensions.");
        }

        // addition
        int[][] returnArr = new int[thisCopy.length][thisCopy[0].length];
        for (int i = 0; i < thisCopy.length; i++) {
            for (int j = 0; j < thisCopy[0].length; j++) {
            	returnArr[i][j] = thisCopy[i][j] + otherCopy[i][j];
            }
        }

        // return
        Matrix returnMatrix = new CompletedMatrix(returnArr);
        return returnMatrix;
    }

    @Override
    public Matrix minus(Matrix other) {
        int[][] otherCopy = new int[other.getRows()][other.getColumns()]; 
        for (int i = 0; i < otherCopy.length; i++) {
            for (int j = 0; j < otherCopy[0].length; j++) {
            	otherCopy[i][j] = other.getElement(i, j);
            }
        }
        int[][] thisCopy = this.newMatrix.clone();        

        // exceptions
        if (otherCopy == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null.");
        }
        if (thisCopy.length == 0) {
        	Matrix thisReturnMatrix = new CompletedMatrix(thisCopy);
        	return thisReturnMatrix;
        }
        if (otherCopy.length != thisCopy.length || otherCopy[0].length != thisCopy.length) {
            throw new java.lang.RuntimeException("Both matrices must have the same dimensions.");
        }

        // subtraction
        for (int i = 0; i < thisCopy.length; i++) {
            for (int j = 0; j < thisCopy[0].length; j++) {
                thisCopy[i][j] = thisCopy[i][j] - otherCopy[i][j];
            }
        }

        // return
        Matrix returnMatrix = new CompletedMatrix(thisCopy);
        return returnMatrix;
    }

    @Override
    public Matrix multiply(Matrix other) {
        int[][] otherCopy = new int[other.getRows()][other.getColumns()];
        for (int i = 0; i < otherCopy.length; i++) {
            for (int j = 0; j < otherCopy[0].length; j++) {
            	otherCopy[i][j] = other.getElement(i, j);
            }
        }
        int[][] thisCopy = this.newMatrix.clone(); 
        int[][] returnArr = new int[thisCopy.length][otherCopy[0].length];      

        // exceptions
        if (otherCopy == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null.");
        }
        if (thisCopy[0].length != otherCopy.length) {
            throw new java.lang.RuntimeException("The inputted matrix does not have the proper dimensions.");
        }

        // multiplication
        for (int i = 0; i < thisCopy.length; i++) {
            for (int j = 0; j < otherCopy[0].length; j++) {
                returnArr[i][j] = thisCopy[i][j] * otherCopy[i][j];
            }
        }
        
        Matrix returnMatrix = new CompletedMatrix(returnArr);
        return returnMatrix;
    }

    @Override
    public boolean equals(Object other) {
        // class checks
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        // equality check
        Matrix otherMatrix = (CompletedMatrix) other;
        if ((otherMatrix.getRows() == 0) && (this.getRows() == 0)) {
        	return true;
        }
        else if ((otherMatrix.getRows() != 0) && (this.getRows() == 0)) {
        	return false;
        }
        else if ((otherMatrix.getRows() == 0) && (this.getRows() != 0)) {
        	return false;
        }
        else {
        	for (int i = 0; i < this.rows; i++) {
	            for (int j = 0; j < this.cols; j++) {
	                if (this.getElement(i, j) != otherMatrix.getElement(i, j)) {
	                    return false;
	                }
	            }
	        }
        }

        return true;
    }

    @Override
    public String toString() {
        Matrix thisCopy = new CompletedMatrix(this.newMatrix);
    	String output = null;
        for (int i = 0; i < thisCopy.getRows(); i++) {
            for (int j = 0; j < thisCopy.getColumns(); j++) {
                if ((i == 0) && (j == 0)) {
                	output = thisCopy.getElement(i, j) + " ";
                }
                else if ((j == (thisCopy.getColumns() - 1)) && (i == (thisCopy.getRows() - 1))) {
                    output = output + thisCopy.getElement(i, j) + "\n";
                }
                else if ((j == (thisCopy.getColumns() - 1)) && (i != (thisCopy.getRows() - 1))) {
                    output = output + thisCopy.getElement(i, j) + "\n";
                }
                else { 
                	output = output + thisCopy.getElement(i, j) + " ";
                }
            }
        }

        return output;
    }

    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //These tests show sample usage of the matrix, and some basic ideas for testing. They are not comprehensive.

        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data5 = {{1, 4, 7}, {2, 5, 8}};

        Matrix m1 = new CompletedMatrix(data1);
        Matrix m2 = new CompletedMatrix(data2);
        Matrix m3 = new CompletedMatrix(data3);
        Matrix m4 = new CompletedMatrix(data4);
        Matrix m5 = new CompletedMatrix(data5);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2)); //FIXME                //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4)); //FIXME                //true

        //test operations (valid)
        System.out.println("m1 + m1:\n" + m1.plus(m1)); //FIXME
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("3 * m5:\n" + m5.scale(3));

        //not tested... multiply(). you know what to do.

        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 + m5" + m1.plus(m5));
        //System.out.println("m1 - m2" + m1.minus(m2));
    }
}