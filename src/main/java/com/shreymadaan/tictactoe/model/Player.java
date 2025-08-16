package com.shreymadaan.tictactoe.model;

import com.shreymadaan.tictactoe.model.constants.PlayerType;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType type;
    private boolean hasUsedUndo;

    public Player(int id,String name, char symbol, PlayerType playerType){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.type = playerType;
        this.hasUsedUndo = false;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public PlayerType getPlayerType() {
        return type;
    }
    public void setType(PlayerType type) {
        this.type = type;
    }
    public boolean isHasUsedUndo() {
        return hasUsedUndo;
    }
    public void setHasUsedUndo(boolean hasUsedUndo) {
        this.hasUsedUndo = hasUsedUndo;
    }


}
