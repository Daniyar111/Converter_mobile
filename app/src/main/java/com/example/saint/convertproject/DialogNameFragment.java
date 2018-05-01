package com.example.saint.convertproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by saint on 20.03.2018.
 */

public class DialogNameFragment extends DialogFragment implements View.OnClickListener {

    private TextView textViewNotWork, textViewTurnOn;
    private Button buttonOk;

    public static DialogNameFragment newDialog(){
        DialogNameFragment dialogNameFragment = new DialogNameFragment();
        return dialogNameFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        textViewNotWork = view.findViewById(R.id.textViewNotWork);
        textViewTurnOn = view.findViewById(R.id.textViewTurnOn);
        buttonOk = view.findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
