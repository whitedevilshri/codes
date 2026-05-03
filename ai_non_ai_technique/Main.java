import java.util.*;

/**
 * AI vs Non-AI Technique - Tic Tac Toe
 * Demonstrates the difference between:
 *   - Non-AI move: simple first-available cell strategy
 *   - AI move: Minimax algorithm (optimal play)
 * Human plays 'X', Computer plays 'O'
 */
public class Main {

    static char[][] board = new char[3][3];

    /** Initialize board with empty spaces */
    static void initBoard() {
        for (char[] row : board)
            Arrays.fill(row, ' ');
    }

    /** Display current board state */
    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("--+---+--");
        }
        System.out.println();
    }

    /** Check if any moves remain */
    static boolean movesLeft() {
        for (char[] row : board)
            for (char c : row)
                if (c == ' ') return true;
        return false;
    }

    /**
     * Evaluate board: +10 if 'O' wins, -10 if 'X' wins, 0 for draw
     */
    static int evaluate() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'O') return +10;
                if (board[i][0] == 'X') return -10;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] == 'O') return +10;
                if (board[0][j] == 'X') return -10;
            }
        }
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'O') return +10;
            if (board[0][0] == 'X') return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'O') return +10;
            if (board[0][2] == 'X') return -10;
        }
        return 0;
    }

    /**
     * Minimax algorithm: AI player is maximizer ('O'), human is minimizer ('X')
     */
    static int minimax(boolean isMax) {
        int score = evaluate();
        if (score == 10 || score == -10) return score;
        if (!movesLeft()) return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        best = Math.max(best, minimax(false));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        best = Math.min(best, minimax(true));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    /** AI move: find best move using Minimax */
    static int[] findBestMove() {
        int bestVal = -1000;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int moveVal = minimax(false);
                    board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove = new int[]{i, j};
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    /** Non-AI move: pick first available cell (no intelligence) */
    static int[] nonAIMove() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return new int[]{i, j};
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initBoard();

        System.out.println("=== Tic Tac Toe: AI vs Non-AI Demo ===");
        System.out.println("1. Non-AI (first-available move)");
        System.out.println("2. AI (Minimax)");
        System.out.print("Choose opponent mode: ");
        int choice = sc.nextInt();

        while (true) {
            printBoard();

            // Human's turn
            System.out.print("Enter row and col (0-2): ");
            int r = sc.nextInt(), c = sc.nextInt();
            if (board[r][c] != ' ') {
                System.out.println("Cell occupied! Try again.");
                continue;
            }
            board[r][c] = 'X';

            if (evaluate() == -10) {
                printBoard();
                System.out.println("You Win!");
                break;
            }
            if (!movesLeft()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }

            // Computer's turn
            int[] move = (choice == 1) ? nonAIMove() : findBestMove();
            board[move[0]][move[1]] = 'O';
            System.out.println("Computer played: (" + move[0] + "," + move[1] + ")");

            if (evaluate() == 10) {
                printBoard();
                System.out.println("Computer Wins!");
                break;
            }
            if (!movesLeft()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }
        }
        sc.close();
    }
}
