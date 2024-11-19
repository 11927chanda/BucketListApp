package com.example.bucketlistapp.bucketlist.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.database.BucketListRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class AddListFragmentViewModel extends AndroidViewModel {


    private final BucketListRepository bucketListRepository;
    private BucketListItem bucketListItem;
    public AddListFragmentViewModel(@NonNull Application application, BucketListRepository bucketListRepository, BucketListRepository bucketListRepository1, BucketListRepository bucketListRepository2) {
        super(application);
        this.bucketListRepository = bucketListRepository2;
        bucketListItem = new BucketListItem();
        bucketListRepository = new BucketListRepository(application);

        bucketListItem= new BucketListItem();
        bucketListItem.setTitle("");
        bucketListItem.setDescription("");
        bucketListItem.setBudget(0.0);
        bucketListItem.setStatus("");
        bucketListItem.setPriorityLvl(1);
        bucketListItem.setLastUpdated(new Date(System.currentTimeMillis()));
        bucketListItem.setCategoryId(1);

    }
    public void insert(BucketListItem bucketListItem){
        bucketListRepository.insert(bucketListItem);
    }

    public BucketListItem getBucketListItem() {
        return bucketListItem;
    }

    public void setBucketListItem(BucketListItem bucketListItem) {
        this.bucketListItem = bucketListItem;
    }
}
