package com.example.ejercicio_03_citastattoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.ejercicio_03_citastattoo.modelos.CitaTattoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddCitasActivity extends AppCompatActivity {

    // Elementos de la vista
    private EditText txtNombre, txtApellidos, txtFechaCita, txtFechaNaciminto, txtFianza;
    private Switch swAutorizado, swColor;
    private Button btnCrear;

    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_citas);

        txtNombre = findViewById(R.id.txtNombreCita);
        txtApellidos = findViewById(R.id.txtApellidosCita);
        txtFechaCita = findViewById(R.id.txtFechaCita);
        txtFechaNaciminto = findViewById(R.id.txtFechaNacimineto);
        txtFianza = findViewById(R.id.txtFianzaCita);
        swAutorizado = findViewById(R.id.swAutorizado);
        swColor = findViewById(R.id.swColorCita);
        btnCrear = findViewById(R.id.btnGuardarCita);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        final LocalDate hoy = LocalDate.now();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().isEmpty() &&
                !txtApellidos.getText().toString().isEmpty() &&
                !txtFechaNaciminto.getText().toString().isEmpty() &&
                !txtFechaCita.getText().toString().isEmpty() &&
                !txtFianza.getText().toString().isEmpty()){

                    try {
                        CitaTattoo citaTattoo = new CitaTattoo(txtNombre.getText().toString(),
                                txtApellidos.getText().toString(),
                                simpleDateFormat.parse(txtFechaNaciminto.getText().toString()),
                                simpleDateFormat.parse(txtFechaCita.getText().toString()),
                                Float.parseFloat(txtFianza.getText().toString()),
                                swColor.isChecked(),swAutorizado.isChecked()

                        );

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("CITA", citaTattoo);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


        txtFechaNaciminto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LocalDate hoy = LocalDate.now();
                try {
                    Date fechaNacimiento = simpleDateFormat.parse(s.toString());
                    LocalDate fNacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (hoy.getYear() - fNacimiento.getYear() < 18){
                        swAutorizado.setVisibility(View.VISIBLE);
                        btnCrear.setEnabled(false);
                    }else {
                        swAutorizado.setChecked(false);
                        swAutorizado.setVisibility(View.GONE);
                        btnCrear.setEnabled(true);

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        swAutorizado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnCrear.setEnabled(isChecked);
            }
        });

    }
}