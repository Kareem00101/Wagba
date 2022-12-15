package com.example.wagba;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;


@Dao
public interface UserDao
{
    @Insert
    void insertUser(UserTable user);

    @Query("SELECT * FROM UserTable WHERE user_id = :user_id")
    boolean isUserExist(int user_id);

    @Query("SELECT * FROM UserTable WHERE user_name= :user_name")
    boolean isUserExistByName(String user_name);

    @Query("SELECT * FROM UserTable WHERE user_email= :user_email")
    boolean isUserExistByEmail(String user_email);

    @Query("SELECT * FROM UserTable WHERE user_phone= :user_phone")
    boolean isUserExistByPhone(String user_phone);
}
