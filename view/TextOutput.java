package battleship.view;

import battleship.model.enums.*;

public class TextOutput {

    /*
     * First output in ship placement phase for each player's turn.
     * @param player        current player's turn
     */

    public static void placeShips(Player player) {
        System.out.printf("\n%s, place your ships on the game field\n", player);
    }

    /*
     * Display's player's board and requests player to enter coordinates to place a type of ship.
     * @param shipType              type of ship to be placed
     * @param boardDisplay          current player's frontend board for viewing
     */

    public static void enterCoordinates(ShipType shipType, BoardDisplay boardDisplay) {
        boardDisplay.printBoardDisplay();
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n> ", shipType, shipType.getLength());
    }

    /*
     * Outputs statement about type of error with ship placement.
     * @param shipPlaceError        type of error made during attempted ship placement
     * @param shipType              type of ship attempted to place
     */

    public static void errorStatements(ShipPlaceError shipPlaceError, ShipType shipType) {
        switch (shipPlaceError) {
            case LENGTH:
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n> ", shipType);
                break;
            case ORIENTATION:
                System.out.print("\nError! Wrong ship orientation! Try again:\n\n> ");
                break;
            case TOO_CLOSE:
                System.out.print("\nError! You placed it too close to another one. Try again:\n\n> ");
                break;
            case OCCUPIED:
                System.out.println("\nError! Another ship is in the way! Try again:\n\n> ");
                break;
            default:
                System.out.print("\nUnknown error. Try again: \n\n> ");
                break;
        }
    }

    /*
     * Outputs error statement for when a player enters ship placement coordinates that are not located on the game board.
     */

    public static void invalidShipArrayFormat() {
        System.out.print("\nError! Wrong format! Enter two coordinates located on the game board:\n\n> ");
    }

    /*
     * Outputs error statement for when a player attempts to shot a location they have already shot.
     */

    public static void alreadyShot() {
        System.out.print("Error! You already shot that location! Try again:\n\n> ");
    }

    /*
     * Instructs player on passing to next player.
     */

    public static void passMove() {
        System.out.print("\nPress Enter and pass the move to another player");
    }

    /*
     * Text to clear console screen.
     */

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\n");
        }
    }

    /*
     * Display during shooting phase for each player. Display's player's view of opponent's board, their own board, and instructions to shoot.
     * @param player            current player's turn
     */

    public static void shoot(Player player) {
        player.getBOARD_DISPLAY_OPPONENT().printBoardDisplay();
        System.out.print("---------------------");
        player.getBOARD_DISPLAY_SELF().printBoardDisplay();
        System.out.printf("\n%s, it's your turn:\n\n> ", player);
    }

    /*
     * Outputs error statement for when player attempts to shoot coordinates not located on the game board.
     */

    public static void invalidShotFormat() {
        System.out.print("\nError! Wrong format! Enter one coordinate located on the game board: \n\n> ");
    }

    /*
     * Outputs text on if valid shot was a hit or miss.
     * @param hit           true if hit, false if miss
     */

    public static void hitOrMiss(boolean hit) {
        if (hit) {
            System.out.print("\nYou hit a ship!");
        } else {
            System.out.print("\nYou missed!");
        }
    }

    /*
     * Output text instead of above when player has sunk a ship with their most recent shot.
     */

    public static void sunkAShip() {
        System.out.print("\nYou sank a ship!");
    }

    /*
     * Outputs text instead of either of above when a player has won the game.
     */

    public static void win() {
        System.out.print("\nYou sank the last ship. You won. Congratulations!");
    }
}
