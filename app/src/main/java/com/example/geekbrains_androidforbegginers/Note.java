package com.example.geekbrains_androidforbegginers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Note {
    private String note;
    private long dataOfCreate;

    public Note(String note, long dataOfCreate) {
        this.note = note;
        this.dataOfCreate = dataOfCreate;
    }

    public Note(String note) {
        String[] data = note.split("\\|");
//        Log.d(MainActivity.TAG,Arrays.toString(data));
        this.note = data[0];
        this.dataOfCreate = Long.parseLong(data[1]);
    }

    public String getNote() {
        return note;
    }

    public long getDataOfCreate() {
        return dataOfCreate;
    }

    public String toString() {
        return String.join("|", note, String.valueOf(dataOfCreate));
    }
}
