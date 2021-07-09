package battleship.view;

import battleship.model.ShipCoordinatesArray;
import battleship.model.ShotCoordinatesArray;

public class BoardDisplay {
    private final String[][] GAME_BOARD = new String[11][11];

    /*
     * Creates a game board that is 11x11 versus 10x10 for backend board (BoardArray). The 0 column is used for labeling coordinates A-J.
     * The 0 row is used for labeling coordinates 1-10. Other values initially empty ("~").
     */

    public BoardDisplay() {
        int count = 0;
        for (int i = '@'; i < 'K'; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == '@' && j == 0) {
                    GAME_BOARD[count][j] = " ";
                } else if (i == '@') {
                    GAME_BOARD[count][j] = String.valueOf(j);
                } else if (j == 0) {
                    GAME_BOARD[count][j] = Character.toString(i);
                } else {
                    GAME_BOARD[count][j] = "~";
                }
            }
            count++;
        }
    }

    public void printBoardDisplay() {
        System.out.println();
        for (String[] aS : GAME_BOARD) {
            for (String s : aS) {
                System.out.printf("%s ", s);
            }
            System.out.println();
        }
    }

    /*
     * After coordinates have been verified as a valid location for ship placement by PlaceShips, can be used to mark corresponding coordinates on display with "O".
     * @param shipCoordinatesArray      valid coordinates for ship placement
     */

    public void setShip(ShipCoordinatesArray shipCoordinatesArray) {
        for (int i = shipCoordinatesArray.getElement(0); i <= shipCoordinatesArray.getElement(2); i++) {
            for (int j = shipCoordinatesArray.getElement(1); j <= shipCoordinatesArray.getElement(3); j++) {
                GAME_BOARD[i + 1][j + 1] = "O";
            }
        }
    }

    /*
     * Returns single element from board to verify if coordinates have already been shot.
     * @param shotCoordinatesArray      validated coordinates for a shot
     * @return String                   array element corresponding to given coordinates
     */

    public String getSingleElement(ShotCoordinatesArray shotCoordinatesArray) {
        return GAME_BOARD[shotCoordinatesArray.getElement(0) + 1][shotCoordinatesArray.getElement(1) + 1];
    }

    /*
     * After coordinates have been verified by FireShots and determined as hit or miss, can be used to mark a location as a hit or miss.
     * @param shotCoordinatesArray          valid coordinates for a shot
     * @param hit                           true if shot is a hit (corresponds to part on opponent's ship) or miss (corresponds to empty part)
     */

    public void setElement(ShotCoordinatesArray shotCoordinatesArray, boolean hit) {
        GAME_BOARD[shotCoordinatesArray.getElement(0) + 1][shotCoordinatesArray.getElement(1) + 1] = hitOrMissElement(hit);
    }

    /*
     * Returns appropriate string element for a hit or miss.
     * @param hit               true if shot is a hit, else false
     * @return String           returns "X" if a hit, "M" if a miss
     */

    private static String hitOrMissElement(boolean hit) {
        return hit ? "X" : "M";
    }
}
