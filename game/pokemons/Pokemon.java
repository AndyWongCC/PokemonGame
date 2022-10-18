package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.EvolveBehaviour;
import game.behaviours.WanderBehaviour;
import game.special_attacks.BackupWeapons;
import game.special_attacks.SpecialAttack;
import game.time.TimePerception;
import game.FeedAction;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 */


public abstract class Pokemon extends Actor implements SpecialAttack, TimePerception, Evolution {
    /**
     * Sorted map for behaviours listed in order of priority
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * attribute to store a Pokemon's special attack
     */
    private ArrayList<BackupWeapons> specialAttacks = new ArrayList<>();


    /**
     * Constructor.
     * @param name              the name of the Pokemon
     * @param displayChar       the display character for Pokemon in game map
     * @param hitPoints         the Pokemon's hit points (health points)
     * @param elements          list of the Pokemon's element
     */
    public Pokemon(String name, char displayChar, int hitPoints, Element[] elements) {
        super(name, displayChar, hitPoints);
        this.registerInstance();    // register Pokemon into TimePerceptionManager to have day and night effects
        this.addCapability(Status.CONSUMES);    // add capability for Pokemon to consume consumables
        this.addCapability(Status.HOSTILE); // add hostile status so it can be seen as an enemy (can be attacked)
        // adds all elements given in parameter for Pokemon
        for (Element element : elements) {
            this.addCapability(element);    // adding element capability
        }
        addBehaviour(0, new EvolveBehaviour(this));     // adding evolve behaviour for this pokemon
        addBehaviour(2, new AttackBehaviour(this));     // adding attack behaviour with special attacks
        addBehaviour(3, new WanderBehaviour());     // adding wandering behaviour
    }

    /**
     * returns the name of the Pokemon with AP towards actor parameter
     * @param actor     actor to display AP of Pokemon towards
     * @return
     */
    public String toString(Actor actor) {
        return this.name + this.printHp() + "(AP: " + AffectionManager.getInstance(actor).getAffectionPoint(this) + ")";
    }

    /**
     * returns just the name and HP of the Pokemon
     * @return
     */
    public String getName() {
        return this.name + this.printHp();
    }

    /**
     * Method used to add a behaviour for a Pokemon
     * @param key           the key (priority) of the behaviour to be added into the Map of behaviours
     * @param behaviour     the behaviour (object) of the Pokemon
     */
    public void addBehaviour(int key, Behaviour behaviour) {
        this.behaviours.put(key, behaviour);
    }


    /**
     * Remove behaviour based on key
     * @param key   key for behaviour map
     */
    public void removeBehaviour(int key) { this.behaviours.remove(key); }


    /**
     * Add special attack for the Pokemon
     * @param newSpecialAttack      the special attack weapon (BackupWeapons)
     */
    @Override
    public void addSpecialAttack(BackupWeapons newSpecialAttack) {
        this.specialAttacks.add(newSpecialAttack);
    }


    /**
     * Method to equip the special attack weapon to the Pokemon's inventory
     *
     * @param isEquipping boolean for equipping (true) or un-equipping (false)
     */
    @Override
    public BackupWeapons toggleSpecialAttack(Boolean isEquipping) {
        // check if Pokemon current has any special attacks equipped
        Boolean equipped = false;
        BackupWeapons equippedSpecial = null;
        for (BackupWeapons specialAttack : specialAttacks) {
            if (this.getInventory().contains(specialAttack)) {
                equipped = true;
                equippedSpecial = specialAttack;
            }
        }

        // if equipping special attack weapon and none is in inventory
        if (isEquipping && !equipped) {
            // get random special attack
            BackupWeapons specialAttack = specialAttacks.get(Utils.randomNum(this.specialAttacks.size()));
            // add a random special attack to inventory
            addItemToInventory(specialAttack);
            // return special weapon
            return specialAttack;
        }
        // or if un-equipping special attack weapon and its in inventory
        else if (!isEquipping && equipped) {
            removeItemFromInventory(equippedSpecial);
            return null;    // return null as special weapon
        }
        // otherwise:
        // if equipping special attack weapon and its already in inventory - do nothing, return that special weapon
        // if un-equipping special attack weapon and its already not in inventory - do nothing, return null
        return equippedSpecial;
    }


    /**
     * Checks the required conditions for the Pokemon to equip its special attack
     * @param target    the actor being attacked
     * @param map   the GameMap instance that this Pokemon is in
     * @return  true if conditions allow special attack, false if condition not satisfied
     */
    @Override
    public Boolean checkSpecialAttackCondition(Actor target, GameMap map) {
        Ground groundHere = map.locationOf(this).getGround();   // get the ground that this Pokemon is on

        // check if current ground has any similar elements to this Pokemon
        if (ElementsHelper.hasAnySimilarElements(groundHere, this.findCapabilitiesByType(Element.class))) {
            return true;    // if so return true
        }
        // if not return false
        return false;
    }


    /**
     * Abstract method for getting the evolution of a Pokemon
     * @return
     */
    @Override
    public abstract Pokemon getEvolution();


    /**
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // check that other actor is not friendly towards Pokemon
        if (!otherActor.hasCapability(Status.FRIENDLY)) {
            // create an attack action for other actor to attack this Pokemon and add it to action list
            actions.add(new AttackAction(this, direction)); // create an attack action for other actor to attack this Pokemon
        }

        // check if Pokemon can consume consumables
        if (this.hasCapability(Status.CONSUMES) && !this.hasCapability(Status.DISLIKE)) {
            // add action for Pokemon to be fed if item in other actors inventory is consumable
            for (Item item : otherActor.getInventory()) {
                if (item.hasCapability(Status.CONSUMABLE)) {
                    actions.add(new FeedAction(item, this, direction));
                }
            }
        }

        // add action for Pokemon to be captured if it is catchable
        if (this.hasCapability(Status.CATCHABLE)) {
            actions.add(new CatchAction(this, direction));
        }

        // add action for Pokemon to be evolved by Player if conditions suffice
        AffectionManager affectionManager = AffectionManager.getInstance(otherActor);
        if (affectionManager != null) {
            if (this.hasCapability(Status.EVOLVE) && affectionManager.getAffectionPoint(this) >= affectionManager.getMaxAP()) {
                actions.add(new EvolveAction(this, direction));
            }
        }

        return actions;
    }


    /**
     * By using behaviour loops, it will decide what will be the next action automatically.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // get an action from behaviours
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }


    /**
     * Runs day effects on corresponding Pokemons
     */
    @Override
    public abstract void dayEffect();


    /**
     * Runs night effects on corresponding Pokemons
     */
    @Override
    public abstract void nightEffect();
}


