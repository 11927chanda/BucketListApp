package com.example.bucketlistapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long insert(User user);

    @Update
    void update(User user);

   @Delete
    void delete(User user);

   @Query("SELECT * FROM USER WHERE ID = :id")
   User findById(Integer id);

   @Query("SELECT * FROM USER WHERE UPPER(EMAIL) =UPPER(:email)")
    User findByEmail(String email);
}
