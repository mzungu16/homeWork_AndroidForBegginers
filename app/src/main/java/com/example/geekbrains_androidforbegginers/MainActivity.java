package com.example.geekbrains_androidforbegginers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new AllNotesFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 1) {
            getSupportFragmentManager()
                    .popBackStack();
        } else {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("Do you really want to quit?")
                    .setTitle("Quit")
                    .setPositiveButton("Ok", ((dialog, which) -> {
                        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences(AllNotesFragment.SHARED_KEY, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        finish();
                    }))
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.toolbar_menu_id) {
          getSupportFragmentManager()
                  .beginTransaction()
                  .add(R.id.fragment_container, new AddNoteFragment())
                  .addToBackStack(null)
                  .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}