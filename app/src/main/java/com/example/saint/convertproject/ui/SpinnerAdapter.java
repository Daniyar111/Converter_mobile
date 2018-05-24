package com.example.saint.convertproject.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saint.convertproject.R;

import java.util.ArrayList;

/**
 * Created by saint on 09.03.2018.
 */

public class SpinnerAdapter extends BaseAdapter {

    private ArrayList<String> arrayList;
    private Context context;

    public SpinnerAdapter(Context context, ArrayList<String> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.spinner_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        String spinner = (String) getItem(i);
        viewHolder.textView.setText(spinner);
        return view;
    }

    public class ViewHolder{
        TextView textView;
        public ViewHolder(View view){
            textView = view.findViewById(R.id.textView);
        }
    }
}
