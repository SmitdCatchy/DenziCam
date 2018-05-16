package com.catchy.denzicam;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChartActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testClassTree(){
        Tree tested = new Tree("Akác", 75);

        assertEquals(22, tested.getAge(), 2 );
        assertEquals(100.1f, Tree.getOxygen(75), 2);
        assertEquals(134.3f, Tree.getCarbon(75), 2 );
    }

    @After
    public void tearDown() throws Exception {
    }
}