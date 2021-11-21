package com.example.geekbrains_androidforbegginers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllNotesFragment extends Fragment implements Serializable {
    private static final String ARG_PARAM1 = "param1";
    public static final String SHARED_KEY = "file_of_sharedPref";
    public static final String SHARED_KEY_SET = "shared_key_set";

    private List<Note> list = new ArrayList<>();

    public AllNotesFragment() {
    }

    public static AllNotesFragment newInstance(String param1) {
        AllNotesFragment fragment = new AllNotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        Set<String> sharedPreferencesStringSet = sharedPreferences.getStringSet(SHARED_KEY_SET, new HashSet<>());
        Set<String> stringSet = new HashSet<>(sharedPreferencesStringSet);

        if (getArguments() != null) {
            stringSet.add(getArguments().getString(ARG_PARAM1));
            Log.d(MainActivity.TAG, "Set после добавления строки" + stringSet.toString());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet(SHARED_KEY_SET, stringSet);
            editor.apply();
        }
        for (String note : stringSet) {
            list.add(new Note(note));
        }
        list.sort((o1, o2) -> (int) (o1.getDataOfCreate() - o2.getDataOfCreate()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (list.isEmpty()) {
            Toast.makeText(requireActivity(), "You've not notes", Toast.LENGTH_SHORT).show();
        } else {
            RecyclerView recyclerView = view.findViewById(R.id.recycler_fragment_conteiner);
            NotesAdapter notesAdapter = new NotesAdapter(list, getContext());
            notesAdapter.setClick(new NotesAdapter.ClickOnNoteListener() {
                @Override
                public void onClickNote(View view, int position) {
                    Toast.makeText(requireActivity(), list.get(position).getNote(), Toast.LENGTH_SHORT).show();
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(notesAdapter);
        }
    }


    private void initPopUp(TextView textView, int index) {
        textView.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), v);
            requireActivity().getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.popup_menu_clear) {
                    Toast.makeText(requireActivity(), "Clear", Toast.LENGTH_SHORT).show();
                } else {
                    showCustomAlertDialog(textView);
                    Toast.makeText(requireActivity(), "Edit", Toast.LENGTH_SHORT).show();
                }
                return false;
            });
            popupMenu.show();
        });

    }

    private void showCustomAlertDialog(TextView textView) {
        final View customView = getLayoutInflater().inflate(R.layout.alert_dialog_with_custom_view, null);
        EditText editText = customView.findViewById(R.id.edit_value);
        AppCompatButton commitButton = customView.findViewById(R.id.btnCommitId);
        commitButton.setOnClickListener(v1 -> {
            textView.setText(editText.getText().toString());
            Toast.makeText(requireActivity(), "Edit Button", Toast.LENGTH_SHORT).show();

        });
        new AlertDialog.Builder(requireActivity())
                .setCancelable(true)
                .setView(customView)
                .setTitle("Edit")
                .show();
    }

    private void recreateFragment() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(AllNotesFragment.this)
                .attach(AllNotesFragment.this)
                .commit();
    }
}