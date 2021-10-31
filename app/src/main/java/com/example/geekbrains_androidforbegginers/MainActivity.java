package com.example.geekbrains_androidforbegginers;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logs";
    EditText editText;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(MainActivity2.loadTheme(getApplicationContext()));
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText.setShowSoftInputOnFocus(false);

          ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                recreate();
                            }
                        });

        settingsButton = findViewById(R.id.settingsBtn);
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            someActivityResultLauncher.launch(intent);
        });

        ArrayList<View> listOfButtons;
        listOfButtons = findViewById(R.id.tableLayout).getTouchables();

        initMethod(listOfButtons);
    }

    private void initMethod(ArrayList<View> listOfButtons) {
        for (int i = 0; i < listOfButtons.size(); i++) {
            Button btn = (Button) listOfButtons.get(i);
            btn.setOnClickListener(v -> {
                updateText(btn.getText().toString());
            });
        }
    }

    private void updateText(String s) {
        String oldString = editText.getText().toString();
        editText.setText(String.format("%s%s", oldString, s));
    }
}