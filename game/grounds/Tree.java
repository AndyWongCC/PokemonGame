package game.grounds;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Element;
import game.ElementsHelper;
import game.Status;
import game.Utils;
import game.items.Candy;
import game.items.Pokefruit;
import game.pokemons.Bulbasaur;
import game.time.TimePerception;
import game.time.TimePerceptionManager;
import game.time.TimePeriod;

import java.sql.Time;

/**
 * Created by:
 * @author Riordan Dervin Alfredo
 * Modified by:
 * @author Cheung Chi Wong
 * @author Wei Khang Lo
 *
 */


public class Tree extends SpawningGround {

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('T', Element.GRASS);
    }


    @Override
    public void tick(Location location) {
        super.tick(location);
        // check if has status to run day effect for ground and run probability is successful
        if (TimePerceptionManager.getInstance().getTimePeriod() == TimePeriod.DAY && Utils.runProbability(5)) {
            // dropping candy
            Candy candy = new Candy();
            location.addItem(candy);
        }
        // check if has status to run night effect on ground and run probability is successful
        else if (TimePerceptionManager.getInstance().getTimePeriod() == TimePeriod.NIGHT && Utils.runProbability(10)) {
            Ground newGround;   // declare new ground variable

            // run probability on expanding tree or hay
            if(Utils.runProbability(50)){
                newGround = new Tree(); // set new ground to be tree
            }
            else{
                newGround = new Hay();  // set new ground to be hay
            }

            // expand the new ground
            for(Exit exit : location.getExits()) {
                // get ground at exit
                Ground exitGround = exit.getDestination().getGround();

                // check that ground is not immune to changes and does not have similar elements to this ground
                if (!exitGround.hasCapability(Status.FIXED_GROUND) && !ElementsHelper.hasAnySimilarElements(this, exitGround.findCapabilitiesByType(Element.class))) {
                    exit.getDestination().setGround(newGround); // set the new ground (tree or hay) to the exit
                }
            }
        }
    }


    /**
     * method for spawning Bulbasaur based on probability and conditions
     * @param location  location of the spawning ground
     */
    @Override
    public void spawnPokemon(Location location) {
        int chance = 15;    // probability to spawn Bulbasaur
        // if probability succeeds and location has no one on it
        if (Utils.runProbability(chance) && !location.containsAnActor()) {
            int grassGroundCount = 0;   // counter for number of surrounding GRASS element grounds

            // check every exit for ground of GRASS element
            for (Exit exit : location.getExits()) {
                // if exit ground has GRASS element
                if (exit.getDestination().getGround().hasCapability(Element.GRASS)) {
                    grassGroundCount++;
                }
            }

            int minGrassGround = 1; // minimum number of surrounding grounds to have GRASS element

            // if at least 1 of surrounding grounds have GRASS element
            if (grassGroundCount >= minGrassGround) {
                // instantiate new Bulbasaur
                Bulbasaur bulbasaur = new Bulbasaur();

                // spawn new Squirtle to the location
                location.addActor(bulbasaur);

                // add newly spawned Pokemon to affection manager
                AffectionManager.registerPokemon(bulbasaur);
                // add newly spawnned Pokemon to Time Perception Manager
                TimePerceptionManager.getInstance().append(bulbasaur);
            }
        }
    }


    /**
     * method for dropping GRASS pokefruit based on probability
     * @param location  location of the spawning ground
     */
    @Override
    public void dropPokefruit(Location location) {
        int chance = 15;    // probability to drop grass pokefruit
        // if probability succeeds (location does not need to be empty, multiple items can be in same location)
        if (Utils.runProbability(chance)) {
            // instantiate new pokefruit with GRASS element
            Pokefruit pokefruit = new Pokefruit(Element.GRASS);
            // drop new pokefruit to the location
            location.addItem(pokefruit);
        }
    }
}
