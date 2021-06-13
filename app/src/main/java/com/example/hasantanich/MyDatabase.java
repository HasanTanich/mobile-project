package com.example.hasantanich;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tasks.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase INSTANCE = null;

    public abstract MyDAO myDAO();

    public static MyDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,MyDatabase.class, "task_database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
