package com.example.android.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView parentrv;
    private ParentRVAdapter parentRVAdapter;
    private ArrayList<ParentObject> parentList;
    private ArrayList<String> childrens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentrv = findViewById(R.id.parent_rv);
        childrens = new ArrayList<>();
        childrens.add("corona");
        childrens.add("politics");
        childrens.add("polution");
        childrens.add("entertainment");
        childrens.add("flood");

        parentList = new ArrayList<>();
        getParentList();

        parentRVAdapter = new ParentRVAdapter(this, parentList);

        parentrv.setLayoutManager(new LinearLayoutManager(this));
        parentrv.setHasFixedSize(true);
        parentrv.setAdapter(parentRVAdapter);
    }

    private void getParentList() {
        for(int i = 0; i < 5; i++){
            ArrayList<ChildObject> childList = new ArrayList<>();
            for(String childTitle : childrens){
                childList.add(new ChildObject(childTitle, null));
            }
            parentList.add(new ParentObject("Parent title " + i, childList));
            Log.v("ParentListAdd", "parent item " + i);
        }
    }
}
