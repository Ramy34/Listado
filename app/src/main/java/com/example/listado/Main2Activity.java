package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    MediaPlayer mp;
    Spinner sp;
    SharedPreferences spr;
    SharedPreferences.Editor editor;
    Empresa emp;
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

        final int iden = getIntent().getIntExtra(getResources().getString(R.string.keyId),0);

        spr = getSharedPreferences(getResources().getString(R.string.archivo),MODE_PRIVATE);
        editor = spr.edit();
        id = spr.getInt(getResources().getString(R.string.keyIdentificador), 1000);
        obtenerDatos();

        String[] opciones = {getResources().getString(R.string.alimenticio), getResources().getString(R.string.automotriz),
                getResources().getString(R.string.entretenimiento), getResources().getString(R.string.farmaceutico)};

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_layout, opciones);
        sp.setAdapter(adapter);

        cargarDatos(iden);

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNom.getText().toString();
                String correo = etCor.getText().toString();
                String telefono = etTel.getText().toString();
                if(validarDatos(nombre, correo, telefono)){
                    String tipo = sp.getSelectedItem().toString();
                    if(iden != 0){
                        emp = new Empresa(iden, nombre, correo, tipo, telefono);
                        arregloEmp.set(iden-1001,emp);
                    }else{
                        emp = new Empresa(id+1, nombre, correo, tipo, telefono);
                        arregloEmp.add(emp);
                        id = id+1;
                    }
                    Toast.makeText(Main2Activity.this,getResources().getString(R.string.correcto), Toast.LENGTH_SHORT).show();
                    sonar(tipo);
                    borrarCampos();
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos(arregloEmp, id);
                sonar(getResources().getString(R.string.maquina));
                if(iden !=0 ){
                    Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    private void cargarDatos(int iden) {
        if(iden != 0){
            etNom.setText(arregloEmp.get(iden-1001).getNombre());
            etCor.setText(arregloEmp.get(iden-1001).getCorreo());
            etTel.setText(arregloEmp.get(iden-1001).getTelefono());
            sp.setSelection(idTipos(arregloEmp.get(iden-1001).getTipo()));
        }
    }

    private void sonar(String tipo) {
        int id_tipos = idTipos(tipo);
        switch (id_tipos){
            case 0:
                mp = MediaPlayer.create(this, R.raw.alimenticio);
                break;
            case 1:
                mp = MediaPlayer.create(this, R.raw.carreras);
                break;
            case 2:
                mp = MediaPlayer.create(this, R.raw.cine);
                break;
            case 3:
                mp = MediaPlayer.create(this, R.raw.farmaceutico);
                break;
            case 4:
                mp = MediaPlayer.create(this, R.raw.maquina);
                break;
            default:
                break;
        }
        mp.start();
    }

    private int idTipos(String tipo) {
        String[] tipos = { getResources().getString(R.string.alimenticio), getResources().getString(R.string.automotriz),
                getResources().getString(R.string.entretenimiento), getResources().getString(R.string.farmaceutico),
        getResources().getString(R.string.maquina)};
        for(int i = 0; i <= tipos.length;i++){
            if(tipo.toLowerCase().equals(tipos[i].toLowerCase())){
                return i;
            }
        }
        return -1;
    }

    private void borrarCampos(){
        etNom.setText(getResources().getString(R.string.vacio));
        etCor.setText(getResources().getString(R.string.vacio));
        etTel.setText(getResources().getString(R.string.vacio));
    }

    private void guardarDatos(ArrayList arregloEmp, int id) {
        int numElementos = arregloEmp.size();
        for(int i = 0; i < numElementos; i++){
            Empresa emp = (Empresa) arregloEmp.get(i);
            editor.putInt(getResources().getString(R.string.nomId) + i, emp.getId());
            editor.putString(getResources().getString(R.string.nomNombre) + i, emp.getNombre());
            editor.putString(getResources().getString(R.string.nomCorreo) + i, emp.getCorreo());
            editor.putString(getResources().getString(R.string.nomTipo) + i, emp.getTipo());
            editor.putString(getResources().getString(R.string.nomTelefono) + i, emp.getTelefono());
            editor.commit();
        }
        editor.putInt(getResources().getString(R.string.keyIdentificador),id);
        editor.commit();
    }

    private void obtenerDatos(){
        String nombre, correo, tipo, telefono;
        int identificador;
        if(id !=1000){
            int numElementos = id - 1000;
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

    private boolean validarDatos(String nombre, String correo, String telefono) {
        if(nombre.length() == 0){
            etNom.setError(getResources().getString(R.string.error));
            return false;
        }
        if(correo.length() == 0 || !(correo.contains(getResources().getString(R.string.idenCorreo)))){
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
