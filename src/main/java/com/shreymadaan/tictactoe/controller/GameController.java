package com.shreymadaan.tictactoe.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.shreymadaan.tictactoe.model.*;
import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;
import com.shreymadaan.tictactoe.model.constants.GameState;
import com.shreymadaan.tictactoe.model.constants.PlayerType;
import com.shreymadaan.tictactoe.service.GameService;
import com.shreymadaan.tictactoe.service.PlayerService;

public class GameController {
    private Scanner sc;
    private PlayerService playerService;
    private GameService gameService;

    public GameController(PlayerService playerService, GameService gameService){
        this.sc = new Scanner(System.in);
        this.playerService = playerService;
        this.gameService = gameService;
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
//            TODO: validate row and columnbefore executing
            return gameService.executeMove(player, game, row, column);
        }else{
            return gameService.executeMove((Bot) player, game);
        }
    }

    public GameState checkWinner(Board board, Move move){
        com.shreymadaan.tictactoe.service.strategy.WinnerCheckStrategy row = new com.shreymadaan.tictactoe.service.strategy.RowWinningStrategy();
        com.shreymadaan.tictactoe.service.strategy.WinnerCheckStrategy col = new com.shreymadaan.tictactoe.service.strategy.ColumnWinningStrategy();
        com.shreymadaan.tictactoe.service.strategy.WinnerCheckStrategy diag = new com.shreymadaan.tictactoe.service.strategy.DiagnolWinningStrategy();
        Player winner = null;
        winner = (winner != null) ? winner : row.checkWinner(board, move);
        winner = (winner != null) ? winner : col.checkWinner(board, move);
        winner = (winner != null) ? winner : diag.checkWinner(board, move);
        if (winner != null) return GameState.WON;
        // check draw
        boolean anyEmpty = false;
        for (List<Cell> cells : board.getCells()) {
            for (Cell cell : cells) {
                if (cell.getCellState() == com.shreymadaan.tictactoe.model.constants.CellState.EMPTY) {
                    anyEmpty = true; break;
                }
            }
            if (anyEmpty) break;
        }
        return anyEmpty ? GameState.IN_PROGRESS : GameState.DRAW;
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
