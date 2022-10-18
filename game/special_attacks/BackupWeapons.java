package game.special_attacks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.AttackAction;


/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wei Khang Lo
 *
 * TODO (DONE but UNSURE): Use this class to store Pokemon's weapons (special attack) permanently.
 * If a Pokemon needs to use a weapon, put it into that Pokemon's inventory.
 * @see Actor#getWeapon() method.
 * @see AttackAction uses getWeapon() in the execute() method.
 */
public abstract class BackupWeapons extends WeaponItem {

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public BackupWeapons(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * abstract method to be implemented for special attacks to have special abilities
     */
    public abstract void triggerSpecialAbility(Location currentLocation);

}
