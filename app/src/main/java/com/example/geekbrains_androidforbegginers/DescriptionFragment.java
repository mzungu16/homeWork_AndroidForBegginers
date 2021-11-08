package com.example.geekbrains_androidforbegginers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int desToShow;

    public DescriptionFragment() {
    }

    public static DescriptionFragment newInstance(int desToShow) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, desToShow);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            desToShow = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout frameLayout = (FrameLayout) view;

        TextView textView = new TextView(requireActivity());
        textView.setTextSize(30);
        String[] stringsOfDes = getResources().getStringArray(R.array.descriptionOfNotes);

        textView.setText(stringsOfDes[desToShow]);

        frameLayout.addView(textView);
    }
}