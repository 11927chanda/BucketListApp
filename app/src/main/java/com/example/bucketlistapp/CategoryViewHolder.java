package com.example.bucketlistapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.databinding.CategoryRecyclerViewItemBinding;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private CategoryRecyclerViewItemBinding binding;

    public CategoryViewHolder(@NonNull CategoryRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(Category category){
        this.binding.categoryTitleTextView.setText(category.getCategoryName());

        if(category.getImageCategory().isEmpty()){
            this.binding.categoryImageView.setImageResource(R.drawable.travel);
        }else{
            int resID = binding.getRoot().getResources().getIdentifier(category.getImageCategory(),"drawable",binding.getRoot().getContext().getPackageName());
            this.binding.categoryImageView.setImageResource(resID);
        }
    }
}
