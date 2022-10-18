package game.special_attacks;


import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Fire;

/**
 * Created by:
 * @author Wei Khang Lo
 */


public class FireSpin extends BackupWeapons {

    /**
     * Constructor for special attack weapon Fire Spin
     */
    public FireSpin() {
        super("Fire Spin", 'F', 70, "fire spins", 90);
    }

    /**
     * Special ability for Fire Spin special attack
     * @param currentLocation   current location of actor using this ability
     */
    @Override
    public void triggerSpecialAbility(Location currentLocation) {
        // drops fire around actor using this special weapon
        for (Exit exit : currentLocation.getExits()) {
            exit.getDestination().addItem(new Fire());
        }
    }

}
