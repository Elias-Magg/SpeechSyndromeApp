package com.example.speechsyndromeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayouteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<MainRecyclerItem> exampleList = new ArrayList<>();
        exampleList.add(new MainRecyclerButton("Line 1","sgwgwewerg"));
        exampleList.add(new MainRecyclerLettering("------------a-------------"));
        exampleList.add(new MainRecyclerButton("Line 1","sgwgwewerg"));
        exampleList.add(new MainRecyclerLettering("------------a-------------"));
        exampleList.add(new MainRecyclerButton("Line 2","sgwgwewerg"));
        exampleList.add(new MainRecyclerLettering("------------a-------------"));
        exampleList.add(new MainRecyclerButton("Line 3","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 4","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 5","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 6","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 7","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 8","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 9","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 0","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 10","sgwgwewerg"));
        exampleList.add(new MainRecyclerButton("Line 11","sgwgwewerg"));

        mRecyclerView = findViewById(R.id.main_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayouteManager = new LinearLayoutManager(this);
        mAdapter = new MainRecyclerViewAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayouteManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}