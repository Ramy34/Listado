package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.listado.empresas.Empresa;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ListView lv;
    SharedPreferences spr;
    SharedPreferences.Editor editor;
    ArrayList<Empresa> arregloEmp = new ArrayList<Empresa>();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv = findViewById(R.id.lv);

        spr = getPreferences(Context.MODE_PRIVATE);
        editor = spr.edit();

        id = spr.getInt("id", 1000);
        obtenerDatos();
        mostrarArreglo();

        Adaptador adaptador = new Adaptador(this, arregloEmp);
        lv.setAdapter(adaptador);
    }

    private void obtenerDatos(){
        String nombre, correo, tipo,telefono;
        if(id !=1000){
            int numElementos = id - 1000;
            for(int i = 0; i < numElementos; i++){
                nombre = spr.getString("nombre" + i, getResources().getString(R.string.nombre));
                correo = spr.getString("correo" + i,getResources().getString(R.string.correo));
                tipo = spr.getString("tipo" + i,getResources().getString(R.string.tipo));
                telefono = spr.getString("telefono",getResources().getString(R.string.telefono));
                Empresa emp = new Empresa(1000 + i, nombre, correo, tipo, telefono);
                arregloEmp.add(emp);
            }
        }
    }
    private void mostrarArreglo(){
        //Log.d("AYUDA","El arreglo tiene: " +  ((Object)arregloEmp).getClass().getSimpleName());
        int tamano = arregloEmp.size();
        Log.d("HELP","El tamano del array es de: " + arregloEmp.size());
        for(int i=0; i < tamano; i++){
            Log.d("HELP","El nombre de la empresa es: " + arregloEmp.get(i).getNombre());
        }
    }
}
