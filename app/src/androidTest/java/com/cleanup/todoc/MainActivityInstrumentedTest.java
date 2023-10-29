package com.cleanup.todoc;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.dao.database.AppDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @author Gaëtan HERFRAY
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase database;
    private TaskDao taskDao;
    private ProjectDao projectDao;

    private final Project PROJECT_DEMO = new Project("Tartampion", 0xFFEADAD1);
    private final Task TASK_DEMO = new Task(1L, "tâche 1", new Date().getTime());


    private final Task TASKAAA = new Task(1L, "aaa Tâche example", 123);
    private final Task TASKZZZ = new Task(1L, "zzz Tâche example", 456);
    private final Task TASKHHH = new Task(1L, "hhh Tâche example", 789);

    @Before
    public void createDb() {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();

        taskDao = database.mTaskDao();
        projectDao = database.mProjectDao();
    }


    //TODO TRY TO USE LiveDataTestUtil.getValue for two tests are passed
    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void addAndRemoveTask() throws InterruptedException {

//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.post(() -> {
//            try {
        long projectId = projectDao.insert(PROJECT_DEMO);
        PROJECT_DEMO.setId(projectId);

        List<Task> listBeforeInsertion = LiveDataTestUtil.getValue(taskDao.getAllTasks());

        long taskId = taskDao.insert(TASK_DEMO);
        TASK_DEMO.setId(taskId);

        List<Task> listAfterInsertion = LiveDataTestUtil.getValue(taskDao.getAllTasks());


        assertEquals(listBeforeInsertion.size() + 1, listAfterInsertion.size());

        Task taskInserted = LiveDataTestUtil.getValue(taskDao.getTaskById(taskId));

        assertEquals(taskId, taskInserted.getId());

        taskDao.delete(taskInserted);

        assertEquals(0, LiveDataTestUtil.getValue(taskDao.getAllTasks()).size());
    }

    @Test
    public void sortTask() throws InterruptedException {


        long projectId = this.projectDao.insert(PROJECT_DEMO);
        PROJECT_DEMO.setId(projectId);

        long id = this.taskDao.insert(TASKAAA);
        TASKAAA.setId(id);

        id = this.taskDao.insert(TASKZZZ);
        TASKZZZ.setId(id);

        id = this.taskDao.insert(TASKHHH);
        TASKHHH.setId(id);

        List<Task> listAlphabetical = LiveDataTestUtil.getValue(taskDao.getAllTaskAZComparator());
        assertEquals("aaa Tâche example", listAlphabetical.get(0).getName());
        assertEquals("hhh Tâche example", listAlphabetical.get(1).getName());
        assertEquals("zzz Tâche example", listAlphabetical.get(2).getName());

        List<Task> listAlphabeticalInverted = LiveDataTestUtil.getValue(taskDao.getAllTaskZAComparator());
        assertEquals("zzz Tâche example", listAlphabeticalInverted.get(0).getName());
        assertEquals("hhh Tâche example", listAlphabeticalInverted.get(1).getName());
        assertEquals("aaa Tâche example", listAlphabeticalInverted.get(2).getName());

        List<Task> listOldFirst = LiveDataTestUtil.getValue(taskDao.getAllTaskOldFirstComparator());
        assertEquals("aaa Tâche example", listOldFirst.get(0).getName());
        assertEquals("zzz Tâche example", listOldFirst.get(1).getName());
        assertEquals("hhh Tâche example", listOldFirst.get(2).getName());

        List<Task> listRecentFirst = LiveDataTestUtil.getValue(taskDao.getAllTaskRecentFirstComparator());
        assertEquals("hhh Tâche example", listRecentFirst.get(0).getName());
        assertEquals("zzz Tâche example", listRecentFirst.get(1).getName());
        assertEquals("aaa Tâche example", listRecentFirst.get(2).getName());

    }
}
