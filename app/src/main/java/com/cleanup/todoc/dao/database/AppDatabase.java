package com.cleanup.todoc.dao.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Arrays;
import java.util.concurrent.Executors;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    // --- SINGLETON ---

    private static volatile AppDatabase INSTANCE;

    // --- DAO ---

    public abstract TaskDao mTaskDao();

    public abstract ProjectDao mProjectDao();

    // --- INSTANCE ---

    public static AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (AppDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),

                                    AppDatabase.class, "MyDatabase.db")
                            .allowMainThreadQueries()
                            .addCallback(prepopulateDatabase())

                            .build();

                }

            }

        }

        return INSTANCE;

    }

    private static Callback prepopulateDatabase() {

        return new Callback() {

            @Override

            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                super.onCreate(db);

                Executors.newSingleThreadExecutor().execute(() -> INSTANCE.mProjectDao().insertAll(
                        Arrays.asList(Project.getAllProjects()))
                );
            }
        };
    }
}
