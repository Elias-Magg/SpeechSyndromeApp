package com.example.speechsyndromeapp;

import android.net.Uri;

import java.util.ArrayList;

public class SyndromeRecyclerText extends SyndromeRecyclerItem{

    private String mText;

    public SyndromeRecyclerText(String text) {
        this.mText = text;
    }

    public String getMtext() {
        return mText;
    }


}
