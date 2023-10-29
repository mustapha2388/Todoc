package com.cleanup.todoc.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TaskRepository repository;

    private final LiveData<List<Task>> allTasks;

    public TaskViewModel(Application application) {
        super(application);
        repository = new TaskRepository(application);
        this.allTasks = repository.getTask();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<Task> getTaskById(long id) {
        return repository.getTaskById(id);
    }

    public long insert(Task task) {
        return repository.insert(task);
    }

    public int delete(Task task) {
        return repository.delete(task);
    }

    public LiveData<List<Task>> getAllTaskAZComparator() {
        return repository.getAllTaskAZComparator();
    }

    public LiveData<List<Task>> getAllTaskZAComparator() {
        return repository.getAllTaskZAComparator();
    }

    public LiveData<List<Task>> getAllTaskRecentFirstComparator() {
        return repository.getAllTaskRecentFirstComparator();
    }

    public LiveData<List<Task>> getAllTaskOldFirstComparator() {
        return repository.getAllTaskOldFirstComparator();
    }
}
