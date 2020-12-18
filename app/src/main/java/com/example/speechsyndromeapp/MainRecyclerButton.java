package com.example.speechsyndromeapp;

import java.util.ArrayList;

public class MainRecyclerButton extends MainRecyclerItem {
    //keywords for search function
    private ArrayList<String> mKeywords;
    public MainRecyclerButton(String text,ArrayList<String> keywords) {
        super(text);
        mKeywords = keywords;
    }


    public ArrayList<String> getKeywords() {
        return mKeywords;
    }
}
