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
        return this.taskDao.getAllTasks();
    }

    public long insert(Task task) {
        return taskDao.insert(task);
    }

    public int delete(Task task) {
        return taskDao.delete(task);
    }

    public LiveData<Task> getTaskById(long id) {
        return this.taskDao.getTaskById(id);
    }

    public LiveData<List<Task>> getAllTaskAZComparator() {
        return this.taskDao.getAllTaskAZComparator();
    }

    public LiveData<List<Task>> getAllTaskZAComparator() {
        return this.taskDao.getAllTaskZAComparator();
    }

    public LiveData<List<Task>> getAllTaskOldFirstComparator() {
        return this.taskDao.getAllTaskOldFirstComparator();
    }

    public LiveData<List<Task>> getAllTaskRecentFirstComparator() {
        return this.taskDao.getAllTaskRecentFirstComparator();
    }

}
