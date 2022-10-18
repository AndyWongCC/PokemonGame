package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    IMMUNE, // an enum to identify that an object is immune to any attack.
    HOSTILE, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy because the enemy sees actor as unfriendly)
    FRIENDLY,   // use this status to be considered friendly towards an actor (e.g. won't attack anyone)
    CATCHABLE, // status for allowing pokemons to be captured by a pokeball
    CAUGHT,     // status to show that a Pokemon has been caught
    FIXED_GROUND,   // status for ground that is immune to changes (expansion or destruction)
    DISLIKE,    // status for Pokemon disliking trainer
    CONSUMABLE,   // status to identify whether an item can be consumed
    CONSUMES, // status to identify whether an actor can consume consumables
    EVOLVE, // status to show that a Pokemon has evolution
    TRAINER,    // status to show that an actor can be a trainer and use affection manager


    // NOTE: can add more specific statuses in the future
    // HOSTILE_TO_POKEMONS
    // HOSTILE_TO_PLAYER
    // FRIENDLY_TO_POKEMONS
    // FRIENDLY_TO_PLAYER
}
