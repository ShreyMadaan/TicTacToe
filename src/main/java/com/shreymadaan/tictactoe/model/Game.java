package com.shreymadaan.tictactoe.model;

import com.shreymadaan.tictactoe.model.constants.GameState;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState state;
    private Player currentPlayer;
    private Player winner;
    private List<Move> moves;
    private List<Board> boardState;

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public GameState getGameState() {
        return state;
    }
    public void setGameState(GameState state) {
        this.state = state;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public List<Board> getBoardState() {
        return boardState;
    }
    public void setBoardState(List<Board> boardState) {
        this.boardState = boardState;
    }


}
