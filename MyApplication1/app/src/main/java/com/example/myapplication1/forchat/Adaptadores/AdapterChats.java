package com.example.myapplication1.forchat.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication1.R;
import com.example.myapplication1.forchat.Usuario;

import java.util.List;

public class AdapterChats extends ArrayAdapter<Usuario> {

        Context context;
        List<Usuario> arrayListPac;


public AdapterChats(@NonNull Context context, List<Usuario> arrayListPac) {
        super(context, R.layout.custom_list_item,arrayListPac);

        this.context = context;
        this.arrayListPac = arrayListPac;

        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvName = view.findViewById(R.id.txt_name);
        tvName.setText(arrayListPac.get(position).getNombre());

        return view;
        }
}
