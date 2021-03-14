package com.example.myapplication1.forchat.Adaptadores;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.forchat.Message;
import com.example.myapplication1.forchat.Usuario;

import java.util.ArrayList;

public class AdapterMensajes extends RecyclerView.Adapter<AdapterMensajes.ViewHolderMensajes> {
    Activity activity;
    ArrayList<Message> listaMensajes;
    String id;
    ArrayList<Usuario> list;
 //   Usuario usuario;

    public AdapterMensajes(Activity activity, ArrayList<Usuario> list, String id, ArrayList<Message> listaMensajes) {
        this.activity = activity;
        this.listaMensajes = listaMensajes;
        this.id = id;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterMensajes.ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_mensaje, null, false);
        return new ViewHolderMensajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMensajes.ViewHolderMensajes viewHolderMensajes, int i) {

        if(id.equals(listaMensajes.get(i).getUserOrig())) {
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.BLUE);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getName());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMessage());
        }
        else {
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.RED);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getName());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public static class ViewHolderMensajes extends RecyclerView.ViewHolder {

        TextView tvNombreUsuario, tvMensaje;

        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);

            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUsuario);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
        }
    }
}
