package com.shreymadaan.tictactoe;

import com.shreymadaan.tictactoe.controller.GameController;
import com.shreymadaan.tictactoe.model.Board;
import com.shreymadaan.tictactoe.model.Game;
import com.shreymadaan.tictactoe.model.Player;
import com.shreymadaan.tictactoe.model.constants.GameState;
import com.shreymadaan.tictactoe.service.GameService;
import com.shreymadaan.tictactoe.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe!");
        int size;
        while (true){
            System.out.println("Enter board size (>=3):");
            while (!sc.hasNextInt()) { sc.next(); System.out.println("Please enter a valid integer >= 3"); }
            size = sc.nextInt();
            sc.nextLine();
            if (size >= 3) break;
            System.out.println("Board size must be at least 3.");
        }

        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();
        GameController controller = new GameController(playerService, gameService);

        // We aim for a two-player game (human vs human or bot vs human based on prompt in controller)
        List<Player> players = controller.generatePlayerList(2);

        Game game = new Game();
        game.setBoard(new Board(size));
        game.setPlayers(players);
        game.setGameState(GameState.YET_TO_START);
        game.setCurrentPlayer(players.get(0));
        game.setMoves(new ArrayList<>());
        game.setBoardState(new ArrayList<>());

        controller.startGame(game);

        System.out.println("Thanks for playing!");
    }
}
