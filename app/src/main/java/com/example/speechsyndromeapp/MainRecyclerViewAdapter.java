package com.example.speechsyndromeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<MainRecyclerItem> mCardList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class LetteringViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public LetteringViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.letteringText);
        }

        public void setLetteringData(MainRecyclerLettering lettering){
            mTextView.setText(lettering.getmText());

        }
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ButtonViewHolder(@NonNull View itemView , OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.cardText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void setButtonData(MainRecyclerButton button){
            mTextView.setText(button.getmText());
        }
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private EditText mEditText;

        public SearchViewHolder(@NonNull View itemView){
            super(itemView);
            mEditText = itemView.findViewById(R.id.search_card);

        }

        public void setSearchData(MainRecyclerSearch search){
            mEditText.setText(search.getmText());
        }
    }

    public MainRecyclerViewAdapter(ArrayList<MainRecyclerItem> cardList) {
        mCardList = cardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Types are 1 = button , 2 = lettering
        if(viewType == 1)
            return new ButtonViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.main_recycler_item, parent, false
                    ),mListener
            );
        else if (viewType == 2){
            return new LetteringViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.main_recycler_lettering, parent, false
                )
            );
        }
        else{
            return new SearchViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.main_recycler_search,parent,false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==1){
            MainRecyclerButton button = (MainRecyclerButton) mCardList.get(position);
            ((ButtonViewHolder) holder).setButtonData(button);
        }
        else if (getItemViewType(position)==2){
            MainRecyclerLettering lettering = (MainRecyclerLettering) mCardList.get(position);
            ((LetteringViewHolder) holder).setLetteringData(lettering);
        }
        else if(getItemViewType(position)==3){
            MainRecyclerSearch search = (MainRecyclerSearch) mCardList.get(position);
            ((SearchViewHolder) holder).setSearchData(search);
        }
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mCardList.get(position) instanceof MainRecyclerButton) return 1;
        else if (mCardList.get(position) instanceof MainRecyclerLettering) return 2;
        else if (mCardList.get(position) instanceof MainRecyclerSearch) return 3;
        else return 0;
    }
}
