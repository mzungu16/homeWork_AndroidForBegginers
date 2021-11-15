package com.example.geekbrains_androidforbegginers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllNotesFragment extends Fragment implements Serializable {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private LinearLayout linearLayout;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public AllNotesFragment() {
    }

    public static AllNotesFragment newInstance(String param1, String param2) {
        AllNotesFragment fragment = new AllNotesFragment();
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
        return inflater.inflate(R.layout.fragment_all_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = view.findViewById(R.id.main_layout2);
        sharedPreferences = requireActivity().getSharedPreferences(AddNoteFragment.SHARED_FILE_1, Context.MODE_PRIVATE);
        Set<String> ret = sharedPreferences.getStringSet(AddNoteFragment.KEY, new HashSet<>());
        if (ret.isEmpty()) {
            Toast.makeText(requireActivity(), "Your note is empty", Toast.LENGTH_SHORT).show();
        } else {
            for (String r : ret) {
                TextView textView = new TextView(getContext());
                textView.setTextSize(25);
                textView.setPadding(2, 20, 2, 0);
                textView.setText(String.format("%s", r));
                initPopUp(textView);
                linearLayout.addView(textView);
            }
        }

    }

    private void initPopUp(TextView textView) {
        textView.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), v);
            requireActivity().getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.popup_menu_clear) {
                    Toast.makeText(requireActivity(), "Clear", Toast.LENGTH_SHORT).show();
//                    linearLayout.removeView(textView);
//                    recreateFragment();
                } else {
                    Toast.makeText(requireActivity(), "Edit", Toast.LENGTH_SHORT).show();
                }
                return false;
            });
            popupMenu.show();
        });

    }

    private void recreateFragment() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(AllNotesFragment.this)
                .attach(AllNotesFragment.this)
                .commit();
    }
}