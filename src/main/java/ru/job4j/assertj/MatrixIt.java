package ru.job4j.assertj;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = -1;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int[][] getData() {
        return data;
    }

    private void setRow(int row) {
        this.row = row;
    }

    private void setColumn(int column) {
        this.column = column;
    }

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    private boolean rowIsLast(int r) {
        return r == getData().length - 1;
    }

    private boolean columnIsLast() {
        return getColumn() == getData()[getRow()].length - 1;
    }

    private boolean rowIsFiled(int rowNomber) {
        return getData()[rowNomber].length != 0;
    }

    @Override
    public boolean hasNext() {
        while ((!rowIsFiled(getRow())
                || rowIsFiled(getRow()) && columnIsLast())
                && !rowIsLast(getRow())
                && !rowIsFiled(getRow() + 1)) {
                setRow(getRow() + 1);
                setColumn(0);
        }
        boolean result = false;
        if (!rowIsFiled(getRow()) && !rowIsLast(getRow())) {
                result = true;
        } else if (rowIsFiled(getRow())) {
            if (!columnIsLast()) {
                result = true;
            } else if (columnIsLast() && !rowIsLast(getRow())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (!rowIsFiled(getRow())
                || rowIsFiled(getRow()) && columnIsLast()) {
            setRow(getRow() + 1);
            setColumn(0);
        } else if (rowIsFiled(getRow()) && !columnIsLast()) {
            setColumn(getColumn() + 1);
        }
        return getData()[getRow()][getColumn()];
    }
}