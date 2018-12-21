package model;

import org.junit.Assert;
import static model.Labyrinth.Directions.*;

import org.jgrapht.graph.SimpleGraph;


/**
 * JUnit test class for the Labyrinth class.
 * 
 * @author Pierre Lorson
 */

public class LabyrinthTest {
    static SimpleGraph<Vertex,Edge> graph;
	static Labyrinth labyrinth;
	static Vertex vertex00;
	static Vertex vertex01;
	static Vertex vertex10;
	static Vertex vertex11; 

    @org.junit.BeforeClass
    public static void generateLabyrinth() {
        graph = new SimpleGraph(Edge.class);
    	labyrinth = Labyrinth.getInstance();
    	
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

        labyrinth.setLabyrinthGraph(graph);
	}

    @org.junit.Test
    public void testIsClosed() {
        Assert.assertTrue(labyrinth.isClosed(vertex00, EAST));

    }

    @org.junit.Test
    public void testIsOpened() {
        Assert.assertTrue(labyrinth.isOpened(vertex00, SOUTH));
    }


    @org.junit.Test
    public void testIsWall() {
        Assert.assertTrue(labyrinth.isWall(vertex00, EAST));
    }

    @org.junit.Test
    public void testAreVerticesConnected() {
        Assert.assertTrue(labyrinth.areVerticesConnected(1,0,1,1));
    }
    
    @org.junit.Test
    public void testGetInstance() {

        Labyrinth lab = Labyrinth.getInstance();
        Assert.assertNotNull(lab);
        Assert.assertTrue(lab instanceof Labyrinth);
    }
}