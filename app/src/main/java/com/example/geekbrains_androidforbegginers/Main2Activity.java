package com.example.geekbrains_androidforbegginers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_for_begginers_lesson1);
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
    }
}