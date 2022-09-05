package com.gram2022.sharingmywishlist_android.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.databinding.RvMainItemBinding;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    ArrayList<WishAllResponse.WishResponseList> dataList;

    Context context;

    public ItemAdapter(ArrayList<WishAllResponse.WishResponseList> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        Log.d(TAG, "ItemAdapter has called");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvMainItemBinding binding = RvMainItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.bindItem(dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RvMainItemBinding itemBinding;

        public ViewHolder(@NonNull RvMainItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }

        void bindItem(WishAllResponse.WishResponseList item, int position) {

            Log.d(TAG, "==========");
            Log.d(TAG, "id : " + item.getId());
            Log.d(TAG, "title : " + item.getTitle());
            Log.d(TAG, "writer : " + item.getWriter());
            Log.d(TAG, "contents : " + item.getContents());
            Log.d(TAG, "contents : " + item.getContents());
            Log.d(TAG, "color : " + item.getColor());
            Log.d(TAG, "clear : " + item.isClear());

            String color = item.getColor();
            setBackgroundTint(color);

            setClear(item.isClear());

            itemBinding.tvRvMainItemTitle.setText(item.getTitle());
            itemBinding.tvRvMainItemWriter.setText(item.getWriter());
            itemBinding.tvRvMainItemContents.setText(item.getContents());

            initItemClear();
        }

        private void initItemClear() {
            itemBinding.chkRvMainItem.setOnClickListener(view -> {
                showDialog(String.valueOf(R.string.main_clearDialog), view.getContext());
            });
        }

        private void showDialog(String title, Context context) {
            // TODO Dialog
        }

        private void setClear(boolean isClear) {
            if (isClear) {
                // TODO if cleared
                itemBinding.chkRvMainItem.setChecked(true);
            } else {
                itemBinding.chkRvMainItem.setChecked(false);
            }
        }

        private void disableCheck() {

        }

        private void setBackgroundTint(String color) {
            switch (color) {
                case "wish-nor":
                    itemBinding.getRoot().setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_nor));
                    break;
                case "wish-red":
                    itemBinding.getRoot().setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_red));
                    break;
                case "wish-gre":
                    itemBinding.getRoot().setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_gre));
                    break;
                case "wish-blu":
                    itemBinding.getRoot().setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_blu));
                    break;
            }
        }
    }
}
