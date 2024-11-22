package com.example.bucketlistapp.bucketlist.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.ShowCategoryViewModel;
import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.bucketlist.show.ShowListItemViewModel;
import com.example.bucketlistapp.category.AddCategoryViewModel;
import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.databinding.AddListFragmentBinding;
import com.example.bucketlistapp.login.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;


public class AddListFragment extends Fragment {
    private AddListFragmentViewModel mViewModel;
    private AddListFragmentBinding binding;
    private Integer priorityLvl = 0;
    private Spinner statusSpinner;
    private Category category;
    private ShowListItemViewModel showListItemViewModel;


    public static AddListFragment newInstance() {
        return new AddListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = AddListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //code using UI
        mViewModel = new ViewModelProvider(this).get(AddListFragmentViewModel.class);
        LoginViewModel loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);



            //populate the spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.status_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.StatusSpinner.setAdapter(adapter);

            BucketListItem bucketListItem = mViewModel.getBucketListItem();
            binding.addTitleTIL.getEditText().setText(bucketListItem.getTitle());
            binding.addDiscriptionTIL.getEditText().setText(bucketListItem.getDescription());
            String status = bucketListItem.getStatus();
            int position = adapter.getPosition(status);
            binding.StatusSpinner.setSelection(position);
            binding.addBudgetTIL.getEditText().setText(String.valueOf(bucketListItem.getBudget()));
            binding.prioritySeekbar.setProgress(bucketListItem.getPriorityLvl());


            binding.prioritySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    priorityLvl = progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            binding.addListItembutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = binding.addTitleTIL.getEditText().getText().toString();
                    if (title.isBlank()) {
                        Snackbar.make(view, "Title cannot be blank", Snackbar.LENGTH_LONG).show();
                        return;
                    }

                    String description = binding.addDiscriptionTIL.getEditText().getText().toString();
                    if (description.isBlank()) {
                        Snackbar.make(view, "Description cannot be blank", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    String status = binding.StatusSpinner.getSelectedItem().toString();
                    if (status.isBlank()) {
                        Snackbar.make(view, "Status cannot be blank", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    String budgetString = binding.addBudgetTIL.getEditText().getText().toString();
                    if (budgetString.isBlank()) {
                        Snackbar.make(view, "Budget cannot be blank", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    double budget;
                    try {
                        budget = Double.parseDouble(budgetString);
                        Log.d("AddListFragment", "Budget Value: " + budget);
                    } catch (NumberFormatException e) {
                        Snackbar.make(view, "Invalid budget value", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    BucketListItem bucketListItem = new BucketListItem();
                    //  bucketListItem.setId(1);
                    bucketListItem.setTitle(title);
                    bucketListItem.setDescription(description);
                    bucketListItem.setBudget(budget);
                    bucketListItem.setStatus(status);
                    bucketListItem.setPriorityLvl(priorityLvl);
                    bucketListItem.setLastUpdated(new Date(System.currentTimeMillis()));
//                bucketListItem.setCategoryId(1); ////TODO: get this from a bundle (passed)
                    bucketListItem.setUserId(loginViewModel.getLoggedInUser().getId());

                    AddCategoryViewModel addCategoryViewModel = new ViewModelProvider(requireActivity()).get(AddCategoryViewModel.class);
                    showListItemViewModel = new ViewModelProvider(requireActivity()).get(ShowListItemViewModel.class);

                    Bundle bundle = getArguments();
                    if (bundle != null && bundle.containsKey("CATEGORY")) {
                        category = (Category) bundle.getSerializable("CATEGORY", Category.class);
//
//                     bucketListItem = showListItemViewModel.find(category.getId());
                        //  bucketListItem.setCategoryId(category.getId());
                        bucketListItem.setCategoryId(addCategoryViewModel.getCategory().getId());
                    }


                    mViewModel.insert(bucketListItem);


                    NavController navController = Navigation.findNavController(view);
                    navController.navigateUp();
                }
            });
            binding.cancelListItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigate back to main fragment
                    NavController navController = Navigation.findNavController(view);
                    navController.navigateUp();
                }
            });
        }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
