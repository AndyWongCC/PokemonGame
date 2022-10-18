package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.time.TimePerceptionManager;

/**
 * Class representing the Player.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wei Khang Lo
 *
 */
public class Player extends Actor {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.TRAINER);	// Player is a trainer
		this.addCapability(Status.IMMUNE);	// add immunity so player can't get hurt
		this.addCapability(Status.FRIENDLY);	// add friendly status so player can't attack anyone
	}


	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// show Goh's stats
		actions.add(new TrainerInfoAction(Goh.getInstance()));

		// run time perception manager
		TimePerceptionManager.getInstance().run();
		// clean up all dead actors
		ClearActorsManager.getInstance().clearAll();
		// print the player's inventory
		display.println("Inventory: " + this.getInventory().toString());

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar() {
		return super.getDisplayChar();
	}
}


// NOTES:
// draw map and all actors
// 1. process actors before player (PK1 attack Charmander HP = 0)
// 2. process player
// run time perception manager run()	(Bulb HP = 0)
// clear dead actors from TPM run()		(remove Bulb)
// 3. process actors after player (PK2 attack Squirtle HP = 0)