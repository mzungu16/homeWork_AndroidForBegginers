package com.example.geekbrains_androidforbegginers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            int mYear = year;
            int mMonth = month;
            int mDayOfMonth = dayOfMonth;
            String string = new StringBuilder().append(mDayOfMonth).append("-").append(mMonth).append("-").append(mYear).toString();
            Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
        });
        TextView textView = findViewById(R.id.txtView);
        EditText editText = findViewById(R.id.editText);
        SwitchCompat switchCompat = findViewById(R.id.switchButton);
        CheckBox checkBox = findViewById(R.id.checkedButton);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        Button button = findViewById(R.id.clearButton);

        button.setOnClickListener(v -> {
            textView.setText("");
            editText.setText("");
            switchCompat.setChecked(false);
            checkBox.setChecked(false);
            toggleButton.setChecked(false);
        });
    }
}