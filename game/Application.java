package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.*;
import game.items.Pokefruit;
import game.pokemons.*;
import game.grounds.Door;
import game.pokemons.Bulbasaur;
import game.pokemons.Squirtle;
import game.time.TimePerceptionManager;

/**
 * The main class to start the game.
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Wei Khang Lo
 * @author Cheung Chi Wong
 */
public class Application {

    public static void main(String[] args) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(), new Hay(), new Crater(), new Lava(), new Waterfall(), new Puddle());

        List<String> map = Arrays.asList(
                ".............................................^^^^^^^^^^^^^^",
                "...........,T,................................,T,..^^^^O^^^",
                ".....................................................^^^^^^",
                "........................................................^^^",
                "..........................###............,,..............^^",
                "..........................#_#...........,T................^",
                "..,,,...............,T.....................................",
                "..,T,......~...............................................",
                "...~~~~~~~~................................................",
                "....~~~~~..................................................",
                "~~W~~~~.,............................,,,...................",
                "~~~~~~.,T,...........................,T,...................",
                "~~~~~~~~~............................,.....................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        //creating the new map called Pokemon Center
        List<String> pokemonCenter = Arrays.asList(
                "##################",
                "#________________#",
                "#______....._____#",
                "#________________#",
                "#________________#",
                "########___#######"
                );
        GameMap pokemonCentreMap = new GameMap(groundFactory, pokemonCenter);
        world.addGameMap(pokemonCentreMap);

        // Create entrance to Pokemon Centre from  Game map
        gameMap.at(27,5).setGround(new Door(pokemonCentreMap.at(9,5), "to Pokemon Centre"));
        // Create exit to Game map from Pokemon Centre
        pokemonCentreMap.at(9,5).setGround(new Door(gameMap.at(27,5), "to Pallet Town"));

        // setting up game managers
        ClearActorsManager.getInstance().addMaps(gameMap);  // adding game map to ClearActorManager to manage
        ClearActorsManager.getInstance().addMaps(pokemonCentreMap); // adding pokemon centre map to ClearActorManager to manage
        TimePerceptionManager timePerceptionManager = TimePerceptionManager.getInstance();      // get time perception manager's singleton instance

        // add player - Ash
        Player ash = new Player("Ash", '@', 1);
        world.addPlayer(ash, gameMap.at(27, 7));
        AffectionManager.getInstance(ash);  // register an affection manager for the player Ash

        // add trainer - Goh
        Goh goh = Goh.getInstance();
        gameMap.at(27,8).addActor(goh); // add goh to game
        AffectionManager.getInstance(goh);  // register an affection manager for trainer Goh
        TrainerManager.getInstance(goh);    // register a trainer manager for Goh
        TrainerManager.getInstance(goh).updateMap(gameMap);     // set map for Goh's trainer manager
        TrainerManager.getInstance(goh).updateCoordinates(gameMap.locationOf(goh).x(), gameMap.locationOf(goh).y());    // set coordinates for Goh's trainer manager
        // give Goh some pokefruits
        goh.addItemToInventory(new Pokefruit(Element.FIRE));
        goh.addItemToInventory(new Pokefruit(Element.WATER));
        goh.addItemToInventory(new Pokefruit(Element.GRASS));

        // spawn Pokemons next to each other to fight with special attack
        Squirtle squirtle = new Squirtle();
        gameMap.at(2,5).addActor(squirtle);
        AffectionManager.registerPokemon(squirtle);
        timePerceptionManager.append(squirtle);
        Bulbasaur bulbasaur = new Bulbasaur();
        gameMap.at(3,3).addActor(bulbasaur);
        AffectionManager.registerPokemon(bulbasaur);
        timePerceptionManager.append(bulbasaur);

        // testing Charizard special attacks
        Charizard charizard = new Charizard();
        gameMap.at(54,1).addActor(charizard);
        AffectionManager.registerPokemon(charizard);
        timePerceptionManager.append(charizard);
        Squirtle squirtle2 = new Squirtle();
        gameMap.at(53,1).addActor(squirtle2);
        AffectionManager.registerPokemon(squirtle2);
        timePerceptionManager.append(squirtle2);

        world.run();

    }
}
