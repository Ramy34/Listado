package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.listado.empresas.Empresa;

public class Main2Activity extends AppCompatActivity {
    Button btnG, btnReg;
    EditText etNom, etCor, etTel;
    Spinner sp;
    int id = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etNom = findViewById(R.id.etNom);
        etCor = findViewById(R.id.etCorreo);
        etTel = findViewById(R.id.etTel);

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
                String nombre = etNom.getText().toString();
                String correo = etCor.getText().toString();
                String telefono = etTel.getText().toString();
                if(validarDatos(nombre, correo, telefono)){
                    String tipo = sp.getSelectedItem().toString();
                    Empresa empresa = new Empresa(nombre,correo,tipo,telefono,id);
                    //Se deben de agregar al array de datos y que se enviarán a la lista
                    id++;
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validarDatos(String nombre, String correo, String telefono) {
        if(nombre.length() == 0){
            etNom.setError(getResources().getString(R.string.error));
            return false;
        }
        if(correo.length() == 0 || !(correo.contains("@"))){
            etCor.setError(getResources().getString(R.string.errorCor));
            return false;
        }
        if(telefono.length() != 10){
            etTel.setError(getResources().getString(R.string.errorTel));
            return false;
        }
        return true;
    }
}
