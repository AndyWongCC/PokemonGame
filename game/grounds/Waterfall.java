package game.grounds;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Element;
import game.Utils;
import game.items.Pokefruit;
import game.pokemons.Squirtle;
import game.time.TimePerceptionManager;

/**
 * Created by:
 * @author Cheung Chi Wong
 * Modified by:
 * @author Cheung Chi Wong
 * @author Wei Khang Lo
 *
 */


public class Waterfall extends SpawningGround {

    /**
     * Constructor.
     */
    public Waterfall() {
        super('W', Element.WATER);
    }


    /**
     * method for spawning Squirtle based on probability and conditions
     * @param location  location of the spawning ground
     */
    @Override
    public void spawnPokemon(Location location) {
        int chance = 20;    // probability to spawn Squirtle
        // if probability succeeds and location has no one on it
        if (Utils.runProbability(chance) && !location.containsAnActor()) {
            int waterGroundCount = 0;   // counter for number of surrounding WATER element grounds

            // check every exit for ground of WATER element
            for (Exit exit : location.getExits()) {
                // if exit ground has WATER element
                if (exit.getDestination().getGround().hasCapability(Element.WATER)) {
                    waterGroundCount++;
                }
            }

            int minWaterGround = 2; // minimum number of surrounding grounds to have WATER element

            // if at least 2 of surrounding grounds have WATER element
            if (waterGroundCount >= minWaterGround) {
                // instantiate new Squirtle
                Squirtle squirtle = new Squirtle();

                // spawn new Squirtle to the location
                location.addActor(squirtle);

                // add newly spawned Pokemon to affection manager
                AffectionManager.registerPokemon(squirtle);
                // add newly spawned Pokemon to Time Perception Manager
                TimePerceptionManager.getInstance().append(squirtle);
            }
        }
    }


    /**
     * method for dropping WATER pokefruit based on probability
     * @param location  location of the spawning ground
     */
    @Override
    public void dropPokefruit(Location location) {
        int chance = 20;    // probability to drop water pokefruit
        // if probability succeeds (location does not need to be empty, multiple items can be in same location)
        if (Utils.runProbability(chance)) {
            // instantiate new pokefruit with WATER element
            Pokefruit pokefruit = new Pokefruit(Element.WATER);
            // drop new pokefruit to the location
            location.addItem(pokefruit);
        }
    }
}
