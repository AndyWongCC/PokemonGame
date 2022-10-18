package game.pokemons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.special_attacks.Blaze;
import game.special_attacks.Ember;


/**
 * Created by:
 * @author Wei Khang Lo
 */


public class Charmeleon extends Pokemon{

    /**
     * Constructor.
     */
    public Charmeleon() {
        super("Charmeleon", 'C', 150, new Element[] {Element.FIRE});
        this.addSpecialAttack(new Ember());   // add special attack Ember for Charmeleon
        this.addSpecialAttack(new Blaze());   // add special attack Blaze for Charmeleon
        this.addCapability(Status.CATCHABLE);   // let Charmeleon be catchable
        this.addCapability(Status.EVOLVE);  // Charmeleon has evolution
    }


    /**
     * Charmeleon's own intrinsic attack (weapon)
     * @return  Charmeleon's own intrinsic attack (weapon)
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }


    /**
     * evolution for Charmeleon
     * @return  Charizard Pokemon
     */
    @Override
    public Pokemon getEvolution() {
        return new Charizard();     // evolves to Charizard
    }


    /**
     * day does not affect Charmeleon
     */
    @Override
    public void dayEffect() {
        // no day effect so does nothing
    }


    /**
     * night does not affect Charmeleon
     */
    @Override
    public void nightEffect() {
        // no night effect so does nothing
    }
}
