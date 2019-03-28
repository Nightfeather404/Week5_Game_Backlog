package com.example.backlog.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.backlog.model.Game;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A class that contains all the logic of fetching data from the database
 */
public class GameRepository {

    private GameRoomDatabase gameRoomDatabase;
    private GameDataAccessObject gameDataAccessObject;
    private LiveData<List<Game>> gamesList;

    private Executor executor = Executors.newSingleThreadExecutor();

    public GameRepository (Context context){
        gameRoomDatabase = GameRoomDatabase.getDatabase(context);
        gameDataAccessObject = gameRoomDatabase.gameDataAccessObject();
        gamesList = gameDataAccessObject.getAllGames();
    }

    public LiveData<List<Game>> getAllGames() {
        return gamesList;
    }

    public void insert(final Game game){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDataAccessObject.insert(game);
            }
        });
    }

    public void insertAll(final List<Game> games){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDataAccessObject.insert(games);
            }
        });
    }

    public void delete(final Game game) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDataAccessObject.delete(game);
            }
        });
    }

    public void deleteAll(final List<Game> games) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDataAccessObject.delete(games);
            }
        });
    }

    public void update(final Game game) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDataAccessObject.update(game);
            }
        });
    }
}
