package game.pokemons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.special_attacks.VineWhip;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 */

public class Bulbasaur extends Pokemon {

    /**
     * Constructor.
     */
    public Bulbasaur() {
        super("Bulbasaur", 'b', 100, new Element[] {Element.GRASS});
        this.addSpecialAttack(new VineWhip());    // add special attack Vine Whip for Bulbasaur
        this.addCapability(Status.CATCHABLE);   // make Bulbasaur catchable
    }

    /**
     * Bulbasaur's own intrinsic attack (weapon)
     * @return  Bulbasaur's own intrinsic attack (weapon)
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }


    /**
     * evolution for Bulbasaur (current has none due to limitations of assignment)
     * @return  Pokemon that Bulbasaur evolves to
     */
    @Override
    public Pokemon getEvolution() {
        return null;    // no evolution for now
    }


    /**
     * Hurts Bulbasuar during day
     */
    @Override
    public void dayEffect() {
        hurt(5);    // hurt Bulbasaur by 5 points
    }


    /**
     * Heals Bulbasaur during night
     */
    @Override
    public void nightEffect() {
        heal(5); // heal Bulbasaur by 5 points
    }
}
