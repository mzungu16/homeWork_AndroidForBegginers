package com.example.geekbrains_androidforbegginers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final List<Note> noteList;
    private final Context context;
    private ClickOnNoteListener click;


    public NotesAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
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
            textView.setTextSize(context.getResources().getDimension(R.dimen.note_text));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setOnLongClickListener(v -> {
                if (click != null) {
                    click.onClickNote(v, getAdapterPosition());
                }
                return false;
            });
        }

    }



    interface ClickOnNoteListener {
        void onClickNote(View view, int position);
    }

}
