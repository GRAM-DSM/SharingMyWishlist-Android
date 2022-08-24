package com.example.sharingmywishlist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingmywishlist.Response.WishAllResponse;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    // TAG
    private final static String TAG = "ItemAdapter";

    // Response List
    private ArrayList<WishAllResponse> dataSet;


    public ItemAdapter(ArrayList<WishAllResponse> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {

        // TextView
        holder.tv_item_title.setText(dataSet.get(position).getTitle()); // title
        holder.tv_item_contents.setText(dataSet.get(position).getContents()); // contents

        // CheckBox
        holder.chk_item_clear.setChecked(dataSet.get(position).isClear()); // clear

        // log
        Log.d(TAG, "title : " + dataSet.get(position).getTitle()); // title
        Log.d(TAG, "contents : " + dataSet.get(position).getContents()); // contents
        Log.d(TAG, "color : " + dataSet.get(position).getColor()); // color
        Log.d(TAG, "clear : " + dataSet.get(position).isClear()); // clear
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        // TAG
        private static final String TAG = "ViewHolder";

        // TextView
        public TextView tv_item_title; // title
        public TextView tv_item_contents; // contents

        // CheckBox
        public CheckBox chk_item_clear; // clear

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            // TextView
            tv_item_title = itemView.findViewById(R.id.tv_item_title); // title
            tv_item_contents = itemView.findViewById(R.id.tv_item_contents); // contents

            // CheckBox
            chk_item_clear = itemView.findViewById(R.id.chk_item_clear); // clear
        }
    }
}
