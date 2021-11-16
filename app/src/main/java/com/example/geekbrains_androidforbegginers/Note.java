package com.example.geekbrains_androidforbegginers;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class Note {
    private String title;
    private String description;
    private long dataOfCreate;

    public Note(String title, String description, long dataOfCreate) {
        this.title = title;
        this.description = description;
        this.dataOfCreate = dataOfCreate;
    }

    public Note(String r) {
        String[] data = r.split("\\|");
        this.title = data[0];
        this.description = data[1];
        this.dataOfCreate = Long.parseLong(data[2]);
    }

    @Override
    public String toString() {
        return String.join("|", title, description, String.valueOf(dataOfCreate));
    }

    public long getDataOfCreate() {
        return dataOfCreate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
