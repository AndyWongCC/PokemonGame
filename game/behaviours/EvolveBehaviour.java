package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.EvolveAction;
import game.Status;
import game.pokemons.Pokemon;


/**
 * Created by:
 * @author Wei Khang Lo
 */


public class EvolveBehaviour implements Behaviour {

    /**
     * Pokemon to evolve
     */
    Pokemon pokemon;

    /**
     * turn since instantiation of this behaviour object.
     * basically an age counter for the Pokemon it corresponds to
     */
    int turn;

    /**
     * Constructor.
     * @param pokemon   pokemon to evolve
     */
    public EvolveBehaviour(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.turn = 0;
    }


    /**
     * gets action for evolving
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // increment turn
        turn++;

        // check if Pokemon can evolve
        if (!pokemon.hasCapability(Status.EVOLVE)) {
            return null;    // if not then move to next behaviour
        }

        // check if Pokemon has survived long enough
        if (turn < 20) {
            return null;    // if not then move to next behaviour
        }

        Location here = map.locationOf(actor);  // get the current location of actor
        // check all exits (surroundings) around actor
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();   // get location at the exit

            // check if a particular exit has an actor
            if (map.isAnActorAt(destination)) {
                return null;    // return null if so since can only evolve when alone
            }
        }

        /* REDUNDANT
        EvolveAction evolve = new EvolveAction(pokemon);
        if (evolve == null) { return null; }    // check if Pokemon has evolution, if not then move on to next behaviour
        */

        // if all checks have passed, Pokemon can evolve
        return new EvolveAction(pokemon);
    }
}
