package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author Wei Khang Lo
 */

public class PickupBehaviour implements Behaviour {


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);  // get the current location of actor
        // check all exits (surroundings) around actor
        for (Item item : here.getItems()) {
            // returns the first item that can be picked up
            return item.getPickUpAction(actor);
        }
        // if no items on ground to pick up then move to next behaviour
        return null;
    }
}
