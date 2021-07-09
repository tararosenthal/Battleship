package battleship.model;

import battleship.model.enums.CoordinatesType;

/*
 * Objects representing validated and converted user input for shot coordinates. Should be an int[2].
 */

public class ShotCoordinatesArray extends CoordinatesArray {
    public ShotCoordinatesArray(int[] array) {
        super(array, CoordinatesType.SHOT);
    }
}
