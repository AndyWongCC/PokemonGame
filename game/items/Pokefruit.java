package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Element;
import game.Status;

/**
 * Created by:
 * @author Cheung Chi Wong
 */


public class Pokefruit extends Item {

    /**
     * Constructor.
     * @param element   the element for this pokefruit
     */
    public Pokefruit(Element element) {
        super(element.toString() + " pokefruit", 'f', true);
        this.addCapability(element);
        this.addCapability(Status.CONSUMABLE);
    }

}
