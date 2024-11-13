package com.example.bucketlistapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.category.CategoryDAO;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BucketListRepository {
    private  BucketListItemDAO bucketListItemDAO;
    private   UserDAO userDAO;
    private  CategoryDAO categoryDAO;
    private User user;
    private Category category;
    private BucketListItem bucketListItem;
    private LiveData<List<BucketListItem>>allListItems;
    private LiveData<List<Category>>categoryTitles;

    public BucketListRepository(Application application) {
        BucketListRoomDatabase db = BucketListRoomDatabase.getDatabase(application);
        bucketListItemDAO= db.bucketListItemDAO();
        userDAO = db.userDAO();
        categoryDAO = db.categoryDAO();
        allListItems = bucketListItemDAO.findAll();
        categoryTitles = categoryDAO.findAll();

    }
    //method to deal with users
    public void insert(User user){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            userDAO.insert(user);
                });
    }
    public void delete(User user){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            userDAO.delete(user);
        });
    }
    public void update(User user){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            userDAO.update(user);
        });
    }
    public User findUserByEmail(String email){
        Callable <User> c = ()-> {
            return userDAO.findByEmail(email);
        };

        Future<User> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            user = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public User findUserById(int id){
        Callable <User> c = ()-> {
            return userDAO.findById(id);
        };

        Future<User> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            user = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public LiveData<List<Category>>getCategoryTitles(){
        return categoryTitles;
    }
    public void insert(Category category){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            categoryDAO.insert(category);
           // CategoryDAO.insert(category);
        });
    }
    public void delete(Category category){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            categoryDAO.delete(category);
        });
    }
    public void update(Category category){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            categoryDAO.update(category);
        });
    }
    public Category findCategoryById(int id){
        Callable <Category> c = ()-> {
            return categoryDAO.findById(id);
        };

        Future<Category> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            category = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    public LiveData<List<BucketListItem>>getAllListItems(){
        return allListItems;
    }

    public void insert(BucketListItem bucketListItem){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            bucketListItemDAO.insert(bucketListItem);
            // CategoryDAO.insert(category);
        });
    }
    public void delete(BucketListItem bucketListItem){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            bucketListItemDAO.delete(bucketListItem);
        });
    }
    public void update(BucketListItem category){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            bucketListItemDAO.update(bucketListItem);
        });
    }
    public BucketListItem findItemById(int id){
        Callable <BucketListItem> c = ()-> {
            return bucketListItemDAO.findById(id);
        };

        Future<BucketListItem> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            bucketListItem = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return bucketListItem;
    }

}
