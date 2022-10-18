package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.SummonAction;
import game.pokemons.Pokemon;

/**
 * Created by:
 * @author Cheung Chi Wong
 * Modified by:
 * @author Wei Khang Lo
 */


public class Pokeball extends Item {

    /**
     * attribute for Pokemon that pokeball is holding
     */
    Pokemon pokemon;


    /**
     * Constructor.
     */
    public Pokeball(Pokemon pokemon) {
        super("pokeball", '0', true);
        this.pokemon = pokemon;
        this.addAction(new SummonAction(this));     // add a new summon action for this pokeball
    }


    /**
     * getter method for Pokemon that pokeball is holding
     * @return
     */
    public Pokemon getPokemon() {
        return this.pokemon;
    }


    /**
     * Name for pokeball with captured Pokemon
     * @return  pokeball with name, health and AP of Pokemon captured
     */
    public String toString(){
        return super.toString() + "(" + pokemon + ")";
    }

}
