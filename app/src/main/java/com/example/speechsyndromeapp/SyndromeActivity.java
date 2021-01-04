package com.example.speechsyndromeapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This activity is where all the info
 * for each individual syndrome is displayed
 */
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
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //Disable night mode

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

        ArrayList<Integer> images = data.getImages();


        mTitle.setText(data.getTitle());
        mCode.setText(data.getCode());

        if(images.size() > 0)mImage1.setImageResource(images.get(0));
        else mImage1.setVisibility(View.GONE);
        if(images.size() > 1)mImage2.setImageResource(images.get(1));
        else mImage2.setVisibility(View.GONE);
        if(images.size() > 2)mImage3.setImageResource(images.get(2));
        else mImage3.setVisibility(View.GONE);
        if(images.size() > 3)mImage4.setImageResource(images.get(3));
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

            SpannableString ss = new SpannableString(text);
            StyleSpan bold = new StyleSpan(Typeface.BOLD);
            StyleSpan bold1 = new StyleSpan(Typeface.BOLD);
            StyleSpan bold2 = new StyleSpan(Typeface.BOLD);
            StyleSpan bold3 = new StyleSpan(Typeface.BOLD);
            StyleSpan bold4 = new StyleSpan(Typeface.BOLD);
            StyleSpan bold5 = new StyleSpan(Typeface.BOLD);
            StyleSpan bold6 = new StyleSpan(Typeface.BOLD);

            ss.setSpan(bold,text.indexOf("Αιτία-Κληρονομικότητα"),text.indexOf("Αιτία-Κληρονομικότητα")+ "Αιτία-Κληρονομικότητα".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(bold1,text.indexOf("Επιπολασμός"),text.indexOf("Επιπολασμός")+ "Επιπολασμός".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(bold2,text.indexOf("Γενικά χαρακτηριστικά"),text.indexOf("Γενικά χαρακτηριστικά")+ "Γενικά χαρακτηριστικά".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(bold3,text.indexOf("Στοματοπροσωπικά ευρήματα/δυσχέρειες"),text.indexOf("Στοματοπροσωπικά ευρήματα/δυσχέρειες")+ "Στοματοπροσωπικά ευρήματα/δυσχέρειες".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(bold4,text.indexOf("Επιπτώσεις στην ακοή"),text.indexOf("Επιπτώσεις στην ακοή")+ "Επιπτώσεις στην ακοή".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(bold5,text.indexOf("Επιπτώσεις στο λόγο & την ομιλία"),text.indexOf("Επιπτώσεις στο λόγο & την ομιλία")+ "Επιπτώσεις στο λόγο & την ομιλία".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(bold6,text.indexOf("Επιπτώσεις στην κατάποση"),text.indexOf("Επιπτώσεις στην κατάποση")+ "Επιπτώσεις στην κατάποση".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mText.setText(ss);







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



