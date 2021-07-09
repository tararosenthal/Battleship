package battleship.model;

import battleship.model.enums.*;
import battleship.view.BoardDisplay;

public class PlaceShips {

    /*
     * Validates that a ship can be placed in a certain location on a board then places ship. If ship cannot be placed, returns an error type explaining why.
     * @param boardArray            backend game board where ship is to be placed
     * @param boardDisplay          frontend game board to mark where ship is placed
     * @param ship                  ship to be placed
     * @param shipCoordinatesArray  location to place ship
     * @return ShipPlaceError       returns type of error in placement or no error if ship was placed
     */

    public static ShipPlaceError placeShip(BoardArray boardArray, BoardDisplay boardDisplay, Ship ship, ShipCoordinatesArray shipCoordinatesArray) {
        if (invalidOrientation(shipCoordinatesArray)) { //ship not vertical or horizontal
            return ShipPlaceError.ORIENTATION;
        } else if(invalidShipLength(ship, shipCoordinatesArray)) { //incorrect length
            return ShipPlaceError.LENGTH;
        } else if(elementsNotEmpty(boardArray, shipCoordinatesArray)) { //another ship in the way
            return ShipPlaceError.OCCUPIED;
        } else if(tooClose(boardArray, shipCoordinatesArray)) { //too close to another ship
            return ShipPlaceError.TOO_CLOSE;
        } else {
            boardArray.setShip(ship, shipCoordinatesArray);
            boardDisplay.setShip(shipCoordinatesArray);
            return ShipPlaceError.NO_ERROR;
        }
    }

    /*
     * Checks if coordinate range on a board is empty (contains only empty parts).
     * @param boardArray                board to be checked
     * @param shipCoordinatesArray      coordinate range to check
     * @return isNotEmpty               returns true if any non-empty part is found, else returns false
     */

    private static boolean elementsNotEmpty(BoardArray boardArray, ShipCoordinatesArray shipCoordinatesArray) {
        ShipPart[] shipParts = boardArray.getElements(shipCoordinatesArray);
        boolean isNotEmpty = false;
        for (ShipPart shipPart: shipParts) {
            if (!(shipPart.getSHIP_TYPE().equals(ShipType.EMPTY))) {
                isNotEmpty = true;
                break;
            }
        }
        return isNotEmpty;
    }

    /*
     * Checks if any elements surrounding given coordinate range are occupied (contain non-empty parts).
     * @param boardArray            board to be checked
     * @param shipCoordinatesArray  coordinate range to check
     * @return boolean              returns true if any elements directly next to given coordinates range are non-empty parts, else returns false
     */

    private static boolean tooClose(BoardArray boardArray, ShipCoordinatesArray shipCoordinatesArray) {
        return boardArray.tooClose(shipCoordinatesArray);
    }

    /*
     * Checks if a coordinate range length matches the length of a ship.
     * @param ship                  ship to be checked
     * @param shipCoordinatesArray  coordinates to check
     * @return boolean              returns true if lengths do not match, else returns false
     */

    private static boolean invalidShipLength(Ship ship, ShipCoordinatesArray shipCoordinatesArray) {
        return shipCoordinatesArray.getPlaceLength() != ship.getShipType().getLength();
    }

    /*
     * Checks if a coordinate range is in a valid orientation: horizontal or vertical.
     * @param shipCoordinatesArray  coordinates to check
     * @return boolean              returns true if orientation is invalid, else returns false
     */

    private static boolean invalidOrientation(ShipCoordinatesArray shipCoordinatesArray) {
        return shipCoordinatesArray.isInvalid();
    }
}
