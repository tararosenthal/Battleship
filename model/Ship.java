package battleship.model;

import battleship.model.enums.*;

import java.util.HashMap;

/*
 * Creates ship objects defined by ship type made of the number of ship parts corresponding to length. All types of ships
 * are created at once and should be assigned to a given player.
 */

public class Ship {
    private final ShipType SHIP_TYPE;
    private final ShipPart[] SHIP_PARTS;
    private boolean sunk;

    private Ship(ShipType shipType) {
        this.SHIP_TYPE = shipType;
        this.SHIP_PARTS = ShipPart.createShipParts(SHIP_TYPE);
        this.sunk = false;
    }

    public static HashMap<ShipType, Ship> createShips() {
        return new HashMap<>()
        {{
            put(ShipType.AIRCRAFT_CARRIER, new Ship(ShipType.AIRCRAFT_CARRIER));
            put(ShipType.BATTLESHIP, new Ship(ShipType.BATTLESHIP));
            put(ShipType.SUBMARINE, new Ship(ShipType.SUBMARINE));
            put(ShipType.CRUISER, new Ship(ShipType.CRUISER));
            put(ShipType.DESTROYER, new Ship(ShipType.DESTROYER));
        }};
    }

    ShipType getShipType() {
        return SHIP_TYPE;
    }

    /*
     * Parses through each part of a ship to check if it has been hit. If all have been hit, designates ship as sunk and returns true.
     * Else returns false.
     */

    public boolean checkIfSunk() {
        boolean sunk = true;
        for (ShipPart shipPart:SHIP_PARTS) {
            if (!shipPart.isHit()) {
                sunk = false;
                break;
            }
        }
        this.sunk = sunk;
        return this.sunk;
    }

    public ShipPart getShipPart(int position) {
        return SHIP_PARTS[position];
    }
}
