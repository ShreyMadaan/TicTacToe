package com.shreymadaan.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;
    private int size;

    public Board(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            List<Cell> cellList = new ArrayList<>();
            for(int j=0;j<size;j++) {
                cellList.add(new Cell(i,j));
            }
            cells.add(cellList);
        }
    }
    public List<List<Cell>> getCells() {
        return cells;
    }
    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

}
