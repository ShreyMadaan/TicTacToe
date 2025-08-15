package com.shreymadaan.tictactoe.model;

import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;

public class Bot extends Player{
    private BotDifficultyLevel difficultyLevel;

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
