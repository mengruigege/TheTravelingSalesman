import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TourTest
{
    private Tour emptyTour;
    private Tour fourPointTour;
    private Point a, b, c, d;
    
    @Before
    public void setUp() {
        emptyTour = new Tour();
        a = new Point(0, 0);
        b = new Point(0, 1);
        c = new Point(1, 1);
        d = new Point(1, 0);
        fourPointTour = new Tour(a, b, c, d);
    }

    @Test
    public void testSizeEmpty() {
        assertEquals("Expected size to be 0 for an empty tour", 0, emptyTour.size());
    }

    @Test
    public void testSizeFourPoints() {
        assertEquals("Expected size to be 4 for a four-point tour", 4, fourPointTour.size());
    }

    @Test
    public void testLengthEmpty() {
        assertEquals("Expected length to be 0.0 for an empty tour", 0.0, emptyTour.length(), 0.0);
    }

    @Test
    public void testLengthFourPoints() {
        double expectedLength = 4.0; // Assuming each side of the square is of length 1
        assertEquals("Expected length to be 4.0 for a four-point tour", expectedLength, fourPointTour.length(), 0.0);
    }

    @Test
    public void testToStringEmpty() {
        assertNull("Expected string representation to be null for an empty tour", emptyTour.toString());
    }

    @Test
    public void testToStringFourPoints() {
        String expected = a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n" + d.toString() + "\n";
        assertEquals("Expected specific string representation for a four-point tour", expected, fourPointTour.toString());
    }
    
    @Test
    public void testInsertNearestEmpty() {
        Point newPoint = new Point(2, 2);
        emptyTour.insertNearest(newPoint);
        assertEquals("Expected size to be 1 after inserting into an empty tour", 1, emptyTour.size());
        String expectedString = newPoint.toString() + "\n";
        assertEquals("Expected string representation after inserting one point", expectedString, emptyTour.toString());
    }

    @Test
    public void testInsertNearestNonEmpty() {
        Point newPoint = new Point(0, 0.5); // Closest to Point a
        fourPointTour.insertNearest(newPoint);
        assertEquals("Expected size to increase by 1 after inserting into four-point tour", 5, fourPointTour.size());
        // Additional checks can be added here to verify the correct insertion position
    }

    @Test
    public void testInsertSmallestEmpty() {
        Point newPoint = new Point(2, 2);
        emptyTour.insertSmallest(newPoint);
        assertEquals("Expected size to be 1 after inserting into an empty tour", 1, emptyTour.size());
        String expectedString = newPoint.toString() + "\n";
        assertEquals("Expected string representation after inserting one point", expectedString, emptyTour.toString());
    }

    @Test
    public void testInsertSmallestNonEmpty() {
        Point newPoint = new Point(0, 0.5); // Expected to cause the smallest increase when inserted next to Point a
        fourPointTour.insertSmallest(newPoint);
        assertEquals("Expected size to increase by 1 after inserting into four-point tour", 5, fourPointTour.size());
        // Additional checks can be added here to verify the correct insertion position
    }

    // Add more tests here.
}
