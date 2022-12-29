package com.example.wagba;


import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {UserTable.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase
{
    public abstract UserDao userDao();
}

