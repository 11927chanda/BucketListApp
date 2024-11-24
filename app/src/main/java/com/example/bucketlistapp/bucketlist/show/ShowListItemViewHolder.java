package com.example.bucketlistapp.bucketlist.show;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.databinding.ShowBucketlistItemRecyclerViewItemBinding;

import java.text.Format;
import java.text.SimpleDateFormat;

public class ShowListItemViewHolder extends RecyclerView.ViewHolder {

    private ShowBucketlistItemRecyclerViewItemBinding binding;
    public ShowListItemViewHolder(@NonNull ShowBucketlistItemRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void update (BucketListItem bucketListItem){
        this.binding.showTitleTextView.setText(bucketListItem.getTitle());
        this.binding.showStatusTextView.setText(bucketListItem.getStatus());
        this.binding.priorityDiscreteSlider.setValue(bucketListItem.getPriorityLvl());
        this.binding.priorityLvlTextView.setText(bucketListItem.getPriorityLvl().toString());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(bucketListItem.getLastUpdated());
        binding.dateTextView.setText(date);

        binding.priorityDiscreteSlider.setEnabled(false);

    }

    //on clicking specific item
    public void bind(BucketListItem bucketListItem, OnItemClickListner onItemClickListner){
        binding.bucketListItemMCV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                binding.bucketListItemMCV.setChecked(!binding.bucketListItemMCV.isChecked());
                return true;

//                NavController navController = Navigation.findNavController(v);
//                navController.navigate(R.id.action_showListFragment_to_addListFragment);
            }
        });

        binding.bucketListItemMCV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("UPDATE_ITEM",bucketListItem);


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_showListFragment_to_addListFragment, bundle);
            }
        });


    }
}
