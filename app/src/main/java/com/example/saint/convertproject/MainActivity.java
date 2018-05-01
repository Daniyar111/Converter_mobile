package com.example.saint.convertproject;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLength, buttonMass, buttonCurrency;
    private Toolbar toolbar;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Converter");
        }

        sqLiteHelper = new SQLiteHelper(this);

        buttonLength = findViewById(R.id.buttonLength);
        buttonMass = findViewById(R.id.buttonMass);
        buttonCurrency = findViewById(R.id.buttonCurrency);

        buttonLength.setOnClickListener(this);
        buttonMass.setOnClickListener(this);
        buttonCurrency.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLength:
                Intent intentLength = new Intent(MainActivity.this, LengthActivity.class);
                startActivity(intentLength);
                break;
            case R.id.buttonMass:
                Intent intentMass = new Intent(MainActivity.this, MassActivity.class);
                startActivity(intentMass);
                break;
            case R.id.buttonCurrency:

                ConnectivityManager manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
                if(manager != null){
                    NetworkInfo info = manager.getActiveNetworkInfo();
                    if(info != null && info.isConnected()){
                        Intent intentCurrency = new Intent(MainActivity.this, CurrencyActivity.class);
                        startActivity(intentCurrency);
                    }
                    else{
                        DialogNameFragment.newDialog().show(getSupportFragmentManager(), "DialogName");
                    }
                }
                break;
        }
    }

}
