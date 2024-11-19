package com.example.bucketlistapp.bucketlist.show;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.ShowCategoryViewModel;
import com.example.bucketlistapp.ShowListItemViewAdapter;
import com.example.bucketlistapp.ShowListItemViewHolder;
import com.example.bucketlistapp.ShowListItemViewModel;
import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.databinding.ShowListFragmentBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowListFragment extends Fragment {

    private ShowListFragmentBinding binding;
    private Category category;

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

        ShowListItemViewModel showListItemViewModel = new ViewModelProvider(this).get(ShowListItemViewModel.class);

        //if something has passes something to this Fragment, getArguments will have it
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("BUCKETLIST")){
            //yoy know the fragment has been opened
            category = bundle.getSerializable("BUCKETLIST", Category.class);

            BucketListItem bucketListItem = showListItemViewModel.find(category.getId());
            //for the bucket list item
            binding.headingTextView.setText(category.getCategoryName());

        }

        //navigate to add List fragment
        binding.addNewItemFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_showListFragment_to_addListFragment);
            }
        });
        //Recycler view
        binding.showListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.showListRecyclerView.setHasFixedSize(true);
        //create adapter
        ShowListItemViewAdapter adapter = new ShowListItemViewAdapter();
        //set it to recyclerView
        binding.showListRecyclerView.setAdapter(adapter);
        //get an observer and set it
        final Observer<List<BucketListItem>> allBucketListItemObserver = new Observer<List<BucketListItem>>() {
            @Override
            public void onChanged(List<BucketListItem> bucketListItems) {
                adapter.submitList(bucketListItems);
            }
        };
        //make LiveData observer for changes
        showListItemViewModel.getAllListItems().observe(getViewLifecycleOwner(), allBucketListItemObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}