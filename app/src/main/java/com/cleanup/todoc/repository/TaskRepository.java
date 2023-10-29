package com.cleanup.todoc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.dao.database.AppDatabase;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.mTaskDao();
    }

    public LiveData<List<Task>> getTask() {
        return this.taskDao.getAllTask();
    }

    public long insert(Task task) {
        return taskDao.insert(task);
    }

    public int delete(Task task) {
        return taskDao.delete(task);
    }
}
