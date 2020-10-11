package com.example.Ideal_play_app.DatabaseClasses;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.Ideal_play_app.Users;

@Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao mUserDao() ;
}
