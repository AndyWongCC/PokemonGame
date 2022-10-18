package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.pokemons.Pokemon;

public class FeedAction extends Action {

    /**
     * Item to be consumed (has CONSUMABLE status)
     */
    Item consumable;

    /**
     * Pokemon to consume consumable
     */
    Actor pokemon;

    /**
     * direction of feeding
     */
    String direction;


    /**
     * Constructor
     * @param consumable    Item to be consumed
     * @param pokemon       Pokemon to feed
     * @param direction     Direction of feeding
     */
    public FeedAction(Item consumable, Actor pokemon, String direction) {
        this.consumable = consumable;
        this.pokemon = pokemon;
        this.direction = direction;
    }


    /**
     * Execute action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  message for feed action outcome
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String message = actor + " gives a " + consumable.toString() + " to " + pokemon;

        // remove pokefruit from actor's (Player) inventory
        actor.removeItemFromInventory(consumable);

        // check if any similar elements between consumer (Pokemon) and pokefruit
        if (ElementsHelper.hasAnySimilarElements(consumable, pokemon.findCapabilitiesByType(Element.class))) {
            // increase affection points for consumer (Pokemon) if so
            message += "\n" + AffectionManager.getInstance(actor).increaseAffection(pokemon, 20);
        }
        else {
            // decrease the affection points for consumer (Pokemon) if not
            message += "\n" + AffectionManager.getInstance(actor).decreaseAffection(pokemon, 10);
        }

        return message;
    }


    /**
     * menu description of action
     * @param actor The actor performing the action.
     * @return  menu description of feed action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds a " + consumable + " to " + pokemon + " at " + direction;
    }
}
