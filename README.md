# Pokemon

In this game, you will play as Ash Ketchum (@) to explore Pokemon Universe! This game will be slightly different from the usual Pokemon game where you started with a Pokemon selection, for now. There's only one trainer, and that will be you.
The environment in this world is dynamic. It can spawn (create) objects, expand, or be destroyed by nature or activities. In this world, there are three main elements, Fire, Water and Grass (other elements may show up here, but may not be used in this assignment yet). Pokemon, items, and grounds may have one or several elements as part of their capabilities. 

### Environment 🔥 💧 🌳
Spawning ground is a place to create a pokemon and drop fruit at every turn. Its element defines which fruit's element it will drop. For the sake of simplicity, the spawning ground has only one element. Dropping a fruit depends only on a chance, but the spawning pokemon will depend on a chance plus what is happening with its surrounding. In the future, we expect the spawning pokemon feature to be much more complex, but drop fruit will remain the same (i.e., it follows on whatever element of the spawning ground). 

##### 1. Crater O and Lava ^ 🔥 
Crater O (capital O) is a spawning ground. Crater and Lava have Fire element. At every turn:
Crater has a 10% chance of spawning a Charmander (see REQ2). 
Crater has a 25% chance of dropping a "Fire Pokefruit" (see REQ3) on its location. 

##### 2. Waterfall W and Puddle ~ 💧
Waterfall is a spawning ground. Waterfall and Puddle have Water element. At every turn:
Waterfall has a 20% chance of spawning a Squirtle (see REQ2) AND if there are at least two (2) WATER element grounds in its surrounding.
Waterfall has a 20% chance of dropping a "Water Pokefruit" (see REQ3) on its location. 

##### 3. Tree T  and Hay , 🌳
Tree is a spawning ground. Tree and Hay have Grass element. At every turn:
Tree has a 15% chance will spawn a Bulbasaur (see REQ2), AND if there is at least one (1) GRASS element ground in its surrounding.
Tree has a 15% chance of dropping a "Grass Pokefruit" (see REQ3) on its location. 

###  Pokemons🐈🐕
All Pokemons wander around the map. When wandering around, they cannot enter the Floor _ or pass through a Wall #. The elements that are mentioned earlier define the type of a Pokemon (e.g., Fire, Water, and Grass). A Pokemon can automatically attack (or be attacked) a Pokemon that is standing next to it if it doesn't belong to the same element(s). For example, Pokemon that has Grass the element will attack a Pokemon that doesn't have Grass element (such as Fire or Water). 
When attacking, each Pokemon will check if it meets a certain condition for using a Special attack on that turn. If the condition is met, it will equip a weapon that gives more damage whenever it attacks. Otherwise, it unequips the weapon (i.e., remove it from the inventory) and uses an intrinsic attack instead (a.k.a. normal attack). 

##### 1. Charmander c 
It is a Fire type Pokemon. 
It spawns from a Crater (See REQ1)
Intrinsic attack: A "scratch" attack that deals 10 HP damage with a 50% chance to hit (UPDATE (30/8): it was 55% chance to hit)
Special attack: If Charmander is standing on the Fire element ground, it will equip "Ember", a weapon that can deal 20 HP damage with a 90% chance to hit ( "sparks").
Currently, this Pokemon is uncatchable/ cannot be caught with Pokeball (See REQ4).

##### 2. Squirtle s
It is a Water type Pokemon.
It spawns from a Waterfall (See REQ1)
Intrinsic attack: "tackle" attack that deals 10 HP damage with a 50% chance to hit
Special attack: If Squirtle is standing on the Water element ground or the enemy has a Fire element, it will equip "Bubble", a weapon that can deal 25 HP damage with an 80% chance to hit ( "burbles").

##### 3. Bulbasaur b
It is a Grass type Pokemon.
It spawns from a Tree (See REQ1)
Intrinsic attack:  "tackle" attack that deals 10 HP damage with a 50% chance to hit
Special attack: If Bulbasaur is standing on the Grass element ground, it will equip "Vine Whip", a weapon that can deal 30 HP damage with a 70% chance to hit ("whips"). 

###  Items 🧰
##### 1. Pokeball 0 
This portable item is illustrated as 0 (zero), will be used to catch a pokemon. It can only contain one Pokemon at a time and will always expect to have a Pokemon inside. Once the player captures a Pokemon, it will store the corresponding Pokemon instance in this Pokeball. It can be summoned back to the world at one of the trainer's adjacent squares. There is an unlimited number of Pokeballs in this game, 

##### 2. Pokefruit f 
It is illustrated as f in the console. Each fruit has a unique element, such as Fire, Water, and Grass. Whenever it is fed to a Pokemon, it will increase an affection rate towards the giver (see REQ4). The affection rate will decrease if the given fruit doesn't belong to the same element as the Pokemon.

##### 3. Candy *
Candy * is an item that will drop whenever a Pokemon is successfully captured (see REQ4). The trainer can pick it up from the ground or drop it. This candy can be traded with Nurse Joy % for objects, such as Charmander (inside a Pokeball) or Pokefruits (see REQ6).

###  Interactions🌟

##### 1. Pokemon Affection ❤️ 
All pokemon have different affection for the player. Depending on how the Player treats them, it can increase or decrease affection points. Each Pokemon will start from 0 affection points (AP) to a maximum of 100 AP. It differs from hit points as we use them for a battle/fight between Pokemons. Here are the level points:
-50 and below: It dislikes you, and the relationship cannot be fixed. It can't be captured anymore.
0: Neutral. 
50: It likes you and is willing to be captured with Pokeball (note: not all pokemon are capturable/catchable). However, it won't follow the trainer/player yet.
75: It will follow the trainer/player around.
100: The maximum affection level.

##### 2. Increase Pokemon's affection 💕 
You can give any kind of Pokefruit to Pokemon you can find on the map. If the Pokemon doesn't have the same element as the given fruit,  it will decrease the affection by 10 points. However, if it has a similar element to the given fruit, it increases its affection level by 20 points. In the future, there will be more ways to increase affection for Pokemons.

##### 3. Capture and summon a Pokemon 🧤 
Not all Pokemon are capturable/catchable (See REQ3). When the player stands adjacent to a catchable Pokemon, the Player will see an action to capture/catch it. If the player tries to catch a Pokemon with less than 50 affection points, it cannot be captured and automatically decrease its affection by 10 points. 
A captured Pokemon will be stored in the Pokeball and can be summoned back to the world, next to the trainer's location.

### Day and Night ☀️ 🌙

Each ground, actor, and some items may have a time perception of the world. They may experience two different kinds of time periods Day and Night. A day will last for 5 turns and so the night. They will repeat alternately and indefinitely (see pictures below).

##### 1. Day-time ☀️ 
When it is at day, at every turn:

Charmander will be healed by 10 hit points.
Squirtle will be hurt by 10 hit points.
Bulbasaur will be hurt by 5 hit points.

Lava ground ^ has 10% to expand (convert its surrounding grounds to Lava). It looks like a flood of lava.
Puddle ~ have a 10% chance of being destroyed (converted to a Dirt).
Trees T have a 5% chance of dropping a Candy. 

##### 2. Night-time 🌙  
When it is at night, at every turn:

Squirtle will be healed by 10 hit points.
Bulbasaur will be healed by 5 points.
Charmander will be hurt by 10 hit points.

Lava ^ has a 10% chance of being destroyed (converted to a Dirt) as long as there's no actor on it.
Puddle ~ has 10% chance to expand (convert its surrounding grounds to Puddle~). 
Trees T have 10% chance to expand (convert its surroundings to either all Trees or all Hays randomly).
The expanding grounds won't convert the Floors, Walls, and grounds with similar elements (e.g., the expansion of puddles won't convert Waterfall to puddles).

### Evolution 🐉
Evolution is similar to metamorphosis in the real world. Here, a Pokemon changes into a different species. A Pokemon can transform to the next evolution from its current location depending on how long a Pokemon can survive in the World (i.e., 20 turns). Pokemon won't evolve until it is alone (i.e., there are no actors in its surrounding). Alternatively, this evolution can be manually triggered by a trainer if a Pokemon has 100 AP towards that trainer. 

Charmander, Squirtle, and Bulbasaur can evolve. Due to several limitations of the assignment, we only provide detailed evolutions of Charmander as follows. 
##### 1. Charmeleon C (capital C)
Charmander's next evolution is Charmeleon. It has a maximum of 150 HP and is a Fire-Type Pokemon.

Scratch (intrinsic attack): refer to Assignment 1 & 2 requirements

Ember: refer to Assignment 1 & 2 requirements

Blaze (60 damage/90 hit rate): a fire-element weapon item.

##### 2. Charizard Z

Charmeleon's next evolution is Charizard. It has a maximum of 250 HP, Fire and Dragon-type Pokemon. 

Scratch (intrinsic attack): refer to Assignment 1 & 2 requirements

Ember: refer to Assignment 1 & 2 requirements

Blaze: refer to Charmeleon's special attack above.

Fire Spin (70 damage, 90 hit rate): A fire element weapon item. At every turn (when it is equipped), it burns the surrounding (8 squares) by dropping a Fire v. A Fire v inflicts 10 damage to a non-Fire type Pokemons per turn and stays on the ground for the next two turns.

Here, Charmeleon and Charizard can randomly select a weapon/special attack when the ground that it is standing on has similar elements. Moreover, they won't be affected by the Day or Night (i.e., we only expect Charmander, Bulbasaur, and Squirtle to be Pokemons that are affected by time). Charmander becomes a catchable Pokemon (in Assignments 1 & 2, the trainer couldn't catch it). So, we expect some refactoring in your current design & system.

### Pokemon Center (new map) 🗺️ 
##### 1. Shop
A shop (i.e., imaginary space) in the middle of the map that is made of walls # and floors _ , may take too much space in the current game map. Luckily, our game engine (specifically World class) allows us to have multiple maps. It can run these maps in parallel (meaning, it will tick all actors, items, and grounds in all registered maps). It creates an opportunity to move this "imaginary" Pokemon Center/shop layout to a proper place as a new map. 
We will create another map that is a bit smaller than the current game map. Nurse Joy will stand in the middle of the new map. 

##### 2. Door =
Remove Nurse Joy from the original map, including floors on the current game map, by replacing them with dirt . Then, place a door = in the middle.
When you stand on top of the door = sign on the map (as shown above), it gives you an action to enter the Pokemon Center. Executing this action will teleport you to the new game map you've created above (at the centre-bottom of the new map). 

##### 3. Exit =
Put another door = at the centre-bottom of the Pokemon Center map. Standing on top of it will allow you to teleport back to the main/original map, specifically to the original door you entered.

### New Trainer-Structured Mode
We will have a new Trainer in this game. This character is uncontrollable because it can decide its action depending on the situation in its surrounding.

### Goh - a new Trainer 🏃‍♂️   
Goh is another trainer in this game. Here is the list of Goh's activities in this game:

- Goh will wander around most of the time.

- Whenever Goh stands on any Item (e.g., Pokefruit), he will pick it up.

- Whenever Goh stands next to any Pokemon, he will give a Pokefruit to the corresponding Pokemon (disregarding their elements).

- Whenever Goh stands next to a Pokemon, and this Pokemon has affection above 50 points towards him, Goh will catch it.

There must be an action in the console (e.g., press z) that is available anytime to show Goh's location (x and y), inventory, and a list of Pokemons' affection levels toward Goh. 
