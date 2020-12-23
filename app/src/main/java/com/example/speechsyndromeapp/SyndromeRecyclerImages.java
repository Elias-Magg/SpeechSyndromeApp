package com.example.speechsyndromeapp;

import android.widget.ImageView;

public class SyndromeRecyclerImages extends SyndromeRecyclerItem{

    private int ImageLeft;
    private int ImageRight;

    public SyndromeRecyclerImages(int img1 , int img2){
        ImageLeft = img1;
        ImageRight = img2;
    }

    public int getImageLeft() {
        return ImageLeft;
    }

    public int getImageRight() {
        return ImageRight;
    }

}
