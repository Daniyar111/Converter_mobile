package com.example.saint.convertproject;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LengthActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextFirst, editTextSecond;
    private Spinner spinnerFirst, spinnerSecond;
    private ArrayList<String> spinners;
    private double x = LengthSolver.MM;
    private double y = LengthSolver.MM;
    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    private SQLiteModel sqLiteModel = new SQLiteModel();
    private int a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Conversion");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextFirst = findViewById(R.id.editTextFirst);
        editTextSecond = findViewById(R.id.editTextSecond);
        spinnerFirst = findViewById(R.id.spinnerFirst);
        spinnerSecond = findViewById(R.id.spinnerSecond);

        String[] arrayLength = getResources().getStringArray(R.array.length);
        spinners = new ArrayList<>();

        for (int i = 0; i < arrayLength.length; i++) {
            spinners.add(arrayLength[i]);
        }
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, spinners);
        spinnerFirst.setAdapter(spinnerAdapter);
        spinnerSecond.setAdapter(spinnerAdapter);

        spinnerFirst.setOnItemSelectedListener(firstItemSelected);
        spinnerSecond.setOnItemSelectedListener(secondItemSelected);

        editTextFirst.addTextChangedListener(firstTextListener);
        editTextSecond.addTextChangedListener(secondTextListener);

        if(sqLiteModel != null){
            sqLiteModel = sqLiteHelper.getData();
            editTextFirst.setText(sqLiteModel.getEditTextFirst());
            editTextSecond.setText(sqLiteModel.getEditTextSecond());
            spinnerFirst.setSelection(sqLiteModel.getSpinnerFirst());
            spinnerSecond.setSelection(sqLiteModel.getSpinnerSecond());
        }
    }

    TextWatcher firstTextListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            firstTextChanged();
        }
    };

    TextWatcher secondTextListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            secondTextChanged();
        }
    };

    private void firstTextChanged(){
        if (editTextFirst.isFocused()) {
            String first = editTextFirst.getText().toString();
            String second = editTextSecond.getText().toString();
            if (!first.isEmpty()) {
                double a = Double.parseDouble(first);
                double result = a * LengthSolver.converter(x, y);
                editTextSecond.setText(doubleConverter(result));
                return;
            }
            if (first.isEmpty() && !second.isEmpty()) {
                editTextSecond.setText("0");
            }
        }
    }
    private void secondTextChanged(){
        if(editTextSecond.isFocused()){
            String first = editTextFirst.getText().toString();
            String second = editTextSecond.getText().toString();
            if(!second.isEmpty()){
                double a = Double.parseDouble(editTextSecond.getText().toString());
                double result = a * LengthSolver.converter(x, y);
                editTextFirst.setText(doubleConverter(result));
                return;
            }
            if (second.isEmpty() && !first.isEmpty()){
                editTextFirst.setText("0");
            }
        }
    }

    private String doubleConverter(double number){
        if (number == (int) number) {
            return String.valueOf((int) number);
        }
        String lastNumber;
        for (int i = 0; i < 50; i++) {
            lastNumber = String.format(Locale.ENGLISH, "%." + i + "f", number);
            if (number == Double.parseDouble(lastNumber)) {
                return lastNumber;
            }
        }
        return String.valueOf(String.format(Locale.ENGLISH, "%.2f", number));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    AdapterView.OnItemSelectedListener firstItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position){
                case 0:
                    x = LengthSolver.MM;
                    break;
                case 1:
                    x = LengthSolver.CM;
                    break;
                case 2:
                    x = LengthSolver.INCH;
                    break;
                case 3:
                    x = LengthSolver.FEET;
                    break;
                case 4:
                    x = LengthSolver.YARD;
                    break;
                case 5:
                    x = LengthSolver.METER;
                    break;
                case 6:
                    x = LengthSolver.KM;
                    break;
                case 7:
                    x = LengthSolver.MILE;
                    break;
            }
            commonSpinnerChanged();
            a = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    AdapterView.OnItemSelectedListener secondItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position){
                case 0:
                    y = LengthSolver.MM;
                    break;
                case 1:
                    y = LengthSolver.CM;
                    break;
                case 2:
                    y = LengthSolver.INCH;
                    break;
                case 3:
                    y = LengthSolver.FEET;
                    break;
                case 4:
                    y = LengthSolver.YARD;
                    break;
                case 5:
                    y = LengthSolver.METER;
                    break;
                case 6:
                    y = LengthSolver.KM;
                    break;
                case 7:
                    y = LengthSolver.MILE;
                    break;
            }
            commonSpinnerChanged();
            b = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private void commonSpinnerChanged(){
        if (editTextFirst.isFocused()){
            firstTextChanged();
        } else if (editTextSecond.isFocused()){
            secondTextChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!editTextFirst.getText().toString().isEmpty() && !editTextSecond.getText().toString().isEmpty()){
            sqLiteModel.setEditTextFirst(editTextFirst.getText().toString());
            sqLiteModel.setEditTextSecond(editTextSecond.getText().toString());
            sqLiteModel.setSpinnerFirst(a);
            sqLiteModel.setSpinnerSecond(b);
            sqLiteHelper.saveData(sqLiteModel);
        }
    }
}
