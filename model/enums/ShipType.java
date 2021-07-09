package battleship.model.enums;

/*
 * Defines types of ships along with their length.
 */

public enum ShipType {
        AIRCRAFT_CARRIER(5),
        BATTLESHIP(4),
        SUBMARINE(3),
        CRUISER(3),
        DESTROYER(2),
        EMPTY(1);

        private final int LENGTH;

        ShipType (int length) {
            this.LENGTH = length;
        }

        public int getLength() {
            return LENGTH;
        }


        @Override
        public String toString() {
                if(this.equals(ShipType.AIRCRAFT_CARRIER)) {
                        String string = this.name().replaceAll("_", " ");
                        return string.charAt(0) + string.substring(1, 9).toLowerCase() + string.charAt(9) + string.substring(10).toLowerCase();
                } else {
                        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
                }
        }
}
