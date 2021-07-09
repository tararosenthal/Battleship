package battleship.model;

import battleship.model.enums.Player;
import battleship.model.enums.ShipType;

public class FireShots {

    /*
     * Takes shot coordinates and alters player and opponent's displayed boards based on if shot is a hit or miss. Also marks the corresponding ship part as hit if is not an empty part (i.e. not belonging to a ship).
     * @param shotCoordinatesArray      location to fire on
     * @param player                    current player who fired shot
     * @return shipPart                 individual part of ship on opponent's board (or empty part) corresponding to shotCoordinatesArray location
     */

    public static ShipPart Shoot(ShotCoordinatesArray shotCoordinatesArray, Player player) {
        ShipPart shipPart = player.getOpponent().getBOARD_ARRAY().getSingleElement(shotCoordinatesArray);

        if (ShipType.EMPTY.equals(shipPart.getSHIP_TYPE())) { //miss
            player.getBOARD_DISPLAY_OPPONENT().setElement(shotCoordinatesArray, false); //player's display of opponent's board
            player.getOpponent().getBOARD_DISPLAY_SELF().setElement(shotCoordinatesArray, false); //opponent's display of own board
        } else { //hit
            shipPart.setHit();
            player.getBOARD_DISPLAY_OPPONENT().setElement(shotCoordinatesArray, true); //player's display of opponent's board
            player.getOpponent().getBOARD_DISPLAY_SELF().setElement(shotCoordinatesArray, true); //opponent's display of own board
        }
        return shipPart;
    }

    /*
     * Determines if given coordinates on opponent's board have already been shot by player.
     * @param shotCoordinatesArray      location to fire on
     * @param player                    current player attempting to shoot
     * @return boolean                  returns true if coordinates have already been shot by player, ignoring whether shot was a hit or miss
     */

    public static boolean alreadyShot(ShotCoordinatesArray shotCoordinatesArray, Player player) {
        String element = player.getBOARD_DISPLAY_OPPONENT().getSingleElement(shotCoordinatesArray);
        return "X".equals(element) || "M".equals(element);
    }
}
