package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
import game.pokemons.Pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

/**
 * Affection Manager
 * <p>
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wei Khang Lo
 *
 */
public class AffectionManager {

    /**
     * Singleton instance of map of each trainer to an affection manager
     */
    private static Map<Actor, AffectionManager> instance = new HashMap<>();

    /**
     * map of Pokemons to their affection points
     */
    private final Map<Pokemon, Integer> affectionPoints;

    /**
     * We assume there's only one trainer in this manager.
     * Think about how will you extend it.
     */
    private Actor trainer;

    /**
     * pre defined affection point levels
     */
    private int dislikeAP = -50;    // dislikes trainer, cannot be fixed
    private int neutralAP = 0;      // default affection points
    private int catchAP = 50;       // able to be caught by trainer
    private int followAP = 75;      // able to follow trainer
    private int maxAP = 100;        // max affection points


    /**
     * private singleton constructor
     */
    private AffectionManager() {
        this.affectionPoints = new HashMap<>();
    }

    /**
     * Access instance of Affection Manager for a specific actor
     *
     * @return this instance
     */
    public static AffectionManager getInstance(Actor actor) {
        // check if actor can be a trainer
        if (actor.hasCapability(Status.TRAINER)) {
            // check if affection manager for actor is available
            if (instance.get(actor) == null) {
                // if none then add a new instance of affection manager for that actor
                instance.put(actor, new AffectionManager());
                instance.get(actor).registerTrainer(actor);     // register the actor as the trainer for their
            }
            return instance.get(actor);
        }
        // if actor can't be a trainer then no affection manager for the actor
        return null;
    }


    /**
     * getter for dislike AP
     * @return  dislike AP
     */
    public int getDislikeAP() {
        return dislikeAP;
    }

    /**
     * getter for neutral AP
     * @return  neutral AP
     */
    public int getNeutralAP() {
        return neutralAP;
    }

    /**
     * getter for catchable AP
     * @return  catchable AP
     */
    public int getCatchAP() {
        return catchAP;
    }

    /**
     * getter for follow AP
     * @return  follow AP
     */
    public int getFollowAP() {
        return followAP;
    }

    /**
     * getter for max AP
     * @return  max AP
     */
    public int getMaxAP() {
        return maxAP;
    }


    /**
     * Add a trainer to this class's attribute. Assume there's only one trainer at a time.
     *
     * @param trainer the actor instance
     */
    public void registerTrainer(Actor trainer) {
        this.trainer = trainer;
    }

    /**
     * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register all instantiated Pokemon
     *
     * @param pokemon   Pokemon to add to manager
     */
    public static void registerPokemon(Pokemon pokemon) {
        for (AffectionManager affectionManager : instance.values()) {
            affectionManager.affectionPoints.put(pokemon, 0);   // add pokemon to all collections with 0 affection points
        }
    }

    /**
     * Add Pokemon to the collection but with specific affection points
     *
     * @param pokemon   Pokemon to add to manager
     * @param points    affection points for Pokemon
     */
    public static void registerPokemon(Pokemon pokemon, int points) {
        for (AffectionManager affectionManager : instance.values()) {
            affectionManager.affectionPoints.put(pokemon, points);   // add pokemon to all collections with specific affection points
        }
    }

    /**
     * Get the affection point by using the pokemon instance as the key.
     *
     * @param actor Pokemon instance
     * @return integer of affection point.
     */
    public int getAffectionPoint(Actor actor) {
        Pokemon pokemon = findPokemon(actor); // get the pokemon from actor
        return affectionPoints.get(pokemon);
    }

    /**
     * Useful method to search a pokemon by using Actor instance.
     *
     * @param actor general actor instance
     * @return the Pokemon instance.
     */
    public Pokemon findPokemon(Actor actor) {
        for (Pokemon pokemon : affectionPoints.keySet()) {
            if (pokemon.equals(actor)) {
                return pokemon;
            }
        }
        return null;
    }


    /**
     * remove pokemons from affection manager
     * @param actor     actor to remove (Pokemon)
     */
    public static void removePokemon(Actor actor) {
        for (AffectionManager affectionManager : instance.values()) {
            Pokemon pokemon = affectionManager.findPokemon(actor);   // check if pokemon is in array list
            // if in array list
            if (pokemon != null) {
                // remove pokemon
                affectionManager.affectionPoints.remove(pokemon);
            }
        }
    }


    /**
     * Increase the affection. Work on both cases when there's a Pokemon,
     * or when it doesn't exist in the collection.
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier
     * @return custom message to be printed by Display instance later.
     */
    public String increaseAffection(Actor actor, int point) {
        Pokemon pokemon = findPokemon(actor);   // get the pokemon in collection

        // if pokemon in collection (not null)
        if (pokemon != null) {
            // check for max AP when increasing AP
            int AP = Math.min(affectionPoints.get(pokemon) + point, this.getMaxAP());
            affectionPoints.replace(pokemon, AP);     // increase affection points or set to max if passes over

            // check for follow behaviour
            if (AP >= this.getFollowAP()) {
                // add follow behaviour towards trainer for Pokemon
                pokemon.addBehaviour(1, new FollowBehaviour(trainer));
            }
            // if AP not enough for follow
            else {
                // remove follow behaviour if in behaviour map
                pokemon.removeBehaviour(1);
            }

            return pokemon.toString() + " likes it! +" + point + " affection points";   // return custom display string
        }
        // else return string for not in collection or not pokemon
        return actor.toString() + " is not a Pokemon or not in the collection";
    }

    /**
     * Decrease the affection level of the . Work on both cases when it is
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Actor actor, int point) {
        Pokemon pokemon = findPokemon(actor);   // get the pokemon in collection

        // if pokemon in collection (not null)
        if (pokemon != null) {
            int AP = affectionPoints.get(pokemon) - point;  // get AP after decrease
            affectionPoints.replace(pokemon, AP);     // decrease affection points

            // check for unfollow
            if (AP < this.getFollowAP()) {
                // remove follow behaviour if in behaviour map
                pokemon.removeBehaviour(0);
            }
            // if AP enough for follow
            else {
                // add follow behaviour towards trainer for Pokemon
                pokemon.addBehaviour(0, new FollowBehaviour(trainer));
            }

            // check for dislike
            if (AP <= this.getDislikeAP()) {
                pokemon.removeCapability(Status.CATCHABLE); // make Pokemon uncatchable
                pokemon.addCapability(Status.DISLIKE);  // make Pokemon dislike trainer and can't be fed by trainer anymore
            }

            return pokemon.toString() + " dislikes it! -" + point + " affection points";   // return custom display string
        }
        // else return string for not in collection or not pokemon
        return actor.toString() + " is not a Pokemon or not in the collection";
    }


    /**
     * String of info for all Pokemons, their AP and their locations
     * @param map   map that Pokemons are in
     * @return
     */
    public String getInfo(GameMap map) {
        String info = "";
        for (Pokemon pokemon : affectionPoints.keySet()) {
            if (!pokemon.hasCapability(Status.CAUGHT)) {    // if a pokemon is caught, it won't be on map
                info += " - " + pokemon.getName() + " with " + affectionPoints.get(pokemon) + " AP at " + map.locationOf(pokemon).x() + "," + map.locationOf(pokemon).y() + "\n";
            }
        }
        info += "...";
        return info;
    }

    /**
     * Transfers the affection points of actor (pokemon) to new pokemon
     * @param actor     pokemon to transfer from
     * @param newPokemon   new pokemon to transfer to (new Pokemon not in manager yet)
     */
    public static void transferAffection(Actor actor, Pokemon newPokemon) {
        for (AffectionManager affectionManager : instance.values()) {
            Pokemon oldPokemon = affectionManager.findPokemon(actor);   // get the pokemon from actor
            int affection = affectionManager.getAffectionPoint(oldPokemon); // get affection points from old Pokemon
            affectionManager.affectionPoints.put(newPokemon, affection);   // add pokemon to all collections with specific affection points
        }
    }

}
