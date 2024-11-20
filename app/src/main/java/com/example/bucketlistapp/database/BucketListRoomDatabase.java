package com.example.bucketlistapp.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bucketlistapp.bucketlist.BucketListItem;
import com.example.bucketlistapp.bucketlist.BucketListItemDAO;
import com.example.bucketlistapp.User;
import com.example.bucketlistapp.UserDAO;
import com.example.bucketlistapp.category.Category;
import com.example.bucketlistapp.category.CategoryDAO;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BucketListItem.class, User.class, Category.class},
    version = 3, exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class BucketListRoomDatabase extends RoomDatabase{

    //DAO's section
    public abstract BucketListItemDAO bucketListItemDAO();
    public abstract UserDAO userDAO();
    public abstract CategoryDAO categoryDAO();

    //The volatile keyword ensures that updates to a variable are propagated prectictably to other threads
    private static volatile BucketListRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE CATEGORY ADD COLUMN imageCategory TEXT");
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE BUCKETLIST ADD COLUMN status TEXT DEFAULT 'Pending'");
        }
    };

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


            CategoryDAO categoryDAO =instance.categoryDAO();
            //insert new category
            Category category1= new Category("Travel","travel.jpg");
            Long categoryId1 =categoryDAO.insert(category1);
            Category category2= new Category("Adventure","");
            Long categoryId2 =categoryDAO.insert(category2);
            Category category3= new Category("Goal","");
            Long categoryId3 =categoryDAO.insert(category3);




            BucketListItemDAO bucketListItemDao = instance.bucketListItemDAO();
            //insert new item
            BucketListItem bucketListItem1 = new BucketListItem("Japan","cherry blossom in japan",new Date(), "pending", 33.3, 3, category1.getId());
            Long bucketListItem1Id = bucketListItemDao.insert(bucketListItem1);

            BucketListItem bucketListItem2 = new BucketListItem("Japan","cherry blossom in japan",new Date(), "pending", 33.3, 3, 1);
            Long bucketListItem2Id = bucketListItemDao.insert(bucketListItem2);
            BucketListItem bucketListItem3 = new BucketListItem("Japan","cherry blossom in japan",new Date(), "pending", 33.3, 3, 2);
            Long bucketListItem3Id = bucketListItemDao.insert(bucketListItem2);

        });

    }
}
