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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseMatrix {

    private final Table<Integer, Integer, Double> innerTable;

    /**
     * Constructs a new SparseMatrix with a given amount of rows and columns.
     * <p>
     * This constructor will throw an {@link IllegalArgumentException} if {@code rows} or {@code columns} is less
     * than {@code 1}.
     *
     * @param rows    number of rows
     * @param columns number of columns
     */
    public SparseMatrix(int rows, int columns) {
        if (rows < 1) {
            throw new IllegalArgumentException("must have at least one row");
        }
        if (columns < 1) {
            throw new IllegalArgumentException("must have at least one column");
        }
        innerTable = new Table<>(rows, columns);
    }

    /**
     * Returns the maximum number of rows in this DenseMatrix.
     *
     * @return max number of rows
     */
    public int numOfRows() {
        return innerTable.getMaximumRows();
    }

    /**
     * Returns the maximum number of columns in this DenseMatrix.
     *
     * @return max number of columns
     */
    public int numOfColumns() {
        return innerTable.getMaximumColumns();
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
        if (row < 0 || row >= numOfRows()) {
            throw new IllegalArgumentException("row must be greater than -1 and less than number of rows");
        }
        if (column < 0 || column >= numOfColumns()) {
            throw new IllegalArgumentException("column must be greater than -1 and less than number of columns");
        }
        return innerTable.has(row, column) ? innerTable.get(row, column) : 0D;
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
        if (row < 0 || row >= numOfRows()) {
            throw new IllegalArgumentException("row must be greater than -1 and less than number of rows");
        }
        if (column < 0 || column >= numOfColumns()) {
            throw new IllegalArgumentException("column must be greater than -1 and less than number of columns");
        }
        innerTable.put(row, column, value);
    }

    /**
     * Adds two {@code SparseMatrix}s together. Returns a new SparseMatrix with the results.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@code matrix} is null or if the matrices are not compatible.
     *
     * @param matrix SparseMatrix to add to this matrix
     * @return new SparseMatrix with the results
     */
    public SparseMatrix add(SparseMatrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must not be null");
        }
        if (matrix.numOfRows() != numOfRows() || matrix.numOfColumns() != numOfColumns()) {
            throw new IllegalArgumentException("matrix must have same number of rows AND columns");
        }
        SparseMatrix retMatrix = new SparseMatrix(numOfRows(), numOfColumns());
        for (int i = 0; i < numOfRows(); i++) {
            for (int j = 0; j < numOfColumns(); j++) {
                retMatrix.set(i, j, get(i, j) + matrix.get(i, j));
            }
        }
        return retMatrix;
    }

    /**
     * Multiplies two {@code SparseMatrix}s together. Returns a new SparseMatrix with the results.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@code matrix} is null or if the matrices are not compatible.
     *
     * @param matrix SparseMatrix to multiply with
     * @return new SparseMatrix with the results
     */
    public SparseMatrix multiply(SparseMatrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must not be null");
        }
        if (numOfRows() != matrix.numOfColumns() && numOfColumns() != matrix.numOfRows()) {
            throw new IllegalArgumentException("matrix dimensions are not equal");
        }
        int retRows = numOfRows();
        int retCols = matrix.numOfColumns();
        SparseMatrix retMatrix = new SparseMatrix(retRows, retCols);
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
    public String toString() {
        return innerTable.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SparseMatrix)) {
            return false;
        }

        SparseMatrix that = (SparseMatrix) o;

        return innerTable.equals(that.innerTable);
    }

    @Override
    public int hashCode() {
        return innerTable.hashCode();
    }

    /**
     * A collection that associates an ordered pair of keys, called a row key and a column key, with a single value.
     *
     * @param <R> row type
     * @param <C> column type
     * @param <V> value type
     */
    private class Table<R, C, V> {
        private final int maximumRows;
        private final int maximumColumns;
        private final Map<R, Map<C, V>> innerMap;

        /**
         * Constructs a new Table with maximum rows and columns as indicated by the passed-in parameters.
         *
         * @param rows    maximum amount of rows
         * @param columns maximum amount of columns
         */
        public Table(int rows, int columns) {
            this.maximumRows = Math.max(rows, -1);
            this.maximumColumns = Math.max(columns, -1);
            this.innerMap = new HashMap<>();
        }

        public boolean has(R row, C column) {
            if (!innerMap.containsKey(row)) {
                return false;
            }
            Map<C, V> fromRow = innerMap.get(row);
            return fromRow.containsKey(column);
        }

        /**
         * Gets and returns the value at the given mapping, or null if it doesn't exist.
         *
         * @param row    row to check
         * @param column column to check
         * @return value at the given mapping
         */
        public V get(R row, C column) {
            if (!innerMap.containsKey(row)) {
                return null;
            }
            Map<C, V> fromRow = innerMap.get(row);
            if (!fromRow.containsKey(column)) {
                return null;
            }
            return fromRow.get(column);
        }

        /**
         * Puts a value into the Table at the given mapping.
         * <p>
         * Will fail if the amount of rows would be greater than the maximum amount of rows and the same for columns.
         *
         * @param row    row to insert into
         * @param column column to insert into
         * @param value  value to place
         */
        public void put(R row, C column, V value) {
            if (innerMap.containsKey(row)) {
                Map<C, V> fromRow = innerMap.get(row);
                if (maximumColumns > 0 && fromRow.keySet().size() > maximumColumns) {
                    return;
                }
                fromRow.put(column, value);
                innerMap.put(row, fromRow);
                return;
            }
            if (maximumRows > 0 && innerMap.keySet().size() > maximumRows) {
                return;
            }
            Map<C, V> newMap = new HashMap<>();
            newMap.put(column, value);
            innerMap.put(row, newMap);
        }

        /**
         * Gets and returns the maximum amount of rows this Table can hold
         * @return maximum rows
         */
        public int getMaximumRows() {
            return maximumRows;
        }

        /**
         * Gets and returns the maximum amount of columns this Table can hold
         * @return maximum columns
         */
        public int getMaximumColumns() {
            return maximumColumns;
        }

        @Override
        public String toString() {
            List<String> strings = new ArrayList<>();
            for (Map.Entry<R, Map<C, V>> rowEntry : innerMap.entrySet()) {
                Map<C, V>  columnMap = rowEntry.getValue();
                for (Map.Entry<C, V>  columnEntry : columnMap.entrySet()) {
                    R row = rowEntry.getKey();
                    C column = columnEntry.getKey();
                    V value = columnEntry.getValue();
                    strings.add(row.toString() + ":" + column.toString() + ":" + value.toString());
                }
            }
            return strings.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Table)) {
                return false;
            }

            Table table = (Table) o;

            return maximumColumns == table.maximumColumns && maximumRows == table.maximumRows &&
                    innerMap.equals(table.innerMap);
        }

        @Override
        public int hashCode() {
            int result = maximumRows;
            result = 31 * result + maximumColumns;
            result = 31 * result + innerMap.hashCode();
            return result;
        }
    }

}
