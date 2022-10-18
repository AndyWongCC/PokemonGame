package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.special_attacks.BackupWeapons;
import game.special_attacks.SpecialAttack;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wei Khang Lo
 *
 *
 * Includes the logic for normal and special attacks
 *
 */


public class AttackBehaviour implements Behaviour {

    /**
     * attribute for the actor with special attack
     */
    SpecialAttack attacker;

    /**
     * Constructor for normal attack behaviours
     */
    public AttackBehaviour() {}

    /**
     * Constructor for attack behaviour for special attacks
     * @param attacker  the attacking actor with special attacks
     */
    public AttackBehaviour(SpecialAttack attacker) {
        this.attacker = attacker;
    }

    /**
     * method to check surrounding actors and its elements and return action to attack the first surrounding actor that is an opponent
     *  HINT: develop a logic to check surrounding, check elements, and return an action to attack that opponent.
     *  Is overrided by
     * @param actor     the actor to attack another actor in its surroundings
     * @param map       the instance of GameMap that actor is currently in
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);  // get the current location of actor
        // check all exits (surroundings) around actor
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();   // get location at the exit

            // check if a particular exit has an actor
            if (map.isAnActorAt(destination)) {
                Actor target = map.getActorAt(destination);

                // check if other actor is hostile
                if (!target.hasCapability(Status.HOSTILE)) {
                    // if other actor is not a pokemon, skip it and check next exit
                    continue;
                }

                // check elements of surrounding actors
                if(!ElementsHelper.hasAnySimilarElements(actor, target.findCapabilitiesByType(Element.class))){

                    // check if attack behaviour also includes special attacks (has actor with special attacks)
                    if (attacker != null) {
                        // if so, check conditions for special attack, equip and get the special weapon
                        BackupWeapons specialAttack = attacker.toggleSpecialAttack(attacker.checkSpecialAttackCondition(target, map));
                        // then trigger the special attack's ability if available
                        if (specialAttack != null) {
                            specialAttack.triggerSpecialAbility(here);
                        }
                    }

                    // if element of other actor is different, return a new attack action to attack that other actor
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        // returns the first other actor found with different elements, otherwise return null
        return null; // go to next behaviour
    }

}
