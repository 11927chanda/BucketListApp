package com.example.bucketlistapp.bucketlist.show;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.ShowCategoryViewModel;
import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.databinding.ShowListFragmentBinding;
import com.example.bucketlistapp.login.LoginViewModel;

import java.util.List;


public class ShowListFragment extends Fragment {

    private ShowListItemViewModel showListItemViewModel;

    private ShowListFragmentBinding binding;
    private Category category;
    private ShowCategoryViewModel showCategoryViewModel;
    private ShowListItemViewAdapter adapter;

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

         showListItemViewModel = new ViewModelProvider(this).get(ShowListItemViewModel.class);
         LoginViewModel loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        //if something has passes something to this Fragment, getArguments will have it
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("BUCKETLIST")){
            //yoy know the fragment has been opened
            category = bundle.getSerializable("BUCKETLIST",Category.class);
            binding.headingTextView.setText(category.getCategoryName());

        }

        //navigate to add List fragment
        binding.addNewItemFAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("BUCKETLIST", category);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_showListFragment_to_addListFragment);
            }
        });
        //Recycler view
        binding.showListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.showListRecyclerView.setHasFixedSize(true);
        //create adapter
//        ShowListItemViewAdapter adapter = new ShowListItemViewAdapter();
        adapter = new ShowListItemViewAdapter();
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
//        showListItemViewModel.getAllListItems().observe(getViewLifecycleOwner(), allBucketListItemObserver);
        showListItemViewModel.findByUserIdAndCategoryId(Long.valueOf(loginViewModel.getLoggedInUser().getId()), Long.valueOf(category.getId())).observe(getViewLifecycleOwner(), allBucketListItemObserver);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                BucketListItem bucketListItem = adapter.getCurrentList().get(position);
                showDeleteConfirmationDialogue(bucketListItem, position);

            }
        };
        //for deleting item from list
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.showListRecyclerView);
    }
    //confirmation dialogue

    private void showDeleteConfirmationDialogue(BucketListItem bucketListItem, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("Delete",(dialogue, which)->{
            showListItemViewModel.delete(bucketListItem);
            adapter.removeBucketlistItem(position);
        });
        builder.setNegativeButton("Cancel",(dialog, which) -> {
            adapter.notifyItemChanged(position);
            dialog.dismiss();
        });
        builder.create().show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}