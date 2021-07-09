package battleship.model.enums;

import battleship.model.BoardArray;
import battleship.model.Ship;
import battleship.view.BoardDisplay;

import java.util.HashMap;

/*
 * Creates and organizes all objects needed for each player including boards and ships. Do not add more players without adjusting getOpponent method.
 */

public enum Player {
    PLAYER1,
    PLAYER2;

    private final BoardArray BOARD_ARRAY;
    private final BoardDisplay BOARD_DISPLAY_SELF;
    private final BoardDisplay BOARD_DISPLAY_OPPONENT;
    private final HashMap<ShipType, Ship> PLAYER_SHIPS;

    Player() {
            BOARD_ARRAY = new BoardArray();
            BOARD_DISPLAY_SELF = new BoardDisplay();
            BOARD_DISPLAY_OPPONENT = new BoardDisplay();
            PLAYER_SHIPS = Ship.createShips();
    }

    public BoardArray getBOARD_ARRAY() {
        return BOARD_ARRAY;
    }

    public BoardDisplay getBOARD_DISPLAY_SELF() {
        return BOARD_DISPLAY_SELF;
    }

    public BoardDisplay getBOARD_DISPLAY_OPPONENT() {
        return BOARD_DISPLAY_OPPONENT;
    }

    public HashMap<ShipType, Ship> getPLAYER_SHIPS() {
        return PLAYER_SHIPS;
    }

    public Player getOpponent() {
        if (this.equals(PLAYER1)) {
            return PLAYER2;
        } else {
            return PLAYER1;
        }
    }


    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1, 6).toLowerCase() + " " + this.name().substring(6);
    }
}
