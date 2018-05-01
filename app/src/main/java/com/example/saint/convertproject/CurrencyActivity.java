package com.example.saint.convertproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextFirst, editTextSecond;
    private TextView textViewRate;
    private Spinner spinnerFirst, spinnerSecond;
    private double x;
    private double y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

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
        textViewRate = findViewById(R.id.textViewRate);

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

    @Override
    protected void onStart() {
        super.onStart();
        sendRequest();
    }

    private void sendRequest(){
        RetrofitService service = StartApplication.get(this).getRetrofitService();
        Call<CurrencyGetter> call = service.getCurrencies();
        call.enqueue(new Callback<CurrencyGetter>() {
            @Override
            public void onResponse(Call<CurrencyGetter> call, Response<CurrencyGetter> response) {
                final CurrencyGetter currencyGetter = response.body();
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<Double> ratesList = new ArrayList<>();
                x = currencyGetter.getRates().getEUR();
                y = currencyGetter.getRates().getEUR();

                for (int i = 0; i < currencyGetter.getCurrencyList().size(); i++) {
                    arrayList.add(currencyGetter.getCurrencyList().get(i).getName());
                }
                for (int i = 0; i < currencyGetter.getCurrencyList().size(); i++) {
                    ratesList.add(currencyGetter.getCurrencyList().get(i).getRate());
                }
                spinnerFirst.setAdapter(new SpinnerAdapter(getApplicationContext(), arrayList));
                spinnerSecond.setAdapter(new SpinnerAdapter(getApplicationContext(), arrayList));

                spinnerFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        switch (position){
                            case 0:
                                x = currencyGetter.getRates().getEUR();
                                break;
                            case 1:
                                x = currencyGetter.getRates().getAUD();
                                break;
                            case 2:
                                x = currencyGetter.getRates().getBGN();
                                break;
                            case 3:
                                x = currencyGetter.getRates().getBRL();
                                break;
                            case 4:
                                x = currencyGetter.getRates().getCAD();
                                break;
                            case 5:
                                x = currencyGetter.getRates().getCHF();
                                break;
                            case 6:
                                x = currencyGetter.getRates().getCNY();
                                break;
                            case 7:
                                x = currencyGetter.getRates().getCZK();
                                break;
                            case 8:
                                x = currencyGetter.getRates().getDKK();
                                break;
                            case 9:
                                x = currencyGetter.getRates().getGBP();
                                break;
                            case 10:
                                x = currencyGetter.getRates().getHKD();
                                break;
                            case 11:
                                x = currencyGetter.getRates().getHRK();
                                break;
                            case 12:
                                x = currencyGetter.getRates().getHUF();
                                break;
                            case 13:
                                x = currencyGetter.getRates().getIDR();
                                break;
                            case 14:
                                x = currencyGetter.getRates().getILS();
                                break;
                            case 15:
                                x = currencyGetter.getRates().getINR();
                                break;
                            case 16:
                                x = currencyGetter.getRates().getISK();
                                break;
                            case 17:
                                x = currencyGetter.getRates().getJPY();
                                break;
                            case 18:
                                x = currencyGetter.getRates().getKRW();
                                break;
                            case 19:
                                x = currencyGetter.getRates().getMXN();
                                break;
                            case 20:
                                x = currencyGetter.getRates().getMYR();
                                break;
                            case 21:
                                x = currencyGetter.getRates().getNOK();
                                break;
                            case 22:
                                x = currencyGetter.getRates().getNZD();
                                break;
                            case 23:
                                x = currencyGetter.getRates().getPHP();
                                break;
                            case 24:
                                x = currencyGetter.getRates().getPLN();
                                break;
                            case 25:
                                x = currencyGetter.getRates().getRON();
                                break;
                            case 26:
                                x = currencyGetter.getRates().getRUB();
                                break;
                            case 27:
                                x = currencyGetter.getRates().getSEK();
                                break;
                            case 28:
                                x = currencyGetter.getRates().getSGD();
                                break;
                            case 29:
                                x = currencyGetter.getRates().getTHB();
                                break;
                            case 30:
                                x = currencyGetter.getRates().getTRY();
                                break;
                            case 31:
                                x = currencyGetter.getRates().getUSD();
                                break;
                            case 32:
                                x = currencyGetter.getRates().getZAR();
                                break;
                        }
                        commonSpinnerChanged();
                        textViewRate.setText(CurrencySolver.converter(y, x) + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinnerSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        switch (position){
                            case 0:
                                y = currencyGetter.getRates().getEUR();
                                break;
                            case 1:
                                y = currencyGetter.getRates().getAUD();
                                break;
                            case 2:
                                y = currencyGetter.getRates().getBGN();
                                break;
                            case 3:
                                y = currencyGetter.getRates().getBRL();
                                break;
                            case 4:
                                y = currencyGetter.getRates().getCAD();
                                break;
                            case 5:
                                y = currencyGetter.getRates().getCHF();
                                break;
                            case 6:
                                y = currencyGetter.getRates().getCNY();
                                break;
                            case 7:
                                y = currencyGetter.getRates().getCZK();
                                break;
                            case 8:
                                y = currencyGetter.getRates().getDKK();
                                break;
                            case 9:
                                y = currencyGetter.getRates().getGBP();
                                break;
                            case 10:
                                y = currencyGetter.getRates().getHKD();
                                break;
                            case 11:
                                y = currencyGetter.getRates().getHRK();
                                break;
                            case 12:
                                y = currencyGetter.getRates().getHUF();
                                break;
                            case 13:
                                y = currencyGetter.getRates().getIDR();
                                break;
                            case 14:
                                y = currencyGetter.getRates().getILS();
                                break;
                            case 15:
                                y = currencyGetter.getRates().getINR();
                                break;
                            case 16:
                                y = currencyGetter.getRates().getISK();
                                break;
                            case 17:
                                y = currencyGetter.getRates().getJPY();
                                break;
                            case 18:
                                y = currencyGetter.getRates().getKRW();
                                break;
                            case 19:
                                y = currencyGetter.getRates().getMXN();
                                break;
                            case 20:
                                y = currencyGetter.getRates().getMYR();
                                break;
                            case 21:
                                y = currencyGetter.getRates().getNOK();
                                break;
                            case 22:
                                y = currencyGetter.getRates().getNZD();
                                break;
                            case 23:
                                y = currencyGetter.getRates().getPHP();
                                break;
                            case 24:
                                y = currencyGetter.getRates().getPLN();
                                break;
                            case 25:
                                y = currencyGetter.getRates().getRON();
                                break;
                            case 26:
                                y = currencyGetter.getRates().getRUB();
                                break;
                            case 27:
                                y = currencyGetter.getRates().getSEK();
                                break;
                            case 28:
                                y = currencyGetter.getRates().getSGD();
                                break;
                            case 29:
                                y = currencyGetter.getRates().getTHB();
                                break;
                            case 30:
                                y = currencyGetter.getRates().getTRY();
                                break;
                            case 31:
                                y = currencyGetter.getRates().getUSD();
                                break;
                            case 32:
                                y = currencyGetter.getRates().getZAR();
                                break;
                        }
                        commonSpinnerChanged();
                        textViewRate.setText(CurrencySolver.converter(y, x) + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                editTextFirst.addTextChangedListener(new TextWatcher() {
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
                });

                editTextSecond.addTextChangedListener(new TextWatcher() {
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
                });

            }

            @Override
            public void onFailure(Call<CurrencyGetter> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void firstTextChanged(){
        if (editTextFirst.isFocused()) {
            String first = editTextFirst.getText().toString();
            String second = editTextSecond.getText().toString();
            if (!first.isEmpty()) {
                double a = Double.parseDouble(first);
                double result = a * CurrencySolver.converter(x, y);
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
                double result = a * CurrencySolver.converter(x, y);
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
    private void commonSpinnerChanged(){
        if (editTextFirst.isFocused()){
            firstTextChanged();
        } else if (editTextSecond.isFocused()){
            secondTextChanged();
        }
    }
}
