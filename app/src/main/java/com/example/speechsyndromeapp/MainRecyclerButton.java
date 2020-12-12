package com.example.speechsyndromeapp;

public class MainRecyclerButton extends MainRecyclerItem {
    private String msomething;
    public MainRecyclerButton(String text,String something) {
        super(text);
        msomething=something;
    }

    public String getMsomething() {
        return msomething;
    }
}
