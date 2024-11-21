package com.example.bucketlistapp.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.databinding.AddCategoryFragmentBinding;
import com.google.android.material.snackbar.Snackbar;


public class AddCategoryFragment extends Fragment {
    private AddCategoryViewModel mViewModel;
    private AddCategoryFragmentBinding binding;

    public static AddCategoryFragment newInstance() {
        return new AddCategoryFragment() ;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AddCategoryFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AddCategoryViewModel mViewModel = new ViewModelProvider(this).get(AddCategoryViewModel.class);

        //getting data from UI if it exists
        Category category = mViewModel.getCategory();
        binding.categoryTIL.getEditText().setText(category.getCategoryName());

        binding.addCategoryButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               //navigate back to main fragment
                //add the inserted value into db
                String categoryName = binding.categoryTIL.getEditText().getText().toString();
                if (categoryName.isBlank()) {
                    Snackbar.make(view, "Category can not be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }
                category.setCategoryName(categoryName);

                mViewModel.insert(category);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            }
        });

        binding.cancelCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate back to main fragment
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            }
        });
    }
    public void onDestroyView() {
        super.onDestroyView();
    }
}