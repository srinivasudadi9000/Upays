package com.upays.Models;

/**
 * Created by user on 15-08-2018.
 */

public class Markers {
     Double lat,longi;

    /*Home_m(int image, String homename) {
        this.image = image;
        this.homename = homename;
    }*/
    public Markers(Double lat,Double longi) {
        //this.image = image;
        this.lat = lat;
        this.longi = longi;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLongi() {
        return longi;
    }
}
