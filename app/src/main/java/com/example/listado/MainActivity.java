package com.example.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.listado.empresas.Empresa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnR, btnC, btnBor;
    SharedPreferences spr;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnC = findViewById(R.id.btC);
        btnR = findViewById(R.id.btR);
        btnBor = findViewById(R.id.btnBor);

        spr = getSharedPreferences(getResources().getString(R.string.archivo), MODE_PRIVATE);
        editor = spr.edit();

        btnBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Toast.makeText(MainActivity.this, R.string.mensaje,Toast.LENGTH_SHORT).show();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
