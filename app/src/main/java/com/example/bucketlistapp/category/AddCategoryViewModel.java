package com.example.bucketlistapp.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.bucketlistapp.database.BucketListRepository;

public class AddCategoryViewModel extends AndroidViewModel {

    private final BucketListRepository bucketListRepository;
    private Category category;
    public AddCategoryViewModel(@NonNull Application application) {
        super(application);
        //
        bucketListRepository = new BucketListRepository(application);
        //create an instance of category to create new category
        category = new Category();
        category.setCategoryName("");

    }
    public void insert(Category category){
        bucketListRepository.insert(category);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}