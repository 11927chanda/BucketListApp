package com.example.bucketlistapp.bucketlist.show;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.databinding.ShowBucketlistItemRecyclerViewItemBinding;

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

    }


}
