package com.malik.myprofile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity; // Penting: Pastikan ini ada

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final Context context;
    private final List<Category> categoryList; // Ini adalah variabel yang butuh inisialisasi

    // KONSTRUKTOR INI ADALAH BAGIAN YANG SANGAT KRITIS
    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList; // <-- BARIS INI HARUS ADA DAN BENAR!
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryTitle.setText(category.getTitle());
        holder.categoryIcon.setImageResource(category.getIconResId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileDetailActivity.class);
                intent.putExtra("detail_type", category.getDetailType());

                // Pastikan context adalah instance Activity sebelum memulai
                if (context instanceof AppCompatActivity) {
                    ((AppCompatActivity) context).startActivity(intent);
                } else {
                    // Fallback jika bukan Activity context (seharusnya tidak terjadi jika BottomSheetFragment benar)
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryTitle = itemView.findViewById(R.id.category_title);
        }
    }
}