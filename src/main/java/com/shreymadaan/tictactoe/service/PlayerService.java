package com.shreymadaan.tictactoe.service;

import com.shreymadaan.tictactoe.exception.DuplicateSymbolException;
import com.shreymadaan.tictactoe.model.Bot;
import com.shreymadaan.tictactoe.model.Player;
import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;
import com.shreymadaan.tictactoe.model.constants.PlayerType;

import java.util.HashSet;

public class PlayerService {
    private static int counter = 0;
    private HashSet<Character> symbolSet;

    public PlayerService() {
        this.symbolSet = new HashSet<>();
    }

    public Player createPlayer(String name, char symbol){
        if(symbolSet.contains(symbol)){
            throw new DuplicateSymbolException("Symbol already exists");
        }
        symbolSet.add(symbol);
        return new Player(
                counter++,
                name,
                symbol,
                PlayerType.HUMAN
        );
    }

    public Bot createBot(String name, char symbol){
        if(symbolSet.contains(symbol)){
            throw new DuplicateSymbolException("Symbol already exists");
        }
        symbolSet.add(symbol);
        return new Bot(
                counter++,
                name,
                symbol,
                PlayerType.BOT,
                BotDifficultyLevel.MEDIUM
        );
    }
}
