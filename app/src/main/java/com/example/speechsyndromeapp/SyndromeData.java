package com.example.speechsyndromeapp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * This is a simple data container
 * for the traits of each syndrome
 */

public class SyndromeData implements Parcelable {
    private int textFile;
    private String code;
    private String title;
    private String source;
    private ArrayList<Integer> images;

    public SyndromeData (String title, int textFile, String code, String source, ArrayList<Integer> images) {
        this.title = title;
        this.code = code;
        this.textFile = textFile;
        this.source = source;
        this.images = images;
    }

    protected SyndromeData(Parcel in) {
        textFile = in.readInt();
        code = in.readString();
        title = in.readString();
        source = in.readString();
    }

    public static final Creator<SyndromeData> CREATOR = new Creator<SyndromeData>() {
        @Override
        public SyndromeData createFromParcel(Parcel in) {
            return new SyndromeData(in);
        }

        @Override
        public SyndromeData[] newArray(int size) {
            return new SyndromeData[size];
        }
    };

    public void setTextFile(int path) {
        this.textFile = path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public int getTextFile() {
        return textFile;
    }

    public String getCode() {
        return code;
    }

    public String getSource() {
        return source;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(textFile);
        dest.writeString(code);
        dest.writeString(title);
        dest.writeString(source);
    }
}
