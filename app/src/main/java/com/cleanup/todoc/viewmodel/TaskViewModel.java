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

    public long insert(Task task) {
        return repository.insert(task);
    }

    public int delete(Task task) {
        return repository.delete(task);
    }
}
