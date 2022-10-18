package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.TrainerManager;

public class WanderBehaviour implements Behaviour {
	
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {

		ArrayList<Exit> exitList = new ArrayList<>();	// create list of exits
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
				exitList.add(exit);
            }
        }

		if (!exitList.isEmpty()) {
			// randomly pick an exit from viable exits to move to
			Exit chosenExit = exitList.get(random.nextInt(exitList.size()));

			// update Trainer manager if actor is a trainer
			TrainerManager trainerManager = TrainerManager.getInstance(actor);
			if (trainerManager != null) {
				trainerManager.updateMap(map);
				trainerManager.updateCoordinates(chosenExit.getDestination().x(), chosenExit.getDestination().y());
			}

			return chosenExit.getDestination().getMoveAction(actor, "around", chosenExit.getHotKey());
		}
		else {
			return null; // go to next behaviour
		}
	}
}
