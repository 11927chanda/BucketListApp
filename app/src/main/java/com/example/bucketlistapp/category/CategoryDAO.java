package com.example.bucketlistapp.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Category category);

    @Update
    void update(Category category);
    @Delete
    void delete(Category category);
    @Query("SELECT * FROM CATEGORY WHERE ID = :id")
    Category findById(int id);

    @Query("SELECT * FROM CATEGORY WHERE CATEGORY_NAME = :categoryName")
    Category findByCategoryTitle(String categoryName);
    //fixed
    @Query("SELECT * FROM CATEGORY")
    LiveData<List<Category>>findAll();
}
