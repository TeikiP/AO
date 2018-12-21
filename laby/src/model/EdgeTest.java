package model;

import static org.junit.Assert.*;

import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;


/**
 * JUnit test class for the Edge class.
 * 
 * @author Teiki Pepin
 */

public class EdgeTest {

	
	/**
	 * Test the custom constructor for an Edge <p>
	 * 
	 * Makes sure that the constructed edge is not null 
	 * and has the provided type.
	 */
	@Test
	public void testEdgeType() {
		Edge edge1 = new Edge(Edge.Type.OPENED_DOOR);
		Edge edge2 = new Edge(Edge.Type.CLOSED_DOOR);
		Edge edge3 = new Edge(Edge.Type.CORRIDOR);

		assertNotNull(edge1);
		assertEquals(edge1.getType(), Edge.Type.OPENED_DOOR);
		assertEquals(edge2.getType(), Edge.Type.CLOSED_DOOR);
		assertEquals(edge3.getType(), Edge.Type.CORRIDOR);
	}
	

	/**
	 * Test the default constructor for an Edge through its
	 * usage by the SimpleGraph class. <p>
	 * 
	 * Makes sure that the edge is properly constructed 
	 * in the graph.
	 * 
	 * @see SimpleGraph
	 */
	@Test
	public void testEdge() {
		SimpleGraph<Integer, Edge> graph = new SimpleGraph(Edge.class);
		
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);

		assertTrue(graph.containsEdge(1, 2));
		assertTrue(graph.containsEdge(2, 1));
		
		assertTrue(graph.containsEdge(2, 3));
		assertFalse(graph.containsEdge(1, 3));
	}


	/**
	 * Test the getter for the source vertex of an edge.
	 * 
	 * @see SimpleGraph
	 */
	@Test
	public void testGetSource() {
		SimpleGraph<Vertex, Edge> graph = new SimpleGraph(Edge.class);
		
		Vertex vertex1 = new Vertex(1,1);
		Vertex vertex2 = new Vertex(2,2);
		Vertex vertex3 = new Vertex(3,3);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		
		graph.addEdge(vertex1, vertex3);
		
		graph.edgeSet().forEach((edge) -> {
			String s = vertex1.toString();
			String t = edge.getSource().toString();
			assertTrue(s.equals(t));
		});
	}

	/**
	 * Test the getter for the target vertex of an edge.
	 * 
	 * @see SimpleGraph
	 */
	@Test
	public void testGetTarget() {
		SimpleGraph<Vertex, Edge> graph = new SimpleGraph(Edge.class);
		
		Vertex vertex1 = new Vertex(1,1);
		Vertex vertex2 = new Vertex(2,2);
		Vertex vertex3 = new Vertex(3,3);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		
		graph.addEdge(vertex1, vertex3);
		
		graph.edgeSet().forEach((edge) -> {
			String s = vertex3.toString();
			String t = edge.getTarget().toString();
			assertTrue(s.equals(t));
		});
	}

	
	/**
	 * Test the getter for the type of an edge.
	 */
	@Test
	public void testGetType() {
		Edge edge = new Edge();

		assertEquals(edge.getType(), Edge.Type.CORRIDOR);
	}

	
	/**
	 * Test the setter for the type of an edge.
	 */
	@Test
	public void testSetType() {
		Edge edge1 = new Edge();
		Edge edge2 = new Edge();
		Edge edge3 = new Edge();

		edge1.setType(Edge.Type.OPENED_DOOR); 
		edge1.setType(Edge.Type.CORRIDOR); // redefinition
		edge2.setType(Edge.Type.OPENED_DOOR);
		edge3.setType(Edge.Type.CLOSED_DOOR);

		assertEquals(edge1.getType(), Edge.Type.CORRIDOR);
		assertEquals(edge2.getType(), Edge.Type.OPENED_DOOR);
		assertEquals(edge3.getType(), Edge.Type.CLOSED_DOOR);
	}

	
	/**
	 * Test the comparison method which has to 
	 * return 0 of two edges have the same vertices
	 * and an integer that isn't 0 otherwise.
	 */
	@Test
	public void testCompareTo() {
		SimpleGraph<Vertex, Edge> graph = new SimpleGraph(Edge.class);
		
		Vertex vertex1 = new Vertex(1,1);
		Vertex vertex2 = new Vertex(2,2);
		Vertex vertex3 = new Vertex(3,3);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		
		graph.addEdge(vertex1, vertex3);
		graph.addEdge(vertex3, vertex1);
		graph.addEdge(vertex2, vertex3);
		
		Edge edge13 = graph.getEdge(vertex1, vertex3);
		Edge edge31 = graph.getEdge(vertex3, vertex1);
		Edge edge23 = graph.getEdge(vertex2, vertex3);
		
		
		assertTrue(edge13.compareTo(edge13) == 0);
		assertTrue(edge13.compareTo(edge31) == 0);
		assertFalse(edge13.compareTo(edge23) == 0);
	}

}
