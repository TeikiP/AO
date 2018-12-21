package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private static Game game =new Game();

	private Player player;
	private Labyrinth labyrinth;
	private HashMap<Integer, Element> candies;
	private HashMap<Integer, Element> enemies;
	private HashMap<Integer, Element> buttons;


	private Score score;
	private Door door;

	
	private Game() {
		labyrinth = Labyrinth.getInstance();
		player = Player.getInstance();

		candies = new HashMap<>();
		enemies = new HashMap<>();
		buttons = new HashMap<>();

		generateCandies();
		generateEnemies();
		//generateButtons();

		score = Score.getInstance();

        int coordX = ThreadLocalRandom.current().nextInt(0, 16);
        int coordY = ThreadLocalRandom.current().nextInt(0, 16);
		this.door = Door.getInstance(coordX, coordY);
	}
	
	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	public Player getPlayer() {
		return player;
	}

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	public HashMap<Integer, Element> getCandies() {
		return candies;
	}

	public HashMap<Integer, Element> getEnemies() {
		return enemies;
	}

	public HashMap<Integer, Element> getButtons() {
		return buttons;
	}

	public Score getScore() {
		return score;
	}

	public Door getDoor() {
		return door;
	}

	private void generateCandies(){
		for (int i = 0; i < 16; i++){
			int coordX = ThreadLocalRandom.current().nextInt(0, 16);
			int coordY = ThreadLocalRandom.current().nextInt(0, 16);
			Candy candy = new Candy(coordX, coordY, i);
			candies.put(i, candy);
		}
	}

	private void generateEnemies(){
		int coordX = ThreadLocalRandom.current().nextInt(0, 16);
		int coordY = ThreadLocalRandom.current().nextInt(0, 16);
		Enemy enemy = new Enemy(coordX,coordY);
		enemies.put(0, enemy);
		coordX = ThreadLocalRandom.current().nextInt(0, 16);
		coordY = ThreadLocalRandom.current().nextInt(0, 16);
		enemy = new Enemy(coordX,coordY);
		enemies.put(1, enemy);
	}

	private void generateButtons(){
		int coordX = ThreadLocalRandom.current().nextInt(0, 16);
		int coordY = ThreadLocalRandom.current().nextInt(0, 16);
		//Button button = new Button(coordX, coordY);
		//buttons.put(0, button);
	}

	public void resetGame(){
		labyrinth.reset();
		labyrinth = Labyrinth.getInstance();
		System.out.println(labyrinth);
		player.setX(0);
		player.setY(0);

		candies = new HashMap();
		enemies = new HashMap();
		buttons = new HashMap();

		int coordX = ThreadLocalRandom.current().nextInt(0, 16);
		int coordY = ThreadLocalRandom.current().nextInt(0, 16);
		door.setX(coordX);
		door.setY(coordY);

		generateCandies();
		generateEnemies();

	}
}
