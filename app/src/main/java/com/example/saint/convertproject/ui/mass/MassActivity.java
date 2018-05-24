package com.example.saint.convertproject.ui.mass;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.saint.convertproject.R;
import com.example.saint.convertproject.config.Constants;
import com.example.saint.convertproject.data.db.SQLiteHelper;
import com.example.saint.convertproject.ui.BaseActivity;
import com.example.saint.convertproject.ui.Solver;
import com.example.saint.convertproject.ui.SpinnerAdapter;
import com.example.saint.convertproject.data.entity.SQLiteModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MassActivity extends BaseActivity {

    private EditText mEditTextFirst, mEditTextSecond;
    private Spinner mSpinnerFirst, mSpinnerSecond;
    private ArrayList<String> mSpinners = new ArrayList<>();
    private double mFirstTemp, mSecondTemp, mCommonTemplate;
    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    private SQLiteModel sqLiteModel = new SQLiteModel();
    private int mFirstPosition, mSecondPosition;
    private String[] mArrayMass;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_mass;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        getToolbar(getResources().getString(R.string.conversion), true);

        initializeViews();
        initAdapter();

        mSpinnerFirst.setOnItemSelectedListener(spinnerItemSelectedListener);
        mSpinnerSecond.setOnItemSelectedListener(spinnerItemSelectedListener);
        mEditTextFirst.addTextChangedListener(firstTextListener);
        mEditTextSecond.addTextChangedListener(secondTextListener);

        getDataFromDb();
    }

    private void initializeViews(){
        mEditTextFirst = findViewById(R.id.editTextFirst);
        mEditTextSecond = findViewById(R.id.editTextSecond);
        mSpinnerFirst = findViewById(R.id.spinnerFirst);
        mSpinnerSecond = findViewById(R.id.spinnerSecond);
    }

    private void initAdapter(){
        mArrayMass = getResources().getStringArray(R.array.mass);
        mSpinners.addAll(Arrays.asList(mArrayMass));
        mSpinnerFirst.setAdapter(new SpinnerAdapter(this, mSpinners));
        mSpinnerSecond.setAdapter(new SpinnerAdapter(this, mSpinners));
    }

    private void getDataFromDb(){
        if(sqLiteHelper.getMass() != null){
            sqLiteModel = sqLiteHelper.getMass();
            mEditTextFirst.setText(sqLiteModel.getEditTextFirst());
            mEditTextSecond.setText(sqLiteModel.getEditTextSecond());
            mSpinnerFirst.setSelection(sqLiteModel.getSpinnerFirst());
            mSpinnerSecond.setSelection(sqLiteModel.getSpinnerSecond());
        }
    }

    private AdapterView.OnItemSelectedListener spinnerItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:
                    mCommonTemplate = Constants.GRAM;
                    break;
                case 1:
                    mCommonTemplate = Constants.OUNCE;
                    break;
                case 2:
                    mCommonTemplate = Constants.POUND;
                    break;
                case 3:
                    mCommonTemplate = Constants.KG;
                    break;
                case 4:
                    mCommonTemplate = Constants.TONNE;
                    break;
            }
            if(parent.getId() == R.id.spinnerFirst){
                mFirstTemp = mCommonTemplate;
                mFirstPosition = position;
            }
            if(parent.getId() == R.id.spinnerSecond){
                mSecondTemp = mCommonTemplate;
                mSecondPosition = position;
            }
            commonSpinnerChanged();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}};

    TextWatcher firstTextListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            firstTextChanged();
        }
    };

    TextWatcher secondTextListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            secondTextChanged();
        }
    };

    private void firstTextChanged(){
        if (mEditTextFirst.isFocused()) {
            String first = mEditTextFirst.getText().toString();
            String second = mEditTextSecond.getText().toString();
            if (!first.isEmpty()) {
                double a = Double.parseDouble(first);
                double result = a * Solver.converter(mFirstTemp, mSecondTemp);
                mEditTextSecond.setText(doubleConverter(result));
                return;
            }
            if (!second.isEmpty()) {
                mEditTextSecond.setText("0");
            }
        }
    }

    private void secondTextChanged(){
        if(mEditTextSecond.isFocused()){
            String first = mEditTextFirst.getText().toString();
            String second = mEditTextSecond.getText().toString();
            if(!second.isEmpty()){
                double a = Double.parseDouble(mEditTextSecond.getText().toString());
                double result = a * Solver.converter(mFirstTemp, mSecondTemp);
                mEditTextFirst.setText(doubleConverter(result));
                return;
            }
            if (!first.isEmpty()){
                mEditTextFirst.setText("0");
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

    private void commonSpinnerChanged(){
        if (mEditTextFirst.isFocused()){
            firstTextChanged();
        } else if (mEditTextSecond.isFocused()){
            secondTextChanged();
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        if(!mEditTextFirst.getText().toString().isEmpty() && !mEditTextSecond.getText().toString().isEmpty()){
            sqLiteModel.setEditTextFirst(mEditTextFirst.getText().toString());
            sqLiteModel.setEditTextSecond(mEditTextSecond.getText().toString());
            sqLiteModel.setSpinnerFirst(mFirstPosition);
            sqLiteModel.setSpinnerSecond(mSecondPosition);
            sqLiteHelper.saveMass(sqLiteModel);
        }
    }
}
