package com.gram2022.sharingmywishlist_android.Main;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gram2022.sharingmywishlist_android.databinding.RvMainItemBinding;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RvMainItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvMainItemBinding.bind(itemView);
        }
    }
}
