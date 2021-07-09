package battleship.model;

import battleship.model.enums.*;

/*
 * Represents both parts of ships and empty spots on backend game board. Each object stores type of ship or empty
 * and can have the property of being hit or not. (This should not be used with empty parts as it is error prone.) If parts
 * belong to a ship, rather than empty parts, they should be created in an array corresponding to the entire ship length.
 */

public class ShipPart{
    private final ShipType SHIP_TYPE;
    private boolean isHit = false;

    private ShipPart(ShipType shipType) {
        this.SHIP_TYPE = shipType;
    }

    static ShipPart[] createShipParts(ShipType shipType) {
        ShipPart[] shipParts = new ShipPart[shipType.getLength()];
        for (int i = 0; i < shipType.getLength(); i++) {
            shipParts[i] = new ShipPart(shipType);
        }
        return shipParts;
    }

    public static ShipPart getEmptyPart() {
        return new ShipPart(ShipType.EMPTY);
    }

    public ShipType getSHIP_TYPE() {
        return SHIP_TYPE;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit() {
        isHit = true;
    }
}
