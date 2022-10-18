package game.pokemons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.special_attacks.Blaze;
import game.special_attacks.Ember;
import game.special_attacks.FireSpin;


/**
 * Created by:
 * @author Wei Khang Lo
 */


public class Charizard extends Pokemon {

    /**
     * Constructor.
     */
    public Charizard() {
        super("Charizard", 'Z', 250, new Element[] {Element.FIRE, Element.DRAGON});
        this.addSpecialAttack(new Ember());   // add special attack Ember for Charizard
        this.addSpecialAttack(new Blaze());   // add special attack Blaze for Charizard
        this.addSpecialAttack(new FireSpin());   // add special attack Blaze for Charizard
        this.addCapability(Status.CATCHABLE);   // let Charizard be catchable
    }


    /**
     * Charizard's own intrinsic attack (weapon)
     * @return  Charizard's own intrinsic attack (weapon)
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }


    /**
     * evolution for Charizard (current has none because it is the highest in evolution)
     * @return  Pokemon that Charizard evolves to
     */
    @Override
    public Pokemon getEvolution() {
        return null;    // no evolution, Charizard is highest evolution level
    }


    /**
     * day does not affect Charizard
     */
    @Override
    public void dayEffect() {
        // no day effect so does nothing
    }


    /**
     * night does not affect Charizard
     */
    @Override
    public void nightEffect() {
        // no night effect so does nothing
    }

}
