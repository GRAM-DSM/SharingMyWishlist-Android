package com.example.sharingmywishlist;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingmywishlist.API.API;
import com.example.sharingmywishlist.API.APIProvider;
import com.example.sharingmywishlist.Activity.SignInActivity;
import com.example.sharingmywishlist.Request.ClearRequest;
import com.example.sharingmywishlist.Response.WishAllResponse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    // TAG
    private final static String TAG = "ItemAdapter";
    // Response List
    private ArrayList<WishAllResponse> dataSet;

    public ItemAdapter(ArrayList<WishAllResponse> dataSet) {
        this.dataSet = dataSet;
    }

    public void clearWish() {
        dataSet.clear();
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // TextView
        holder.tv_item_title.setText(dataSet.get(position).getTitle()); // title
        holder.tv_item_contents.setText(dataSet.get(position).getContents()); // contents
        holder.tv_item_writer.setText(dataSet.get(position).getWriter()); // writer

        // CheckBox
        holder.chk_item_clear.setChecked(dataSet.get(position).isClear()); // clear
        holder.chk_item_clear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean changed) {
                // log
                Log.d(TAG, "onCheckedChanged : " + changed);
                if (changed) {
                    // clear
                    clear(dataSet.get(position).getId());
                }
            }
        });


        // Set Item Background Drawable

        // Background Color
        String color = dataSet.get(position).getColor();
        // change Background
        switch (color) {
            case "wish-nor": // 기본(하얀색)
                holder.layout_item_background.setBackgroundResource(R.drawable.bg_item_nor);
                break;
            case "wish-red": // 빨강
                holder.layout_item_background.setBackgroundResource(R.drawable.bg_item_red);
                break;
            case "wish-gre": // 초록
                holder.layout_item_background.setBackgroundResource(R.drawable.bg_item_gre);
                break;
            case "wish-blu": // 파랑
                holder.layout_item_background.setBackgroundResource(R.drawable.bg_item_blu);
                break;
        }

        // log
        Log.d(TAG, "===== Item on " + position + " =====");
        Log.d(TAG, "@id : " + dataSet.get(position).get_id()); // server id
        Log.d(TAG, "id : " + dataSet.get(position).getId()); // id
        Log.d(TAG, "title : " + dataSet.get(position).getTitle()); // title
        Log.d(TAG, "writer : " + dataSet.get(position).getWriter()); // writer
        Log.d(TAG, "contents : " + dataSet.get(position).getContents()); // contents
        Log.d(TAG, "color : " + dataSet.get(position).getColor()); // color
        Log.d(TAG, "clear : " + dataSet.get(position).isClear()); // clear
        Log.d(TAG, "==========");
    }


    // clear Wish
    private void clear(int id) {

        Log.d(TAG, "clear() has called");

        // API
        API api = APIProvider.getInstance().create(API.class);
        api.clear(SignInActivity.accessToken, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "response code : " + response.code());
                Log.d(TAG, "id : " + id);

                if (response.isSuccessful()) {
                    try {
                        Log.d(TAG, "response body : " + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "item " + id + " has set to cleared");
                } else {
                    try {
                        Log.d(TAG, "error body : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "item clear failed", t);
            }
        });
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
        public TextView tv_item_writer; // writer

        // CheckBox
        public CheckBox chk_item_clear; // clear

        // Background
        public View layout_item_background; // linearLayout

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            // TextView
            tv_item_title = itemView.findViewById(R.id.tv_item_title); // title
            tv_item_contents = itemView.findViewById(R.id.tv_item_contents); // contents
            tv_item_writer = itemView.findViewById(R.id.tv_item_writer); // writer

            // CheckBox
            chk_item_clear = itemView.findViewById(R.id.chk_item_clear); // clear

            // Background Layout
            layout_item_background = itemView.findViewById(R.id.layout_item_background); // background layout
        }
    }
}
