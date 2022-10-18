package game.pokemons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.special_attacks.Bubble;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 */

public class Squirtle extends Pokemon {

    /**
     * Constructor.
     */
    public Squirtle() {
        super("Squirtle", 's', 100, new Element[] {Element.WATER});
        this.addSpecialAttack(new Bubble());     // add special attack Bubble for Squirtle
        this.addCapability(Status.CATCHABLE);   // make Squirtle catchable
    }

    /**
     * Squirtle's own intrinsic attack (weapon)
     * @return  Squirtle's own intrinsic attack (weapon)
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

    /**
     * Checks the enemy conditions needed for the Squirtle to equip its special attack
     * @param target    instance of the Pokemon that it is attacking
     * @return  true if condition allows special attack, false if condition does not allow, null (by default) if enemy conditions not taken into account
     */
    @Override
    public Boolean checkSpecialAttackCondition(Actor target, GameMap map) {
        // check if other actor has water element and also the default conditions in Pokemon class
        if (target.hasCapability(Element.FIRE) || super.checkSpecialAttackCondition(target, map)) {
            return true;    // return true if so
        }
        return false;   // else return false
    }


    /**
     * evolution for Squirtle (current has none due to limitations of assignment)
     * @return  Pokemon that Squirtle evolves to
     */
    @Override
    public Pokemon getEvolution() {
        return null;    // no evolution for now
    }


    /**
     * Hurts Squirtle during day
     */
    @Override
    public void dayEffect() {
        hurt(10);   // hurt Squirtle by 10 points
    }


    /**
     * Heals Squirtle during night
     */
    @Override
    public void nightEffect() {
        heal(10);   // heal Squirtle by 10 points
    }
}
