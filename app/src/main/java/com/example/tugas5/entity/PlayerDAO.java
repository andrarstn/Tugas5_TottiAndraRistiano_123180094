package com.example.tugas5.entity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDAO {
    @Query("SELECT * FROM tPlayer")
    LiveData<List<Player>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Player... players);

    @Query("DELETE FROM tPlayer")
    void deleteAllData();

    @Query("DELETE FROM tPlayer WHERE uid= :uid")
    void deleteSingleData(int uid);

    @Query("UPDATE tPlayer SET name = :name, age = :age, number = :number, position = :position WHERE uid = :uid")
    void updateData(String name, int age, int number, String position, int uid);
}
