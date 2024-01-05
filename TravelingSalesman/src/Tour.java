public class Tour {
    private class Node {
        private Point data;
        private Node next;

        // Constructor for Node
        public Node(Point data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node home;
    private int size;

    public Tour() {
        home = null;
        size = 0;
    }

    public Tour(Point a, Point b, Point c, Point d) {
        home = new Node(a);
        Node bNode = new Node(b);
        Node cNode = new Node(c);
        Node dNode = new Node(d);

        home.next = bNode;
        bNode.next = cNode;
        cNode.next = dNode;
        dNode.next = home;
        
        size = 4;
    }

    public int size() {
        return size;
    }

    public double length() {
        double totalLength = 0;
        if (home != null) {
            Node current = home;
            do {
                totalLength += current.data.distanceTo(current.next.data);
                current = current.next;
            } while (current != home);
        }
        return totalLength;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (home != null) {
            Node current = home;
            do {
                sb.append(current.data.toString());
                if (current.next != home) {
                    sb.append(" -> ");
                }
                current = current.next;
            } while (current != home);
        }
        return sb.toString();
    }

    public void draw() {
        if (home != null) {
            Node current = home;
            do {
                current.data.drawTo(current.next.data);
                current = current.next;
            } while (current != home);
        }
    }

    public void insertNearest(Point p) {
        if (home == null) {
            home = new Node(p);
            home.next = home;
            size = 1;
        } else {
            Node nearest = home;
            Node current = home;
            double minDistance = Double.MAX_VALUE;

            do {
                double distance = current.data.distanceTo(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = current;
                }
                current = current.next;
            } while (current != home);

            Node newNode = new Node(p);
            newNode.next = nearest.next;
            nearest.next = newNode;
            size++;
        }
    }

    public void insertSmallest(Point p) {
        if (home == null) {
            home = new Node(p);
            home.next = home;
            size = 1;
        } else {
            Node smallestIncreaseNode = home;
            Node current = home;
            double smallestIncrease = Double.MAX_VALUE;

            do {
                double increase = current.data.distanceTo(p) + p.distanceTo(current.next.data) - current.data.distanceTo(current.next.data);
                if (increase < smallestIncrease) {
                    smallestIncrease = increase;
                    smallestIncreaseNode = current;
                }
                current = current.next;
            } while (current != home);

            Node newNode = new Node(p);
            newNode.next = smallestIncreaseNode.next;
            smallestIncreaseNode.next = newNode;
            size++;
        }
    }
}
