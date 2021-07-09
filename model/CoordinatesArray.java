package battleship.model;

import battleship.model.enums.CoordinatesType;

/*
 * Parent class for ShipCoordinateArray and ShotCoordinateArray
 * @param array             validated user input converted into int[]
 * @param coordinatesType   type of coordinates provided (i.e. Ship or Shot)
 */

public abstract class CoordinatesArray {
    protected final int[] ARRAY;
    protected final CoordinatesType COORDINATES_TYPE;

    public CoordinatesArray(int[] array, CoordinatesType coordinatesType) {
        this.ARRAY = array;
        this.COORDINATES_TYPE = coordinatesType;
    }

    public int getElement(int element) {
        return ARRAY[element];
    }

}
