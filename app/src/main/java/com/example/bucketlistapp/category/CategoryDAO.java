package com.example.bucketlistapp.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bucketlistapp.BucketListItem;

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
    @Query("SELECT * FROM `BUCKET LIST`")
    LiveData<List<Category>>findAll();
}
