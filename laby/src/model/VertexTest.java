package model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test class for the Vertex class.
 * 
 * @author Teiki Pepin
 */

public class VertexTest {


	/**
	 * Test the hashing for a vertex. <p>
	 * 
	 * Makes sure that two vertices with identical coordinates
	 * have the same hashcode but that two vertices with different
	 * coordinates have different hashcodes.
	 */
	@Test
	public void testHashCode() {
		Vertex vertex1 = new Vertex(1,2);
		Vertex vertex2 = new Vertex(2,1);
		Vertex vertex3 = new Vertex(1,2);
		

		assertTrue(vertex1.hashCode() != vertex2.hashCode());
		assertTrue(vertex1.hashCode() == vertex3.hashCode());
	}

	
	/**
	 * Test the default constructor for a vertex. <p>
	 * 
	 * Makes sure that the constructed vertex is not null
	 * and has the expected x=0 and y=0 as coordinates.
	 */
	@Test
	public void testVertex() {
		Vertex vertex = new Vertex();
		
		assertNotNull(vertex);
		assertTrue(vertex.getX() == 0);
		assertTrue(vertex.getY() == 0);
	}

	
	/**
	 * Test the custom constructor for a vertex. <p>
	 * 
	 * Makes sure that the constructed vertex is not null 
	 * and has the provided x and y as coordinates.
	 */
	@Test
	public void testVertexIntInt() {
		int x = 4;
		int y = 5;
		
		Vertex vertex = new Vertex(x, y);

		assertNotNull(vertex);
		assertTrue(vertex.getX() == x);
		assertTrue(vertex.getY() == y);
	}


	/**
	 * Test the conversion to string method. <p>
	 * 
	 * Makes sure that the returned string is neither 
	 * null nor empty.
	 */
	@Test
	public void testToString() {
		Vertex vertex = new Vertex();
		
		assertNotNull(vertex.toString());
		assertTrue(vertex.toString().length() > 0);
	}


	/**
	 * Test the equality comparison method. <p>
	 * 
	 * Makes sure that the comparison returns true
	 * when two different vertices with the same coordinates
	 * are compared to each other and returns false if they
	 * have different coordinates. <p>
	 * 
	 * Tests that the result is the same if A is 
	 * compared to B and if B is compared to A. <p>
	 * 
	 * Tests that a vertex comparing to itself always return true.
	 * Tests that a comparison to null always returns false.
	 */
	@Test
	public void testEqualsObject() {
		Vertex vertex00 = new Vertex(0,0);
		Vertex vertex00bis = new Vertex(0,0);
		Vertex vertex05 = new Vertex(0,5);
		Vertex vertex50 = new Vertex(5,0);

		assertTrue(vertex00.equals(vertex00));
		assertTrue(vertex00.equals(vertex00bis));
		assertTrue(vertex00bis.equals(vertex00));
		
		assertFalse(vertex00.equals(vertex05));
		assertFalse(vertex05.equals(vertex00));
		
		assertFalse(vertex00.equals(vertex50));
		assertFalse(vertex50.equals(vertex00));
		
		assertFalse(vertex50.equals(null));
	}

	
	/**
	 * Test the distance-based comparison method. <p>
	 * 
	 * Makes sure that the method always returns the
	 * grid based distance between any two vertices
	 * in any order. <p>
	 * 
	 * Tests that the distance between a vertex and 
	 * itself is 0.
	 * Comparisons to null are not handled.
	 */
	@Test
	public void testCompareTo() {
		Vertex vertex00 = new Vertex(0,0);
		Vertex vertex00bis = new Vertex(0,0);
		Vertex vertex05 = new Vertex(0,5);
		Vertex vertex50 = new Vertex(5,0);
		Vertex vertex55 = new Vertex(5,5);

		assertTrue(vertex00.compareTo(vertex00) == 0);
		assertTrue(vertex00.compareTo(vertex00bis) == 0);
		assertTrue(vertex00.compareTo(vertex05) == 5);
		assertTrue(vertex00.compareTo(vertex50) == 5);
		assertTrue(vertex00.compareTo(vertex55) == 10);
		assertTrue(vertex55.compareTo(vertex00) == 10);
	}

	
	/**
	 * Test the getter for the 'x' field.
	 */
	@Test
	public void testGetX() {
		Vertex vertex = new Vertex(5,6);
		
		assertTrue(vertex.getX() == 5);
	}

	
	/**
	 * Test the setter for the 'x' field.
	 */
	@Test
	public void testSetX() {
		Vertex vertex = new Vertex(5,6);
		vertex.setX(1);
		
		assertTrue(vertex.getX() == 1);
	}


	/**
	 * Test the getter for the 'y' field.
	 */
	@Test
	public void testGetY() {
		Vertex vertex = new Vertex(5,6);
		
		assertTrue(vertex.getY() == 6);
	}


	/**
	 * Test the setter for the 'y' field.
	 */
	@Test
	public void testSetY() {
		Vertex vertex = new Vertex(5,6);
		vertex.setY(2);
		
		assertTrue(vertex.getY() == 2);
	}


	/**
	 * Test the getter for the 'nbr' field.
	 */
	@Test
	public void testGetNbr() {
		Vertex vertex = new Vertex();
		
		assertTrue(vertex.getNbr() == 0);
	}


	/**
	 * Test the setter for the 'nbr' field.
	 */
	@Test
	public void testSetNbr() {
		Vertex vertex = new Vertex();
		vertex.setNbr(5);
		
		assertTrue(vertex.getNbr() == 5);
	}

}
