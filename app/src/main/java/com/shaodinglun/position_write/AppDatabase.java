package com.shaodinglun.position_write;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class, Category.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    public abstract CategoryDao categoryDao();
}


