package com.example.geekbrains_androidforbegginers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final List<Note> noteList;
    private ClickOnNoteListener click;


    public NotesAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void setClick(ClickOnNoteListener click) {
        this.click = click;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView textView = itemView.findViewById(R.id.textViewId);

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Note note) {
            textView.setText(note.getNote());
            textView.setTextSize(40);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setOnClickListener(v -> {
                if (click != null){
                    click.onClickNote(v,getAdapterPosition());
                }
            });
        }

    }
    interface ClickOnNoteListener {
        void onClickNote(View view, int position);
    }

}
