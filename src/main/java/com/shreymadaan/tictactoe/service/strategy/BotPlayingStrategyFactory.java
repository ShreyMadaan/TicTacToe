package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        switch (botDifficultyLevel){
            case EASY:
                return new EasyBotPlayingStrategy();
            case MEDIUM:
                return new MediumBotPlayingStrategy();
            case HARD:
                return new HardBotPlayingStrategy();
            default:
                throw new IllegalArgumentException("Unsupported bot difficulty level: " + botDifficultyLevel);
        }
    }
}
