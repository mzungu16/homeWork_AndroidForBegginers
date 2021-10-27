package com.example.geekbrains_androidforbegginers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "myLogs";
    Button btnOneClick, btnTwoClick, btnThreeClick, btnFourClick, btnFiveClick,
            btnSixClick, btnSevenClick, btnEightClick, btnNineClick, btnZeroClick, btnSumClick,
            btnResidualClick, btnMultiplyClick, btnDivisionClick, btnClear, btnEqualClick;
    EditText editText;
    SaveObject saveObject;
    Calculate calculate;
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOneClick = findViewById(R.id.btnOne);
        btnOneClick.setOnClickListener(this);

        btnTwoClick = findViewById(R.id.btnTwo);
        btnTwoClick.setOnClickListener(this);

        btnThreeClick = findViewById(R.id.btnThree);
        btnThreeClick.setOnClickListener(this);

        btnFourClick = findViewById(R.id.btnFour);
        btnFourClick.setOnClickListener(this);

        btnFiveClick = findViewById(R.id.btnFive);
        btnFiveClick.setOnClickListener(this);

        btnSixClick = findViewById(R.id.btnSix);
        btnSixClick.setOnClickListener(this);

        btnSevenClick = findViewById(R.id.btnSeven);
        btnSevenClick.setOnClickListener(this);

        btnEightClick = findViewById(R.id.btnEight);
        btnEightClick.setOnClickListener(this);

        btnNineClick = findViewById(R.id.btnNine);
        btnNineClick.setOnClickListener(this);

        btnZeroClick = findViewById(R.id.btnZero);
        btnZeroClick.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnSumClick = findViewById(R.id.btnSum);
        btnSumClick.setOnClickListener(this);

        btnResidualClick = findViewById(R.id.btnResidual);
        btnResidualClick.setOnClickListener(this);

        btnMultiplyClick = findViewById(R.id.btnMultiply);
        btnMultiplyClick.setOnClickListener(this);

        btnDivisionClick = findViewById(R.id.btnDivision);
        btnDivisionClick.setOnClickListener(this);

        btnEqualClick = findViewById(R.id.btnEqual);
        btnEqualClick.setOnClickListener(this);

        saveObject = new SaveObject();
        calculate = new Calculate();
        decimalFormat = new DecimalFormat("##.00");


        editText = findViewById(R.id.editText1);
        editText.setShowSoftInputOnFocus(false);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        saveObject = (SaveObject) savedInstanceState.getSerializable("key");
        updateEditText2(saveObject.getArrayList());
    }

    private void updateEditText2(ArrayList<String> arrayList) {
        editText.setText("");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            string = string.append(arrayList.get(i));
        }
        editText.setText(String.format("%s", string));
    }

    private void updateEditText(String s) {
        String oldString = editText.getText().toString();
        editText.setText(String.format("%s%s", oldString, s));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                updateEditText(btnOneClick.getText().toString());
                saveObject.setArrayList(btnOneClick.getText().toString());
                break;
            case R.id.btnTwo:
                updateEditText(btnTwoClick.getText().toString());
                saveObject.setArrayList(btnTwoClick.getText().toString());
                break;
            case R.id.btnThree:
                updateEditText(btnThreeClick.getText().toString());
                saveObject.setArrayList(btnThreeClick.getText().toString());
                break;
            case R.id.btnFour:
                updateEditText(btnFourClick.getText().toString());
                saveObject.setArrayList(btnFourClick.getText().toString());
                break;
            case R.id.btnFive:
                updateEditText(btnFiveClick.getText().toString());
                saveObject.setArrayList(btnFiveClick.getText().toString());
                break;
            case R.id.btnSix:
                updateEditText(btnSixClick.getText().toString());
                saveObject.setArrayList(btnSixClick.getText().toString());
                break;
            case R.id.btnSeven:
                updateEditText(btnSevenClick.getText().toString());
                saveObject.setArrayList(btnSevenClick.getText().toString());
                break;
            case R.id.btnEight:
                updateEditText(btnEightClick.getText().toString());
                saveObject.setArrayList(btnEightClick.getText().toString());
                break;
            case R.id.btnNine:
                updateEditText(btnNineClick.getText().toString());
                saveObject.setArrayList(btnNineClick.getText().toString());
                break;
            case R.id.btnZero:
                updateEditText(btnZeroClick.getText().toString());
                saveObject.setArrayList(btnZeroClick.getText().toString());
                break;
            case R.id.btnClear:
                editText.setText("");
                saveObject.setArrayList();
                calculate.emptyMethod();
                break;
            case R.id.btnSum:
                updateEditText(btnSumClick.getText().toString());
                saveObject.setArrayList(btnSumClick.getText().toString());
                break;
            case R.id.btnResidual:
                updateEditText(btnResidualClick.getText().toString());
                saveObject.setArrayList(btnResidualClick.getText().toString());
                break;
            case R.id.btnMultiply:
                updateEditText(btnMultiplyClick.getText().toString());
                saveObject.setArrayList(btnMultiplyClick.getText().toString());
                break;
            case R.id.btnDivision:
                updateEditText(btnDivisionClick.getText().toString());
                saveObject.setArrayList(btnDivisionClick.getText().toString());
                break;
            case R.id.btnEqual:
                double result = 0;
                result = calculate.calculateResult(saveObject.getArrayList());
                Log.d(TAG, "Result " + result);
                editText.setText(String.format("%s",String.valueOf(decimalFormat.format(result))));
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("key", saveObject);
    }
}