//CreateNoteActivity
package com.shaodinglun.position_write;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private EditText categoryEditText;
    private EditText dateEditText;
    private AppDatabase db;
    private NoteDao noteDao;
    private CategoryDao categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleEditText = findViewById(R.id.note_title);
        contentEditText = findViewById(R.id.note_content);
        categoryEditText = findViewById(R.id.category_edit_text);
        dateEditText = findViewById(R.id.date_edit_text);
        Button saveButton = findViewById(R.id.save_button);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "notes.db")
                .fallbackToDestructiveMigration() // 这行代码会在数据库模式改变时销毁旧数据库
                .allowMainThreadQueries()
                .build();
        noteDao = db.noteDao();
        categoryDao = db.categoryDao();

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            String category = categoryEditText.getText().toString();
            String date = dateEditText.getText().toString();

            if (title.isEmpty() || content.isEmpty() || category.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            note.setCategory(category);
            note.setDate(date);

            noteDao.insert(note);
            finish();
        });
    }
}



