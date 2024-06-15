//EditNoteActivity
package com.shaodinglun.position_write;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class EditNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private EditText categoryEditText;
    private EditText dateEditText;
    private AppDatabase db;
    private NoteDao noteDao;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        titleEditText = findViewById(R.id.note_title);
        contentEditText = findViewById(R.id.note_content);
        categoryEditText = findViewById(R.id.category_edit_text);
        dateEditText = findViewById(R.id.date_edit_text);
        Button saveButton = findViewById(R.id.save_button);
        Button deleteButton = findViewById(R.id.delete_button);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "notes.db")
                .fallbackToDestructiveMigration() // 这行代码会在数据库模式改变时销毁旧数据库
                .allowMainThreadQueries()
                .build();
        noteDao = db.noteDao();

        int noteId = getIntent().getIntExtra("note_id", -1);
        note = noteDao.getNoteById(noteId);

        if (note != null) {
            titleEditText.setText(note.getTitle());
            contentEditText.setText(note.getContent());
            categoryEditText.setText(note.getCategory());
            dateEditText.setText(note.getDate());
        }

        saveButton.setOnClickListener(v -> {
            if (note != null) {
                note.setTitle(titleEditText.getText().toString());
                note.setContent(contentEditText.getText().toString());
                note.setCategory(categoryEditText.getText().toString());
                note.setDate(dateEditText.getText().toString());
                noteDao.update(note);
            }
            finish();
        });

        deleteButton.setOnClickListener(v -> {
            if (note != null) {
                noteDao.delete(note);
            }
            finish();
        });
    }
}


