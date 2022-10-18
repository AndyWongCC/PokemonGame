package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Candy;
import game.items.Pokeball;
import game.pokemons.Pokemon;

public class CatchAction extends Action {

    /**
     * Pokemon to be captured
     */
    Actor pokemon;

    /**
     * direction of Pokemon to be captured to capturer (Player)
     */
    String direction;


    /**
     * Constructor
     * @param pokemon       Pokemon to be captured
     * @param direction     direction of Pokemon to be captured to actor capturing (Player)
     */
    public CatchAction(Actor pokemon, String direction){
        this.pokemon = pokemon;
        this.direction = direction;
    }


    /**
     * Execute the catch action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  message for catch action outcome
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        AffectionManager affectionManager = AffectionManager.getInstance(actor); // affection manager instance

        // check if Pokemon has enough affection points to be captured
        if (affectionManager.getAffectionPoint(pokemon) >= affectionManager.getCatchAP()) {
            // instantiate new pokeball and put the captured Pokemon in it
            Pokeball pokeball = new Pokeball(affectionManager.findPokemon(pokemon));

            // add pokeball with Pokemon to actor's inventory
            actor.addItemToInventory(pokeball);

            // drop a candy at location caught
            map.locationOf(pokemon).addItem(new Candy());

            // remove the Pokemon from the map
            map.removeActor(pokemon);

            // add status for being caught
            pokemon.addCapability(Status.CAUGHT);

            return pokemon + " has been captured by " + actor;
        }
        // if not enough affection points then decrease by 10 points for trying to be captured
        return affectionManager.decreaseAffection(pokemon, 10);
    }


    /**
     * menu description of the catch action
     * @param actor The actor performing the action.
     * @return  menu description of catch action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attempts to catch " + pokemon + " to " + direction + " using a pokeball";
    }
}
