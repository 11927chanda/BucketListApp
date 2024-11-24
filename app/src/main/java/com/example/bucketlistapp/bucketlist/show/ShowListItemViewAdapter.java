package com.example.bucketlistapp.bucketlist.show;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.databinding.ShowBucketlistItemRecyclerViewItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ShowListItemViewAdapter extends ListAdapter<BucketListItem, ShowListItemViewHolder> {

    private ShowBucketlistItemRecyclerViewItemBinding binding;
    private OnItemClickListner onItemClickListner;

    public ShowListItemViewAdapter(OnItemClickListner onItemClickListner){
        super(DIFF_CALLBACK);
        this.onItemClickListner = onItemClickListner;
    }

    private static final DiffUtil.ItemCallback<BucketListItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<BucketListItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull BucketListItem oldItem, @NonNull BucketListItem newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull BucketListItem oldItem, @NonNull BucketListItem newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDescription().equals(newItem.getDescription())&&
                    oldItem.getStatus().equals(newItem.getStatus())&&
                    oldItem.getBudget().equals(newItem.getBudget())&&
                    oldItem.getLastUpdated().equals(newItem.getLastUpdated())&&
                    oldItem.getPriorityLvl().equals(newItem.getPriorityLvl());
                   // oldItem.getCategoryId().equals(newItem.getCategoryId());
        }
    };
    @NonNull
    @Override
    public ShowListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ShowBucketlistItemRecyclerViewItemBinding.inflate(inflater, parent, false);
        ShowListItemViewHolder showListItemViewHolder = new ShowListItemViewHolder(binding);
        return showListItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListItemViewHolder holder, int position) {
        BucketListItem bucketListItem = getItem(position);
        holder.update(bucketListItem);
        holder.bind(bucketListItem, onItemClickListner);
    }
    //for deleting item from list
    public void removeBucketlistItem(int position){
        List<BucketListItem> currentList = new ArrayList<>(getCurrentList());
        currentList.remove(position);
        submitList(currentList);
    }
}
