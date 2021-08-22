package battleship.controller;

import battleship.model.*;
import battleship.model.enums.*;
import battleship.view.TextOutput;

import java.util.Map;
import java.util.Scanner;

public class RunGame {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static ShipCoordinatesArray shipCoordinatesArray;
    private static ShotCoordinatesArray shotCoordinatesArray;
    private static RunState runState = RunState.ENTER_COORDINATES;
    private static boolean shipPlaced = false;
    private static boolean gameOver = false;
    private static int count = 0;

    /*
     * Runs Battleship game with initial phase of ship placement by two players followed by alternating turns of firing shots until the game has ended.
     */

    public static void run() {
        shipPlacement(Player.PLAYER1);
        shipPlacement(Player.PLAYER2);
        while (!gameOver) {
            if (++count % 2 == 0) {
                fireShots(Player.PLAYER2);
            } else {
                fireShots(Player.PLAYER1);
            }
        }
    }

    /*
     * Runs ship placing phase of game. Iterates through all ShipTypes and coordinates three stages of handling user input:
     * Enter Coordinates, Error Checking Coordinates, Error Checking Ship Placement.
     * @param player            assigns current player completing phase
     */

    private static void shipPlacement(Player player) {
        TextOutput.placeShips(player); //instructs player to place ships
        for (ShipType shipType: ShipType.values()) {
            if(shipType.equals(ShipType.EMPTY)) { //ensures does not attempt to place an empty game tile
                break;
            }
            while (!shipPlaced) {
                switch (runState) {
                    case ENTER_COORDINATES:
                        runState = enterCoordinates(shipType, player);
                        break;
                    case ERROR_CHECKING_COORDINATES:
                        runState = errorCheckingCoordinates();
                        break;
                    case ERROR_CHECKING_SHIP_PLACEMENT:
                        runState = errorCheckingShipPlacement(shipType, player);
                        break;
                }
            }
            shipPlaced = false; //resets for next ShipType
        }
        passingPhase(player); //passes turn to next player
    }

    /*
     * Handles passing to the next player. If not in shooting phase, displays placement of player's ships before passing.
     * @param player        current player for displaying ships
     */

    private static void passingPhase(Player player) {
        if(!(runState.equals(RunState.FIRING_SHOT))) {
            player.getBOARD_DISPLAY_SELF().printBoardDisplay();
        }
        TextOutput.passMove();
        SCANNER.nextLine();
        TextOutput.clearScreen();
    }

    /*
     * Runs shot firing phase of game. Gets valid shot coordinates from user, finds corresponding place on opponent board to determine hit or miss.
     * Checks if game is over or if a ship has sunk. Calls passing phase or ends game.
     * @param player        current player for shot firing phase
     */

    private static void fireShots(Player player) {
        runState = RunState.ENTER_SHOT;
        TextOutput.shoot(player);
        while (RunState.ENTER_SHOT.equals(runState)) {
            validShotArray(SCANNER.nextLine(), player); //calls error checking for shot input and assignment to ShotArray object
        }

        ShipPart shipPart = FireShots.Shoot(shotCoordinatesArray, player); //gets corresponding part on opponent's board
        boolean winner = checkForWinner(player);

        if (!winner) {
            if (checkIfShipIsSunk(shipPart, player)) {
                TextOutput.sunkAShip();
            } else {
                TextOutput.hitOrMiss(shipPart.getSHIP_TYPE() != ShipType.EMPTY); //determines a hit if Ship Part is not empty type
            }
            passingPhase(player); //passes turn to next player if game not over
        } else {
            gameOver = true;
            TextOutput.win();
        }
    }

    /*
     * Validates user input for shot coordinates and ensures the coordinates have not already been fired on. Otherwise asks for further input from user and returns null.
     * @param input         a line of raw user input
     * @param player        current player entering input
     * @return shotArray    a ShotCoordinatesArray object corresponding to valid, non-repeated user input or null if coordinates are not valid
     */

    private static void validShotArray(String input, Player player) {
            CoordinatesArray shotCoordinates = ProcessInput.convertInput(input, CoordinatesType.SHOT);
            if (!(shotCoordinates instanceof ShotCoordinatesArray)) {
                TextOutput.invalidShotFormat();
            }  else {
                shotCoordinatesArray = (ShotCoordinatesArray) shotCoordinates;
                if (FireShots.alreadyShot(shotCoordinatesArray, player)) {
                    TextOutput.alreadyShot();
                }  else {
                    runState = RunState.FIRING_SHOT;
                }
            }
    }

    /*
     * Checks if current player has won the game by determining if all of opponent's ships are sunk.
     * @param player                current player in shot firing phase
     * @return allShipsDestroyed    true if all opponent's ships are sunk (all parts of each ship have been hit one time)
     */

    private static boolean checkForWinner(Player player) {
        boolean allShipsSunk = true;

        for (Map.Entry<ShipType, Ship> entry : player.getOpponent().getPLAYER_SHIPS().entrySet()) {
            if(!entry.getValue().checkIfSunk()) {
                allShipsSunk = false;
            }
        }
        return allShipsSunk;
    }

    /*
     * Checks if an individual ship is sunk.
     * @param shipPart      part of a ship that has been hit or empty if shot was a miss
     * @param player        current player who fired shot
     * @return boolean      returns false if shot was a miss or ship was not sunk, otherwise returns true
     */

    private static boolean checkIfShipIsSunk(ShipPart shipPart, Player player) {
        if (ShipType.EMPTY.equals(shipPart.getSHIP_TYPE())) {
            return false;
        } else {
            return player.getOpponent().getPLAYER_SHIPS().get(shipPart.getSHIP_TYPE()).checkIfSunk();
        }
    }

    /*
     * Handles first step of the Ship Placement phase for each ship. Outputs text to user and transitions to next step.
     * @param shipType      current ship to be placed
     * @param player        current player in Ship Placement phase
     * @return RunState     returns next step of ship placement phase
     */

    private static RunState enterCoordinates(ShipType shipType, Player player) {
        TextOutput.enterCoordinates(shipType, player.getBOARD_DISPLAY_SELF());
        return RunState.ERROR_CHECKING_COORDINATES;
    }

    /*
     * Handles second step of Ship Placement phase. Gets input from user and validates. If successful, obtains ShipArray object and goes to next step. Else notifies user and retries current step.
     * @return RunState         If able to turn user input into ShipCoordinatesArray object, returns next step. Else returns current step
     */

    private static RunState errorCheckingCoordinates() {
        String line = SCANNER.nextLine();
        CoordinatesArray shipCoordinates = ProcessInput.convertInput(line, CoordinatesType.SHIP); //if user input valid for ship placement, returns ShipArray, else returns null
        if (!(shipCoordinates instanceof ShipCoordinatesArray)) {
            TextOutput.invalidShipArrayFormat();
            return RunState.ERROR_CHECKING_COORDINATES;
        } else {
            shipCoordinatesArray = (ShipCoordinatesArray) shipCoordinates;
            return RunState.ERROR_CHECKING_SHIP_PLACEMENT;
        }
    }

    /*
     * Handles third and final step of Ship Placement phase. Attempts to place ship based on ShipCoordinatesArray. If successful, goes to first step for next ship. Else returns error type for displaying to user and
     * returns to second step to obtain new coordinates.
     * @param shipType          type of ship to be placed
     * @param player            current player in ship placement phase
     * @return RunState         If successful ship placement, returns first step to place next ship. Else returns second step.
     */

    private static RunState errorCheckingShipPlacement(ShipType shipType, Player player) {
        ShipPlaceError shipPlaceError = PlaceShips.placeShip(player.getBOARD_ARRAY(), player.getBOARD_DISPLAY_SELF(),
                player.getPLAYER_SHIPS().get(shipType), shipCoordinatesArray);
        if (ShipPlaceError.NO_ERROR.equals(shipPlaceError)) {
            shipPlaced = true;
            return RunState.ENTER_COORDINATES;
        } else {
            TextOutput.errorStatements(shipPlaceError, shipType);
            return RunState.ERROR_CHECKING_COORDINATES;
        }
    }
}
