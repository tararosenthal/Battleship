package battleship.model.enums;

/*
 * Defines phases of game with three steps associated with set up of the game and two steps with playing phase.
 */

public enum RunState {
    ENTER_COORDINATES,
    ERROR_CHECKING_COORDINATES,
    ERROR_CHECKING_SHIP_PLACEMENT,
    ENTER_SHOT,
    FIRING_SHOT,
}
