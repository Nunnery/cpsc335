/*
 * This file is part of cspc335, licensed under the Fair License.
 *
 * (C) 2015 Richard Harrah <richard.harrah.13@cnu.edu>
 *
 * Usage of the works is permitted provided that this instrument is retained with the works, so that any entity that uses
 * the works is notified of this instrument.
 *
 * DISCLAIMER: THE WORKS ARE WITHOUT WARRANTY.
 */
package me.topplethenun.cpsc335;

import java.util.Arrays;

/**
 * This class represents a densely-stored matrix using a 2D array.
 *
 * @author Richard Harrah
 */
public class DenseMatrix {

    private double[][] elements;

    /**
     * Constructs a new DenseMatrix with a given amount of rows and columns.
     * <p>
     * This constructor will throw an {@link IllegalArgumentException} if {@code rows} or {@code columns} is less
     * than {@code 1}.
     *
     * @param rows    number of rows
     * @param columns number of columns
     */
    public DenseMatrix(int rows, int columns) {
        if (rows < 1) {
            throw new IllegalArgumentException("must have at least one row");
        }
        if (columns < 1) {
            throw new IllegalArgumentException("must have at least one column");
        }
        this.elements = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.elements[i][j] = 0D;
            }
        }
    }

    /**
     * Returns the maximum number of rows in this DenseMatrix.
     *
     * @return max number of rows
     */
    public int numOfRows() {
        return elements.length;
    }

    /**
     * Returns the maximum number of columns in this DenseMatrix.
     *
     * @return max number of columns
     */
    public int numOfColumns() {
        return elements[0].length;
    }

    /**
     * Gets and returns the value at the given row and column.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@code row} is less than 0 or equal to or greater than
     * {@link #numOfRows()} or if {@code column} is less than 0 or equal to or greater than {@link #numOfColumns()}.
     *
     * @param row    row to query
     * @param column column to query
     * @return value at the given row and column
     */
    public double get(int row, int column) {
        if (row < 0 || row >= elements.length) {
            throw new IllegalArgumentException("row must be greater than -1 and less than number of rows");
        }
        if (column < 0 || column >= elements[0].length) {
            throw new IllegalArgumentException("column must be greater than -1 and less than number of columns");
        }
        return elements[row][column];
    }

    /**
     * Sets the value at the given row and column.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@code row} is less than 0 or equal to or greater than
     * {@link #numOfRows()} or if {@code column} is less than 0 or equal to or greater than {@link #numOfColumns()}.
     *
     * @param row    row to set value
     * @param column column to set value
     * @param value  value to set
     */
    public void set(int row, int column, double value) {
        if (row < 0 || row >= elements.length) {
            throw new IllegalArgumentException("row must be greater than -1 and less than number of rows");
        }
        if (column < 0 || column >= elements[0].length) {
            throw new IllegalArgumentException("column must be greater than -1 and less than number of columns");
        }
        elements[row][column] = value;
    }

    /**
     * Adds two {@code DenseMatrix}s together. Returns a new DenseMatrix with the results.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@code matrix} is null or if the matrices are not compatible.
     *
     * @param matrix DenseMatrix to add to this matrix
     * @return new DenseMatrix with the results
     */
    public DenseMatrix add(DenseMatrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must not be null");
        }
        if (matrix.numOfRows() != numOfRows() || matrix.numOfColumns() != numOfColumns()) {
            throw new IllegalArgumentException("matrix must have same number of rows AND columns");
        }
        DenseMatrix retMatrix = new DenseMatrix(numOfRows(), numOfColumns());
        for (int i = 0; i < numOfRows(); i++) {
            for (int j = 0; j < numOfColumns(); j++) {
                retMatrix.set(i, j, get(i, j) + matrix.get(i, j));
            }
        }
        return retMatrix;
    }

    /**
     * Multiplies two {@code DenseMatrix}s together. Returns a new DenseMatrix with the results.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@code matrix} is null or if the matrices are not compatible.
     *
     * @param matrix DenseMatrix to multiply with
     * @return new DenseMatrix with the results
     */
    public DenseMatrix multiply(DenseMatrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must not be null");
        }
        if (numOfRows() != matrix.numOfColumns() && numOfColumns() != matrix.numOfRows()) {
            throw new IllegalArgumentException("matrix dimensions are not equal");
        }
        int retRows = numOfRows();
        int retCols = matrix.numOfColumns();
        DenseMatrix retMatrix = new DenseMatrix(retRows, retCols);
        for (int i = 0; i < retRows; i++) {
            for (int j = 0; j < retCols; j++) {
                retMatrix.set(i, j, 0D);
            }
        }
        for (int i = 0; i < numOfRows(); i++) {
            for (int j = 0; j < matrix.numOfColumns(); j++) {
                for (int k = 0; k < numOfColumns(); k++) {
                    double curVal = retMatrix.get(i, j);
                    double adding = get(i, k) * matrix.get(k, j);
                    retMatrix.set(i, j, curVal + adding);
                }
            }
        }
        return retMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DenseMatrix)) {
            return false;
        }

        DenseMatrix other = (DenseMatrix) o;

        if (numOfRows() != other.numOfRows() || numOfColumns() != other.numOfColumns()) {
            return false;
        }

        for (int i = 0; i < numOfRows(); i++) {
            for (int j = 0; j < numOfColumns(); j++) {
                if (Math.abs(get(i, j) - other.get(i, j)) > 0.00005) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (double[] element : elements) {
            hash += Arrays.hashCode(element);
        }
        return hash;
    }

}
