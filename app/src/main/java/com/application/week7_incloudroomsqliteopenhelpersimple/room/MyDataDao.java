package com.application.week7_incloudroomsqliteopenhelpersimple.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDataDao {

    @Insert
    void insert(MyData myData);

    @Update
    void update(MyData myData);

    @Delete
    void delete(MyData myData);

    @Query("SELECT * FROM mytable")
    List<MyData> getAllData();


}
