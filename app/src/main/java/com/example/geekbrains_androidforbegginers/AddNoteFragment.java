package com.example.geekbrains_androidforbegginers;


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

public class AddNoteFragment extends Fragment implements Serializable {

    private final DataStoreClass dataStoreClass = new DataStoreClass();

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
        ArrayList<View> listOfButtons = constraintLayout.getTouchables();

        EditText title = (EditText) constraintLayout.getViewById(R.id.editText2);
        EditText description = (EditText) constraintLayout.getViewById(R.id.editTextTextMultiLine2);

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
            dataStoreClass.setTitleWithDes(title.getText().toString(), description.getText().toString());
            Log.d(MainActivity.TAG, dataStoreClass.getTitleWithDes().toString());
            Toast.makeText(requireActivity(), "Note Saved", Toast.LENGTH_SHORT).show();
        }
    }
}