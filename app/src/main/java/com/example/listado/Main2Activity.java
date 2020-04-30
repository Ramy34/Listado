package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.listado.empresas.Empresa;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Button btnG, btnReg;
    EditText etNom, etCor, etTel;
    Spinner sp;
    SharedPreferences spr;
    SharedPreferences.Editor editor;
    ArrayList<Empresa> arregloEmp = new ArrayList<Empresa>();
    int id;

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

        spr = getSharedPreferences(getResources().getString(R.string.archivo),MODE_PRIVATE);
        editor = spr.edit();
        id = spr.getInt("id", 1000);

        String[] opciones = {getResources().getString(R.string.alimenticio), getResources().getString(R.string.automotriz),
                getResources().getString(R.string.entretenimiento), getResources().getString(R.string.farmaceutico)};
        obtenerDatos();

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
                    id = id+1;
                    arregloEmp.add(new Empresa(id, nombre, correo, tipo, telefono));
                    Toast.makeText(Main2Activity.this,getResources().getString(R.string.correcto), Toast.LENGTH_SHORT).show();
                    borrarCampos();
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos(arregloEmp, id);
                mostrarArreglo();
                Bundle bundle = new Bundle();
                bundle.putSerializable("datos",arregloEmp);
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    private void mostrarArreglo(){
        int tamano = arregloEmp.size();
        for(int i=0; i < tamano; i++){
            Log.d("TAMANO","El id es: " + arregloEmp.get(i).getId());
        }
    }

    private void borrarCampos(){
        etNom.setText("");
        etCor.setText("");
        etTel.setText("");
    }

    private void guardarDatos(ArrayList arregloEmp, int id) {
        int numElementos = arregloEmp.size();
        for(int i = 0; i < numElementos; i++){
            Empresa emp = (Empresa) arregloEmp.get(i);
            editor.putInt("id" + i, emp.getId());
            editor.putString("nombre" + i, emp.getNombre());
            editor.putString("correo" + i, emp.getCorreo());
            editor.putString("tipo" + i, emp.getTipo());
            editor.putString("telefono" + i, emp.getTelefono());
        }
        editor.putInt("id",id);
        editor.commit();
    }

    private void obtenerDatos(){
        String nombre, correo, tipo,telefono;
        if(id !=1000){
            int numElementos = id - 1000;
            for(int i=0; i<numElementos; i++){
                nombre = spr.getString("nombre" + i, getResources().getString(R.string.nombre));
                correo = spr.getString("correo" + i,getResources().getString(R.string.correo));
                tipo = spr.getString("tipo" + i,getResources().getString(R.string.tipo));
                telefono = spr.getString("telefono",getResources().getString(R.string.telefono));
                Empresa emp = new Empresa(1000+i, nombre, correo, tipo, telefono);
                arregloEmp.add(emp);

            }
        }
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
