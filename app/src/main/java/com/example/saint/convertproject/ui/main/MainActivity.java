package com.example.saint.convertproject.ui.main;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.saint.convertproject.ui.BaseActivity;
import com.example.saint.convertproject.ui.length.LengthActivity;
import com.example.saint.convertproject.ui.mass.MassActivity;
import com.example.saint.convertproject.R;
import com.example.saint.convertproject.data.db.SQLiteHelper;
import com.example.saint.convertproject.ui.currency.CurrencyActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mButtonLength, mButtonMass, mButtonCurrency;
    private SQLiteHelper mSQLiteHelper;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        getToolbar(getResources().getString(R.string.converter), false);

        mSQLiteHelper = new SQLiteHelper(this);

        mButtonLength = findViewById(R.id.buttonLength);
        mButtonMass = findViewById(R.id.buttonMass);
        mButtonCurrency = findViewById(R.id.buttonCurrency);

        mButtonLength.setOnClickListener(this);
        mButtonMass.setOnClickListener(this);
        mButtonCurrency.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLength:
                startActivity(new Intent(MainActivity.this, LengthActivity.class));
                break;
            case R.id.buttonMass:
                startActivity(new Intent(MainActivity.this, MassActivity.class));
                break;
            case R.id.buttonCurrency:

                ConnectivityManager manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
                if(manager != null){
                    NetworkInfo info = manager.getActiveNetworkInfo();
                    if(info != null && info.isConnected()){
                        startActivity(new Intent(MainActivity.this, CurrencyActivity.class));
                    }
                    else{
                        DialogNameFragment.newDialog().show(getSupportFragmentManager(), "DialogName");
                    }
                }
                break;
        }
    }
}
