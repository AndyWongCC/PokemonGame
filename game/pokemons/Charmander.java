package game.pokemons;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.special_attacks.Ember;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wei Khang Lo
 *
 */
public class Charmander extends Pokemon {

    /**
     * Constructor.
     */
    public Charmander() {
        super("Charmander", 'c', 100, new Element[] {Element.FIRE});
        this.addSpecialAttack(new Ember());   // add special attack Ember for Charmandar
        this.addCapability(Status.CATCHABLE);   // let Charmander be catchable
        this.addCapability(Status.EVOLVE);  // Charmander has evolution
    }

    /**
     * Charmander's own intrinsic attack (weapon)
     * @return  Charmander's own intrinsic attack (weapon)
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }


    /**
     * evolution for Charmander
     * @return  Charmeleon Pokemon
     */
    @Override
    public Pokemon getEvolution() {
        return new Charmeleon();    // evolves to Charmeleon
    }


    /**
     * Heals Charmander during day
     */
    @Override
    public void dayEffect() {
        heal(10);   // heal Charmander by 10 points
    }


    /**
     * Hurts Charmander during night
     */
    @Override
    public void nightEffect() {
        hurt(10);   // hurt Charmander by 10 points
    }
}
