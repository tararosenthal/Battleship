package battleship.model;

import battleship.model.enums.CoordinatesType;

/*
 * Created from validated user input for ship placement. Must be an int[4]. Determines length between coordinates and if orientation is valid (horizontal or vertical).
 */

public class ShipCoordinatesArray extends CoordinatesArray {
        private boolean isHorizontal = false;
        private boolean isInvalid = false;
        private int placeLength = 0;

        public ShipCoordinatesArray(int[] array) {
            super(array, CoordinatesType.SHIP);
            determineOrientation();
            determinePlaceLength();
        }

        private void determineOrientation() {
            if(ARRAY[0] == ARRAY[2] && ARRAY[1] != ARRAY[3]) {
                isHorizontal = true;
            } else if(!(ARRAY[1] == ARRAY[3] && ARRAY[0] != ARRAY[2])) { //if not vertical orientation
                isInvalid = true;
            }
        }

        private void determinePlaceLength() {
            if (isHorizontal) {
                placeLength = ARRAY[3] - ARRAY[1] + 1;
            } else if(!isInvalid) { //if vertical orientation
                placeLength = ARRAY[2] - ARRAY[0] + 1;
            }
        }

        public boolean isInvalid() {
            return isInvalid;
        }

        public int getPlaceLength() {
            return placeLength;
        }
    }