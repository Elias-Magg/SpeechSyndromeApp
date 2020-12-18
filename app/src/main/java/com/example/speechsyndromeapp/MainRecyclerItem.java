package com.example.speechsyndromeapp;

public abstract class MainRecyclerItem {
    private String mText;

    public MainRecyclerItem(String text){
        mText = text;
    }


    public void click(){
        mText = "CLICKED";
    }

    public String getmText() {
        return mText;
    }

}
