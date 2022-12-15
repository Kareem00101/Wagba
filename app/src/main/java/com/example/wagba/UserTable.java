package com.example.wagba;

import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "UserTable")
public class UserTable
{
    @PrimaryKey(autoGenerate = true)
    public int user_id;
    public String user_name;
    public String user_email;
    public String user_phone;
    public String profile_picture;

    public UserTable(int user_id, String user_name, String user_phone, String user_email, String profile_picture)
    {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.profile_picture = profile_picture;
    }

    public int get_user_id()
    {
        return user_id;
    }

    public void set_user_id(int user_id) {
        this.user_id = user_id;
    }

    public String get_user_name() {
        return user_name;
    }

    public void set_user_name(String user_name) {
        this.user_name = user_name;
    }

    public String get_user_email()
    {
        return user_email;
    }

    public void set_user_email(String user_email)
    {
        this.user_email = user_email;
    }

    public String get_user_phone()
    {
        return user_phone;
    }

    public void set_user_phone(String user_phone)
    {
        this.user_phone = user_phone;
    }

    public String get_profile_picture() {
        return profile_picture;
    }

    public void set_profile_picture(String profile_picture)
    {
        this.profile_picture = profile_picture;
    }

    public UserTable(){}
}
