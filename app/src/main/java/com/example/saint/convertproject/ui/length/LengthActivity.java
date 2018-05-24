package com.example.saint.convertproject.ui.length;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class LengthActivity extends BaseActivity {

    private EditText mEditTextFirst, mEditTextSecond;
    private Spinner mSpinnerFirst, mSpinnerSecond;
    private ArrayList<String> mSpinners = new ArrayList<>();
    private double mFirstTemp, mSecondTemp, mCommonTemplate;
    private SQLiteHelper mSQLiteHelper = new SQLiteHelper(this);
    private SQLiteModel mSQLiteModel = new SQLiteModel();
    private int mFirstPosition, mSecondPosition;
    private String[] mArrayLength;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_length;
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
        mArrayLength = getResources().getStringArray(R.array.length);
        mSpinners.addAll(Arrays.asList(mArrayLength));
        mSpinnerFirst.setAdapter(new SpinnerAdapter(this, mSpinners));
        mSpinnerSecond.setAdapter(new SpinnerAdapter(this, mSpinners));
    }

    private void getDataFromDb(){
        if(mSQLiteModel != null){
            mSQLiteModel = mSQLiteHelper.getLength();
            mEditTextFirst.setText(mSQLiteModel.getEditTextFirst());
            mEditTextSecond.setText(mSQLiteModel.getEditTextSecond());
            mSpinnerFirst.setSelection(mSQLiteModel.getSpinnerFirst());
            mSpinnerSecond.setSelection(mSQLiteModel.getSpinnerSecond());
        }
    }

    private AdapterView.OnItemSelectedListener spinnerItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:
                    mCommonTemplate = Constants.MM;
                    break;
                case 1:
                    mCommonTemplate = Constants.CM;
                    break;
                case 2:
                    mCommonTemplate = Constants.INCH;
                    break;
                case 3:
                    mCommonTemplate = Constants.FEET;
                    break;
                case 4:
                    mCommonTemplate = Constants.YARD;
                    break;
                case 5:
                    mCommonTemplate = Constants.METER;
                    break;
                case 6:
                    mCommonTemplate = Constants.KM;
                    break;
                case 7:
                    mCommonTemplate = Constants.MILE;
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

    private void commonSpinnerChanged(){
        if (mEditTextFirst.isFocused()){
            firstTextChanged();
        } else if (mEditTextSecond.isFocused()){
            secondTextChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!mEditTextFirst.getText().toString().isEmpty() && !mEditTextSecond.getText().toString().isEmpty()){
            mSQLiteModel.setEditTextFirst(mEditTextFirst.getText().toString());
            mSQLiteModel.setEditTextSecond(mEditTextSecond.getText().toString());
            mSQLiteModel.setSpinnerFirst(mFirstPosition);
            mSQLiteModel.setSpinnerSecond(mSecondPosition);
            mSQLiteHelper.saveLength(mSQLiteModel);
        }
    }
}