package com.example.wagba.Models;

public class users {
    String user_id;
    String user_name;
    String profile_picture;

    public users(String user_id, String user_name, String profile_picture) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.profile_picture = profile_picture;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
    public users(){}
}
