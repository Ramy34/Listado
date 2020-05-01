package com.example.listado;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.listado.empresas.Empresa;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;
    MediaPlayer mp;
    Context context;
    ArrayList<Empresa> datos;
    SharedPreferences spr;
    SharedPreferences.Editor editor;

    public Adaptador(Context context, ArrayList<Empresa> datos) {
        this.context = context;
        this.datos = datos;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datos.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        spr = context.getSharedPreferences(context.getResources().getString(R.string.archivo), context.MODE_PRIVATE);
        editor = spr.edit();
        TextView tvNombre = vista.findViewById(R.id.tvNombre);
        TextView tvCorreo = vista.findViewById(R.id.tvCorreo);
        TextView tvTelefono = vista.findViewById(R.id.tvTel);
        TextView tvTipo = vista.findViewById(R.id.tvTipo);
        ImageView imV = vista.findViewById(R.id.imV);
        ImageView imViConf = vista.findViewById(R.id.imViConf);

        tvNombre.setText(datos.get(position).getNombre());
        tvCorreo.setText(datos.get(position).getCorreo());
        tvTelefono.setText(datos.get(position).getTelefono());
        tvTipo.setText(datos.get(position).getTipo());

        imagen(datos.get(position).getTipo(), imV);

        imV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonar(datos.get(position).getTipo());
            }
        });

        imViConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo(position);
            }
        });

        return vista;
    }

    private void sonar(String tipo) {
        int id_tipos = idTipos(tipo);
        switch (id_tipos){
            case 0:
                mp = MediaPlayer.create(context, R.raw.alimenticio);
                break;
            case 1:
                mp = MediaPlayer.create(context, R.raw.carreras);
                break;
            case 2:
                mp = MediaPlayer.create(context, R.raw.cine);
                break;
            case 3:
                mp = MediaPlayer.create(context, R.raw.farmaceutico);
                break;
            case 4:
                mp = MediaPlayer.create(context, R.raw.maquina);
                break;
            default:
                break;
        }
        mp.start();
    }

    private void imagen(String tipo, ImageView imV) {
        int id_Tipos = idTipos(tipo);
        switch (id_Tipos){
            case 0:
                imV.setImageDrawable(context.getResources().getDrawable(R.drawable.alimenticio));
                 break;
            case 1:
                imV.setImageDrawable(context.getResources().getDrawable(R.drawable.automotriz));
                break;
            case 2:
                imV.setImageDrawable(context.getResources().getDrawable(R.drawable.entretenimiento));
                break;
            case 3:
                imV.setImageDrawable(context.getResources().getDrawable(R.drawable.farmaceutico));
                break;
            default:
                break;
        }
    }

    private int idTipos(String tipo) {
        String[] tipos = { context.getResources().getString(R.string.alimenticio), context.getResources().getString(R.string.automotriz),
                context.getResources().getString(R.string.entretenimiento), context.getResources().getString(R.string.farmaceutico)};
        for(int i = 0; i <= tipos.length;i++){
            if(tipo.toLowerCase().equals(tipos[i].toLowerCase())){
                return i;
            }
        }
        return -1;
    }

    private ArrayList<Empresa> mostrarDialogo(final int position) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.importante)
                .setMessage(R.string.editar)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, Main2Activity.class);
                        intent.putExtra("id", datos.get(position).getId());
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
        return datos;
    }

}
