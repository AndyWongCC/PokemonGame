package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.Status;


/**
 * Created by:
 * @author Wei Khang Lo
 */


public class Fire extends Item {
    /**
     * damage done by fire to actors standing on it
     */
    int damage = 10;

    /**
     * how long fire has existed (turns)
     */
    int age = 0;

    /**
     * how long fire can burn for (turns)
     */
    int maxAge = 2;

    /**
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
    }

    /**
     * Overrided tick method for fire from Item.
     * Used to determine how long
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        // damage actor in location if any
        if (currentLocation.containsAnActor()) {
            Actor currentActor = currentLocation.getActor();
            if (!currentActor.hasCapability(Status.IMMUNE) && !currentActor.hasCapability(Element.FIRE)) {
                currentActor.hurt(damage);
            }
        }

        // check if duration of fire has reached limit
        if (age == maxAge+1) {
            // remove fire (itself) from location if age limit is reached
            currentLocation.removeItem(this);
        }

        // increase age of fire
        this.age++;
    }
}
