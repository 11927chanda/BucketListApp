package com.example.bucketlistapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.databinding.ShowListFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowListFragment extends Fragment {

    private ShowListFragmentBinding binding;

    public static ShowListFragment newInstance() {

        return new ShowListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.show_list_fragment, container, false);
        binding = ShowListFragmentBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //navigate to add List fragment
        binding.addNewItemFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_showListFragment_to_addListFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}