package com.example.speechsyndromeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class SyndromeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SyndromeRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayouteManager;
    private ArrayList<SyndromeRecyclerItem> mCardList;
    private String text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syndrome_activity);

        Intent intent = getIntent();
        SyndromeData data = intent.getParcelableExtra(MainActivity.EXTRA_SYNDROME_DATA);

        mCardList = new ArrayList<>();

        mCardList.add(new SyndromeRecyclerTitle(data.getTitle()));
        mCardList.add(new SyndromeRecyclerCode(data.getCode()));
        ArrayList<Integer> images = data.getImages();
        for(int i = 0; i < images.size(); i = i + 2){
            if((i+2)>images.size()){
                mCardList.add(new SyndromeRecyclerImages(images.get(i),R.drawable.ic_baseline_missing));
                break;
            }
            mCardList.add(new SyndromeRecyclerImages(images.get(i),images.get(i+1)));
        }
        InputStream inputStream = getResources().openRawResource(data.getTextFile());
            try{
                byte[] buffer = new byte[inputStream.available()];
                while(inputStream.read(buffer) != -1){
                    text = new String(buffer);
                }

            } catch (IOException e){
                e.printStackTrace();
            }

            mCardList.add(new SyndromeRecyclerText(text));


        buildRecyclerView();

    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.syndrome_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayouteManager = new LinearLayoutManager(this);
        mAdapter = new SyndromeRecyclerViewAdapter(mCardList);
        mRecyclerView.setLayoutManager(mLayouteManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}



