package game.special_attacks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 */


public interface SpecialAttack {

    /**
     * Add special attack for the actor
     * @param newSpecialAttack      the special attack weapon (BackupWeapons)
     */
    abstract void addSpecialAttack(BackupWeapons newSpecialAttack);


    /**
     * Method to equip or un-equip the special attack weapon from the Actor's inventory
     * @param isEquipping   true for equipping special attack, false for un-equipping special attack
     */
     BackupWeapons toggleSpecialAttack(Boolean isEquipping);


    /**
     * Method to check the conditions necessary to equip special attack for attacking
     * @param target    the target actor being attacked
     * @param map   the map that attacking actor is in
     * @return  true if conditions allow special attack, false if condition not satisfied
     */
     Boolean checkSpecialAttackCondition(Actor target, GameMap map);


    // NOTE: possibilities for future updates - multiple special attacks
    // add special attack
    //void addSpecialAttack();

    // remove special attack
    //void removeSpecialAttack();

}
