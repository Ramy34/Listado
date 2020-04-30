package com.example.listado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listado.empresas.Empresa;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    ArrayList<Empresa> datos;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView tvNombre = vista.findViewById(R.id.tvNombre);
        TextView tvCorreo = vista.findViewById(R.id.tvCorreo);
        TextView tvTelefono = vista.findViewById(R.id.tvTel);
        TextView tvTipo = vista.findViewById(R.id.tvTipo);
        ImageView imV = vista.findViewById(R.id.imV);

        tvNombre.setText(datos.get(position).getNombre());
        tvCorreo.setText(datos.get(position).getCorreo());
        tvTelefono.setText(datos.get(position).getTelefono());
        tvTipo.setText(datos.get(position).getTipo());

        imagen(datos.get(position).getTipo(), imV);

        return vista;
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

}
