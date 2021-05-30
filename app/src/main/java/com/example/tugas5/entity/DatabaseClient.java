package com.example.tugas5.entity;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private static DatabaseClient databaseClient;
    private AppDatabase appDatabase;

    private DatabaseClient(Context context){
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "player_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (databaseClient == null){
            databaseClient = new DatabaseClient(context);
        }
        return databaseClient;
    }

    public static DatabaseClient getInstance() {
        if (databaseClient != null) {
            return databaseClient;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
