enum SYMBOL {
    X, O, EMPTY
}

enum GAME_STATUS {
    IN_PROGRESS, WIN, TIE
}


class Player {
    private final SYMBOL symbol;
    private final String name;

    public Player(String name, SYMBOL symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public SYMBOL getSymbol() {
        return this.symbol;
    }
}


interface WinningStrategy {
    boolean isWin(Board board, SYMBOL symbol);
}

class RowWinningStrategy implements WinningStrategy {
    public boolean isWin(Board board, SYMBOL symbol) {
        boolean isWin = true;
        for(int row=0;i<board.getSize(); row++) {
            isWin = true;
            for(int col=0; col<board.getSize(); col++) {
                if (grid.getSymbol(row, col) != symbol) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return true;
            }
        }
        return false;
    }
}

class ColumnWinningStrategy implements WinningStrategy {
    public boolean isWin(Board board, SYMBOL symbol) {
        boolean isWin =  true;
        for(int col=0;col<board.getSize(); col++) {
            isWin = true;
            for(int row=0; row<board.getSize(); row++) {
                if (grid.getSymbol(row, col) != symbol) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return true;
            }
        }
        return false;
    }
}

class DiagonalWinningStrategy implements WinningStrategy {
    public boolean isWin(Board board, SYMBOL symbol) {
        boolean isWin =  true;
        
        // diagonal
        for(int row=0; row<board.getSize(); row++) {
            if (grid.getSymbol(row, col) != symbol) {
                isWin = false;
                break;
            }
        }

        if (isWin) {
            return true;
        }

        // anti diagonal
        isWin = true;
        for(int row = board.getSize()-1; row >=0; row--) {
            if (grid.getSymbol(row, board.getSize()-1-row) != symbol) {
                isWin = false;
                break;
            }
        }
        return isWin;
    }
}

class Cell {
    SYMBOL symbol;

    Cell() {
        this.symbol = SYMBOL.EMPTY;
    }

    public SYMBOL getSymbol() { 
        return this.symbol;
    }

    public void setSymbol(SYMBOL symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty(int row, int col) {
        return this.symbol == SYMBOL.EMPTY;
    }
}

class Board {
    private final Cell[][] grid;
    private WinningStrategry[] winningStrategiesList;
    private int moveCount;
    private int size;

    Board(int size, WinningStrategry[] winningStrategiesList) {
        grid = new Cell[size][size];
        this.initalizeBoard();
        this.winningStrategiesList = winningStrategiesList;
    }

    private void initalizeBoard() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public boolean checkWinner(SYMBOL symbol) {
        for(WinningStrategry winningStrategry: winningStrategiesList) {
            if (winningStrategry.isWin(symbol))
                return true;
        }
        return false;
    }

    public boolean isValidMove(int row, int col) {
        return row >=0 && row < size && col >=0 && col < size && grid[row][col].isEmpty();
    }

    public void markSymbol(int row, int col, SYMBOl symbol) {
        grid[row][col].setSymbol(row, col, symbol);
        moveCount++;
    }

    public boolean isFull() {
        return moveCount == size*size;
    }

    public void print() {
        for(int row=0; row<size; row++) {
            for(int col=0; col<size; col++) {
                System.out.println(grid[row][col] + "");
            }
        }
    }

    public int getSize() {
        return this.size;
    }
}


public class TicTacToe {
    private final int SIZE = 3;
    private final Board board;
    public final Player[] players;
    private int currentPlayerIndex;
    private GAME_STATUS gameStatus;

    public TicTacToe(Board board, Player player1, Player player2) {
        board = new Board(SIZE, new WinningStrategy[]{new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy() });
        this.players = new Player[]{player1, player2};
        this.currentPlayerIndex = 0;
        this.gameStatus = GAME_STATUS.IN_PROGRESS;
    }

    public void makeMove(int row, int col) {
        if (!board.isValidMove(row, col)) {
            throw new Exception("Invalid move");
        }

        if (gameStatus != GAME_STATUS.IN_PROGRESS) {
            throw new Exception("Game already ended");
        }

        Player currentPlayer = players[currentPlayerIndex];
        board.markSymbol(row, col, currentPlayer.getSymbol());

        if (board.checkWinner(currentPlayer.getSymbol())) {
            gameStatus = GAME_STATUS.WON;
        } else if (board.isFull()) {
            gameStatus = GAME_STATUS.TIE;
        } else {
            currentPlayerIndex = (currentPlayerIndex+1) % 2;
        }
    }
}

