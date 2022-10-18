package game.grounds;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Element;
import game.Utils;
import game.items.Pokefruit;
import game.pokemons.Charmander;
import game.time.TimePerceptionManager;

import java.sql.Time;

/**
 * Created by:
 * @author Cheung Chi Wong
 * Modified by:
 * @author Cheung Chi Wong
 * @author Wei Khang Lo
 *
 */


public class Crater extends SpawningGround {

    /**
     * Constructor.
     */
    public Crater() {
        super('O', Element.FIRE);
    }


    /**
     * method for spawning Charmander based on probability and conditions
     * @param location  location of the spawning ground
     */
    @Override
    public void spawnPokemon(Location location) {
        int chance = 10;    // probability to spawn Charmander
        // if probability succeeds and location has no one on it
        if (Utils.runProbability(chance) && !location.containsAnActor()) {
            // instantiate new Charmander
            Charmander charmander = new Charmander();

            // spawn new Charmander to the location
            location.addActor(charmander);

            // add newly spawned Pokemon to affection manager
            AffectionManager.registerPokemon(charmander);
            // add newly spawned Pokemon to Time Perception Manager
            TimePerceptionManager.getInstance().append(charmander);
        }
    }


    /**
     * method for dropping FIRE pokefruit based on probability
     * @param location  location of the spawning ground
     */
    @Override
    public void dropPokefruit(Location location) {
        int chance = 25;    // probability to drop fire pokefruit
        // if probability succeeds (location does not need to be empty, multiple items can be in same location)
        if (Utils.runProbability(chance)) {
            // instantiate new pokefruit with FIRE element
            Pokefruit pokefruit = new Pokefruit(Element.FIRE);
            // drop new pokefruit to the location
            location.addItem(pokefruit);
        }
    }
}
