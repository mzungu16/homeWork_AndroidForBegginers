package com.example.geekbrains_androidforbegginers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStoreClass implements Serializable {
    private final HashMap<String, String> titleWithDes = new HashMap<>();

    public DataStoreClass() {
    }

    public void setTitleWithDes(String title, String description) {
        this.titleWithDes.put(title, description);
    }

    public void removeTitleWithDes(String key) {
        this.titleWithDes.remove(key);
    }

    public HashMap<String, String> getTitleWithDes() {
        return titleWithDes;
    }

    /*public void saveData (){
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE,titleWithDes);

    }*/

}
