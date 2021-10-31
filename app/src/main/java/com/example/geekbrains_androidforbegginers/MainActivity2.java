package com.example.geekbrains_androidforbegginers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity2 extends AppCompatActivity {
    public static final String PREF_NAME = "name";
    public static final String THEME_NAME = "nameOfTheme";
    public static final int DARK = R.style.Theme_MaterialComponents_DayNight_DarkActionBar;
    public static final int LIGHT = R.style.Theme_MaterialComponents_DayNight_NoActionBar;
    public static boolean SET_CHECKED = false;
    ToggleButton toggleButton;
    Button returnBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(loadTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        returnBackButton = findViewById(R.id.returnBtn);
        returnBackButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });

        toggleButton = findViewById(R.id.toggle);
        switchThemeButton();
    }

    private void switchThemeButton() {
        if(SET_CHECKED){
            toggleButton.setChecked(true);
        }
        toggleButton.setOnClickListener(v -> {
            if (toggleButton.isChecked()) {
                saveCodeOfTheme(DARK);
                SET_CHECKED = true;
            } else {
                saveCodeOfTheme(LIGHT);
                SET_CHECKED = false;
            }
            recreate();
        });
    }


    private void saveCodeOfTheme(int code) {
        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().putInt(THEME_NAME, code).apply();
    }

    public static int loadTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt(THEME_NAME, LIGHT);
    }
}