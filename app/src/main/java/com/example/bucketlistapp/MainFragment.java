package com.example.bucketlistapp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.category.OnCategoryClickListener;
import com.example.bucketlistapp.databinding.MainFragmentBinding;

import java.util.List;

public class MainFragment extends Fragment implements OnCategoryClickListener {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.main_fragment, container, false);
        binding = MainFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShowCategoryViewModel showCategoryViewModel =new ViewModelProvider(this).get(ShowCategoryViewModel.class);
        //safe to code using UI
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

//        binding.showListButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavController navController = Navigation.findNavController(v);
//                navController.navigate(R.id.action_mainFragment_to_showListFragment);
//            }
//        });

        binding.addCategoryFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_mainFragment_to_addCategoryFragment);
            }
        });
        //recycler view
        binding.categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.categoryRecyclerView.setHasFixedSize(true);
        //create the adapter
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this);
        //set it to recycler view
        binding.categoryRecyclerView.setAdapter(adapter);

        //get an observer and set it
        final Observer<List<Category>> allCategoryObserver = new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categoryList) {
                adapter.submitList(categoryList);
            }
        };
        //make LiveData observer for changes
        showCategoryViewModel.getAllCategory().observe(getViewLifecycleOwner(), allCategoryObserver);


    }

    @Override
    public void onClick(Category category, View view) {
        //code here whenever clicked for particular category
    }
}