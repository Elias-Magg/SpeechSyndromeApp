package com.example.speechsyndromeapp;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Documented;
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

    public static class TitleViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public TitleViewHolder(@NonNull View itemView){
            super(itemView);
            mTextView = itemView.findViewById(R.id.syndrome_recycler_title);
        }

        public void setTextData(SyndromeRecyclerTitle text){
            mTextView.setText(text.getMtext());
        }
    }

    public static class CodeViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public CodeViewHolder(@NonNull View itemView){
            super(itemView);
            mTextView = itemView.findViewById(R.id.syndrome_recycler_code);
        }

        public void setTextData(SyndromeRecyclerCode text){
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

        else if (viewType == 2)
            return new ImageViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.syndrome_recycler_images, parent, false
                    )
            );
        else if(viewType == 3)
            return new CodeViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.syndrome_recycler_code, parent, false
                    )
            );
        else
            return new TitleViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.syndrome_recycler_title, parent, false
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
        else if(getItemViewType(position)==3){
            SyndromeRecyclerCode code = (SyndromeRecyclerCode) mCardList.get(position);
            ((CodeViewHolder) holder).setTextData(code);
        }
        else if(getItemViewType(position)==4){
            SyndromeRecyclerTitle title = (SyndromeRecyclerTitle) mCardList.get(position);
            ((TitleViewHolder) holder).setTextData(title);
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
        else if (mCardList.get(position) instanceof SyndromeRecyclerCode) return 3;
        else if (mCardList.get(position) instanceof SyndromeRecyclerTitle) return 4;
        else return 0;
    }

}
