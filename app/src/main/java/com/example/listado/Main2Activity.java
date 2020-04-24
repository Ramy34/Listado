package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.listado.empresas.Empresa;

public class Main2Activity extends AppCompatActivity {
    Button btnG, btnReg;
    Spinner sp;
    int id = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnG = findViewById(R.id.btnG);
        btnReg = findViewById(R.id.btnReg);
        sp = findViewById(R.id.sp);

        String[] opciones = {getResources().getString(R.string.alimenticio), getResources().getString(R.string.automotriz),
                getResources().getString(R.string.entretenimiento), getResources().getString(R.string.farmaceutico)};
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_layout, opciones);
        sp.setAdapter(adapter);



        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = "";
                String correo = "";
                String tipo = sp.getSelectedItem().toString();
                int telefono = 0;
                Empresa empresa = new Empresa(nombre,correo,tipo,telefono,id);
                Toast.makeText(Main2Activity.this,"Se guardó la empresa con id " + id + " y tipo: " + tipo, Toast.LENGTH_SHORT).show();
                id++;
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
