package com.example.wagba;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;
import androidx.room.RawQuery;


@Dao
public interface UserDao
{
    @Insert
    void insertUser(UserTable user);

    @Query("SELECT * FROM UserTable WHERE user_id= :user_id")
    boolean isUserExistByUID(String user_id);

    @Query("SELECT * FROM UserTable WHERE user_email= :user_email")
    boolean isUserExistByEmail(String user_email);

    @Query("SELECT * FROM UserTable WHERE user_name= :user_name")
    boolean isUserExistByName(String user_name);

    @Query("SELECT * FROM UserTable WHERE user_phone= :user_phone")
    boolean isUserExistByPhone(String user_phone);

    @Query("SELECT * FROM UserTable WHERE user_id = :user_id")
    UserTable getUser(int user_id);

    @Query("SELECT * FROM UserTable WHERE user_email = :user_email")
    UserTable getUserByMail(String user_email);

    @Insert
    void InsertUser(UserTable user);

    @Update
    void UpdateUser(UserTable user);

    @Delete
    void DeleteUser(UserTable user);


}
