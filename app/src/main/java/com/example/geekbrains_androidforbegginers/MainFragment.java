package com.example.geekbrains_androidforbegginers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) view;
        ArrayList<View> buttonList = linearLayout.getTouchables();
        for (View button : buttonList) {
            button.setOnClickListener(v -> {
                if (button.getId() == R.id.checkNoteBtnId) {
                    goToCheckNotesFragment();
                } else if (button.getId() == R.id.addNoteBtnId) {
                    goToAddNoteFragment();
                }
            });
        }
    }

    private void goToAddNoteFragment() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, new AddNoteFragment())
                .addToBackStack(null)
                .commit();
    }

    private void goToCheckNotesFragment() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, new AllNotesFragment())
                .addToBackStack(null)
                .commit();
    }
}