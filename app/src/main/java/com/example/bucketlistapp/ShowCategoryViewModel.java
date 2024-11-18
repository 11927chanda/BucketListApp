package com.example.bucketlistapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.database.BucketListRepository;

import java.util.List;

public class ShowCategoryViewModel extends AndroidViewModel {
    private BucketListRepository bucketListRepository;
    private LiveData<List<Category>> allCategories;
    public ShowCategoryViewModel(@NonNull Application application) {
        super(application);
        bucketListRepository = new BucketListRepository(application);
        allCategories = bucketListRepository.getCategoryTitles();

    }
    public LiveData<List<Category>> getAllCategory(){
        return allCategories;
    }
}
