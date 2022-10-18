package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Pokeball;
import game.pokemons.Pokemon;

public class SummonAction extends Action {

    /**
     * pokeball that Pokemon to be summoned is in
     */
    Pokeball pokeball;

    /**
     * Pokemon to be summoned
     */
    Pokemon pokemon;


    public SummonAction(Pokeball pokeball) {
        this.pokeball = pokeball;
        this.pokemon = pokeball.getPokemon();   // get the Pokemon in the pokeball
    }


    /**
     * execute the summon action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  message for summon action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);  // get location of actor summoning Pokemon
        Location summonLocation = null;    // declare a location to summon Pokemon

        // finds the first exit with that Pokemon can be summoned in
        for (Exit exit : here.getExits()) {
            // check that exit does not have actor and Pokemon can enter exit
            if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(pokemon)) {
                summonLocation = exit.getDestination();     // set the exit to be the summon location
            }
        }

        // if a summon location is found
        if (summonLocation != null) {
            // summon Pokemon to location
            summonLocation.addActor(pokemon);
            // remove pokeball from actor's inventory
            actor.removeItemFromInventory(pokeball);
            // remove caught status from pokemon
            pokemon.removeCapability(Status.CAUGHT);
            // return successful summon message
            return "I choose you... " + pokemon + "!";
        }
        // if no summon location is available, return unsuccessful summon message
        return "Not able to summon a Pokemon at current location and surroundings";
    }


    /**
     * menu description of summon action
     * @param actor The actor performing the action.
     * @return  menu description of summon action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summon " + pokemon;
    }
}
