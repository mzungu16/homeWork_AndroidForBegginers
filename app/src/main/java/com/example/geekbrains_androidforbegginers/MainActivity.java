package com.example.geekbrains_androidforbegginers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logs";
    public static final String PREF_NAME = "name";
    public static final String THEME_NAME = "nameOfTheme";
    public static final int DARK = R.style.Theme_MaterialComponents_DayNight_DarkActionBar;
    public static final int LIGHT = R.style.Theme_MaterialComponents_DayNight_NoActionBar;
    EditText editText;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(loadTheme());
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        toggleButton = findViewById(R.id.password_toggle);
        ArrayList<View> listOfButtons;
        listOfButtons = findViewById(R.id.tableLayout).getTouchables();
        editText.setShowSoftInputOnFocus(false);
        initMethod(listOfButtons);
        switchTheme();

    }

    private void initMethod(ArrayList<View> listOfButtons) {
        for (int i = 0; i < listOfButtons.size(); i++) {
            Button btn = (Button) listOfButtons.get(i);
            btn.setOnClickListener(v -> {
                updateText(btn.getText().toString());
            });
        }
    }

    private void switchTheme() {
        toggleButton.setOnClickListener(v -> {
            if (toggleButton.isChecked()) {
                saveCodeOfTheme(DARK);
                toggleButton.setChecked(true);
            } else {
                saveCodeOfTheme(LIGHT);
            }
            recreate();
        });
    }

    private void saveCodeOfTheme(int code) {
        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().putInt(THEME_NAME, code).apply();
    }

    private int loadTheme() {
        return getSharedPreferences(PREF_NAME, MODE_PRIVATE).getInt(THEME_NAME, LIGHT);
    }

    private void updateText(String s) {
        String oldString = editText.getText().toString();
        editText.setText(String.format("%s%s", oldString, s));
    }
}