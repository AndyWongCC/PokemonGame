package game.special_attacks;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 * Special attack weapon Vine Whip
 * used by Pokemons such as:
 * @see game.pokemons.Bulbasaur
 */


public class VineWhip extends BackupWeapons {

    /**
     * Constructor for special attack weapon Vine Whip
     */
    public VineWhip() {
        super("Vine Whip", 'V', 30, "whips", 70);
    }

    /**
     * Special ability for Vine Whip special attack
     * @param currentLocation   current location of actor using this ability
     */
    @Override
    public void triggerSpecialAbility(Location currentLocation) {
        // no special ability so do nothing
    }

}
