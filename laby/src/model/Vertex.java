package model;

import java.util.Objects;

/**
 * Class representing a vertex in a graph.
 * Is comparable to other instances of this class. <p>
 * 
 * A few methods implemented in this make strong usage of methods 
 * that are provided by the following classes for the purpose of 
 * comparison between objects of a same class :
 * https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html
 * 
 * @author Teiki Pepin
 */

public class Vertex implements Comparable<Vertex> {
	
	/**
	 * The x coordinate for the vertex.
	 */
	private int x;
	
	/**
	 * The y coordinate for the vertex.
	 */
	private int y;
	
	/**
	 * Number used to mark vertices through the Manhattan algorithm
	 */
	private int nbr;
	
	
	/**
	 * Allocates a new Vertex with the position 0 for the x coordinate and 0 for the y coordinate.
	 */
	public Vertex() {
		this(0, 0);
	}
	
	
	/**
	 * Allocates a new Vertex with the x and y coordinates given.
	 * 
	 * @param	x	the x coordinate we want for the vertex
	 * @param	y	the y coordinate we want for the vertex
	 */
	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Gets a string representation of this vertex.
	 * Overrides the toString() method.
	 * 
	 * @return		a string value of this vertex's coordinates in the following format : "(x,y)"
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y +")";
	}
	
	
	/**
	 * Compares an object if it is a Vertex with this one.
	 * If they both have the same position on x and y coordinate, it returns true.
	 * If they don't have the same position on x and y coordinate, it returns false.
	 * If the object is not a Vertex it returns false too.
	 * 
	 * @return 		boolean value which is true if the vertices are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex)
			if (this.x == ((Vertex) obj).getX() && this.y == ((Vertex) obj).getY())
				return true;
		
		return false;		
	}

	/**
	 * Method returning the hashed value for an object which is used
	 * for comparisons. Makes uses of the hashing method provided 
	 * by Objects.
	 * 
	 * @return 		the reference of an object.
	 * @see java.util.Objects
	 */
	@Override
	public int hashCode() {
		//used by SimpleGraph.containsVertex()
		return Objects.hash(x, y);
	}

	
	/**
	 * Compares a vertex to another.
	 * 
	 * @param 	o 	the vertex to be compared to.
	 * @return 		the distance between the two vertices.
	 */
	@Override
	public int compareTo(Vertex o) {
		//used by Edge.compareTo()		
		return java.lang.Math.abs(this.x-o.getX()) + java.lang.Math.abs(this.y-o.getY());
	}
	
	
	/**
	 * Gets the x coordinate.
	 * 
	 * @return the x coordinate.
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * Sets the new x coordinate.
	 * 
	 * @param x the new x coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	
	/**
	 * Gets the y coordinate.
	 * 
	 * @return the y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * Sets the new y coordinate.
	 * @param y the new y coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	
	/**
	 * return the mark number of a vertex
	 * 
	 * @return the mark number
	 */
    public int getNbr() {
        return nbr;
    }

    
	/**
	 * Set the mark number of a vertex trough the manhattan algorithm
	 * 
	 * @param nbr the mark number to set to.
	 */
	public void setNbr(int nbr) {
        this.nbr = nbr;
    }
}
