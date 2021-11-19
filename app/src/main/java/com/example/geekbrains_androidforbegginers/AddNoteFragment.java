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

        EditText editValue = (EditText) constraintLayout.getViewById(R.id.noteId);

        view.findViewById(R.id.addBtn).setOnClickListener(v -> {
            if (editValue.getText().toString().equals("")) {
                Toast.makeText(requireActivity(), "Must fill Note", Toast.LENGTH_SHORT).show();
            } else {
                Note note1 = new Note(editValue.getText().toString(), System.currentTimeMillis());

                Log.d(MainActivity.TAG, note1.toString());

                AllNotesFragment allNotesFragment = AllNotesFragment.newInstance(note1.toString());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, allNotesFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}