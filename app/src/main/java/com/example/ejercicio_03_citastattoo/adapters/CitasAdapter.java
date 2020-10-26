package com.example.ejercicio_03_citastattoo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejercicio_03_citastattoo.R;
import com.example.ejercicio_03_citastattoo.modelos.CitaTattoo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SimpleTimeZone;

public class CitasAdapter extends ArrayAdapter<CitaTattoo> {

    private Context context;
    private int resource;
    private List<CitaTattoo> objects;

    public CitasAdapter(@NonNull Context context, int resource, @NonNull List<CitaTattoo> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View filaCIta = LayoutInflater.from(context).inflate(resource, null);
        CitaTattoo citaTattoo = objects.get(position);

        TextView txxtNombre = filaCIta.findViewById(R.id.txtNombreFilaCita);
        TextView txtFechaCita= filaCIta.findViewById(R.id.txtFechaCitaFilaCita);

        // OJO PARA EXAMEN!!!!
        txxtNombre.setText(citaTattoo.getNombre());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtFechaCita.setText(simpleDateFormat.format(citaTattoo.getFechaCita()));

        return filaCIta;

    }
}
