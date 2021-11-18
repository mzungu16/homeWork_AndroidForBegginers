package com.example.geekbrains_androidforbegginers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class AllNotesFragment extends Fragment implements Serializable {

    private LinearLayout linearLayout;
    private List<Note> stringsSet;

    public AllNotesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(AddNoteFragment.SHARED_FILE_1, Context.MODE_PRIVATE);

        Set<String> ret = sharedPreferences.getStringSet(AddNoteFragment.KEY_FOR_SHAREDPREF, new HashSet<>());
        stringsSet = new ArrayList<>();
        for (String r : ret) {
            Note note = new Note(r);
            stringsSet.add(note);
        }

        stringsSet.sort(new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return (int) (o1.getDataOfCreate() - o2.getDataOfCreate());
            }
        });

        checkSetFromSharedPref(stringsSet);
    }

    private void checkSetFromSharedPref(List<Note> stringSet) {
        if (stringSet.isEmpty()) {
            Toast.makeText(requireActivity(), "Your note is empty", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(MainActivity.TAG, "Выводим - " + stringSet.toString());

            for (int i = 0; i < stringSet.size(); i++) {
                TextView titleText = new TextView(getContext());
                TextView descriptionText = new TextView(getContext());
                titleText.setTextSize(25);
                descriptionText.setTextSize(25);
                titleText.setPadding(2, 20, 2, 0);
                titleText.setText(String.format("%s", stringSet.get(i).getTitle()));
                descriptionText.setText(String.format("%s", stringSet.get(i).getDescription()));
                initPopUp(titleText, i);
                initPopUp(descriptionText, i);
                linearLayout.addView(titleText);
                linearLayout.addView(descriptionText);
            }
        }
    }

    private void initPopUp(TextView textView, int index) {
        textView.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), v);
            requireActivity().getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.popup_menu_clear) {
                    linearLayout.removeView(textView);
                    stringsSet.remove(index);
                    Log.d(MainActivity.TAG, stringsSet.toString());
                    recreateFragment();
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