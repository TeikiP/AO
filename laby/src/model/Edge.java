package model;

import org.jgrapht.graph.DefaultEdge;

/**
 * Class representing the edge of a graph.
 * 
 * @author Teiki Pepin
 */
public class Edge extends DefaultEdge implements Comparable<Edge> {

    /**
     * Enum representing the different possible types for an edge.
     */
    public enum Type {
        OPENED_DOOR,
        CLOSED_DOOR,
        CORRIDOR
        }

    /**
     * Type of the edge
     */
    private Type type;
    

    /**
     * Creates an edge defined by its type
     * 
     * @param type the type of the edge.
     */
    public Edge(Type type) {
        super();
        this.type = type;
    }

    
    /**
     * Creates an default edge being a corridor.
     */
    public Edge() {
        super();
        this.type = Type.CORRIDOR;
    }

    
    /**
     * Returns the source vertex of an edge. It can be any side of the edge.
     * 
     * @return the source Vertex of an edge
     */
    public Vertex getSource() {
        return (Vertex) super.getSource();
    }

    
    /**
     * returns the target vertex of an edge. It can be any side of the edge.
     * 
     * @return the target vertex
     */
    public Vertex getTarget() {
        return (Vertex) super.getTarget();
    }

    
    /**
     * returns the tyoe of the edge according to the enum.
     * 
     * @return Type of the edge.
     */
    public Type getType() {
        return type;
    }

    
    /**
     * Set the type of the Edge.
     * 
     * @param type type of the edge.
     */
    public void setType(Type type) {
        this.type = type;
    }
    

    /**
     * Compares two edges by checking if their sources and targets are the same.
     * 
     * @param o the Edge to be compared to.
     * @return 0 only if the two edges are the same. The number of the source vertex otherwise.
     */
    @Override
    public int compareTo(Edge o) {
        int source = this.getSource().compareTo((o).getSource());
        
        if (source != 0)
            return source;
        else {
            return this.getTarget().compareTo((o).getTarget());
        }
    }
}