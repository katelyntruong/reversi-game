import java.util.Scanner;
/**
 * Reversi Class
 *
 * This program helps to start the game Reversi.
 *
 * @author Katelyn Truong
 *
 * @version July 15, 2020
 *
 */
public class Reversi {
    public static boolean isEmpty(Point[] board) {
        /**
         * Check whether any valid moves can be played
         * @param board The game board to be checked
         * @return true if there are valid moves; false if there are no valid moves
         */
        if (board != null) {
            for (Point p : board) {
                if (p != null && p.x != -1)
                    return false;
            }
        }
        return true;
    }

    //Check whether a Point is the Points array or not

    public static boolean contains(Point[] board, Point point) {
        /**
         * Check whether the board already contains a point corresponding to a previous move
         * @param board The game board to be checked
         * @param p The point to be checked for validity
         * @return true if the board contains the point; false if the board does not contain the point
         */
        for (Point p : board) {
            if (p != null && point != null && p.x == point.x && p.y == point.y) {
                return true;
            }
        }
        return false;
    }

    public static void start(Game game) {
        /**
         * Handle input corresponding to the sequence of moves entered by the user
         * Check the validity of the move, update the board, and print out the updated board after each move
         * Determine and print out the result after the game is completed
         * @param g The Reversi game currently in play
         */

        char player = 'B';
        char opponent = 'W';
        String draw = "It is a draw.";
        String exit = "Exiting!";

        String blackMove = "Place move (Black): row then column: ";
        String blackSkipping = "Black needs to skip... Passing to white";
        String invalidBlackMove = "Invalid move!\nPlace move (Black): row then column: ";

        String whiteSkipping = "White needs to skip... Passing to Black";
        String whiteMove = "Place move (White): row then column: ";
        String invalidWhiteMove = "Invalid move!\nPlace move (White): row then column: ";

        Point[] placeablePositions = game.getPlaceableLocations(player, opponent);
        game.updateScores();
        Scanner sc = new Scanner(System.in);

        print:
        while (!isEmpty(placeablePositions) && game.bScore != 0 && game.wScore != 0 && game.remaining != 0) {
            game.showPlaceableLocations(placeablePositions, player, opponent);
            game.displayBoard();
            System.out.println();

            if (player == 'B')
                System.out.println(blackMove);
            else
                System.out.println(whiteMove);

            //input position to place piece
            String input = sc.nextLine();

            //exiting
            if (input.equals("exit")) {
                System.out.println(exit);
                break;
            }

            Point position;
            //get point based on the 2 digit input
            if (input.length() == 2) {
                try {
                    position = new Point(Integer.parseInt(String.valueOf(input.charAt(0))),
                            Integer.parseInt(String.valueOf(input.charAt(1))));
                } catch (NumberFormatException e) {
                    position = new Point(-2, -2);
                }
            } else {
                position = new Point(-2, -2);
            }
            //reenter input if it is not in the placeable array
            while (!contains(placeablePositions, position)) {
                if (player == 'B')
                    System.out.println(invalidBlackMove);
                else
                    System.out.println(invalidWhiteMove);

                String newInput = sc.nextLine();
                if (newInput.equals("exit")) {
                    System.out.println(exit);
                    break print;
                }
                if (newInput.length() == 2) {
                    try {
                        position = new Point(Integer.parseInt(String.valueOf(newInput.charAt(0))),
                                Integer.parseInt(String.valueOf(newInput.charAt(1))));
                    } catch (NumberFormatException e) {
                        position = new Point(-2, -2);
                    }
                } else {
                    position = new Point(-2, -2);
                }
            }

            //change the intended piece
            game.placeMove(position, player, opponent);

            //update scores
            game.updateScores();

            //print scores
            if (player == 'B')
                System.out.println("Black: " + game.bScore + " White: " + game.wScore);
            else
                System.out.println("White: " + game.wScore + " Black: " + game.bScore);

            //alternate player
            char temp = player;
            player = opponent;
            opponent = temp;

            placeablePositions = game.getPlaceableLocations(player, opponent);

        }

        if (isEmpty(placeablePositions) && game.remaining != 0 && game.bScore != 0 && game.wScore != 0) {
            if (player == 'B')
                System.out.println(blackSkipping);
            else
                System.out.println(whiteSkipping);
        }

        if (game.bScore == 0 || game.wScore == 0) {
            game.displayBoard();
            if (game.gameResult() == 1)
                System.out.println("Black wins: " + game.bScore + ":" + game.wScore);
            else if (game.gameResult() == -1)
                System.out.println("White wins: " + game.wScore + ":" + game.bScore);
            else
                System.out.println(draw);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        start(game);
    }
}
