package com.example.geekbrains_androidforbegginers;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class DataStoreClass {
    private List<String> titleList;
    private List<String> desList;
    public DataStoreClass() {
    }


    public void setTitleList(EditText editText) {
        this.titleList.add(editText.getText().toString());
    }

    public void setDesList(ArrayList<String> desList) {
        this.desList = desList;
    }


    public List<String> getTitleList() {
        return titleList;
    }
    public List<String> getDesList() {
        return desList;
    }

}
