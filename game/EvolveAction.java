package game;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.Pokemon;
import game.time.TimePerceptionManager;

/**
 * Created by:
 * @author Wei Khang Lo
 */


public class EvolveAction extends Action {

    /**
     * Pokemon to evolve
     */
    Pokemon pokemon;

    /**
     * Evolution of Pokemon
     */
    Pokemon evolution;

    /**
     * direction of Pokemon (from Player) to evolve
     */
    String direction = "";


    /**
     * Constructor without direction
     * @param pokemon
     */
    public EvolveAction(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.evolution = pokemon.getEvolution();
    }

    /**
     * Constructor with direction
     * @param pokemon
     */
    public EvolveAction(Pokemon pokemon, String direction) {
        this.pokemon = pokemon;
        this.evolution = pokemon.getEvolution();
        this.direction = direction;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(pokemon);    // get current location of current Pokemon

        // transfer all inventory items to evolution Pokemon
        for (Item item : pokemon.getInventory()) {
            evolution.addItemToInventory(item);
        }

        // transfer affection points to evolution Pokemon
        AffectionManager.transferAffection(pokemon, evolution);
        // add evolution Pokemon to Time Perception Manager
        TimePerceptionManager.getInstance().append(evolution);

        // hold onto current Pokemon name and stats temporarily
        String pokemonStats = pokemon.toString();
        // remove current Pokemon from game
        ClearActorsManager.getInstance().clearActor(pokemon, map);

        // add evolution Pokemon to game
        here.addActor(evolution);

        return pokemonStats + " has evolved to " + evolution.toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " triggers evolution for " + pokemon.toString() + " (to " + evolution.toString() + ") at " + direction;
    }

}
