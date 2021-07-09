package battleship.controller;

import battleship.model.CoordinatesArray;
import battleship.model.ShipCoordinatesArray;
import battleship.model.ShotCoordinatesArray;
import battleship.model.enums.CoordinatesType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessInput {
    /*
     * Attempts to match user input to one of two valid types: ship placement coordinates (ex. F3 F7) or shot coordinates (ex. F7) ignoring case and whitespace.
     * @param input                 one line of raw user input
     * @return coordinatesType      category of input type: Ship, Shot, or Invalid
     */

    private static CoordinatesType validateInput(String input) {
        Pattern patternShip = Pattern.compile("[A-Ja-j]([1-9]|10)[A-Ja-j]([1-9]|10)");
        Matcher matcherShip = patternShip.matcher(input);
        Pattern patternShot = Pattern.compile("[A-Ja-j]([1-9]|10)");
        Matcher matcherShot = patternShot.matcher(input);

        if (matcherShip.matches()) {
            return CoordinatesType.SHIP;
        } else if (matcherShot.matches()) {
            return CoordinatesType.SHOT;
        } else {
            return CoordinatesType.INVALID;
        }
    }

    /*
     * After validating input type, determines if received type matches expected type then returns corresponding object or null.
     * @param input                 one line of raw user input
     * @param coordinatesType       category of expected input type: Ship or Shot
     * @return coordinatesArray     corresponding object for input type: ShipCoordinatesArray or ShotCoordinatesArray,
     *                              returns null if invalid input type or received does not match expected
     */
    public static CoordinatesArray convertInput(String input, CoordinatesType coordinatesType) {
        input = input.replaceAll(" ", "").toUpperCase();

        if (validateInput(input) == coordinatesType) {
            if (CoordinatesType.SHIP.equals(coordinatesType)) {
                return new ShipCoordinatesArray(processShip(input));
            } else if (CoordinatesType.SHOT.equals(coordinatesType)) {
                return new ShotCoordinatesArray(processShot(input));
            } else {
                return null;
            }
        } else {
          return null;
        }
    }

    /*
     * Converts validated Shot input into int array for transforming into ShotCoordinatesArray object.
     * @param input          one line of raw user input after validating matches Shot type
     * @return int[]         two processed int values with first corresponding to letter and second to number, offset by one for array (ex. F7 to {5, 6})
     */

    private static int[] processShot(String input) {
        return new int[] {charToInt(input.charAt(0)) - 1, Integer.parseInt(input.substring(1)) -1};
    }

    /*
     * Converts single char to int, ignoring case (ex. A to 0 or a to 0)
     * @param s         char with values between A-J or a - j
     * @int             int with values between 0-9
     */

    private static int charToInt(char s) {
        return (int) s - 64;
    }

    /*
     * Converts validated ship input into int array with coordinate pairs sorted from lowest to highest for transforming into ShipArray object.
     * @param input             one line of raw user input after validating matches ship type
     * @return int[] array      four processed int values with first and third corresponding to letter and second and forth corresponding to number,
     *                          offset by one for array (ex. F3 F7 to {5, 2, 5, 6})
     */

    private static int[] processShip(String input) {
        int specialCase;  //handles special case of input values of 10
        int[] array = new int[4];
        input = input.replaceAll("10", "0");

        for (int i = 0; i < 4; i++) {
            if (i == 0 || i == 2) {
                array[i] = charToInt(input.charAt(i)) - 1;
            } else {
                specialCase = Integer.parseInt(String.valueOf(input.charAt(i)));
                if (specialCase == 0) {
                    array[i] = 9;
                } else {
                    array[i] = specialCase - 1;
                }
            }
        }
        sortLowestToHighest(array);
        return array;
    }

    /*
     * Sorts coordinate pairs from lowest to highest for Ship input types. If unable to be accurately sorted, will be handled by ShipCoordinatesArray object.
     * @param array         int[] of 4 values of converted Ship type input
     */

    private static void sortLowestToHighest(int[] array) {
        if(array[0] > array[2]) {
            int swap = array[0];
            array[0] = array[2];
            array[2] = swap;
        } else if(array[1] > array[3]) {
            int swap = array[1];
            array[1] = array[3];
            array[3] = swap;
        }
    }


}
