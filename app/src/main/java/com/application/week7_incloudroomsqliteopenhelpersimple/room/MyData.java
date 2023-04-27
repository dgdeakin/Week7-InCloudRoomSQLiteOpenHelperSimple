package com.application.week7_incloudroomsqliteopenhelpersimple.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "mytable")
public class MyData {

    @PrimaryKey(autoGenerate = true)
    int id;

    String name;

    public MyData(String name) {
        this.name = name;
    }

    @Ignore
    public MyData(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
