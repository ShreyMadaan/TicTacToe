package com.shreymadaan.tictactoe.model;

import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;
import com.shreymadaan.tictactoe.model.constants.PlayerType;

public class Bot extends Player{
    private BotDifficultyLevel difficultyLevel;

    public Bot(int id, String name, char symbol,PlayerType playerType, BotDifficultyLevel botDifficultyLevel){
        super(id,name,symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
