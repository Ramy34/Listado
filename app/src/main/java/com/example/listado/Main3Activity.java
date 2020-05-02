package com.example.listado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listado.empresas.Empresa;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ListView lv;
    SharedPreferences spr;
    SharedPreferences.Editor editor;
    ArrayList<Empresa> arregloEmp = new ArrayList<Empresa>();
    int ida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv = findViewById(R.id.lv);

        spr = getSharedPreferences(getResources().getString(R.string.archivo),MODE_PRIVATE);
        editor = spr.edit();

        ida = spr.getInt(getResources().getString(R.string.keyIdentificador), 1000);
        obtenerDatos();

        mostrarDialogo(ida);

        Adaptador adaptador = new Adaptador(Main3Activity.this, arregloEmp);
        lv.setAdapter(adaptador);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main3Activity.this, getResources().getString(R.string.toastMensaje) + id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void mostrarDialogo(int ida) {
        if(ida == 1000){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.importante)
                    .setMessage(R.string.alDiaMen)
                    .setPositiveButton(R.string.positivo, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.negativo, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).show();
        }
    }

    private void obtenerDatos(){
        String nombre, correo, tipo, telefono;
        int identificador;
        if(ida !=1000){
            int numElementos = ida - 1000;
            for(int i=0; i<numElementos; i++){
                identificador = spr.getInt(getResources().getString(R.string.nomId) + i,1000);
                nombre = spr.getString(getResources().getString(R.string.nomNombre) + i, getResources().getString(R.string.nombre));
                correo = spr.getString(getResources().getString(R.string.nomCorreo) + i,getResources().getString(R.string.correo));
                tipo = spr.getString(getResources().getString(R.string.nomTipo) + i,getResources().getString(R.string.tipo));
                telefono = spr.getString(getResources().getString(R.string.nomTelefono) + i, getResources().getString(R.string.telefono));
                Empresa emp = new Empresa(identificador, nombre, correo, tipo, telefono);
                arregloEmp.add(emp);

            }
        }
    }

}
