package model;

import org.jgrapht.graph.DefaultEdge;
import org.junit.Assert;
import org.junit.Test;
import org.jgrapht.graph.SimpleGraph;
/**
 * PlayerTest is used to test collisionWall(...) and moves(...) with a specific labyrinth.
 * @author Martial Duverneix
 *
 */
public class PlayerTest {
	/**
	 * the SimpleGraph which allows us to test a specific labyrinth.
	 */
	static SimpleGraph<Vertex,Edge> graph;
	/**
	 * the first vertex of our SimpleGraph.
	 */
	static Vertex vertex00;
	/**
	 * the second vertex of our SimpleGraph.
	 */
	static Vertex vertex01;
	/**
	 * the third vertex of our SimpleGraph.
	 */
	static Vertex vertex10;
	/**
	 * the fourth vertex of our SimpleGraph.
	 */
	static Vertex vertex11; 
	/**
	 * Creates the specific labyrinth.
	 */
    @org.junit.BeforeClass
    public static void generateLabyrinth() {
        graph = new SimpleGraph(Edge.class);
    	
    	
    	vertex00 = new Vertex(0,0);
    	vertex01 = new Vertex(0,1);
    	vertex10 = new Vertex(1,0);
    	vertex11 = new Vertex(1,1);
    	
		graph.addVertex(vertex00);
        graph.addVertex(vertex01);
        graph.addVertex(vertex10);
        graph.addVertex(vertex11);

        graph.addEdge(vertex00, vertex01);
        graph.addEdge(vertex01, vertex11);
        graph.addEdge(vertex11, vertex10);
        
        Labyrinth.getInstance().setLabyrinthGraph(graph);
	}

    /**
     * Tests the collisions.
     * First, we test if collisionWall(...) detects the wall at (1,0).
     * Then, we test if collisionWall(...) detects that there is no wall at (0,1).
     */
	@org.junit.Test
    public void testCollisionWall() {
        Player player=Player.getInstance();
        player.setX(0);
        player.setY(0);
        Assert.assertEquals(true,player.collisionWall(1,0));
        Assert.assertEquals(false,player.collisionWall(0,1));
    } 
	/**
	 * Tests if the character can move.
	 * First, we test if moves(...) stops us to move through a wall.
	 * Then, we test if moves(...) allows us to move when there is no wall.
	 */
	@org.junit.Test
    public void testMoves() {
        Player player=Player.getInstance();
        player.setX(0);
        player.setY(0);
        
        player.moves(player.getX()+1, player.getY());
        Assert.assertEquals(0,player.getX());
        Assert.assertEquals(0,player.getY());
        
        player.moves(player.getX(), player.getY()+1);
        Assert.assertEquals(0,player.getX());
        Assert.assertEquals(1,player.getY());
    } 
	
}
