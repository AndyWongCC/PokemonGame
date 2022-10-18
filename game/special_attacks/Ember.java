package game.special_attacks;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 * Special attack weapon Ember
 * used by Pokemons such as:
 * @see game.pokemons.Charmander
 */


public class Ember extends BackupWeapons {

    /**
     * Constructor for special attack weapon Ember
     */
    public Ember() {
        super("Ember", 'E', 20, "sparks", 90);
    }

    /**
     * Special ability for Ember special attack
     * @param currentLocation   current location of actor using this ability
     */
    @Override
    public void triggerSpecialAbility(Location currentLocation) {
        // no special ability so do nothing
    }

}
