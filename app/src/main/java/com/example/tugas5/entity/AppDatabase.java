package com.example.tugas5.entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tugas5.entity.Player;
import com.example.tugas5.entity.PlayerDAO;

@Database(entities = {Player.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDAO playerDAO();
}
