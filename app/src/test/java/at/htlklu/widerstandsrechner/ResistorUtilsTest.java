package at.htlklu.widerstandsrechner;

import junit.framework.TestCase;

import org.junit.Test;

public class ResistorUtilsTest extends TestCase {

    @Test
    public void calcParallel() {

        int r1 = 300;
        int r2 = 300;
        int r3 = 300;

        double expected = 100;

        //assertEquals(expected, ResistorUtils.calcParallel(r1, r2, r3), 0,1);
    }

    @Test
    public void calcSeriel() {

        int r1 = 100;
        int r2 = 200;
        int r3 = 300;

        int expected = 600;

        //assertEquals(expected, ResistorUtils.calcSeriel(r1, r2, r3));
    }
}