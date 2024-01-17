package com.example.u2a4citas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button u2a4btfecha, u2a4bthora, u2a4btcita;
    EditText u2a4etdni;
    TextView u2a4tvdni, u2a4tvfecha, u2a4tvhora;

    DatePickerDialog.OnDateSetListener dateSetListener; // Declarar el listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u2a4btfecha =findViewById(R.id.u2a4btfecha);
        u2a4bthora =findViewById(R.id.u2a4bthora);
        u2a4btcita =findViewById(R.id.u2a4btcita);
        u2a4etdni =findViewById(R.id.u2a4etdni);
        u2a4tvdni =findViewById(R.id.u2a4tvdni);
        u2a4tvfecha =findViewById(R.id.u2a4tvfecha);
        u2a4tvhora =findViewById(R.id.u2a4tvhora);

        // Configurar el click listener para el botón de fecha
        u2a4btfecha.setOnClickListener(view -> {
            // Obtener la fecha actual
            Calendar calendar = Calendar.getInstance();
            int año = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int día = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear y mostrar el DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    dateSetListener,
                    año, mes, día);
            datePickerDialog.show();
        });

        // Configurar el listener para capturar la fecha seleccionada
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Aquí puedes hacer algo con la fecha seleccionada
                String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                u2a4tvfecha.setText(fechaSeleccionada);
            }
        };
    }


        // Crear y mostrar TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            // Aquí puedes hacer algo con la hora seleccionada
            String horaSeleccionada = hourOfDay + ":" + minute;
            // Suponiendo que tienes un TextView con el ID tvHoraSeleccionada
            u2a4tvhora.setText(horaSeleccionada);
        }, hora, 0, false);

        timePickerDialog.show();
    };







