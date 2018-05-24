package com.example.saint.convertproject.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.saint.convertproject.R;

/**
 * Created by saint on 20.03.2018.
 */

public class DialogNameFragment extends DialogFragment implements View.OnClickListener {

    public static DialogNameFragment newDialog(){
        return new DialogNameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment, container, false);

        if(getDialog().getWindow() != null){
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        view.findViewById(R.id.buttonOk).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
