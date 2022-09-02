package com.gram2022.sharingmywishlist_android.Main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.databinding.RvMainItemBinding;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    ArrayList<WishAllResponse.WishResponseList> dataList;

    Context context;

    public ItemAdapter(ArrayList<WishAllResponse.WishResponseList> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvMainItemBinding binding = RvMainItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RvMainItemBinding itemBinding;

        public ViewHolder(@NonNull RvMainItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }

        void bindItem(WishAllResponse.WishResponseList item, int position) {
            String color = item.getColor();
            setBackgroundTint(color);

            boolean isClear = item.isClear();
            setClear(isClear);

            itemBinding.tvRvMainItemTitle.setText(item.getTitle());
            itemBinding.tvRvMainItemWriter.setText(item.getWriter());
            itemBinding.tvRvMainItemContents.setText(item.getContents());
        }

        private void setClear(boolean isClear) {
            if (isClear) {
                // TODO if cleared
                itemBinding.chkRvMainItem.setChecked(true);
            } else {
                itemBinding.chkRvMainItem.setChecked(false);
            }
        }

        private void setBackgroundTint(String color) {
            switch (color) {
                case "wish-nor":
                    itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.bg_nor));
                case "wish-red":
                    itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.bg_red));
                case "wish-gre":
                    itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.bg_gre));
                case "wish-blu":
                    itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.bg_blu));
            }
        }
    }
}
