package com.example.geekbrains_androidforbegginers;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BlankFragment extends Fragment {

    public BlankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNameOfNotesToFragment(view);
    }

    private void addNameOfNotesToFragment(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] nameOfNotes = getResources().getStringArray(R.array.nameOfNotes);
        for (int i = 0; i < nameOfNotes.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(nameOfNotes[i]);
            textView.setTextSize(30);
            int desToShow = i;
            textView.setOnClickListener(v -> {
                Toast.makeText(getContext(), String.valueOf(desToShow), Toast.LENGTH_SHORT).show();
                showDescriptionOfNote(desToShow);
                paintBackGround(desToShow);
            });
            linearLayout.addView(textView);
        }
    }

    private void paintBackGround(int desToShow) {
        LinearLayout linearLayout = getView().findViewById(R.id.linearLayout_fragment);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            if (desToShow == i) {
                linearLayout.getChildAt(i).setBackgroundColor(Color.GRAY);
            }
        }
    }

    private void showDescriptionOfNote(int desToShow) {
        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(desToShow);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.my_fragment, descriptionFragment)
                .addToBackStack(null)
                .commit();

    }
}