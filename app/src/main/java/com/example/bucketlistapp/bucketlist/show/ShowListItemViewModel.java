package com.example.bucketlistapp.bucketlist.show;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.database.BucketListRepository;

import java.util.List;

public class ShowListItemViewModel  extends AndroidViewModel {
    private BucketListRepository bucketListRepository;
    private LiveData<List<BucketListItem>>allListItems;


    public ShowListItemViewModel(@NonNull Application application) {
        super(application);
        bucketListRepository = new BucketListRepository(application);
        allListItems = bucketListRepository.getAllListItems();


    }
//    public BucketListItem find(Integer categoryId) {
//        return bucketListRepository.findItemByCategoryId(categoryId);
//    }
    public LiveData<List<BucketListItem>>getAllListItems(){
        return allListItems;
    }

    public LiveData<List<BucketListItem>> findByUserIdAndCategoryId(Long userId, Long categoryId){
        return bucketListRepository.findByUserIdAndCategoryId(userId, categoryId);
    }

    public void delete(BucketListItem bucketListItem){
        bucketListRepository.delete(bucketListItem);
    }


}
