package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Created by:
 * @author Wei Khang Lo
 */

public class TrainerInfoAction extends Action {

    /**
     * Trainer to get info of
     */
    Actor trainer;

    /**
     * Constructor
     * @param trainer
     */
    public TrainerInfoAction(Actor trainer) {
        this.trainer = trainer;
    }

    /**
     * execute method for action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        TrainerManager trainerManager = TrainerManager.getInstance(trainer);
        if (trainerManager != null) {
            return trainerManager.getInfo();
        }
        return trainer.toString() + " is not a trainer";
    }

    /**
     * hotkey for action
     * @return
     */
    @Override
    public String hotkey() {
        return "z";
    }

    /**
     * menu description of action
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Show " + trainer.toString() + " information";
    }
}
