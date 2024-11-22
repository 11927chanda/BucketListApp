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

//    private Category selectedCategory;
    public ShowCategoryViewModel(@NonNull Application application) {
        super(application);
        bucketListRepository = new BucketListRepository(application);
        allCategories = bucketListRepository.getCategoryTitles();
    }
//    public Category getSelectedCategory(){
//        return selectedCategory;
//    }

    public LiveData<List<Category>> getAllCategory(){
        return allCategories;
    }
}
