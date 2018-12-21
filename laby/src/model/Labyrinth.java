package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Create the graph representing the labyrinth using the SimpleGraph class. Also
 * contains all the required methods to check what contains the labyrinth as
 * well as the Manhattan algorithm.
 *
 * @author	Pierre Lorson, Teiki Pepin
 */

public class Labyrinth {
	
    public enum Directions {
        NORTH,
        EAST,
        SOUTH,
        WEST,
    }

	private final static int NORTH_BORDER = 0;
	private final static int EAST_BORDER = 15;
	private final static int SOUTH_BORDER = 15;
	private final static int WEST_BORDER = 0;

	private static Labyrinth instance = new Labyrinth();

	private SimpleGraph<Vertex, Edge> labyrinth;

    /**
     * Create a Labyrinth. <p>
     *
     * Warning : as we're using the Singleton design pattern,
     * this method should not be called directly. Use getInstance instead.
     */

    private Labyrinth() {
        labyrinth = new SimpleGraph(Edge.class);

        Vertex startingCell = new Vertex();
        labyrinth.addVertex(startingCell);

        buildLabyrinth(startingCell);
    }


    /**
     * Updates the graph representing the labyrinth by building a perfect labyrinth
     * that contains only a single path between the same two cells. The generated
     * paths is always created randomly from the starting cell until all cells are
     * linked together.
     *
     * @param	vertex	the starting vertex from which the rest of the labyrinth will be generated
     */
	private void buildLabyrinth(Vertex vertex) {
		int x = vertex.getX();
		int y = vertex.getY();

		int[] ordre = new int[4];

		ordre[0] = ThreadLocalRandom.current().nextInt(0, 4);

		do {
			ordre[1] = ThreadLocalRandom.current().nextInt(0, 4);
		} while (ordre[1] == ordre[0]);

		do {
			ordre[2] = ThreadLocalRandom.current().nextInt(0, 4);
		} while (ordre[2] == ordre[0] || ordre[2] == ordre[1]);

		do {
			ordre[3] = ThreadLocalRandom.current().nextInt(0, 4);
		} while (ordre[3] == ordre[0] || ordre[3] == ordre[1] || ordre[3] == ordre[2]);


		for (int i=0; i<4; i++) {
			Vertex newVertex = null;

			switch (ordre[i]) {
				case 0:
					if (!(labyrinth.containsVertex(new Vertex(x, y-1))) && y > NORTH_BORDER)
						newVertex = new Vertex(x, y-1);
					break;

				case 1:
					if (!(labyrinth.containsVertex(new Vertex(x+1, y))) && x < EAST_BORDER)
						newVertex = new Vertex(x+1, y);
					break;

				case 2:
					if (!(labyrinth.containsVertex(new Vertex(x, y+1))) && y < SOUTH_BORDER)
						newVertex = new Vertex(x, y+1);
					break;

				case 3:
					if (!(labyrinth.containsVertex(new Vertex(x-1, y))) && x > WEST_BORDER)
						newVertex = new Vertex(x-1, y);
					break;

				default:
					System.out.println("Error in model.Labyrinth.buildLabyrinth in switch.");
					break;
			}

			if (newVertex != null) {
				labyrinth.addVertex(newVertex);
				labyrinth.addEdge(vertex, newVertex);
				buildLabyrinth(newVertex);
			}
		}
	}

    /**
     * Returns a vertex from the vertices set.
     * Returns null if the vertex is not within the set.
     *
     * @param 	x 	coordinate on the horizontal axis
     * @param 	y 	coordinate on the vertical axis
     * @return 		the corresponding vertex, if found
     */
    public model.Vertex getVertex(int x, int y) {
        Set<Vertex> setVertex = labyrinth.vertexSet();
        for (Vertex vertex : setVertex) {
            if (vertex.getX() == x && vertex.getY() == y)
                return vertex;
        }
        return null;
    }

    /**
     * Calculates the Manhattan distance using the Manhattan algorithm
     * between two vertices. <p>
     *
     * Should not be called. Use launchManhattan instead.
     *
     * @param 	source	starting vertex
     * @param 	target 	goal vertex
     */
    private void calculateManhattanDistance(Vertex source, Vertex target) {
        Queue<Vertex> fifo = new ArrayDeque<Vertex>();
        target.setNbr(1);
        fifo.add(target);
        while (!fifo.isEmpty()) {
            Vertex actual = fifo.remove();
            for (Directions dir : Directions.values()) {
                if (this.isOpened(actual, dir)) {
                    Vertex next = getVertexByDir(actual, dir);
                    if (next.getNbr() == 0) {
                        next.setNbr(actual.getNbr()+1);
                        if (next != source)
                            fifo.add(next);
                    }
                }
            }
        }
    }

    /**
     * Prepares the vertices for a Manhattan algorithm sweep through and
     * launches the algorithm.
     *
     * @param 	source	starting vertex
     * @param 	target 	goal vertex
     */
    public void launchManhattan(Vertex source, Vertex target) {
        for (Vertex vertex : labyrinth.vertexSet())
            vertex.setNbr(0);

        calculateManhattanDistance(source, target);
    }
    /**
     * Check if there is a wall in the specified direction from
     * a given vertex.
     *
     * @param	vertex	checked vertex
     * @param	dir		checked direction
     * @return			a boolean which is true if there is a wall, false otherwise
     */
	public boolean isWall(Vertex vertex,Directions dir) {
		Vertex dest = getVertexByDir(vertex, dir);
		Edge edge=labyrinth.getEdge(vertex,dest);
		return (edge==null);
	}

    /**
     * Check if the player can go in the specified direction from
     * a given vertex or if something is blocking the way.
     *
     * @param	vertex	starting vertex
     * @param	dir		checked direction
     * @return			a boolean which is true if there is an obstacle, false otherwise
     */
	public boolean isClosed(Vertex vertex,Directions dir){
		Vertex dest = getVertexByDir(vertex, dir);
		Edge edge=labyrinth.getEdge(vertex,dest);
		return (edge==null || (edge.getType()==Edge.Type.CLOSED_DOOR));
	}

    /**
     * Check if the player can go in the specified direction from
     * a given vertex or if something is blocking the way.
     *
     * @param	vertex	starting vertex
     * @param	dir		checked direction
     * @return			a boolean which is true if there is no obstacle, false otherwise
     */

    public boolean isOpened(Vertex vertex, Directions dir){
        Vertex dest = getVertexByDir(vertex,dir);
        Edge edge = labyrinth.getEdge(vertex,dest);
        return ((edge!=null) && ((edge.getType()!=Edge.Type.CLOSED_DOOR)));
    }


    /**
     * Check if there is a closed door at the specified direction from
     * the given vertex.
     *
     * @param	vertex	starting vertex
     * @param	dir		checked direction
     * @return			a boolean which is true if there is a closed door, false otherwise
     */

    public boolean isClosedDoor(Vertex vertex, Directions dir){
        Vertex dest = getVertexByDir(vertex, dir);
        Edge edge = labyrinth.getEdge(vertex,dest);
        return(edge!=null && edge.getType()==Edge.Type.CLOSED_DOOR);
    }


    /**
     * Check if there is an opened door at the specified direction from
     * the given vertex.
     *
     * @param	vertex	starting vertex
     * @param	dir		checked direction
     * @return			a boolean which is true if there is an opened door, false otherwise
     */

    public boolean isOpenedDoor(Vertex vertex,Directions dir){
        Vertex dest = getVertexByDir (vertex, dir);
        Edge edge=labyrinth.getEdge(vertex,dest);
        return ((edge!=null)&&((edge.getType()==Edge.Type.OPENED_DOOR)));
    }

    /**
     * Return the vertex located at the specified direction from the given vertex.
     * Returns null if the target vertex is not found.
     *
     * @param	vertex 	starting vertex
     * @param	dir 	direction from the starting vertex
     * @return			the desired vertex if it exists
     */
	public Vertex getVertexByDir(Vertex vertex, Directions dir){
		Vertex newvertex = null;
		switch (dir){
			case NORTH:
				newvertex = getVertex(vertex.getX(),vertex.getY()+1);
				break;
			case SOUTH:
				newvertex = getVertex(vertex.getX(),vertex.getY()-1);
				break;
			case EAST:
				newvertex = getVertex(vertex.getX()+1,vertex.getY());
				break;
			case WEST:
				newvertex = getVertex(vertex.getX()-1,vertex.getY());
				break;
		}
		return newvertex;
	}


    /**
     * Get an instance of the object Labyrinth.
     * Needed because we are using the Singleton Design Pattern.
     *
     * @return the current instance of this class
     */
	public static Labyrinth getInstance() {
		return instance;
	}

    /**
     * Returns a String version of the content of the labyrinth.
     *
     * @return a string representation of the graph
     */
	@Override
	public String toString() {
		return labyrinth.toString();
	}

    /**
     * Prints a graphical representation of the labyrinth to the console.
     *
     */
	public void printLabyrinth() {
		for (int i=NORTH_BORDER; i<=SOUTH_BORDER; i++) {

			for (int j=WEST_BORDER; j<=EAST_BORDER; j++) {
				if(labyrinth.containsEdge(new Vertex(i, j), new Vertex(i-1, j)))
					System.out.print("|");

				else
					System.out.print(" ");

				System.out.print("  ");
			}

			System.out.println();



			for (int j=WEST_BORDER; j<=EAST_BORDER; j++) {
				System.out.print("o");

				if(labyrinth.containsEdge(new Vertex(i, j), new Vertex(i, j+1)))
					System.out.print("--");

				else
					System.out.print("  ");
			}

			System.out.println();
		}
	}

    /**
     * Check if two vertices are connected by verifying if the labyrinth
     * contains a edge between them.
     *
     * @param 	xs	Horizontal localisation of the source vertex
     * @param 	ys	Vertical localisation of the source vertex
     * @param 	xt	Horizontal localisation of the target vertex
     * @param	yt	Vertical localisation of the target vertex
     * @return		True if the vertices are connected, false if they're not.
     */

    public boolean areVerticesConnected(int xs, int ys, int xt, int yt) {
		return labyrinth.containsEdge(new Vertex(xs, ys), new Vertex(xt, yt));
	}

    /**
     * Return the set of vertices contained in the labyrinth.
     *
     * @return a vertex set.
     */
	public Set<Vertex> getVertices() {
		return labyrinth.vertexSet();
	}

    /**
     * Return the set of edges contained in the labyrinth.
     *
     * @return a edge set.
     */
	public Set<Edge> getPaths() {
		return labyrinth.edgeSet();
	}

    /**
     * Return all the walls contained in the labyrinth.
     *
     * @return a vertex list set.
     */

    public Set<List<Vertex>> getWalls() {
		Set<List<Vertex>> walls = new HashSet<List<Vertex>>();

		for (int y = NORTH_BORDER; y <= SOUTH_BORDER; y++) {
			for (int x = WEST_BORDER; x <= EAST_BORDER; x++) {
				Vertex currentCell = new Vertex(x, y);
				Vertex eastCell = new Vertex(x, y+1);
				Vertex southCell = new Vertex(x+1, y);

				if (labyrinth.containsVertex(southCell) && !labyrinth.containsEdge(currentCell, southCell))
					walls.add(Arrays.asList(currentCell, southCell));

				if (labyrinth.containsVertex(eastCell) && !labyrinth.containsEdge(currentCell, eastCell))
					walls.add(Arrays.asList(currentCell, eastCell));
			}
		}

		return walls;
	}

    /**
     * Opens a random door in order to change the difficulty of the game.
     */
    public void openDoorRandom(){
        //On essaie 1000 fois, apres quoi on renonce
        for (int i=1 ; i<=1000 ; ++i){
            //On choisit un sommet au hasard
            Vertex vertex = randomVertex();
            if (vertex!=null){
                //On choisit une direction au hasard(on devrait prendre seulement
                //celles qui correspondent a des murs...)
                Labyrinth.Directions dir= Directions.values()[ThreadLocalRandom.current().nextInt(Directions.values().length)];
                if (isWall(vertex,dir)){
                    Vertex vertex2 = getVertexByDir(vertex,dir);
                    if(vertex2!=null){
                        Edge edge=labyrinth.getEdge(vertex,vertex2);
                        if(edge==null){
                            //On ajoute un saut entre ces sommets
                            labyrinth.addEdge(vertex,vertex2);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds a random vertex and returns it.
     *
     * @return a random vertex.
     */
    private Vertex randomVertex(){
        int x = ThreadLocalRandom.current().nextInt(WEST_BORDER, EAST_BORDER+1);
        int y = ThreadLocalRandom.current().nextInt(NORTH_BORDER,SOUTH_BORDER+1);
        return new Vertex(x,y);
    }

    /**
     * A setter used in the JUnit tests. We need it because we're using a Singleton design pattern
     * for the Labyrinth.
     *
     * @param graph a SimpleGraph to be injected in the Labyrinth.
     */
    public void setLabyrinthGraph(SimpleGraph<Vertex, Edge> graph) {
        this.labyrinth = graph;
    }

    /**
     * A getter used in the JUnit tests. We need it because we're using a Singleton design pattern
     * for the Labyrinth.
     *
     * @return a SimpleGraph representing the Labyrinth.
     */
    public SimpleGraph<?, ?> getLabyrinthGraph() {
        return this.labyrinth;
    }

    /**
     * Resets the current instance of this class by calling the constructor once more.
     */
    public void reset(){
        instance = new Labyrinth();
    }

}
