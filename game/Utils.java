package game;
import java.util.Random;

public class Utils {

    /**
     * Runs a given probability
     * @param chance    the probability in percentage (%), e.g. 40% would be 40
     * @return  true if success, false if fail
     */
    public static boolean runProbability(int chance){
        
        Random rand = new Random();
        return rand.nextInt(100) <= chance;
        
        //chance /= 100;
        //boolean check = new Random().nextDouble()<=chance; //Using the random to generate a number from 0 to (chance-1) to calculate the probability
        //return check;
    }

    /**
     * get random number between 0 (inclusive) and max (exclusive), [0,max)
     * @param max   maximum number range (exclusive)
     * @return  random number between [0,max)
     */
    public static int randomNum(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

}
