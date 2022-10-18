package game.grounds;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;

/**
 * Created by:
 * @author Cheung Chi Wong
 * Modified by:
 * @author Cheung Chi Wong
 * @author Wei Khang Lo
 *
 */


public abstract class SpawningGround extends Ground {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar, Element element) {
        super(displayChar);
        this.addCapability(element);
    }

    /**
     * Override the tick method in Ground class for spawning grounds to spawn Pokemons and drop pokefruits
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        spawnPokemon(location);
        dropPokefruit(location);
    }

    /**
     * abstract method for spawning Pokemon to be implemented by subclasses
     * @param location  location of the spawning ground
     */
    public abstract void spawnPokemon(Location location);


    /**
     * abstract method for dropping pokefruits to be implemented by subclasses
     * @param location  location of the spawning ground
     */
    public abstract void dropPokefruit(Location location);

}

