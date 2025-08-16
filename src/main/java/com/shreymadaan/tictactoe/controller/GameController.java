package com.shreymadaan.tictactoe.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.shreymadaan.tictactoe.model.*;
import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;
import com.shreymadaan.tictactoe.model.constants.GameState;
import com.shreymadaan.tictactoe.model.constants.PlayerType;
import com.shreymadaan.tictactoe.service.PlayerService;

public class GameController {
    private Scanner sc;
    private PlayerService playerService;
    public GameController(PlayerService playerService){
        this.sc = new Scanner(System.in);
        this.playerService = playerService;
    }

    public List<Player> generatePlayerList(int playerCount){
        System.out.println("Please Enter 1 for BOT and 0 for USER");
        int botCheck = sc.nextInt();
        List<Player> players = new ArrayList<>();
        if(botCheck == 1){
            System.out.println("Please Enter the Bot Difficulty Level");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            int botDifficultyLevel = sc.nextInt();
            sc.nextLine();
            switch (botDifficultyLevel){
                case 1:
                    BotDifficultyLevel botDifficulty = BotDifficultyLevel.EASY;
                    break;
                case 2:
                    botDifficulty = BotDifficultyLevel.MEDIUM;
                    break;
                case 3:
                    botDifficulty = BotDifficultyLevel.HARD;
                    break;
                default: System.out.println("Invalid Input!");
            }
            System.out.println("Please Enter the Bot Name");
            String botName = sc.nextLine();
            System.out.println("Enter the Symbol for Bot " + botName);
            char botSymbol = sc.nextLine().charAt(0);

            Bot bot = playerService.createBot(botName,botSymbol);
            players.add(bot);
            playerCount--;
        }
        for(int i = 0; i < playerCount; i++){
            System.out.println("Please Enter Player Name");
            String playerName = sc.nextLine();
            System.out.println("Enter the Symbol for Player " + playerName);
            char symbol = sc.nextLine().charAt(0);
            Player player = playerService.createPlayer(playerName, symbol);
            players.add(player);
        }
        Collections.shuffle(players);
        return players;
    }

    public Move createMove(Player player, Game game){
        if(player.getPlayerType().equals(PlayerType.HUMAN)){
            System.out.println("Please Enter the Row");
            int row = sc.nextInt();
            System.out.println("Please Enter the Column");
            int column = sc.nextInt();

        }else{

        }
    }

    public GameState checkWinner(Board board, Move move){
        return null;
    }

    public Game undo(int moves, Game game){
        return null;
    }

    public Game startGame(Game game){
        return null;
    }
    public void replayGame(Game game){

    }
}
