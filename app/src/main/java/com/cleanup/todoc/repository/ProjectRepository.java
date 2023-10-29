package com.cleanup.todoc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.database.AppDatabase;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private final ProjectDao projectDao;
    private final LiveData<List<Project>> allProjects;

    public ProjectRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        projectDao = db.mProjectDao();
        allProjects = projectDao.getAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public LiveData<Project> getProjectById(long id) {
        return projectDao.getProjectById(id);
    }

    public long insert(Project project) {
        return projectDao.insert(project);
    }

}