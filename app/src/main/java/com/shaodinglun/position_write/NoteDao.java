//NoteDao
package com.shaodinglun.position_write;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();

    @Query("SELECT * FROM notes WHERE category = :category")
    List<Note> getNotesByCategory(String category);

    @Query("SELECT * FROM notes WHERE date LIKE :date")
    List<Note> getNotesByTime(String date);

    @Query("SELECT * FROM notes WHERE content LIKE '%' || :content || '%'")
    List<Note> getNotesByContent(String content);

    @Query("SELECT * FROM notes WHERE id = :id")
    Note getNoteById(int id);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}


