package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by:
 * @author Wei Khang Lo
 */

public class Goh extends Actor {

    /**
     * Singleton instance of Goh
     */
    private static Goh instance;

    /**
     * Sorted map for behaviours listed in order of priority
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * Constructor.
     */
    private Goh() {
        // Goh has symbol 'G' and hitpoint of 1 just like Player
        super("Goh", 'G', 1);
        addCapability(Status.TRAINER);  // Goh is a trainer
        addCapability(Status.FRIENDLY);
        addBehaviour(0, new CatchBehaviour());
        addBehaviour(1, new FeedBehaviour());
        addBehaviour(2, new PickupBehaviour());
        addBehaviour(3, new WanderBehaviour());
    }

    public static Goh getInstance() {
        if (instance == null) {
            instance = new Goh();
        }
        return instance;
    }

    /**
     * Method used to add a behaviour for Goh
     * @param key           the key (priority) of the behaviour to be added into the Map of behaviours
     * @param behaviour     the behaviour (object) of Goh
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
     * Play turn method to decide Goh's actions
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
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
}
