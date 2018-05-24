package com.example.saint.convertproject.data.entity;

import java.io.Serializable;

/**
 * Created by saint on 20.03.2018.
 */

public class SQLiteModel implements Serializable{

    private String editTextFirst;
    private String editTextSecond;
    private int spinnerFirst;
    private int spinnerSecond;

    public String getEditTextFirst() {
        return editTextFirst;
    }

    public void setEditTextFirst(String editTextFirst) {
        this.editTextFirst = editTextFirst;
    }

    public String getEditTextSecond() {
        return editTextSecond;
    }

    public void setEditTextSecond(String editTextSecond) {
        this.editTextSecond = editTextSecond;
    }

    public int getSpinnerFirst() {
        return spinnerFirst;
    }

    public void setSpinnerFirst(int spinnerFirst) {
        this.spinnerFirst = spinnerFirst;
    }

    public int getSpinnerSecond() {
        return spinnerSecond;
    }

    public void setSpinnerSecond(int spinnerSecond) {
        this.spinnerSecond = spinnerSecond;
    }
}
