package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.FeedAction;
import game.Status;
import game.items.Pokefruit;
import game.pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by:
 * @author Wei Khang Lo
 */

public class FeedBehaviour implements Behaviour {

    /**
     * Random
     */
    private final Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Actor pokemon = null;   // actor with highest AP to actor
        String direction = null;

        Location here = map.locationOf(actor);  // get the current location of actor
        // check all exits (surroundings) around actor
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();   // get location at the exit

            // check if a particular exit has an actor
            if (map.isAnActorAt(destination)) {
                Actor target = map.getActorAt(destination);

                // check that the target actor is not another trainer (can't feed a trainer)
                // or that the target actor is not in a dislike state
                // or that the target actor can consume
                if (target.hasCapability(Status.TRAINER) || target.hasCapability(Status.DISLIKE) || !target.hasCapability(Status.CONSUMES)) {
                    continue;
                }

                // set the highest AP pokemon to first found target that is not a trainer
                if (pokemon == null) {
                    pokemon = target;
                    direction = exit.getName();
                    continue;
                }

                // update Pokemon if target actor (Pokemon) has AP to actor higher than current highest AP Pokemon
                if (AffectionManager.getInstance(actor).getAffectionPoint(target) > AffectionManager.getInstance(actor).getAffectionPoint(pokemon)) {
                    pokemon = target;
                    direction = exit.getName();
                }

            }
        }

        // if no actors around to capture then move to next behaviour
        if (pokemon == null) {
            return null;
        }

        // list of pokefruits to feed
        ArrayList<Item> pokefruits = new ArrayList<>();

        // get list of pokefruits
        for (Item item : actor.getInventory()) {
            if (item.hasCapability(Status.CONSUMABLE)) {
                pokefruits.add(item);
                return new FeedAction(item, pokemon, direction);
            }
        }

        // randomly pick a pokefruit from list
        if (!pokefruits.isEmpty()) {
            return new FeedAction(pokefruits.get(random.nextInt(pokefruits.size())), pokemon, direction);
        }

        // else if no pokefruit then move to next behaviour
        return null;

    }

}
