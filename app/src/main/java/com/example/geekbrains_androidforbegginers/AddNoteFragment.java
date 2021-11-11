package com.example.geekbrains_androidforbegginers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class AddNoteFragment extends Fragment implements Serializable {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static HashMap<String, String> hashMap = new HashMap<>();

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
        EditText editText = (EditText) constraintLayout.getViewById(R.id.editText2);
        EditText editText2 = (EditText) constraintLayout.getViewById(R.id.editTextTextMultiLine2);
        AppCompatButton appCompatButton = (AppCompatButton) constraintLayout.getViewById(R.id.addBtn);

        appCompatButton.setOnClickListener(v -> {
            hashMap.put(editText.getText().toString().toUpperCase(Locale.ROOT), editText2.getText().toString());
            Log.d(MainActivity.TAG, hashMap.toString());
            Toast.makeText(requireActivity(), "Note Saved", Toast.LENGTH_LONG).show();
        });
    }

}