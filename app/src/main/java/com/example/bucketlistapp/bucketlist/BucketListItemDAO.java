package com.example.bucketlistapp.bucketlist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BucketListItemDAO {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    Long insert(BucketListItem bucketListItem);

    @Update
    void update(BucketListItem bucketListItem);
    @Delete
    void delete(BucketListItem bucketListItem);

    //display all list to user
    @Query("SELECT * FROM `BUCKETLIST`")
    LiveData<List<BucketListItem>>findAll();
    @Query("SELECT * FROM `BUCKETLIST` WHERE ID= :id")
    BucketListItem findById(Integer id);

    @Query("SELECT * FROM `BUCKETLIST` WHERE TITLE= :title")
    BucketListItem findByTitle(String title);

    @Query("SELECT * FROM `BUCKETLIST` WHERE CATEGORY_ID= :categoryId")
    BucketListItem findByCategoryId(int categoryId);
}
