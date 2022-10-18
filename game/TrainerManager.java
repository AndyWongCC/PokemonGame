package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.HashMap;
import java.util.Map;

public class TrainerManager {

    /**
     * Singleton instance of map of each trainer to an trainer manager
     */
    private static Map<Actor, TrainerManager> instance = new HashMap<>();

    /**
     * variable for the trainer actor
     */
    private Actor trainer;

    /**
     * variable for the trainer's current map
     */
    private GameMap map;

    /**
     * trainer's x coordinate
     */
    private int x;

    /**
     * trainer's y coordinate
     */
    private int y;


    /**
     * Private constructor
     */
    private TrainerManager(Actor trainer) {
        this.trainer = trainer;
    }

    /**
     * Access to the instance of an trainer or create a new one for an trainer
     * @param trainer   trainer actor
     * @return      trainer manager for actor or null if actor is not an trainer
     */
    public static TrainerManager getInstance(Actor trainer) {
        // check if actor is a trainer
        if (trainer.hasCapability(Status.TRAINER)) {
            // check if trainer manager for trainer is available
            if (instance.get(trainer) == null) {
                // if none then add a new instance of trainer manager for that trainer
                instance.put(trainer, new TrainerManager(trainer));
            }
            return instance.get(trainer);
        }
        // if actor can't be a trainer then no affection manager for the actor
        return null;
    }


    /**
     * Updates the trainer's current map
     * @param map   Gamemap
     */
    public void updateMap(GameMap map) {
        this.map = map;
    }

    /**
     * Updates the trainer's coordinates on the map
     * @param x     x coordinate
     * @param y     y coordinate
     */
    public void updateCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * get information of trainer
     * @return
     */
    public String getInfo() {
        String info = "";

        info += this.trainer.toString() + " | ";
        info += this.x + "," + this.y + " | ";
        info += "inventory: " + this.trainer.getInventory().toString() + "\n";

        info += AffectionManager.getInstance(this.trainer).getInfo(this.map);

        return info;
    }


}
