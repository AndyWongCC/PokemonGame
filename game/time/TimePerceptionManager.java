package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.AffectionManager;
import game.pokemons.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wei Khang Lo
 * @author Cheung Chi Wong
 *
 */
public class TimePerceptionManager {
    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;

    private int turn;

    private TimePeriod shift; // DAY or NIGHT

    private Display display;

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     *
     * @return TimePerceptionManager singleton instance
     */
    public static TimePerceptionManager getInstance() {
        if (instance == null) {
            instance = new TimePerceptionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        display = new Display();
        turn = 0;
        shift = TimePeriod.DAY;
    }


    /**
     * returns current time period
     * @return  TimePeriod
     */
    public TimePeriod getTimePeriod() {
        return this.shift;
    }


    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     *
     * FIXME (FIXED): write a relevant logic (i.e., increment turns choose day or night) and call this method once at every turn.
     */
    public void run() {
        // display the time and turn
        String timeOutput = "It is " + this.shift.toString() + "-time (turn " + this.turn + ")";
        display.println(timeOutput);

        // run day effect if day
        if (shift == TimePeriod.DAY) {
            dayEffect();
        }   // run night effect if night
        else { nightEffect(); }

        turn++; // next turn

        // check if time period can change to night
        if (this.shift == TimePeriod.DAY && turn % 5 == 0) {
            this.shift = TimePeriod.NIGHT;
        }   // check if time period can change to day
        else if (this.shift == TimePeriod.NIGHT && turn % 5 == 0) {
            this.shift = TimePeriod.DAY;
        }
    }


    /**
     * Add the TimePerception instance to the list
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {
        this.timePerceptionList.add(objInstance);
    }


    /**
     * Remove a TimePerception instance from the list
     *
     * FIXME (FIXED): [OPTIONAL] run cleanUp once every turn if you don't want to
     *        have too many instances in the list (e.g., memory leak)
     * @param actor object instance
     */
    public void cleanUp(Actor actor) {
        // check if object instance is in time perception list before removing
        TimePerception objInstance = findTimePerception(actor);

        // if in time perception list
        if (objInstance != null) {
            // remove it
            this.timePerceptionList.remove(objInstance);
        }
    }


    /**
     * Useful method to search a TimePerception by using Actor instance.
     *
     * @param actor general actor instance
     * @return the TimePerception instance.
     */
    private TimePerception findTimePerception(Actor actor) {
        for (TimePerception objInstance : timePerceptionList) {
            if (objInstance.equals(actor)) {
                return objInstance;
            }
        }
        return null;
    }


    /**
     * runs day effect for all TimePerception instances
     */
    public void dayEffect(){
        for (TimePerception objInstance : this.timePerceptionList) {
            objInstance.dayEffect();
        }
    }


    /**
     * runs night effect for all TimePerception instances
     */
    public void nightEffect(){
        for (TimePerception objInstance : this.timePerceptionList) {
            objInstance.nightEffect();
        }
    }
}
