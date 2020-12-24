package com.example.speechsyndromeapp;

import java.util.ArrayList;

public abstract class MainRecyclerItem {
    private String mText;
    private ArrayList<String> mKeywords;
    public MainRecyclerItem(String text){
        mText = text;
    }


    public String getmText() {
        return mText;
    }
    public ArrayList<String> getKeywords() {
        return mKeywords;
    }


}
