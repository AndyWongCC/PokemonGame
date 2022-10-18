package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.time.TimePerceptionManager;
import java.util.ArrayList;

/**
 * Created by:
 * @author Wei Khang Lo
 *
 */


public class ClearActorsManager {

    /**
     * Singleton instance of ClearActorsManager
     */
    private static ClearActorsManager instance;

    private ArrayList<GameMap> maps;

    /**
     * private singleton constructor
     */
    private ClearActorsManager() {
        this.maps = new ArrayList<>();
    }

    /**
     * Access single instance publicly
     *
     * @return this instance
     */
    public static ClearActorsManager getInstance() {
        if (instance == null) {
            instance = new ClearActorsManager();
        }
        return instance;
    }

    /**
     * Add a map for ClearActorManager to manage
     * @param map   GameMap
     */
    public void addMaps(GameMap map) {
        this.maps.add(map);
    }

    /**
     * Clears a dead actor by checking where they are in each map
     * @param actor     Actor to clean up
     */
    public void clearActor(Actor actor) {
        for (GameMap map : maps) {  // check each map for actor
            if (map.contains(actor)) {  // if map contains the actor
                map.removeActor(actor); // remove the actor from the system
                TimePerceptionManager.getInstance().cleanUp(actor); // remove actor from TimePerceptionManager
                AffectionManager.removePokemon(actor);    // remove actor (if Pokemon) from AffectionManager
            }
        }
    }

    /**
     * Clears a dead actor with their known map
     * @param actor     Actor to clean up
     * @param actorMap  GameMap of actor to clean up
     */
    public void clearActor(Actor actor, GameMap actorMap) {
        if (actorMap.contains(actor)) {  // if map contains the actor
            actorMap.removeActor(actor); // remove the actor from the system
            TimePerceptionManager.getInstance().cleanUp(actor); // remove actor from TimePerceptionManager
            AffectionManager.removePokemon(actor);    // remove actor (if Pokemon) from AffectionManager
        }
    }

    /**
     * Clears all dead actors from all GameMaps by checking their consciousness
     *
     */
    public void clearAll() {
        for (GameMap map : maps) {
            NumberRange xMap = map.getXRange();
            NumberRange yMap = map.getYRange();

            for (int y : yMap) {
                for (int x : xMap) {
                    // get actor at each (x,y)
                    Actor actor = map.at(x, y).getActor();
                    // check if actor is conscious
                    if (actor != null && !actor.isConscious()) {
                        // remove actor from game map if not conscious
                        map.removeActor(actor);
                        // remove actor from time perception manager
                        TimePerceptionManager.getInstance().cleanUp(actor);
                        // remove actor from affection manager
                        AffectionManager.removePokemon(actor);
                    }
                }
            }
        }

    }

}
