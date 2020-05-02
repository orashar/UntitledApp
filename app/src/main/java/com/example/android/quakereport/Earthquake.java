package com.example.android.quakereport;

public final class Earthquake {


    private String mUrl;

    private String mMagnitude;
    private String murlimage;

    private String  mdes ;

    public Earthquake(String magnitude, String url,String des,String urlimage) {
        mMagnitude = magnitude;
        mUrl = url;
        mdes = des ;
        murlimage = urlimage ;
    }


    public String getMagnitude() {
        return mMagnitude;
    }


    public String getUrl() {
        return mUrl;
    }
    public String imageUrl() {
        return murlimage;
    }

    public  String descriptions() {
        return mdes;
    }


}
