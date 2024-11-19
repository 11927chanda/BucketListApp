package com.example.bucketlistapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.category.OnCategoryClickListener;
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
    // this method will be used to bind category to an independent listener so we know which category was clicked
    public void bind(Category category, OnCategoryClickListener onCategoryClickListener){

        binding.categoryMaterialCardview.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                binding.categoryMaterialCardview.setChecked(binding.categoryMaterialCardview.isChecked());
                return true;
            }
        });
        binding.categoryTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("BUCKETLIST", category);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_mainFragment_to_showListFragment, bundle);
            }
        });
    }
}
