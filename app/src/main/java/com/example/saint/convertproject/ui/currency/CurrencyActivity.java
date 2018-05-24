package com.example.saint.convertproject.ui.currency;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.convertproject.ui.BaseActivity;
import com.example.saint.convertproject.ui.Solver;
import com.example.saint.convertproject.R;
import com.example.saint.convertproject.ui.SpinnerAdapter;
import com.example.saint.convertproject.StartApplication;
import com.example.saint.convertproject.data.entity.CurrencyGetter;
import com.example.saint.convertproject.data.network.RetrofitService;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends BaseActivity {

    private EditText mEditTextFirst, mEditTextSecond;
    private TextView mTextViewRate;
    private Spinner mSpinnerFirst, mSpinnerSecond;
    private RetrofitService mService;
    private CurrencyGetter mCurrencyGetter;
    private double mFirstTemp, mSecondTemp, mCommonTemplate;
    private ArrayList<String> mSpinnerList = new ArrayList<>();

    @Override
    protected int getViewLayout() {
        return R.layout.activity_currency;
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

        mSpinnerFirst.setOnItemSelectedListener(spinnerItemSelectedListener);
        mSpinnerSecond.setOnItemSelectedListener(spinnerItemSelectedListener);
        mEditTextFirst.addTextChangedListener(firstTextChangedListener);
        mEditTextSecond.addTextChangedListener(secondTextChangedListener);
    }

    private void initializeViews(){
        mEditTextFirst = findViewById(R.id.editTextFirst);
        mEditTextSecond = findViewById(R.id.editTextSecond);
        mSpinnerFirst = findViewById(R.id.spinnerFirst);
        mSpinnerSecond = findViewById(R.id.spinnerSecond);
        mTextViewRate = findViewById(R.id.textViewRate);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sendRequest();
    }

    private void sendRequest(){
        mService = StartApplication.get(this).getService();
        mService.getCurrencies().enqueue(new Callback<CurrencyGetter>() {
            @Override
            public void onResponse(@NonNull Call<CurrencyGetter> call, @NonNull Response<CurrencyGetter> response) {

                if(response.body() != null && response.isSuccessful()){
                    mCurrencyGetter = response.body();
                    if(mCurrencyGetter != null){
                        for (int i = 0; i < mCurrencyGetter.getCurrencyList().size(); i++) {
                            mSpinnerList.add(mCurrencyGetter.getCurrencyList().get(i).getName());
                            mSpinnerFirst.setAdapter(new SpinnerAdapter(getApplicationContext(), mSpinnerList));
                            mSpinnerSecond.setAdapter(new SpinnerAdapter(getApplicationContext(), mSpinnerList));
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<CurrencyGetter> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private AdapterView.OnItemSelectedListener spinnerItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:
                    mCommonTemplate = mCurrencyGetter.getRates().getEUR();
                    break;
                case 1:
                    mCommonTemplate = mCurrencyGetter.getRates().getAUD();
                    break;
                case 2:
                    mCommonTemplate = mCurrencyGetter.getRates().getBGN();
                    break;
                case 3:
                    mCommonTemplate = mCurrencyGetter.getRates().getBRL();
                    break;
                case 4:
                    mCommonTemplate = mCurrencyGetter.getRates().getCAD();
                    break;
                case 5:
                    mCommonTemplate = mCurrencyGetter.getRates().getCHF();
                    break;
                case 6:
                    mCommonTemplate = mCurrencyGetter.getRates().getCNY();
                    break;
                case 7:
                    mCommonTemplate = mCurrencyGetter.getRates().getCZK();
                    break;
                case 8:
                    mCommonTemplate = mCurrencyGetter.getRates().getDKK();
                    break;
                case 9:
                    mCommonTemplate = mCurrencyGetter.getRates().getGBP();
                    break;
                case 10:
                    mCommonTemplate = mCurrencyGetter.getRates().getHKD();
                    break;
                case 11:
                    mCommonTemplate = mCurrencyGetter.getRates().getHRK();
                    break;
                case 12:
                    mCommonTemplate = mCurrencyGetter.getRates().getHUF();
                    break;
                case 13:
                    mCommonTemplate = mCurrencyGetter.getRates().getIDR();
                    break;
                case 14:
                    mCommonTemplate = mCurrencyGetter.getRates().getILS();
                    break;
                case 15:
                    mCommonTemplate = mCurrencyGetter.getRates().getINR();
                    break;
                case 16:
                    mCommonTemplate = mCurrencyGetter.getRates().getISK();
                    break;
                case 17:
                    mCommonTemplate = mCurrencyGetter.getRates().getJPY();
                    break;
                case 18:
                    mCommonTemplate = mCurrencyGetter.getRates().getKRW();
                    break;
                case 19:
                    mCommonTemplate = mCurrencyGetter.getRates().getMXN();
                    break;
                case 20:
                    mCommonTemplate = mCurrencyGetter.getRates().getMYR();
                    break;
                case 21:
                    mCommonTemplate = mCurrencyGetter.getRates().getNOK();
                    break;
                case 22:
                    mCommonTemplate = mCurrencyGetter.getRates().getNZD();
                    break;
                case 23:
                    mCommonTemplate = mCurrencyGetter.getRates().getPHP();
                    break;
                case 24:
                    mCommonTemplate = mCurrencyGetter.getRates().getPLN();
                    break;
                case 25:
                    mCommonTemplate = mCurrencyGetter.getRates().getRON();
                    break;
                case 26:
                    mCommonTemplate = mCurrencyGetter.getRates().getRUB();
                    break;
                case 27:
                    mCommonTemplate = mCurrencyGetter.getRates().getSEK();
                    break;
                case 28:
                    mCommonTemplate = mCurrencyGetter.getRates().getSGD();
                    break;
                case 29:
                    mCommonTemplate = mCurrencyGetter.getRates().getTHB();
                    break;
                case 30:
                    mCommonTemplate = mCurrencyGetter.getRates().getTRY();
                    break;
                case 31:
                    mCommonTemplate = mCurrencyGetter.getRates().getUSD();
                    break;
                case 32:
                    mCommonTemplate = mCurrencyGetter.getRates().getZAR();
                    break;
            }
            if(parent.getId() == R.id.spinnerFirst){
                mFirstTemp = mCommonTemplate;
            }
            if(parent.getId() == R.id.spinnerSecond){
                mSecondTemp = mCommonTemplate;
            }

            commonSpinnerChanged();
            mTextViewRate.setText(String.valueOf(Solver.converter(mSecondTemp, mFirstTemp)));
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}};

    private TextWatcher firstTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            firstTextChanged();
        }
    };

    private TextWatcher secondTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
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
        }
        return super.onOptionsItemSelected(item);
    }
}
