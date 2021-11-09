package com.example.geekbrains_androidforbegginers;

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
import android.widget.Button;
import android.widget.EditText;

public class AddNoteFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddNoteFragment() {
    }

    public static AddNoteFragment newInstance(String param1, String param2) {
        AddNoteFragment fragment = new AddNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
            Log.d(MainActivity.TAG, editText.getText().toString());
            Log.d(MainActivity.TAG, editText2.getText().toString());
            AllNotesFragment allNotesFragment = AllNotesFragment.newInstance(editText.getText().toString(), editText2.getText().toString());

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, allNotesFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

}