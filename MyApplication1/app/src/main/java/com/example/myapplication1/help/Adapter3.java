package com.example.myapplication1.help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication1.R;

import java.util.List;

public class Adapter3 extends ArrayAdapter<zabol> {

    Context context;
    List<zabol> arrayListPac;


    public Adapter3(@NonNull Context context, List<zabol> arrayListPac) {
        super(context, R.layout.custom_list_item,arrayListPac);

        this.context = context;
        this.arrayListPac = arrayListPac;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvName = view.findViewById(R.id.txt_name);
        tvName.setText(arrayListPac.get(position).getName2());

        return view;
    }
}
