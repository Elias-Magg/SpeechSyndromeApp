package com.example.speechsyndromeapp;

public abstract class MainRecyclerItem {
    private String mText;

    public MainRecyclerItem(String text){
        mText = text;
    }


    public void click(){
    }

    public String getmText() {
        return mText;
    }

}
