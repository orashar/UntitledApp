package com.example.android.quakereport;

import java.util.ArrayList;

class ParentObject {

    String title;
    ArrayList<ChildObject> childList;

    public ParentObject(String title, ArrayList<ChildObject> childList) {
        this.title = title;
        this.childList = childList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChildObject> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<ChildObject> childList) {
        this.childList = childList;
    }
}
