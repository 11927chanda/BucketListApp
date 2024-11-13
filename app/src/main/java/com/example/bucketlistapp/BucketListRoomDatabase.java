package com.example.bucketlistapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.category.CategoryDAO;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BucketListItem.class, User.class, Category.class},
    version = 1, exportSchema = false
)
public abstract class BucketListRoomDatabase extends RoomDatabase{

    //DAO's section
    public abstract BucketListItemDAO bucketListItemDAO();
    public abstract  UserDAO userDAO();
    public abstract CategoryDAO categoryDAO();

    //The volatile keyword ensures that updates to a variable are propagated prectictably to other threads
    private static volatile BucketListRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static BucketListRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (BucketListRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), BucketListRoomDatabase.class, "bucketlist_db")
                            .addCallback(roomCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populateInitialData(INSTANCE);
            Log.i("XYZ", "onOpen Called");
        }
    };

    private static void populateInitialData(BucketListRoomDatabase instance){
        BucketListRoomDatabase.databaseWriteExecutor.execute(()->{
            UserDAO userDAO = instance.userDAO();
            //create new user
            userDAO.insert(new User("abc@ait.com", "12121212","admin","admin",new Date() ));
            User user1 = userDAO.findByEmail("abc@ait.com");


            CategoryDAO categoryDao =instance.categoryDAO();
            //insert new category
            Category category1= new Category("Travel");

            BucketListItemDAO bucketListItemDao = instance.bucketListItemDAO();
            //insert new item
            BucketListItem bucketListItem1 = new BucketListItem("Japan","cherry blossom in japan",new Date(), true, 33.3, 3, category1.getId());
            Long bucketListItem1Id = bucketListItemDao.insert(bucketListItem1);

        });
    }
}
