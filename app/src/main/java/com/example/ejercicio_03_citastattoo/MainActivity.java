package com.example.ejercicio_03_citastattoo;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicio_03_citastattoo.adapters.CitasAdapter;
import com.example.ejercicio_03_citastattoo.modelos.CitaTattoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ADD_CITA = 77;
    // 1. Modelo de Datoss
    private ArrayList<CitaTattoo> listadoCiitas;

    // 2. Fila a Mostrar
    private int resource;

    // 3. Contenedor (Contenedor + Adapter)
    private ListView contenedor;
    private CitasAdapter citasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listadoCiitas = new ArrayList<>();
        resource =R.layout.fila_cita;
        contenedor = findViewById(R.id.contenedorCitas);
        contenedor.setAdapter(citasAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddCitasActivity.class), ADD_CITA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!= null && data.getExtras() != null){
            CitaTattoo citaTattoo = data.getExtras().getParcelable("CITA");
            if (citaTattoo != null){
                listadoCiitas.add(citaTattoo);
                citasAdapter.notifyDataSetChanged();
            }
        }
    }
}