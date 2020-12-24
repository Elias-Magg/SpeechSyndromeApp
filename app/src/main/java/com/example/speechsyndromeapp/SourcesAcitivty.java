package com.example.speechsyndromeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SourcesAcitivty extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mTextView = findViewById(R.id.sources_text_view);

        Intent intent = getIntent();
        SyndromeData data = intent.getParcelableExtra(MainActivity.EXTRA_SYNDROME_DATA);

        mTextView.setText(data.getSource());



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();

        return true;
    }
}
