package com.example.hasantanich;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    public void addTask(Tasks task);

    @Query("SELECT * FROM tasks")
    public List<Tasks> getAllTasks();

    @Query("SELECT * FROM tasks where id = :taskId LIMIT 1")
    public Tasks getTask(int taskId);

    @Delete
    public void deleteTask(Tasks task);

    @Update
    public void updateTask(Tasks task);
}
