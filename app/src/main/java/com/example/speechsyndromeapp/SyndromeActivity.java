package com.example.speechsyndromeapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;


public class SyndromeActivity extends AppCompatActivity {
    private String text;
    private SyndromeData data;
    private TextView mTitle;
    private TextView mCode;
    private TextView mText;
    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private ImageView mImage4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syndrome_activity);

        mTitle = findViewById(R.id.syndrome_title);
        mCode = findViewById(R.id.syndrome_code);
        mText = findViewById(R.id.syndrome_text);
        mImage1 = findViewById(R.id.syndrome_image_1);
        mImage2 = findViewById(R.id.syndrome_image_2);
        mImage3 = findViewById(R.id.syndrome_image_3);
        mImage4 = findViewById(R.id.syndrome_image_4);

        Intent intent = getIntent();
        data = intent.getParcelableExtra(MainActivity.EXTRA_SYNDROME_DATA);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        mTitle.setText(data.getTitle());
        mCode.setText(data.getCode());
        ArrayList<Integer> images = data.getImages();

        if(images.size()>=1)mImage1.setImageResource(images.get(0));
            else mImage1.setVisibility(View.GONE);
        if(images.size()>=2)mImage2.setImageResource(images.get(1));
            else mImage2.setVisibility(View.GONE);
        if(images.size()>=3)mImage3.setImageResource(images.get(2));
            else mImage3.setVisibility(View.GONE);
        if(images.size()>=4)mImage4.setImageResource(images.get(3));
            else mImage4.setVisibility(View.GONE);

        InputStream inputStream = getResources().openRawResource(data.getTextFile());
            try{
                byte[] buffer = new byte[inputStream.available()];
                while(inputStream.read(buffer) != -1){
                    text = new String(buffer);
                }

            } catch (IOException e){
                e.printStackTrace();
            }
            mText.setText(text);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.syndrome_sources, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();

        if(item.getItemId() == R.id.syndrome_sources){
            Intent intent = new Intent(this,SourcesAcitivty.class);
            intent.putExtra(MainActivity.EXTRA_SYNDROME_DATA,data);
            startActivity(intent);
        }


        return true;
    }

}



