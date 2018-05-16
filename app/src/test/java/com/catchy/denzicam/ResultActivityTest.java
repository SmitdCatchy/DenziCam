package com.catchy.denzicam;

import android.annotation.SuppressLint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ResultActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testClassResult(){

        Date current = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
        String sDate =  df.format(current);
        Result tested = new Result( "Test", 45, 75, 80, 80, 1, 1, current);

        assertEquals("Test,\n45,75.0,80.0,80.0,1.0,1.0,"+ sDate + ";\n", tested.toString());
        assertEquals("80.0 kg kibocsátott oxigén és\n80.0 kg megkötött széndioxid.\nSzélesség: 1.0,\nHosszúság: 1.0.", tested.getStringResult());
        assertEquals( sDate, tested.getStringDate());
    }

    @After
    public void tearDown() throws Exception {
    }
}