package com.application.week7_incloudroomsqliteopenhelpersimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.application.week7_incloudroomsqliteopenhelpersimple.databinding.ActivityMainBinding;
import com.application.week7_incloudroomsqliteopenhelpersimple.room.MyData;
import com.application.week7_incloudroomsqliteopenhelpersimple.room.MyDataDao;
import com.application.week7_incloudroomsqliteopenhelpersimple.room.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    DatabaseHelper databaseHelper;

    MyDatabase myDatabase;
    MyDataDao myDataDao;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper = new DatabaseHelper(this);

        myDatabase = Room.databaseBuilder(this, MyDatabase.class, "myroomdatabase.db").build();
        myDataDao = myDatabase.myDataDao();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                ContentValues contentValues = new ContentValues();
//                contentValues.put("name", binding.editTextText.getText().toString());
//                db.insert("mytable", null, contentValues);

                MyData myData = new MyData(binding.editTextText.getText().toString());

                databaseWriteExecutor.execute(()->{
                    myDataDao.insert(myData);
                });

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//
//                values.put("name", binding.editTextText.getText().toString());
//                db.update(
//                        "mytable",
//                        values,
//                        "id=?",
//                        new String[]{"2"}
//                );

                MyData myData = new MyData("New Updated Value");
                myData.setId(3);
                databaseWriteExecutor.execute(()-> {

                    myDataDao.update(myData);
                });


            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                db.delete("mytable", "id=?", new String[]{"2"});

                MyData myData = new MyData();
                myData.setId(2);

                databaseWriteExecutor.execute(()->{
                    myDataDao.delete(myData);
                });

            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SQLiteDatabase db = databaseHelper.getReadableDatabase();
//
//                Cursor cursor = db.query(
//                        "mytable",
//                        null, null, null, null, null,null);
//
//                while(cursor.moveToNext()){
//                    int index = cursor.getColumnIndex("name");
//                    String value = cursor.getString(index);
//                    Log.i("Values", value);
//                }

                databaseWriteExecutor.execute(()->{
                    List<MyData> dataList = myDataDao.getAllData();
                    for(MyData data: dataList){
                        Log.i("Data", data.getName());
                    }
                });


            }
        });



    }
}