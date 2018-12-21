package view;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Candy;

/**
 * Class handling the display of every element of the game.
 * Dsiplays monsters, scores, candys and door.
 */
public class GlobalView {
    /**
     * Single instance of the view.
     */
    private static GlobalView globalView = null;

    private PlayerView playerView;
    private DoorView doorView;
    private LabyrinthView labyrinthView;
    private ScoreView scoreView;


    /**
     * HashMap containing every monsterView
     */
    private HashMap<Integer, MonsterView> monsterViews;
    /**
     * HashMap containing every candyView
     */
    private HashMap<Integer, CandyView> candyViews;
    /**
     * HashMap containing every buttonView
     */
    private HashMap<Integer, ButtonView> buttonViews;

    /**
     * Pane containing every element of the view.
     */
    private Pane pane = new Pane();

    /**
     * Constructor creating the single instance of the view.
     */
    private GlobalView() {
        labyrinthView = LabyrinthView.getInstance();

        monsterViews = new HashMap<>();
        candyViews = new HashMap<>();
        buttonViews = new HashMap<>();

        playerView = PlayerView.getInstance();

        scoreView = ScoreView.getInstance();

    }

    /**
     * Method creating the single instance of the the global view.
     * @return the instance if not null, creates adn returns it otherwise.
     */
    public static GlobalView getInstance() {
        if (globalView == null)
            globalView = new GlobalView();
        return globalView;
    }

    /**
     * returns the player view
     * @return the single instance of the playerView
     */
    public PlayerView getPlayerView() {
        return playerView;
    }

    /**
     * returns the DoorView
     * @return the single instance of the doorView
     */
    public DoorView getDoorView() {
        return doorView;
    }

    /**
     * returns the labyrinthView
     * @return the single instance of the LabyrinthView
     */
    public LabyrinthView getLabyrinthView() {
        return labyrinthView;
    }

    /**
     * return an hashmap of monsterView
     * @return the hashmap containing every monserView.
     */
    public HashMap<Integer, MonsterView> getMonsterViews() {
        return monsterViews;
    }

    /**
     * return an hashmap of CandyView
     * @return the hashmap containing every CandyView
     */
    public HashMap<Integer, CandyView> getCandyViews() {
        return candyViews;
    }

    /**
     * return an hashmap of ButtonView
     * @return the hashmap containing every ButtonView
     */
    public HashMap<Integer, ButtonView> getButtonViews() {
        return buttonViews;
    }

    /**
     * returns the scoreView
     * @return the single instance of the ScoreView
     */
    public ScoreView getScoreView() {
        return scoreView;
    }

    /**
     * Creates every element needed to be displayed.
     * Displays the labyrinth walls, player and the score.
     * @param stage the window to display the game in.
     * @param wallCoordinates ArrayList representig the coordinates of the labyrinth walls.
     */
    public void createGlobalView(Stage stage, ArrayList<Integer> wallCoordinates){
        labyrinthView.drawFrame(stage, pane, 16,16);
        for (int i = 0; i < wallCoordinates.size(); i+=4){
            labyrinthView.drawWall(pane, wallCoordinates.get(i), wallCoordinates.get(i+1),wallCoordinates.get(i+2), wallCoordinates.get(i+3), LabyrinthView.WALL_COLOR);
        }
        playerView.drawSprite(pane, 0, 0);
        scoreView.createScoreView(pane);

    }

    /**
     * Add a candyView to the hashmap. A hashmap allows us to link the view with the corresponding model.
     * @param ident unique identifier of the CandyView
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void addCandyView(int ident, int x, int y){
        CandyView candyView = new CandyView();
        candyViews.put(ident, candyView);
        candyView.drawSprite(pane, x, y);
    }

    /**
     * Add an enemy to the hashmap. A hashmap allows us to link the view with the corresponding model.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void addEnemyView(int x, int y){
        MonsterView monsterView = new MonsterView();
        monsterViews.put(monsterViews.size(), monsterView);
        monsterView.drawSprite(pane, x, y);
    }

    /**
     * Add an button to the hashmap. A hashmap allows us to link the view with the corresponding model.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void addButtonView(int x, int y){
        ButtonView buttonView = new ButtonView();
        buttonViews.put(0, buttonView);
        buttonView.drawSprite(pane, x, y);
    }

    /**
     * Creates and displays the door.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void addDoorView(int x, int y){
        doorView = new DoorView();
        doorView.drawSprite(pane, x, y);

    }

    /**
     * Remove a candy from display and from HashMap when picked up by the player.
     * @param ident identifier of the candy
     */
    public void removeCandyFromView(int ident){
        pane.getChildren().remove(candyViews.get(ident).getSprite());
        candyViews.remove(ident);
    }

    /**
     * Empties every hashMap and clear the pane to free memory.
     * Called when the player won or touches a monster.
     */
    public void resetView(){
        pane.getChildren().clear();
        System.out.println(pane.getChildren().size());
        monsterViews.clear();
        candyViews.clear();;
        pane = new Pane();
    }
}
