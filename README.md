# TicTacToe (Java, LLD, Console)

A clean, extensible, console-based TicTacToe implementation in Java that demonstrates Low-Level Design (LLD) principles:
- Separation of concerns (controller, services, models, strategies)
- Strategy pattern for both bot moves and winner evaluation
- Dependency composition via an outcome evaluator that aggregates multiple winning strategies

This project supports variable board sizes (N x N, N ≥ 3) and play modes: Human vs Human or Human vs Bot (Easy/Medium/Hard).


## Features
- Variable board size: choose any N ≥ 3 at startup
- Human vs Human or Human vs Bot
- Bot difficulty levels: Easy, Medium, Hard (pluggable strategies)
- Clear game loop with input validation and dynamic current player rotation
- Strategy-driven winner checks: rows, columns, and diagonals
- Simple, readable console UI


## Tech Stack
- Language: Java 21
- Build: Maven (no external dependencies)


## Project Structure
```
TicTacToe/
├─ pom.xml
├─ README.md
└─ src/main/java/com/shreymadaan/tictactoe/
   ├─ Main.java                                # Entry point: input board size, start game
   ├─ controller/
   │  └─ GameController.java                   # Orchestrates game: player creation, loop, I/O
   ├─ exception/
   │  ├─ DuplicateSymbolException.java
   │  └─ InvalidCellException.java
   ├─ model/
   │  ├─ Board.java
   │  ├─ Bot.java
   │  ├─ Cell.java
   │  ├─ Game.java
   │  ├─ Move.java
   │  ├─ Player.java
   │  └─ constants/
   │     ├─ BotDifficultyLevel.java
   │     ├─ CellState.java
   │     ├─ GameState.java
   │     └─ PlayerType.java
   ├─ service/
   │  ├─ GameService.java                      # Core game operations, executes moves
   │  ├─ PlayerService.java                    # Player/Bot creation
   │  ├─ GameOutcomeEvaluator.java             # Abstraction for game outcome evaluation
   │  ├─ DefaultGameOutcomeEvaluator.java      # Composes winner-check strategies
   │  └─ strategy/
   │     ├─ WinnerCheckStrategy.java           # Strategy for checking win/draw
   │     ├─ RowWinningStrategy.java
   │     ├─ ColumnWinningStrategy.java
   │     ├─ DiagnolWinningStrategy.java        # (spelling as in code)
   │     ├─ BotPlayingStrategy.java            # Strategy for bot moves
   │     ├─ EasyBotPlayingStrategy.java
   │     ├─ MediumBotPlayingStrategy.java
   │     ├─ HardBotPlayingStrategy.java
   │     └─ BotPlayingStrategyFactory.java     # Returns strategy by difficulty
```


## How it works (Design Overview)
- Main initializes:
  - Prompts for board size (N ≥ 3)
  - Builds Player list via GameController (Human or Bot, difficulty, names, symbols)
  - Initializes Game with Board, Players, starting state
- GameController:
  - Composes DefaultGameOutcomeEvaluator with strategies: Row, Column, Diagonal
  - Runs the game loop: prompts for moves, validates input, updates board
  - After each move, evaluates game state: WON, DRAW, or IN_PROGRESS
  - Renders the board to the console after each turn
- Strategy Pattern:
  - WinnerCheckStrategy: separate classes handle different win checks
  - BotPlayingStrategy: easy/medium/hard behaviors are swappable

Note: Undo and Replay are placeholders and currently not implemented.


## Requirements
- Java 21+
- Maven 3.9+


## Build and Run
From the project root:

1) Compile the project
- mvn -q -DskipTests package

2) Run the console app (using compiled classes)
- java --class-path target/classes com.shreymadaan.tictactoe.Main

If you prefer, you can install the Maven Exec plugin and run via mvn exec:java, but the project ships plugin-free for simplicity.


## Usage (What to expect)
- On start, you will see: "Welcome to TicTacToe!"
- Enter a board size (>= 3)
- Choose whether to include a Bot (1 = BOT, 0 = USER for both)
- If BOT is selected:
  - Pick difficulty (1 = Easy, 2 = Medium, 3 = Hard)
  - Provide bot name and symbol (single character)
- Provide player names and symbols (single characters) for remaining players
- The players are shuffled to decide who goes first
- For human turns, you will be prompted for Row and Column indices in [0..N-1]
- The board prints after each turn, with '-' for empty cells and each player's symbol for moves

Example turn snippet (N = 3):
```
Current Player: Alice (X)
Please Enter the Row (0-2)
0
Please Enter the Column (0-2)
1
- | X | -
------
- | - | -
------
- | - | -
```


## Extending the game
- Add a new win condition: implement WinnerCheckStrategy and register it in GameController's constructor list before creating DefaultGameOutcomeEvaluator
- Add/modify bot behavior:
  - Implement BotPlayingStrategy (e.g., Minimax) and hook it in BotPlayingStrategyFactory
- Change UI: replace GameController's console I/O with your own adapter while keeping services/models unchanged


## Notes
- Symbols must be unique per player; validation exists at creation time
- Coordinates are zero-based
- Board size is dynamic; win checks adapt to N x N


## License
This project is licensed under the MIT License. See the LICENSE file for details.
