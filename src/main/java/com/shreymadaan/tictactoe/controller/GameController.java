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
    private com.shreymadaan.tictactoe.service.GameOutcomeEvaluator outcomeEvaluator;

    public GameController(PlayerService playerService, GameService gameService){
        this.sc = new Scanner(System.in);
        this.playerService = playerService;
        this.gameService = gameService;
        // Depend on abstractions: compose default strategies via evaluator
        java.util.List<com.shreymadaan.tictactoe.service.strategy.WinnerCheckStrategy> strategies = new java.util.ArrayList<>();
        strategies.add(new com.shreymadaan.tictactoe.service.strategy.RowWinningStrategy());
        strategies.add(new com.shreymadaan.tictactoe.service.strategy.ColumnWinningStrategy());
        strategies.add(new com.shreymadaan.tictactoe.service.strategy.DiagnolWinningStrategy());
        this.outcomeEvaluator = new com.shreymadaan.tictactoe.service.DefaultGameOutcomeEvaluator(strategies);
    }

    public List<Player> generatePlayerList(int playerCount){
        System.out.println("Please Enter 1 for BOT and 0 for USER");
        int botCheck = sc.nextInt();
        sc.nextLine();
        List<Player> players = new ArrayList<>();
        if(botCheck == 1){
            System.out.println("Please Enter the Bot Difficulty Level");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            int botDifficultyLevel = sc.nextInt();
            sc.nextLine();
            BotDifficultyLevel selectedDifficulty = BotDifficultyLevel.MEDIUM;
            switch (botDifficultyLevel){
                case 1:
                    selectedDifficulty = BotDifficultyLevel.EASY;
                    break;
                case 2:
                    selectedDifficulty = BotDifficultyLevel.MEDIUM;
                    break;
                case 3:
                    selectedDifficulty = BotDifficultyLevel.HARD;
                    break;
                default:
                    System.out.println("Invalid Input! Defaulting to MEDIUM");
            }
            System.out.println("Please Enter Bot name");
            String botName = sc.nextLine();
            System.out.println("Enter the Symbol for Bot " + botName);
            char botSymbol = sc.nextLine().charAt(0);

            Bot bot = playerService.createBot(botName,botSymbol);
            bot.setDifficultyLevel(selectedDifficulty);
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
            int size = game.getBoard().getSize();
            int row, column;
            while (true) {
                System.out.println("Please Enter the Row (0-" + (size-1) + ")");
                while (!sc.hasNextInt()) { sc.next(); System.out.println("Please enter a valid integer for row"); }
                row = sc.nextInt();
                System.out.println("Please Enter the Column (0-" + (size-1) + ")");
                while (!sc.hasNextInt()) { sc.next(); System.out.println("Please enter a valid integer for column"); }
                column = sc.nextInt();
                sc.nextLine();
                if (row < 0 || row >= size || column < 0 || column >= size){
                    System.out.println("Invalid coordinates. Try again.");
                    continue;
                }
                com.shreymadaan.tictactoe.model.Cell cell = game.getBoard().getCells().get(row).get(column);
                if (cell.getCellState() != com.shreymadaan.tictactoe.model.constants.CellState.EMPTY){
                    System.out.println("Cell already occupied. Try again.");
                    continue;
                }
                break;
            }
            return gameService.executeMove(player, game, row, column);
        }else{
            return gameService.executeMove((Bot) player, game);
        }
    }

    public GameState checkWinner(Board board, Move move){
        return outcomeEvaluator.evaluate(board, move);
    }

    public Game undo(int moves, Game game){
        System.out.println("Undo not implemented.");
        return game;
    }

    public Game startGame(Game game){
        game.setGameState(GameState.IN_PROGRESS);
        System.out.println("Game Started!\n");
        printBoard(game.getBoard());
        int currentIndex = game.getPlayers().indexOf(game.getCurrentPlayer());
        if (currentIndex < 0) currentIndex = 0;

        while (true){
            Player current = game.getPlayers().get(currentIndex);
            System.out.println("Current Player: " + current.getName() + " (" + current.getSymbol() + ")");
            Move move = createMove(current, game);
            GameState state = checkWinner(game.getBoard(), move);
            if (state == GameState.WON){
                game.setWinner(current);
                game.setGameState(GameState.WON);
                printBoard(game.getBoard());
                System.out.println("Winner is: " + current.getName() + " (" + current.getSymbol() + ")");
                break;
            } else if (state == GameState.DRAW){
                game.setGameState(GameState.DRAW);
                printBoard(game.getBoard());
                System.out.println("Game ended in a DRAW.");
                break;
            } else {
                // continue
                printBoard(game.getBoard());
                currentIndex = (currentIndex + 1) % game.getPlayers().size();
                game.setCurrentPlayer(game.getPlayers().get(currentIndex));
            }
        }
        return game;
    }
    public void replayGame(Game game){
        System.out.println("Replay not implemented.");
    }

    private void printBoard(Board board){
        int size = board.getSize();
        for (int r = 0; r < size; r++){
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < size; c++){
                Cell cell = board.getCells().get(r).get(c);
                char ch;
                if (cell.getPlayer() != null){
                    ch = cell.getPlayer().getSymbol();
                } else {
                    ch = '-';
                }
                sb.append(ch);
                if (c < size-1) sb.append(" | ");
            }
            System.out.println(sb.toString());
            if (r < size-1){
                System.out.println("--".repeat(size*2));
            }
        }
        System.out.println();
    }
}
