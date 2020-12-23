package com.example.speechsyndromeapp;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SyndromeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SyndromeRecyclerItem> mCardList;


    public static class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public TextViewHolder(@NonNull View itemView){
            super(itemView);
            mTextView = itemView.findViewById(R.id.syndrome_recycler_text);
        }

        public void setTextData(SyndromeRecyclerText text){
            mTextView.setText(text.getMtext());
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageViewLeft;
        private ImageView mImageViewRight;
        public ImageViewHolder(@NonNull View itemView){
            super(itemView);
            mImageViewLeft = itemView.findViewById(R.id.syndrome_recycler_image_left);
            mImageViewRight = itemView.findViewById(R.id.syndrome_recycler_image_right);
        }

        public void setImageData(SyndromeRecyclerImages images){
            mImageViewLeft.setImageResource(images.getImageLeft());
            if(images.getImageRight()==R.drawable.ic_baseline_missing){
                mImageViewRight.setVisibility(View.INVISIBLE);
            }
            else mImageViewRight.setImageResource(images.getImageRight());
        }


    }


    public SyndromeRecyclerViewAdapter(ArrayList<SyndromeRecyclerItem> cardList) {
        mCardList = cardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1)
            return new TextViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.syndrome_recycler_text, parent, false
                    )
            );

        else
            return new ImageViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.syndrome_recycler_images, parent, false
                    )
            );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==1){
            SyndromeRecyclerText text = (SyndromeRecyclerText) mCardList.get(position);
            ((TextViewHolder) holder).setTextData(text);
        }
        else if(getItemViewType(position)==2){
            SyndromeRecyclerImages imgs = (SyndromeRecyclerImages) mCardList.get(position);
            ((ImageViewHolder) holder).setImageData(imgs);
        }
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if(mCardList.get(position) instanceof SyndromeRecyclerText) return 1;
        else if (mCardList.get(position) instanceof SyndromeRecyclerImages) return 2;
        else return 0;
    }
}
