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

import org.junit.Test;

import static org.junit.Assert.*;

public class DenseMatrixTest {

    @Test
    public void doesConstructorSetNumberOfRowsAndColumns() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);
        assertEquals(rows, matrix.numOfRows());
        assertEquals(columns, matrix.numOfColumns());
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNegativeRowThrowException() {
        int rows = -5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNegativeColumnThrowException() {
        int rows = 5;
        int columns = -5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);
    }

    @Test
    public void doesGetReturnZeroWithNoSetting() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        double expected = 0;
        double actual = matrix.get(0, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesGetThrowExceptionWithNegativeRow() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        double expected = 0;
        double actual = matrix.get(-1, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesGetThrowExceptionWithNegativeColumn() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        double expected = 0;
        double actual = matrix.get(0, -1);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesGetThrowExceptionWithTooLargeRow() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        double expected = 0;
        double actual = matrix.get(rows, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesGetThrowExceptionWithTooLargeColumn() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        double expected = 0;
        double actual = matrix.get(0, columns);

        assertEquals(expected, actual, 0.000005);
    }

    @Test
    public void doesSetChangeValue() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        matrix.set(0, 0, 5);

        double expected = 5;
        double actual = matrix.get(0, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesSetThrowExceptionWithNegativeRow() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        matrix.set(-1, 0, 5D);

        double expected = 0;
        double actual = matrix.get(0, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesSetThrowExceptionWithNegativeColumn() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        matrix.set(0, -1, 5D);

        double expected = 0;
        double actual = matrix.get(0, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesSetThrowExceptionWithTooLargeRow() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        matrix.set(rows, 0, 5D);

        double expected = 0;
        double actual = matrix.get(0, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesSetThrowExceptionWithTooLargeColumn() {
        int rows = 5;
        int columns = 5;
        DenseMatrix matrix = new DenseMatrix(rows, columns);

        assertNotNull(matrix);

        matrix.set(0, columns, 5D);

        double expected = 0;
        double actual = matrix.get(0, 0);

        assertEquals(expected, actual, 0.000005);
    }

    @Test
    public void doesAddCorrectlyAddValues() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);
        DenseMatrix matrixTwo = new DenseMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixOne.set(i, j, 5);
                matrixTwo.set(i, j, 4);
            }
        }

        DenseMatrix expected = new DenseMatrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                expected.set(i, j, 9);
            }
        }
        DenseMatrix actual = matrixOne.add(matrixTwo);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesAddThrowExceptionWithNull() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixOne.set(i, j, 5);
            }
        }

        DenseMatrix expected = new DenseMatrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                expected.set(i, j, 5);
            }
        }

        DenseMatrix actual = matrixOne.add(null);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesAddThrowExceptionWhenNotSameSize() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);
        DenseMatrix matrixTwo = new DenseMatrix(rows, columns + 1);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixOne.set(i, j, 5);
                matrixTwo.set(i, j, 4);
            }
        }

        DenseMatrix expected = new DenseMatrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                expected.set(i, j, 9);
            }
        }
        DenseMatrix actual = matrixOne.add(matrixTwo);

        assertEquals(expected, actual);
    }

    @Test
    public void doesEqualSelf() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);

        assertEquals(matrixOne, matrixOne);
    }

    @Test
    public void doesNotEqualNull() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);

        assertNotEquals(matrixOne, null);
    }

    @Test
    public void doesEqualSimilar() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);
        DenseMatrix matrixTwo = new DenseMatrix(rows, columns);

        assertEquals(matrixOne, matrixTwo);
    }

    @Test
    public void doesNotEqualDissimilar() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);
        DenseMatrix matrixTwo = new DenseMatrix(rows, columns + 1);

        assertFalse(matrixOne.equals(matrixTwo));
    }

    @Test
    public void doesNotEqualAlmostTheSame() {
        int rows = 2;
        int columns = 2;
        DenseMatrix matrixOne = new DenseMatrix(rows, columns);
        DenseMatrix matrixTwo = new DenseMatrix(rows, columns + 1);

        matrixOne.set(0, 0, 1);
        matrixOne.set(0, 1, 1);
        matrixOne.set(1, 0, 1);
        matrixOne.set(1, 1, 1);

        matrixTwo.set(0, 0, 1);
        matrixTwo.set(0, 1, 2);
        matrixTwo.set(1, 0, 1);
        matrixTwo.set(1, 1, 1);

        assertFalse(matrixOne.equals(matrixTwo));
    }

    @Test
    public void doesMultiplyCorrectlyMultiplyMatrices() {
        DenseMatrix matrixOne = new DenseMatrix(2, 3);
        DenseMatrix matrixTwo = new DenseMatrix(3, 2);

        /*
         * Setup a matrix that looks like the following
         * 1 2 3
         * 4 5 6
         */
        matrixOne.set(0, 0, 1);
        matrixOne.set(0, 1, 2);
        matrixOne.set(0, 2, 3);
        matrixOne.set(1, 0, 4);
        matrixOne.set(1, 1, 5);
        matrixOne.set(1, 2, 6);

        /*
         * Setup a matrix that looks like the following
         * 07 08
         * 09 10
         * 11 12
         */
        matrixTwo.set(0, 0, 7);
        matrixTwo.set(0, 1, 8);
        matrixTwo.set(1, 0, 9);
        matrixTwo.set(1, 1, 10);
        matrixTwo.set(2, 0, 11);
        matrixTwo.set(2, 1, 12);

        DenseMatrix expected = new DenseMatrix(2, 2);
        /*
         * Setup a matrix that looks like the following
         * 058 064
         * 139 154
         */
        expected.set(0, 0, 58);
        expected.set(0, 1, 64);
        expected.set(1, 0, 139);
        expected.set(1, 1, 154);

        DenseMatrix actual = matrixOne.multiply(matrixTwo);

        assertEquals(matrixOne.numOfRows(), actual.numOfRows());
        assertEquals(matrixTwo.numOfColumns(), actual.numOfColumns());
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesMultiplyThrowExceptionWhenNull() {
        DenseMatrix matrixOne = new DenseMatrix(2, 3);

        /*
         * Setup a matrix that looks like the following
         * 1 2 3
         * 4 5 6
         */
        matrixOne.set(0, 0, 1);
        matrixOne.set(0, 1, 2);
        matrixOne.set(0, 2, 3);
        matrixOne.set(1, 0, 4);
        matrixOne.set(1, 1, 5);
        matrixOne.set(1, 2, 6);

        DenseMatrix actual = matrixOne.multiply(null);

        assertEquals(matrixOne.numOfRows(), actual.numOfRows());
    }

    @Test
    public void doesMultiplyThrowExceptionWhenInvalidMults() {
        DenseMatrix matrixOne = new DenseMatrix(2, 3);
        DenseMatrix matrixTwo = new DenseMatrix(4, 2);

        /*
         * Setup a matrix that looks like the following
         * 1 2 3
         * 4 5 6
         */
        matrixOne.set(0, 0, 1);
        matrixOne.set(0, 1, 2);
        matrixOne.set(0, 2, 3);
        matrixOne.set(1, 0, 4);
        matrixOne.set(1, 1, 5);
        matrixOne.set(1, 2, 6);

        /*
         * Setup a matrix that looks like the following
         * 07 08
         * 09 10
         * 11 12
         * 13 14
         */
        matrixTwo.set(0, 0, 7);
        matrixTwo.set(0, 1, 8);
        matrixTwo.set(1, 0, 9);
        matrixTwo.set(1, 1, 10);
        matrixTwo.set(2, 0, 11);
        matrixTwo.set(2, 1, 12);
        matrixTwo.set(3, 0, 13);
        matrixTwo.set(3, 1, 14);

        DenseMatrix actual = matrixOne.multiply(matrixTwo);

        assertEquals(matrixOne.numOfRows(), actual.numOfRows());
        assertEquals(matrixTwo.numOfColumns(), actual.numOfColumns());
    }

}