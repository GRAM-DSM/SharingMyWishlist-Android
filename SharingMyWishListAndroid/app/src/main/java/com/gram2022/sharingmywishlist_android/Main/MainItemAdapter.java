package com.gram2022.sharingmywishlist_android.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.Detail.WishDetailActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.RvMainItemBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ViewHolder> {

    static final String TAG = MainItemAdapter.class.getSimpleName();

    ArrayList<WishAllResponse.WishResponseList> dataList;

    Context context;

    public MainItemAdapter(ArrayList<WishAllResponse.WishResponseList> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        Log.d(TAG, "MainItemAdapter has called");
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
        holder.bindItem(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private void clear(int id) {
        API api = APIProvider.getInstance().create(API.class);
        api.clear(SignInActivity.accessToken, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "clear() success!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d(TAG, "clear() failure..");
            }
        });
    }

    private void delete(int id) {
        API api = APIProvider.getInstance().create(API.class);
        api.delete(SignInActivity.accessToken, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "delete() success!");
                    Toast.makeText(context, R.string.main_delete_success, Toast.LENGTH_SHORT).show();
                    clearWish();
                    MainActivity.getWishAll();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d(TAG, "delete() failure..");
                Toast.makeText(context, R.string.main_delete_failure, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clearWish() {
        dataList.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RvMainItemBinding itemBinding;

        public ViewHolder(@NonNull RvMainItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }

        void bindItem(WishAllResponse.WishResponseList item) {

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
            itemBinding.chkRvMainItem.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                        .setTitle(R.string.main_clearDialog)
                        .setPositiveButton(R.string.main_dialog_positive, (dialog, which) -> {
                            clear(item.getId());
                            disableCheck(itemBinding.chkRvMainItem);
                        })
                        .setNeutralButton(R.string.main_dialog_neutral, (dialog, which) -> setClear(false))
                        .setOnCancelListener(dialog -> setClear(false));
                builder.show();
            });

            itemBinding.getRoot().setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                        .setTitle(R.string.main_deleteDialog)
                        .setPositiveButton(R.string.main_dialog_positive, (dialog, which) -> {
                            delete(item.getId());
                            notifyItemChanged(item.getId());
                        })
                        .setNeutralButton(R.string.main_dialog_neutral, (dialog, which) -> {
                        })
                        .setOnCancelListener(dialog -> {
                        });
                builder.show();
                return true;
            });

            itemBinding.getRoot().setOnClickListener(v -> {
                Intent wishDetailIntent = new Intent(context, WishDetailActivity.class);
                wishDetailIntent.putExtra("wishId", item.getId());
                wishDetailIntent.putExtra("title", item.getTitle());
                wishDetailIntent.putExtra("writer", item.getWriter());
                wishDetailIntent.putExtra("contents", item.getContents());
                context.startActivity(wishDetailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            });
        }

        private void setClear(boolean isClear) {
            if (isClear) {
                itemBinding.chkRvMainItem.setChecked(true);
                disableCheck(itemBinding.chkRvMainItem);
            } else {
                itemBinding.chkRvMainItem.setChecked(false);
                enableCheck(itemBinding.chkRvMainItem);
            }
        }

        private void enableCheck(View view) {
            view.setEnabled(true);
            view.setAlpha(1);
        }

        private void disableCheck(View view) {
            view.setEnabled(false);
            view.setAlpha((float) 0.4);
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
