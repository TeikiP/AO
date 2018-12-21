package controller;

import model.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import view.GlobalView;
import view.LabyrinthView;

import java.util.*;

import view.ElementView;
import view.MonsterView;

/**
 * Class controlling and handling events between model (eg. the game) and the view.
 * This class acts like a third person avoiding direct communication between model and view.
 * It contains a reference to Game and one to the view.
 */
public class Controller {
    private Stage stage;

    /**
     * Single instance of the controller
     */
    private static Controller instance = null;

    /**
     * Single instance to the differents graphical elements of the game
     */
    private static GlobalView globalView;
    /**
     * Single instance to the game.
     */
    private static Game game;

    /**
     * ArrayList of threads handling Enenmy movement.
     */
    ArrayList<ThreadMovementEnemy> threadMovement;

    /**
     * Handler function for keyboards events.
     * Each time a key is pressed, the controller updates the graphical elements and check if there is any
     * collision or if the player has reached the door.
     */
    private EventHandler<KeyEvent> keyboardListener = event -> {
        switch (event.getCode()) {

            case UP:
                game.getPlayer().moves(game.getPlayer().getX(), game.getPlayer().getY() - 1);
                globalView.getPlayerView().updatePosition(game.getPlayer().getX(), game.getPlayer().getY());
                areColliding(game.getCandies());
                areColliding(game.getEnemies());
                isGameOver();
                break;
            case DOWN:
                game.getPlayer().moves(game.getPlayer().getX(), game.getPlayer().getY() + 1);
                globalView.getPlayerView().updatePosition(game.getPlayer().getX(), game.getPlayer().getY());
                areColliding(game.getCandies());
                areColliding(game.getEnemies());
                isGameOver();
                break;
            case LEFT:
                game.getPlayer().moves(game.getPlayer().getX() - 1, game.getPlayer().getY());
                globalView.getPlayerView().updatePosition(game.getPlayer().getX(), game.getPlayer().getY());
                areColliding(game.getCandies());
                areColliding(game.getEnemies());
                isGameOver();
                break;
            case RIGHT:
                game.getPlayer().moves(game.getPlayer().getX() + 1, game.getPlayer().getY());
                globalView.getPlayerView().updatePosition(game.getPlayer().getX(), game.getPlayer().getY());
                areColliding(game.getCandies());
                areColliding(game.getEnemies());
                isGameOver();
                break;

        }
    };

    /**
     * Private constructor which creates the View and the Game.
     */
    private Controller() {
        globalView = GlobalView.getInstance();
        game = Game.getInstance();
    }

    /**
     * Method creating the instance of the controller
     * @return the existing instance of the controller, creates it otherwise.
     */
    public static Controller makeInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * Method creating and lauching a game.
     * Allocates and creates all the graphical elements and all the model components.
     * @param primaryStage the Stage(eg. window) the game has to be created in.
     */
    public void start(Stage primaryStage){
        this.stage = primaryStage;

        ArrayList wallCoordinates = generateWalls(game.getLabyrinth().getWalls());

        game.getCandies().forEach((key, candy) -> globalView.addCandyView(key, candy.getX(), candy.getY()));

        game.getEnemies().forEach((key, enemy) -> globalView.addEnemyView(enemy.getX(), enemy.getY()));

        game.getButtons().forEach((key, button) -> globalView.addEnemyView(button.getX(), button.getY()));

        globalView.addDoorView(game.getDoor().getX(), game.getDoor().getY());


        globalView.createGlobalView(primaryStage, wallCoordinates);
        globalView.getLabyrinthView().setOnAction(keyboardListener);
        primaryStage.show();
        moveEnemy();
    }

    /**
     * Gather the graph vertices and build a list of edges.
     * @param walls list of the non-connected nodes.
     * @return An array list of numbers representing the coordinates of each wall.
     */
    private ArrayList generateWalls(Set<List<Vertex>> walls) {
        ArrayList<Integer> wallsCoordinates = new ArrayList();
        walls.forEach((wall) -> {
            int xs = wall.get(0).getX();
            int ys = wall.get(0).getY();
            int xt = wall.get(1).getX();
            int yt = wall.get(1).getY();
            wallsCoordinates.add(xs);
            wallsCoordinates.add(ys);
            wallsCoordinates.add(xt);
            wallsCoordinates.add(yt);
        });
        return wallsCoordinates;
    }

    /**
     * Checks if there is any collision between the player and any element on screen.
     * When a game is restarted, everything is generated again from scratch.
     * @param elementHashMap The HashMap of a specific type of objects (Enemy,
     */
    private void areColliding(HashMap<Integer, Element> elementHashMap) {
        int coordPlayerX = game.getPlayer().getX();
        int coordPlayerY = game.getPlayer().getY();

        //Iterator through the hashMap. Needed to avoid ConcurrentModification Exception.
        Iterator<Map.Entry<Integer, Element>> iter = elementHashMap.entrySet().iterator();

        while (iter.hasNext()) {

            //Building th entry set
            Map.Entry<Integer, Element> entry = iter.next();

            Element element = entry.getValue();
            if (coordPlayerX == element.getX() && coordPlayerY == element.getY()) {
                iter.remove();
                //Removeing Candy, increasing score.
                if (element instanceof Candy) {
                    globalView.removeCandyFromView(entry.getKey());
                    game.getScore().incrementScore();
                    globalView.getScoreView().updateScore(game.getScore().getTotalScore());
                }
                //Resetting the game from scratch
                if (element instanceof Enemy) {
                    game.resetGame();
                    game.getScore().resetScore();
                    globalView.resetView();
                    stopMovements();
                    try {
                        stage.setScene(null);
                        start(this.stage);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Checks if the game is over by getting to the door.
     * When getting to the door, the game is resetted but the score remains the same.
     */
    private void isGameOver() {
        int coordPlayerX = game.getPlayer().getX();
        int coordPlayerY = game.getPlayer().getY();

        if (coordPlayerX == game.getDoor().getX() && coordPlayerY == game.getDoor().getY()) {
            game.resetGame();
            globalView.resetView();
            stopMovements();
            try {
                stage.setScene(null);
                start(this.stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    /**
     * Starts a bunch of threads for handling enemy movements.
     * Each enemy has its own movement thread. Each of these threads are stored in a arrayList.
     */
    private void moveEnemy() {
        threadMovement = new ArrayList();
        game.getEnemies().forEach((key, enemy) -> {
            ThreadMovementEnemy t = new ThreadMovementEnemy((Enemy) enemy, globalView.getMonsterViews().get(key));
            threadMovement.add(t);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(t, 0, 500);
        });
    }

    /**
     * Cancels any enemy movements by shutting down every threads.
     */
    private void stopMovements() {
        threadMovement.forEach(thread -> {
            thread.cancel();
        });
        threadMovement.clear();
    }

    /**
     * Class Handling the enemies movement via a Timer.
     * Periodically, the enemies will move towards the player trying to catch him.
     */
    public class ThreadMovementEnemy extends TimerTask {

        Enemy enemy;
        MonsterView monsterView;

        /**
         * Create a thread for an enemy.
         * @param e enemy to move
         * @param monsterView view to update when a move is done.
         */
        private ThreadMovementEnemy(Enemy e, MonsterView monsterView) {
            this.enemy = e;
            this.monsterView = monsterView;
        }

        /**
         * Routine to execute perodically.
         */
        @Override
        public void run() {
            game.getLabyrinth().launchManhattan(new Vertex(enemy.getX(), enemy.getY()), new Vertex(game.getPlayer().getX(), game.getPlayer().getY()));
            moveManhattan();
            monsterView.updatePosition(enemy.getX(), enemy.getY());
        }


        /**
         * Computes the direciton for the enemy to mave to thanks to the manhattan distance.
         */
        private void moveManhattan() {
            Enemy e = enemy;
            Vertex vertex = game.getLabyrinth().getVertex(enemy.getX(), enemy.getY());
            for (Labyrinth.Directions dir : Labyrinth.Directions.values()) {
                Vertex next = game.getLabyrinth().getVertexByDir(vertex, dir);
                if (next == null)
                    continue;
                if (game.getLabyrinth().areVerticesConnected(vertex.getX(), vertex.getY(), next.getX(), next.getY()) && (next.getNbr() == vertex.getNbr() - 1)) {
                    if (dir == Labyrinth.Directions.SOUTH)
                        e.moves(e.getX(), e.getY() - 1);
                    if (dir == Labyrinth.Directions.NORTH)
                        e.moves(e.getX(), e.getY() + 1);
                    if (dir == Labyrinth.Directions.WEST)
                        e.moves(e.getX() - 1, e.getY());
                    if (dir == Labyrinth.Directions.EAST)
                        e.moves(e.getX() + 1, e.getY());
                }
            }
        }
    }

}
