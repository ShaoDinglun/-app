//MainActivity
package com.shaodinglun.position_write;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteAdapter noteAdapter;
    private EditText categoryText;
    private EditText timeText;
    private EditText contentText;
    private NoteDao noteDao;
    private CategoryDao categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "notes.db")
                .fallbackToDestructiveMigration() // 这行代码会在数据库模式改变时销毁旧数据库
                .allowMainThreadQueries()
                .build();
        noteDao = db.noteDao();
        categoryDao = db.categoryDao();

        categoryText = findViewById(R.id.category_edit_text);
        timeText = findViewById(R.id.time_edit_text);
        contentText = findViewById(R.id.content_edit_text);
        Button filterButton = findViewById(R.id.filter_button);
        Button createNoteButton = findViewById(R.id.create_note_button);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterButton.setOnClickListener(v -> {
            String enteredCategory = categoryText.getText().toString().trim();
            String enteredTime = timeText.getText().toString().trim();
            String enteredContent = contentText.getText().toString().trim();

            if (enteredCategory.isEmpty() && enteredTime.isEmpty() && enteredContent.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter at least one filter criterion", Toast.LENGTH_SHORT).show();
                return;
            }

            List<Note> notes;
            if (!enteredCategory.isEmpty()) {
                notes = noteDao.getNotesByCategory(enteredCategory);
            } else if (!enteredTime.isEmpty()) {
                notes = noteDao.getNotesByTime(enteredTime);
            } else {
                notes = noteDao.getNotesByContent(enteredContent);
            }

            noteAdapter = new NoteAdapter(notes, note -> {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("note_id", note.getId());
                startActivity(intent);
            });
            recyclerView.setAdapter(noteAdapter);
        });

        createNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> notes = noteDao.getAllNotes();
        noteAdapter = new NoteAdapter(notes, note -> {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            intent.putExtra("note_id", note.getId());
            startActivity(intent);
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(noteAdapter);
    }
}


