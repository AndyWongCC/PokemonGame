package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the door of a building.
 *
 * Created by: CheungChi Wong
 * @author CheungChi Wong
 * Modified by:
 * @author Wei Khang Lo
 *
 */
public class Door extends Ground  {

    /**
     * Location that the door teleports Player to
     */
    Location location;

    /**
     * Direction that door takes Player to
     */
    String direction;

    /**
     * Constructor.
     *
     */
    public Door(Location location, String direction) {
        super('=');
        this.location = location;
        this.direction = direction;
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);
        actions.add(new MoveActorAction(this.location, this.direction));
        return actions;
    }
}
