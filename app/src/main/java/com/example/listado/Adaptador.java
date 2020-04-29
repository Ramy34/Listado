package com.example.listado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        tvNombre.setText(datos.get(position).getNombre());
        tvCorreo.setText(datos.get(position).getCorreo());
        tvTelefono.setText(datos.get(position).getTelefono());
        tvTipo.setText(datos.get(position).getTipo());

        return null;
    }
}
