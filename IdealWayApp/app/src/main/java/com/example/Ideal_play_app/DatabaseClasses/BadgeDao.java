package com.example.Ideal_play_app.DatabaseClasses;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.Ideal_play_app.Badges;

import java.util.List;

@Dao
public interface BadgeDao {


    @Insert
    void insertSingleBadge(Badges badges);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleBadges (List<Badges> badgesList);

    @Query("SELECT * FROM Badges WHERE badgeID = :id ")
    Badges fetchBadgeByID (int id);

    @Query("SELECT * FROM Badges ORDER BY badgeID")
    List<Badges> getAll();

}

