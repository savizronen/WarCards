package com.example.warcards.objects;

import android.location.Location;

public class Winner implements Comparable<Winner> {

    private String name;
    private int score;
    private int imgIndex;
    private double latitude;
    private double longitude;
    private String date;

    // ================================================================

    public Winner(String name, int score, int imgIndex, Location location, String date){
        this.name = name;
        this.score = score;
        this.imgIndex = imgIndex;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.date = date;
    }

    // ================================================================

    @Override
    public int compareTo(Winner otherWinner) {
        if(this.getScore() > otherWinner.getScore())
            return -1;
        if(this.getScore() < otherWinner.getScore())
            return 1;
        return 0;
    }

    // ================================================================

    public String getName() { return name; }

    public String getDate() { return date; }

    public int getScore() { return score; }

    public int getImgIndex() { return imgIndex; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

}
