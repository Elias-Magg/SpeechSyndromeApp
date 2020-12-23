package com.example.speechsyndromeapp;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class MainRecyclerButton extends MainRecyclerItem {
    //keywords for search function
    private SyndromeData data ;
    private ArrayList<String> mKeywords;
    public MainRecyclerButton(String text,ArrayList<String> keywords, SyndromeData data) {
        super(text);
        this.data = data;
        mKeywords = keywords;
    }


    public SyndromeData getData() {
        return data;
    }

    public ArrayList<String> getKeywords() {
        return mKeywords;
    }
}
