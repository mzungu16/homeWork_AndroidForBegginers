package com.example.geekbrains_androidforbegginers;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddNoteFragment extends Fragment implements Serializable {
    public static final String SHARED_FILE_1 = "shared_file2";
    public static final String KEY_FOR_SHAREDPREF = "key1";

    public AddNoteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConstraintLayout constraintLayout = (ConstraintLayout) view;

        EditText title = (EditText) constraintLayout.getViewById(R.id.editText2);
        EditText description = (EditText) constraintLayout.getViewById(R.id.editTextTextMultiLine2);

        ArrayList<View> listOfButtons = constraintLayout.getTouchables();

        for (View btn : listOfButtons) {
            btn.setOnClickListener(v -> {
                if (btn.getId() == R.id.addBtn) {
                    addNote(title, description);
                } else {
                    clearNote(title, description);
                }
            });
        }
    }

    private void clearNote(EditText title, EditText description) {
        title.setText("");
        description.setText("");
    }

    private void addNote(EditText title, EditText description) {
        if (title.getText().toString().equals("") || description.getText().toString().equals("")) {
            Toast.makeText(requireActivity(), "You must add smth", Toast.LENGTH_SHORT).show();
        } else {
            Note note = new Note(title.getText().toString(), description.getText().toString(), System.currentTimeMillis());
            addNoteToSharedPref(note);
            Toast.makeText(requireActivity(), "Note Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void addNoteToSharedPref(Note note) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(SHARED_FILE_1, Context.MODE_PRIVATE);
        Set<String> stringSet = sharedPreferences.getStringSet(KEY_FOR_SHAREDPREF, new HashSet<>());
        stringSet.add(note.toString());
        Log.d(MainActivity.TAG, "Передаем - " + stringSet.toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KEY_FOR_SHAREDPREF, stringSet);
        editor.apply();
    }
}