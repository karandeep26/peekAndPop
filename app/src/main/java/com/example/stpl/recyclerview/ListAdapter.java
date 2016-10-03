package com.example.stpl.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by stpl on 9/30/2016.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private String data[];
    private Context context;
    public ListAdapter(Context context ,String[] objects) {
        super(context, -1, objects);
        this.context=context;
        data=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.row_item,parent,false);
        TextView label= (TextView) view.findViewById(R.id.label);
        TextView number=(TextView)view.findViewById(R.id.number);
        label.setText(data[position]);
        number.setText(data[position]);
        return view;
    }
}
