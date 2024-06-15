//NoteAdapter
package com.shaodinglun.position_write;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public NoteAdapter(List<Note> notes, OnItemClickListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.contentTextView.setText(note.getContent());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(note));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title);
            contentTextView = itemView.findViewById(R.id.note_content);
        }
    }
}


