package battleship.model;

import battleship.model.enums.*;

import java.util.Arrays;

public class BoardArray {
    private final ShipPart[][] BOARD = new ShipPart[10][10];

    /*
     * Creates new 10x10 board filled with empty parts.
     */

    public BoardArray() {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(BOARD[i], ShipPart.getEmptyPart());
        }
    }

    /*
     * Returns single element on board to determine hit or miss with ShotCoordinatesArray.
     */

    ShipPart getSingleElement(ShotCoordinatesArray shotCoordinatesArray) {
        return BOARD[shotCoordinatesArray.getElement(0)][shotCoordinatesArray.getElement(1)];
    }
    /*
     * Returns multiple elements on board to determine if ship placement is possible with given ShipCoordinatesArray.
     */

    ShipPart[] getElements(ShipCoordinatesArray shipCoordinatesArray) {
        ShipPart[] shipParts = new ShipPart[shipCoordinatesArray.getPlaceLength()];
        int count = 0;
        for (int i = shipCoordinatesArray.getElement(0); i <= shipCoordinatesArray.getElement(2); i++) {
            for (int j = shipCoordinatesArray.getElement(1); j <= shipCoordinatesArray.getElement(3); j++) {
                shipParts[count] = BOARD[i][j];
                count++;
            }
        }
        return shipParts;
    }

    /*
     * Determines if any board elements surrounding given coordinate range contain ship parts rather than empty.
     * @param shipCoordinatesArray         validated coordinates for ship placement
     * @return boolean                     returns true if any elements contain ship parts, false if all elements are empty
     */

    boolean tooClose(ShipCoordinatesArray shipCoordinatesArray) {
        for (int i = Math.max(0, shipCoordinatesArray.getElement(0) - 1); i <= Math.min(9, shipCoordinatesArray.getElement(2) + 1); i++) {
            for (int j = Math.max(0, shipCoordinatesArray.getElement(1) - 1); j <= Math.min(9, shipCoordinatesArray.getElement(3) + 1); j++) {
                if (!(BOARD[i][j].getSHIP_TYPE() == ShipType.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Places ship on board once coordinates given and location on board have been validated by PlaceShips.
     * @param ship                      ship to be placed
     * @param shipCoordinatesArray      coordinates for ship placement
     */

    void setShip(Ship ship, ShipCoordinatesArray shipCoordinatesArray) {
        int count = 0;
        for (int i = shipCoordinatesArray.getElement(0); i <= shipCoordinatesArray.getElement(2); i++) {
            for (int j = shipCoordinatesArray.getElement(1); j <= shipCoordinatesArray.getElement(3); j++) {
                BOARD[i][j] = ship.getShipPart(count);
                count++;
            }
        }
    }
}
