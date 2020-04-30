package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
    MediaPlayer mp;
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
        id = spr.getInt("identificador", 1000);
        obtenerDatos();

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
                    arregloEmp.add(new Empresa(id+1, nombre, correo, tipo, telefono));
                    Toast.makeText(Main2Activity.this,getResources().getString(R.string.correcto), Toast.LENGTH_SHORT).show();
                    id = id+1;
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
                finish();
            }
        });
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
            editor.commit();
        }
        editor.putInt("identificador",id);
        editor.commit();
    }

    private void obtenerDatos(){
        String nombre, correo, tipo, telefono;
        int identificador;
        if(id !=1000){
            int numElementos = id - 1000;
            for(int i=0; i<numElementos; i++){
                identificador = spr.getInt("id" + i,1000);
                nombre = spr.getString("nombre" + i, getResources().getString(R.string.nombre));
                correo = spr.getString("correo" + i,getResources().getString(R.string.correo));
                tipo = spr.getString("tipo" + i,getResources().getString(R.string.tipo));
                telefono = spr.getString("telefono" + i, getResources().getString(R.string.telefono));
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

    /*private void mostrarArreglo(){
        int tamano = arregloEmp.size();
        for(int i=0; i < tamano; i++){
            Log.d("TAMANO","El id es: " + arregloEmp.get(i).getId());
            Log.d("TAMANO","El correo es: " + arregloEmp.get(i).getCorreo());
            Log.d("TAMANO","El teléfono es: " + arregloEmp.get(i).getTelefono());
            Log.d("TAMANO","El tipo es: " + arregloEmp.get(i).getTipo());
        }
    }*/
}
