package game.special_attacks;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 * Special attack weapon Bubble
 * used by Pokemons such as:
 * @see game.pokemons.Squirtle
 */


public class Bubble extends BackupWeapons {

    /**
     * Constructor for special attack weapon Bubble
     */
    public Bubble() {
        super("Bubble", 'B', 25, "burbles", 80);
    }

    /**
     * Special ability for Bubble special attack
     * @param currentLocation   current location of actor using this ability
     */
    @Override
    public void triggerSpecialAbility(Location currentLocation) {
        // no special ability so do nothing
    }
}
