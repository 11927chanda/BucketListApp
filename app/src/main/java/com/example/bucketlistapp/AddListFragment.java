package com.example.bucketlistapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.databinding.AddListFragmentBinding;
import com.example.bucketlistapp.databinding.ShowListFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddListFragment extends Fragment {

    private AddListFragmentBinding binding;


    public static AddListFragment newInstance() {
        return new AddListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.add_list_fragment, container, false);
       // binding = ShowListFragmentBinding.inflate(inflater, container, false);
        //return binding.getRoot();
        binding = AddListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}