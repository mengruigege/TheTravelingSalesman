/**
 * <pre>
 * Name: Rui Meng
 * Mrs. Kankelborg
 * Period 1
 * Project 1 Traveling Salesman
 * Last Revised on: 1/5/2024 
 * </pre>
 */

public class Tour {
	/** 
    * Internal Node class - you may add constructors
    */
    private class Node
    {   	
        private Point data;
        private Node next;

        public Node(Point data) {
            this.data = data;
            this.next = null;
        }
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
    	// Creating a circular linked list for the four points
        home = new Node(a);
        home.next = new Node(b);
        home.next.next = new Node(c);
        home.next.next.next = new Node(d);
        home.next.next.next.next = home; // Closing the loop
        
        size = 4; // Initializes the size to 4
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
    	double length = 0;
        if (home != null) {
            Node n = home;
            do {
                length += n.data.distanceTo(n.next.data); // Add the distance between consecutive nodes
                n = n.next;
            } while (n != home); // Loop until the tour completes a cycle
        }
        return length;
    }


    /**
     * Returns a string representation of this tour.
     */
    public String toString()
    {
        if (home == null) {
            return null; // Return null for an empty tour
        }

        StringBuilder sb = new StringBuilder();
        Node n = home;
        
        do {
            sb.append(n.data.toString());
            sb.append("\n"); // Append a newline after each point
            n = n.next;
        } while (n != home);

        return sb.toString();
    }

    /**
     * Draws this tour to standard drawing.
     */
    public void draw()
    {
        if (home != null) {
            Node n = home;
            
            do {
                n.data.drawTo(n.next.data);
                n = n.next;
            } while (n != home);
        }
    }    

    /**
     * Inserts p into the tour using the nearest neighbor heuristic.
     */
    public void insertNearest(Point p)
    {
        if (home == null) {
            // If the tour is empty, create a new node with the point and point it to itself
            home = new Node(p);
            home.next = home;
            size = 1;
        } else {
            Node nearest = home; // Start with the home node as the nearest
            Node n = home; // Current node for iteration
            double minD = Double.MAX_VALUE; // Track the minimum distance found

            do {
                // Calculate the distance from the current node to the new point
                double dis = n.data.distanceTo(p);
                // If this distance is the smallest, update the nearest node and minD
                if (dis < minD) {
                    minD = dis;
                    nearest = n;
                }
                n = n.next; // Move to the next node
            } while (n != home); // Continue until the tour completes a cycle

            // Create a new node for the point and insert it after the nearest node
            Node newNode = new Node(p);
            newNode.next = nearest.next;
            nearest.next = newNode;
            size++; // Increase the size of the tour
        }
    }

    /**
     * Inserts p into the tour using the smallest increase heuristic.
     */
     public void insertSmallest(Point p)
     {
    	    if (home == null) {
    	        // If the tour is empty, create a new node and point it to itself
    	        home = new Node(p);
    	        home.next = home;
    	        size = 1;
    	    } else {
    	        Node minNode = home; // Start with the home node as the insertion point
    	        Node n = home; // Current node for iteration
    	        double minD = Double.MAX_VALUE; // Track the smallest distance increase found

    	        do {
    	            // Calculate the increase in distance if the point is inserted between current node and the next node
    	            double dis = n.data.distanceTo(p) + p.distanceTo(n.next.data) - n.data.distanceTo(n.next.data);
    	            // If this increase is the smallest, update the insertion point and minD
    	            if (dis < minD) {
    	                minD = dis;
    	                minNode = n;
    	            }
    	            n = n.next; // Move to the next node
    	        } while (n != home); // Continue until the tour completes a cycle

    	        // Create a new node for the point and insert it after the chosen insertion point
    	        Node newNode = new Node(p);
    	        newNode.next = minNode.next;
    	        minNode.next = newNode;
    	        size++; // Increase the size of the tour
    	    }
    	}
}
