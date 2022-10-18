package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.CatchAction;
import game.Status;
import game.pokemons.Pokemon;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 */

public class CatchBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);  // get the current location of actor
        // check all exits (surroundings) around actor
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();   // get location at the exit

            // check if a particular exit has an actor
            if (map.isAnActorAt(destination)) {
                Actor target = map.getActorAt(destination);

                // check that the target actor is a Pokemon
                Pokemon pokemon = AffectionManager.getInstance(actor).findPokemon(target);
                if (pokemon != null) {
                    // check if target pokemon can be caught and return catch action
                    if (AffectionManager.getInstance(actor).getAffectionPoint(pokemon) > AffectionManager.getInstance(actor).getCatchAP()) {
                        return new CatchAction(target, exit.getName());
                    }
                }
            }
        }
        // if no actors around to capture then move to next behaviour
        return null;
    }
}
