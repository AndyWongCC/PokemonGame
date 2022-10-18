package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.ElementsHelper;
import game.Status;
import game.Utils;
import game.time.TimePerceptionManager;
import game.time.TimePeriod;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Lava extends Ground {

    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
    }

    @Override
    public void tick(Location location){
        // check if has status to run day effect for ground and run probability is successful
        if (TimePerceptionManager.getInstance().getTimePeriod() == TimePeriod.DAY && Utils.runProbability(10)) {
            for(Exit exit : location.getExits()) {
                // get ground at exit
                Ground exitGround = exit.getDestination().getGround();
                // check that ground is not immune to changes and does not have similar elements to this ground
                if (!exitGround.hasCapability(Status.FIXED_GROUND) && !ElementsHelper.hasAnySimilarElements(this, exitGround.findCapabilitiesByType(Element.class))) {
                    // update the ground at exit
                    exit.getDestination().setGround(new Lava());
                }
            }
        }
        // check if has status to run night effect on ground and run probability is successful
        else if (TimePerceptionManager.getInstance().getTimePeriod() == TimePeriod.NIGHT && Utils.runProbability(10)) {
            // check that there is no actor at current location
            if (!location.containsAnActor()) {
                // convert ground to dirt
                location.setGround(new Dirt());
            }
        }
    }
}

