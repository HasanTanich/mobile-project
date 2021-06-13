package com.example.hasantanich;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String date;


    @Ignore
    public Tasks(String title, String date){
        this.title = title;
        this.date = date;
    }

    public Tasks(int id, String title, String date){
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

