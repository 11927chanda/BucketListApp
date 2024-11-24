package com.example.bucketlistapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.bucketlist.BucketListItemDAO;
import com.example.bucketlistapp.User;
import com.example.bucketlistapp.UserDAO;
import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.category.CategoryDAO;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BucketListRepository {
    private BucketListItemDAO bucketListItemDAO;
    private UserDAO userDAO;
    private  CategoryDAO categoryDAO;
    private User user;
    private Category category;
    private BucketListItem bucketListItem;
    private LiveData<List<BucketListItem>>allListItems;
    private LiveData<List<Category>>categoryTitles;
    private Long bucketListItemId ;
    private Long categoryId;
    private Long userId;

    public BucketListRepository(Application application) {
        BucketListRoomDatabase db = BucketListRoomDatabase.getDatabase(application);
        bucketListItemDAO= db.bucketListItemDAO();
        userDAO = db.userDAO();
        categoryDAO = db.categoryDAO();
        allListItems = bucketListItemDAO.findAll();
        categoryTitles = categoryDAO.findAll();

    }
    //method to deal with users
    public  Long insert(User myUser){
//        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
//            userDAO.insert(user);
//                });
        Callable <Long> c = ()-> {
            return userDAO.insert(myUser);
        };

        Future<Long> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            userId = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userId;

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

    public Long insert(Category myCategory){
//        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
//            categoryDAO.insert(category);
//
//        });
        Callable <Long> c = ()-> {
            return categoryDAO.insert(myCategory);
        };

        Future<Long> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            categoryId = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return categoryId;
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

    public Category findByCategoryTitle(String categoryName){
        Callable <Category> c = ()-> {
            return categoryDAO.findByCategoryTitle(categoryName);
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

    public Long insert(BucketListItem item){
//        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
//            bucketListItemDAO.insert(item);
//        });

        Callable <Long> c = ()-> {
            return bucketListItemDAO.insert(item);
        };

        Future<Long> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            bucketListItemId = future.get();
        }
        catch (ExecutionException |InterruptedException e) {
            throw new RuntimeException(e);
        }
        return bucketListItemId;
    }
    public void delete(BucketListItem bucketListItem){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            bucketListItemDAO.delete(bucketListItem);
        });
    }
    public void update(BucketListItem bucketListItem){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            bucketListItemDAO.update(bucketListItem);
            //asynchronous
//            Callable <Long> c = ()-> {
//                return bucketListItemDAO.update(item);
//            };
//
//            Future<Long> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
//            try {
//                item = future.get();
//            }
//            catch (ExecutionException |InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return item;
//
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
//    public BucketListItem findItemByCategoryId(int categoryId){
//        Callable<BucketListItem> c = ()->{
//            return bucketListItemDAO.findByCategoryId(categoryId);
//        };
//        Future<BucketListItem> future = BucketListRoomDatabase.databaseWriteExecutor.submit(c);
//        try {
//            bucketListItem = future.get();
//        }
//        catch (ExecutionException |InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        return bucketListItem;
//    }


    public LiveData<List<BucketListItem>> findByUserIdAndCategoryId(Long userId, Long categoryId){
        return bucketListItemDAO.findByUserIdAndCategoryId(userId, categoryId);
    }

    public Category findItemByCategoryId(Integer categoryId) {
        return categoryDAO.findById(categoryId);
    }
}
