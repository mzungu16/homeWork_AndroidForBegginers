package com.example.geekbrains_androidforbegginers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.helloWorldId);
        EditText editText = findViewById(R.id.putSomeTextId);
        SwitchCompat switchCompat = findViewById(R.id.switchMeButtonId);
        CheckBox checkBox = findViewById(R.id.checkedMeButtonId);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        Button button = findViewById(R.id.clearButtonId);
        Button buttonNextActivity = findViewById(R.id.nextButtonId);

        button.setOnClickListener(v -> {
            textView.setText("");
            editText.setText("");
            switchCompat.setChecked(false);
            checkBox.setChecked(false);
            toggleButton.setChecked(false);
        });

        buttonNextActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        });
    }
}