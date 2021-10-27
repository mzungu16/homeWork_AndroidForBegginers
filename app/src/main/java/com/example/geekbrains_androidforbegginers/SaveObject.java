package com.example.geekbrains_androidforbegginers;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveObject implements Serializable {
    ArrayList<String> arrayList = new ArrayList<>();
    public SaveObject() {
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(String s) {
        this.arrayList.add(s);
    }

    public void setArrayList(){
        this.arrayList.clear();
    }
}
