
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGeoCountDownTimer {


    /**
     * The following are simple JUnit test cases... After talking with your instructor, create
     * many, many more that shows that your code is functioning correctly.
     */

    @Test
    public void testConstructor1() {
        GeoCountDownTimer s = new GeoCountDownTimer(2121, 5, 10);
        assertEquals(s.toDateString(), "5/10/2121");

        s = new GeoCountDownTimer(2121, 1, 11);
        assertEquals(s.toDateString(), "1/11/2121");

        s = new GeoCountDownTimer(2121, 12, 10);
        assertEquals(s.toDateString(), "12/10/2121");

        s = new GeoCountDownTimer(2023, 11, 10);
        assertEquals(s.toDateString(), "11/10/2023");

    }

    @Test
    public void testConstructor2() {
        GeoCountDownTimer s = new GeoCountDownTimer("5/10/2121");
        assertTrue(s.toDateString().equals("5/10/2121"));

        s = new GeoCountDownTimer("12/10/2121");
        assertEquals(s.toDateString(), "12/10/2121");

        s = new GeoCountDownTimer("11/10/2023");
        assertEquals(s.toDateString(), "11/10/2023");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        GeoCountDownTimer s = new GeoCountDownTimer("2/29/2121");
        assertTrue(s.toDateString().equals("2/29/2020"));

        s = new GeoCountDownTimer("11/10/2020");
        assertTrue(s.toDateString().equals("11/10/2020"));

        s = new GeoCountDownTimer("13/11/2121");
        assertTrue(s.toDateString().equals("13/11/2121"));

        s = new GeoCountDownTimer("1/50/2121");
        assertTrue(s.toDateString().equals("1/50/2121"));

        s = new GeoCountDownTimer("-11/11/2121");
        assertTrue(s.toDateString().equals("-11/11/2121"));

        s = new GeoCountDownTimer("11/-11/2121");
        assertTrue(s.toDateString().equals("11/-11/2121"));

        s = new GeoCountDownTimer("11/11/-2121");
        assertTrue(s.toDateString().equals("11/11/-2121"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor4() {
        GeoCountDownTimer s = new GeoCountDownTimer(2121,2,29);
        assertTrue(s.toDateString().equals("2/29/2020"));

        s = new GeoCountDownTimer(2020,11,20);
        assertTrue(s.toDateString().equals("11/10/2020"));

        s = new GeoCountDownTimer(2121,13,11);
        assertTrue(s.toDateString().equals("13/11/2121"));

        s = new GeoCountDownTimer(2121,1,50);
        assertTrue(s.toDateString().equals("1/50/2121"));

        s = new GeoCountDownTimer(2121, -1, 11);
        assertTrue(s.toDateString().equals("-1/11/2121") );

        s = new GeoCountDownTimer(2121, 1, -11);
        assertTrue(s.toDateString().equals("1/-11/2121") );

        s = new GeoCountDownTimer(-2121, 1, 11);
        assertTrue(s.toDateString().equals("1/11/-2121") );
    }


    @Test
    public void testAddMethod() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 5, 10);
        s1.inc(365);
        assertTrue(s1.toDateString().equals("5/10/2122"));

        s1 = new GeoCountDownTimer(2120, 1, 10);

        for (int i = 0; i < 366; i++)
            s1.inc();
        assertTrue(s1.toDateString().equals("1/10/2121"));

        s1 = new GeoCountDownTimer(2022, 5, 10);

        for (int i = 0; i < 31665; i++)
            s1.inc();

        for (int i = 0; i < 31665; i++)
            s1.dec();

        assertTrue(s1.toDateString().equals("5/10/2022"));

    }


    // must have a separate test for each Exception
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor1() {
        new GeoCountDownTimer(2, -3, -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContuctor2() {
        new GeoCountDownTimer("2,-3/-3");

    }

    @Test
    public void testEqual() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(3000, 5, 9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 6, 1);
        GeoCountDownTimer s3 = new GeoCountDownTimer(2121, 5, 5);
        GeoCountDownTimer s4 = new GeoCountDownTimer(3000, 5, 9);
        GeoCountDownTimer s5 = new GeoCountDownTimer(3000, 5, 9);

        assertEquals(s4, s5);
        assertFalse(s1.equals(s2));
        assertTrue(s1.equals(s4));

    }

    @Test
    public void testCompareTo() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 5, 9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 6, 1);
        GeoCountDownTimer s3 = new GeoCountDownTimer(2121, 5, 8);
        GeoCountDownTimer s4 = new GeoCountDownTimer(2121, 5, 9);

        assertTrue(s2.compareTo(s1) > 0);
         assertTrue(s3.compareTo(s1) < 0);
        assertTrue(s1.compareTo(s4) == 0);

    }

    @Test
    public void testLoadSave() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 9, 1);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 9, 1);

        s1.save("file1");
        s1 = new GeoCountDownTimer(2111, 1, 1);  // resets
        s1.load("file1");
        assertTrue(s2.equals(s1));

    }

    @Test
    public void testDaysToGo() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 2, 9);
        assertTrue(s1.daysToGo("2/1/2121") == 8);
        assertTrue(s1.daysToGo("2/8/2121") == 1);
        assertTrue(s1.daysToGo("2/9/2121") == 0);

    }

    @Test
    public void testDaysInFuture () {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 12,9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 12,19);

        assertTrue (s1.daysInFuture(10).equals(s2));

    }
}

