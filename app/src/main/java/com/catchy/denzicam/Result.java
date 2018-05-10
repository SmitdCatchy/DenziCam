package com.catchy.denzicam;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Result {

    private String name;
    private int average;
    private float percentage;
    private float oxygen;
    private float carbon;
    private double latitude;
    private double longitude;
    private Date date;

    Result(String n, int avg, float per, float oxy, float car, double lat, double lon, Date d){

        name = n;
        average = avg;
        percentage = per;
        oxygen = oxy;
        carbon = car;
        latitude = lat;
        longitude = lon;
        date = d;

    }

    Result(String raw){
        List<String> data = Arrays.asList(raw.split("\\s*,\\s*"));
        name = data.get(0);
        average = Integer.parseInt(data.get(1));
        percentage = Float.parseFloat(data.get(2));
        oxygen = Float.parseFloat(data.get(3));
        carbon = Float.parseFloat(data.get(4));
        latitude = Double.parseDouble(data.get(5));
        longitude = Double.parseDouble(data.get(6));
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
        try {
            date =  df.parse(data.get(7));
        } catch (ParseException e) {
            date = null;
        }

    }

    public String toString(){
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
        String sDate =  df.format(date);
        return name + ",\n" +
            average + "," +
            percentage + "," +
            oxygen + "," +
            carbon + "," +
            latitude + "," +
            longitude + "," +
                sDate +
            ";\n";
    }
    public String getStringResult(){
        String toReturn = "";
        toReturn += oxygen + " kg kibocsátott oxigén és\n";
        toReturn += carbon + " kg megkötött széndioxid.";
        toReturn += "\n";
        if(latitude != 0 || longitude != 0){
            toReturn += "Szélesség: " + latitude + ",\n";
            toReturn += "Hosszúság: " + longitude + ".";
        }
        return toReturn;
    }

    public String getStringDate(){
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
        return df.format(date);
    }

    public String getName() {
        return name;
    }

    public int getAverage() {
        return average;
    }

    public Date getDate() {
        return date;
    }
}
