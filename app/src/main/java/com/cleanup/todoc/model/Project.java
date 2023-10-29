package com.cleanup.todoc.model;


import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <p>Models for project in which tasks are included.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(tableName = "project_table")
public class Project {
    /**
     * The unique identifier of the project
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    /**
     * The name of the project
     */
    @NonNull
    private String name;

    /**
     * The hex (ARGB) code of the color associated to the project
     */
    @ColorInt
    private final int color;

    /**
     * Instantiates a new Project.
     *
     * @param name  the name of the project to set
     * @param color the hex (ARGB) code of the color associated to the project to set
     */
    public Project(@NonNull String name, @ColorInt int color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Returns all the projects of the application.
     *
     * @return all the projects of the application
     */
    @NonNull
    public static Project[] getAllProjects() {
        return new Project[]{
                new Project("Projet Tartampion", 0xFFEADAD1),
                new Project("Projet Lucidia", 0xFFB4CDBA),
                new Project("Projet Circus", 0xFFA3CED2),
        };
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }


}
