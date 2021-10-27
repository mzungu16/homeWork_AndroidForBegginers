package com.example.geekbrains_androidforbegginers;

import android.util.Log;

import java.util.ArrayList;

public class Calculate {
    private StringBuilder holeString = new StringBuilder();
    private String firstStringValue, secondStringValue;
    private int indexOfElement = 0, value1, value2;

    public Calculate() {
    }

    public void emptyMethod() {
        this.firstStringValue = null;
        this.secondStringValue = null;
        this.indexOfElement = 0;
        this.holeString = new StringBuilder();
        this.value1 = 0;
        this.value2 = 0;
    }

    private void separateValues(ArrayList<String> arrayList) {
        Log.d(MainActivity.TAG, "doSeparateValuesMethod");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals("+") || arrayList.get(i).equals("-") ||
                    arrayList.get(i).equals("*") || arrayList.get(i).equals("/")) {
                this.indexOfElement = i;
            }
            this.holeString.append(arrayList.get(i));
        }
        this.firstStringValue = this.holeString.substring(0, indexOfElement);
        this.secondStringValue = this.holeString.substring(indexOfElement + 1);
    }

    public double calculateResult(ArrayList<String> arrayList) {
        Log.d(MainActivity.TAG, "doCalculateMethod");
        separateValues(arrayList);
        this.value1 = Integer.parseInt(this.firstStringValue);
        Log.d(MainActivity.TAG, String.valueOf(this.value1));
        this.value2 = Integer.parseInt(this.secondStringValue);
        Log.d(MainActivity.TAG, String.valueOf(this.value2));
        switch (arrayList.get(this.indexOfElement)) {
            case "+":
                return this.value1 + this.value2;
            case "-":
                return this.value1 - this.value2;
            case "*":
                return this.value1 * this.value2;
            case "/":
                return (double)this.value1 / this.value2;
        }
        return 0;
    }

}
