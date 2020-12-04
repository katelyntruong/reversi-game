/**
 * Game Class
 *
 * This program used to implements the game
 *
 * @author Katelyn Truong
 *
 * @version July 15, 2020
 *
 */
public class Game {

    public Point point;
    private final char[][] board;
    public int wScore;
    public int bScore;
    public int remaining;
    private final char[] boardX = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};

    public Game() {
        board = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', 'W', 'B', '_', '_', '_'},
                {'_', '_', '_', 'B', 'W', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'}};
    }

    public void displayBoard() {
        /**
         * Display the current board along with the current positions of the pieces
         * @param b The game board to be displayed
         */
        System.out.println();
        System.out.print("  ");
        for (int i = 0 ; i < boardX.length; i++) {
            System.out.print(boardX[i] + " ");
        }
        System.out.println();
        for (int k = 0; k < boardX.length; k++) {
            System.out.print(boardX[k] + " ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[k][j] + " ");
            }
            System.out.println();
        }
    }

    public int gameResult() {
        /**
         * Update the score and determine the game result
         * @param whitePiecePlaceableLocations Array of possible moves for white pieces
         * @param blackPiecePlaceableLocations Array of possible moves for black pieces
         * @return The integer corresponding to the game result: 1 for black win, 0 for draw, -1 for white win
         */
        if (bScore > wScore)
            return 1;
        else if (wScore > bScore)
            return -1;
        else
            return 0;
    }

    public Point[] getPlaceableLocations(char player, char opponent) {
        /**
         * Check the board for and fill the placeablePositions array with valid moves that the player can make
         * @param player Current player
         * @param opponent player's opponent
         * @return placeablePositions array, with corresponding point for a valid location and (-1,-1) for
         * an invalid location
         */

        Point[] placeablePositions = new Point[64];
        int rowBoard = board.length;
        int colBoard = board[0].length;
        int index = 0;

        for (int i = 0; i < rowBoard; i++) {
            for (int j = 0; j < colBoard; j++) {
                if (board[i][j] == player) {
                    int tempi = i;
                    int tempj = j;
                    while (board[i][j + 1] == opponent && j < 6) {
                        j++;
                        if (board[i][j + 1] == '_') {
                            placeablePositions[index] = new Point(i + 1, j + 2);
                            index++;
                            break;
                        }
                        if (board[i + 1][j + 1] == player)
                            break;
                    }
                    i = tempi;
                    j = tempj;
                    while (board[i][j - 1] == opponent && j > 1) {
                        j--;
                        if (board[i][j - 1] == '_') {
                            placeablePositions[index] = new Point(i + 1, j);
                            index++;
                            break;
                        }
                        if (board[i][j - 1] == player)
                            break;

                    }
                    i = tempi;
                    j = tempj;
                    while (board[i + 1][j] == opponent && i < 6) {
                        i++;
                        if (board[i + 1][j] == '_') {
                            placeablePositions[index] = new Point(i + 2, j + 1);
                            index++;
                            break;
                        }
                        if (board[i + 1][j] == player)
                            break;

                    }
                    i = tempi;
                    j = tempj;
                    while (board[i - 1][j] == opponent && i > 1) {
                        i--;
                        if (board[i - 1][j] == '_') {
                            placeablePositions[index] = new Point(i, j + 1);
                            index++;
                            break;
                        }
                        if (board[i - 1][j] == player)
                            break;

                    }
                    i = tempi;
                    j = tempj;
                    while (board[i + 1][j + 1] == opponent && i < 6 && j < 6) {
                        i++;
                        j++;
                        if (board[i + 1][j + 1] == '_') {
                            placeablePositions[index] = new Point(i + 2, j + 2);
                            index++;
                            break;
                        }
                        if (board[i + 1][j + 1] == player)
                            break;

                    }
                    i = tempi;
                    j = tempj;
                    while (board[i - 1][j - 1] == opponent && i > 1 && j > 1) {
                        i--;
                        j--;
                        if (board[i - 1][j - 1] == '_') {
                            placeablePositions[index] = new Point(i, j);
                            index++;
                            break;
                        }
                        if (board[i - 1][j - 1] == player)
                            break;

                    }
                    i = tempi;
                    j = tempj;
                    while (board[i - 1][j + 1] == opponent && i > 1 && j < 6) {
                        i--;
                        j++;
                        if (board[i - 1][j + 1] == '_') {
                            placeablePositions[index] = new Point(i, j + 2);
                            index++;
                            break;
                        }
                        if (board[i - 1][j + 1] == player)
                            break;

                    }
                    i = tempi;
                    j = tempj;
                    while (board[i + 1][j - 1] == opponent && i < 6 && j > 1) {
                        i++;
                        j--;
                        if (board[i + 1][j - 1] == '_') {
                            placeablePositions[index] = new Point(i + 2, j);
                            index++;
                            break;
                        }
                        if (board[i + 1][j - 1] == player)
                            break;
                    }
                    i = tempi;
                    j = tempj;
                }
            }
        }
        for (Point p : placeablePositions) {
            if (p == null)
                p = new Point(-1, -1);
        }
        return placeablePositions;
    }


    public void showPlaceableLocations(Point[] locations, char player, char opponent) {
        /**
         * Display the board with valid moves for the player
         * @param locations Array containing placeable locations for the player
         * @param player Current player
         * @param opponent player's opponent
         */
        for (Point p : locations) {
            if (p != null && p.x != -1 && p.y != -1)
                board[p.x - 1][p.y - 1] = '*';
        }
    }

    public void placeMove(Point replacedPoint, char player, char opponent) {
        /**
         * Place a piece on the board at the location by the point p and update the board accordingly
         * @param p Point corresponding to the location of the piece to be placed
         * @param player Current player
         * @param opponent player's opponent
         */
        if (replacedPoint != null) {
            int xboard = replacedPoint.x - 1;
            int yboard = replacedPoint.y - 1;
            board[xboard][yboard] = player;

            int tempx = xboard;
            int tempy = yboard;
            while (board[xboard][yboard + 1] == opponent && yboard < 6) {
                yboard++;
                if (board[xboard][yboard + 1] == player) {
                    for (int i = tempy + 1; i < yboard + 1; i++) {
                        board[xboard][i] = player;
                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard][yboard - 1] == opponent  && yboard > 1) {
                yboard--;
                if (board[xboard][yboard - 1] == player) {
                    for (int i = tempy - 1; i > yboard - 1; i--) {
                        board[xboard][i] = player;
                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard + 1][yboard] == opponent && xboard < 6) {
                xboard++;
                if (board[xboard + 1][yboard] == player) {
                    for (int i = tempx + 1; i < xboard + 1; i++) {
                        board[i][yboard] = player;

                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard - 1][yboard] == opponent && xboard > 1) {
                xboard--;
                if (board[xboard - 1][yboard] == player) {
                    for (int i = tempx - 1; i > xboard - 1; i--) {
                        board[i][yboard] = player;

                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard - 1][yboard - 1] == opponent && xboard > 1 && yboard > 1) {
                yboard--;
                xboard--;
                if (board[xboard - 1][yboard - 1] == player) {
                    while (board[xboard][yboard] == opponent) {
                        board[xboard][yboard] = player;
                        xboard++;
                        yboard++;
                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard + 1][yboard - 1] == opponent && xboard < 6 && yboard > 1) {
                xboard++;
                yboard--;
                if (board[xboard + 1][yboard - 1] == player) {
                    while (board[xboard][yboard] == opponent) {
                        board[xboard][yboard] = player;
                        xboard--;
                        yboard++;
                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard + 1][yboard + 1] == opponent && xboard < 6 && yboard < 6) {
                xboard++;
                yboard++;
                if (board[xboard + 1][yboard + 1] == player) {
                    while (board[xboard][yboard] == opponent) {
                        board[xboard][yboard] = player;
                        xboard--;
                        yboard--;
                    }
                    break;
                }
            }
            xboard = tempx;
            yboard = tempy;
            while (board[xboard - 1][yboard + 1] == opponent && xboard > 1 && yboard < 6) {
                xboard--;
                yboard++;
                if (board[xboard - 1][yboard + 1] == player) {
                    while (board[xboard][yboard] == opponent) {
                        board[xboard][yboard] = player;
                        xboard++;
                        yboard--;
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '*')
                    board[i][j] = '_';
            }
        }
    }

    public void updateScores() {
        /**
         * Update the scores (number of pieces of a player's color) of each player
         */
        wScore = 0;
        bScore = 0;
        int rowBoard = board.length;
        int colBoard = board[0].length;
        for (int i = 0; i < rowBoard; i++) {
            for (int j = 0; j < colBoard; j++) {
                if (board[i][j] == 'W')
                    wScore++;
                if (board[i][j] == 'B')
                    bScore++;
            }
        }
        remaining = 64 - wScore - bScore;
    }
}
