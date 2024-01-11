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
	private final int[][] newMatrix;
	private int rows;
	private int cols;
	
    public CompletedMatrix(int[][] matrix) {
        int[][] copyMatrix = matrix.clone();
        this.newMatrix = copyMatrix;
        this.rows = this.newMatrix.length;
        this.cols = this.newMatrix[0].length;
        //TODO: throw an exception per the instruction document
    }

    @Override
    public int getElement(int y, int x) {
        return this.newMatrix[x][y];
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

        // scale
        for (int i = 0; i < copyMatrix.length; i++) {
        	for (int j = 0; j < copyMatrix[0].length; j++) {
        		copyMatrix[i][j] = scalar * copyMatrix[i][j];
        	}
        }

        // return
        Matrix returnMatrix = new CompletedMatrix(copyMatrix);
        return returnMatrix;
    }

    @Override
    public Matrix plus(Matrix other) {
        int[][] otherCopy = new int[other.getRows()][other.getColumns()]; 
        int[][] thisCopy = this.newMatrix.clone();        

        // exceptions
        if (otherCopy == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null");
        }
        if (otherCopy.length != thisCopy.length || otherCopy[0].length != thisCopy.length) {
            throw new java.lang.RuntimeException("Both matrices must have the same dimensions.")
        }

        // addition
        for (int i = 0; i < thisCopy.length; i++) {
            for (int j = 0; j < thisCopy[0].length; j++) {
                thisCopy[i][j] = thisCopy[i][j] + otherCopy[i][j];
            }
        }

        // return
        Matrix returnMatrix = new CompletedMatrix(thisCopy);
        return returnMatrix;
    }

    @Override
    public Matrix minus(Matrix other) {
        int[][] otherCopy = new int[other.getRows()][other.getColumns()]; 
        int[][] thisCopy = this.newMatrix.clone();        

        // exceptions
        if (otherCopy == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null");
        }
        if (otherCopy.length != thisCopy.length || otherCopy[0].length != thisCopy.length) {
            throw new java.lang.RuntimeException("Both matrices must have the same dimensions.")
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
        int[][] thisCopy = this.newMatrix.clone();        

        // exceptions
        if (otherCopy == null) {
            throw new java.lang.IllegalArgumentException("Inputted matrix must not be null");
        }
        if (otherCopy.length != thisCopy.length || otherCopy[0].length != thisCopy.length) {
            throw new java.lang.RuntimeException("Both matrices must have the same dimensions.")
        }

        // TODO: multiplication

        
        // TODO: return
    }

    @Override
    public boolean equals(Object other) {
        // class checks
        if (other == this.newMatrix) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.newMatrix.getClass()) {
            return false;
        }

        // equality check
        Matrix otherMatrix = (CompletedMatrix) other;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.getElement(i, j) != otherMatrix.getElement(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String output = null;
        for (int i = 0; i < this.newMatrix.length; i++) {
            for (int j = 0; j < this.newMatrix[0].length; j++) {
                if ((j == (this.newMatrix[0].length - 1)) && (i == (this.newMatrix.length - 1))) {
                    output = output + this.getElement(i, j);
                }
                if ((j == (this.newMatrix[0].length - 1)) && (i != (this.newMatrix.length - 1))) {
                    output = output + this.getElement(i, j) + "\n";
                }
                output = output + this.getElement(i, j) + " ";
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
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true

        //test operations (valid)
        System.out.println("m1 + m1:\n" + m1.plus(m1));
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