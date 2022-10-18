package game.special_attacks;


import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author Wei Khang Lo
 */
public class Blaze extends BackupWeapons {

    /**
     * Constructor for special attack weapon Blaze
     */
    public Blaze() {
        super("Blaze", 'B', 60, "blazes", 90);
    }

    /**
     * Special ability for Blaze special attack
     * @param currentLocation   current location of actor using this ability
     */
    @Override
    public void triggerSpecialAbility(Location currentLocation) {
        // no special ability so do nothing
    }

}
