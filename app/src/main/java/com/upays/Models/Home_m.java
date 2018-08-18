package com.upays.Models;

/**
 * Created by user on 15-08-2018.
 */

public class Home_m {
    int image;
    String homename;

    /*Home_m(int image, String homename) {
        this.image = image;
        this.homename = homename;
    }*/
    public Home_m(String homename) {
        //this.image = image;
        this.homename = homename;
    }

    public int getImage() {
        return image;
    }

    public String getHomename() {
        return homename;
    }


}
