package com.example.backlog.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.backlog.model.Game;

import java.util.List;

/**
 * A simple data access object interface to handle the database operation required to manipulate the Game entity
 */
@Dao
public interface GameDataAccessObject {

    @Insert
    void insert(Game game);

    @Insert
    void insert(List<Game> games);

    @Delete
    void delete(Game game);

    @Delete
    void delete(List<Game> games);

    @Update
    void update(Game game);

    // get all the fields in the table 'game'
    @Query("SELECT * FROM game")
    LiveData<List<Game>> getAllGames();
}
