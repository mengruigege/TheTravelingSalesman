/**
 * <pre>
 * Name: Rui Meng
 * Mrs. Kankelborg
 * Period 1
 * Project 1 Traveling Salesman
 * Last Revised on: 1/2/2024 
 * </pre>
 */
public class Tour
{
    /** 
    * Internal Node class - you may add constructors
    */
    private class Node
    {
        private Point data;
        private Node next;
    }
    
    /**
    * Required fields
    */
    private Node home;
    private int size;
    
    /**
     * Creates an empty tour.
     */
    public Tour()
    {
    	home = null;
    	size = 0;
    }

    /**
     * Creates the 4-point tour a->b->c->d->a (for debugging)
     */
    public Tour(Point a, Point b, Point c, Point d)
    {
    	home.data = a;
    	home.next.next = new Node();
    	
    	home.next.data = b;
    	home.next.next.next = new Node();
    	
    	home.next.next.data = c;
    	home.next.next.next.next = new Node();
    	
    	home.next.next.next.data = d;
    	home.next.next.next.next = new Node();
    	
    	home.next.next.next.next = home;
    	home.next.next = new Node();
    	
    	size = 4;
    }
    
    /**
     * Returns the number of points in this tour.
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns the length of this tour.
     */
    public double length()
    {
 	    double totalLength = 0;
        Node current = home;
        
        while (current != null && current.next != home) {
            totalLength += current.data.distanceTo(current.next.data);
            current = current.next;
        }
        if (current != null) {
            totalLength += current.data.distanceTo(home.data);
        }
        
        return totalLength;
    }

    /**
     * Returns a string representation of this tour.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node current = home;
        while (current != null && current.next != home) {
            sb.append(current.data.toString()).append(" -> ");
            current = current.next;
        }
        if (current != null) {
            sb.append(current.data.toString()).append(" -> ");
            sb.append(home.data.toString()); // Closing the loop
        }
        return sb.toString();
    
    }

    /**
     * Draws this tour to standard drawing.
     */
    public void draw()
    {
    	Node current = home;
        while (current != null && current.next != home) {
            current.data.drawTo(current.next.data);
            current = current.next;
        }
        if (current != null) {
            current.data.drawTo(home.data); // Closing the loop
        }
    }

    /**
     * Inserts p into the tour using the nearest neighbor heuristic.
     */
    public void insertNearest(Point p)
    {
    	
    }

    /**
     * Inserts p into the tour using the smallest increase heuristic.
     */
    public void insertSmallest(Point p)
    {
    }
}
