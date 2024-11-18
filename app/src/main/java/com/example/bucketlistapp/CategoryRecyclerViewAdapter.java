package com.example.bucketlistapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.databinding.CategoryGridViewItemBinding;

public class CategoryRecyclerViewAdapter extends ListAdapter<Category, CategoryViewHolder> {

    private CategoryGridViewItemBinding binding;

    public CategoryRecyclerViewAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Category>DIFF_CALLBACK = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getImageCategory().equals(newItem.getImageCategory()) &&
                    oldItem.getCategoryName().equals(newItem.getCategoryName());
        }
    };

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //inflate the recyclerViewItem
        binding = CategoryGridViewItemBinding.inflate(inflater, parent, false);
        //Create a new viewholder passing the view binding to it
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(binding);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = getItem(position);
        holder.update(category);
    }
}
