package com.example.android.quakereport;

class ChildObject {

    String title;
    String ImageUrl;

    public ChildObject(String title, String imageUrl) {
        this.title = title;
        ImageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
