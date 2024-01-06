import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TourTest
{
    private Tour emptyTour;
    private Tour fourPointTour;
    private Point a, b, c, d;
    
    @Test
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
        assertEquals(0, emptyTour.size());
    }

    @Test
    public void testSizeFourPoints() {
        assertEquals(4, fourPointTour.size());
    }

    @Test
    public void testLengthEmpty() {
        assertEquals(0.0, emptyTour.length(), 0.0);
    }

    @Test
    public void testLengthFourPoints() {
        // Assuming each side of the square is of length 1
        assertEquals(4.0, fourPointTour.length(), 0.0);
    }

    @Test
    public void testToStringEmpty() {
        assertNull(emptyTour.toString());
    }

    @Test
    public void testToStringFourPoints() {
        String expected = a.toString() + "\n" + b.toString() + "\n" + c.toString() + "\n" + d.toString() + "\n";
        assertEquals(expected, fourPointTour.toString());
    }

    @Test
    public void testInsertNearestEmpty() {
        Point newPoint = new Point(2, 2);
        emptyTour.insertNearest(newPoint);
        assertEquals(1, emptyTour.size());
        assertEquals(newPoint.toString() + "\n", emptyTour.toString());
    }

    @Test
    public void testInsertNearestNonEmpty() {
        Point newPoint = new Point(0, 0.5); // Closest to Point a
        fourPointTour.insertNearest(newPoint);
        assertEquals(5, fourPointTour.size());
    }

    @Test
    public void testInsertSmallestEmpty() {
        Point newPoint = new Point(2, 2);
        emptyTour.insertSmallest(newPoint);
        assertEquals(1, emptyTour.size());
        assertEquals(newPoint.toString() + "\n", emptyTour.toString());
    }

    @Test
    public void testInsertSmallestNonEmpty() {
        Point newPoint = new Point(0, 0.5); // Smallest increase when inserted next to Point a
        fourPointTour.insertSmallest(newPoint);
        assertEquals(5, fourPointTour.size());
    }


    // Add more tests here.
}
