package com.example.todosejercicios.ut03;

import static android.graphics.Color.RED;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import com.example.todosejercicios.R;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class PrincipalOrdinaria1T extends AppCompatActivity {
    public static final String PRESUPUESTO = "PRESUPUESTO";
    TextView tvErrorPresupuestoMaximo,tvErrorPresupuestoMinimo, tvErrorFechaPresupuesto, tvErrorNombre;
    Button btEnviarPresupuesto;
    EditText etNombrePresupuesto, etFechaPresupuesto, etPresupuestoMinimo, etPresupuestoMaximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_ordinaria1_t);
        tvErrorPresupuestoMaximo = findViewById(R.id.tvErrorPresupuestoMaximo);
        tvErrorPresupuestoMinimo = findViewById(R.id.tvErrorPresupuestoMinimo);
        tvErrorFechaPresupuesto = findViewById(R.id.tvErrorFechaPresupuesto);
        tvErrorNombre = findViewById(R.id.tvErrorNombre);
        btEnviarPresupuesto = findViewById(R.id.btEnviarPresupuesto);
        etNombrePresupuesto = findViewById(R.id.etNombrePresupuesto);
        etFechaPresupuesto = findViewById(R.id.etFechaPresupuesto);
        etPresupuestoMinimo = findViewById(R.id.etPresupuestoMinimo);
        etPresupuestoMaximo = findViewById(R.id.etPresupuestoMaximo);

        etFechaPresupuesto.setOnClickListener((View v) -> {
            // on below line we are getting
            // the instance of our calendar.
            final Calendar c = Calendar.getInstance();
            // on below line we are getting
            // our day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // on below line we are creating a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    PrincipalOrdinaria1T.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        Calendar selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        int diaSemana = selectedDate.get(Calendar.DAY_OF_WEEK);
                        // on below line we are setting date to our text view.
                        // on below line we are setting date to our text view.
                        etFechaPresupuesto.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);

                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        btEnviarPresupuesto.setOnClickListener((v) ->{
            if (etNombrePresupuesto.getText().toString().trim().isEmpty()){
                tvErrorNombre.setTextColor(RED);
                tvErrorNombre.setText("Introduce un nombre");
            }else{
                tvErrorNombre.setText("");
            }
            if (!etPresupuestoMinimo.getText().toString().trim().isEmpty() && !etPresupuestoMaximo.getText().toString().trim().isEmpty()) {
                String presupuestoMinimo = etPresupuestoMinimo.getText().toString();
                String presupuestoMaximo = etPresupuestoMaximo.getText().toString();
                if (Integer.parseInt(presupuestoMinimo) > Integer.parseInt(presupuestoMaximo)) {
                    tvErrorPresupuestoMinimo.setTextColor(RED);
                    tvErrorPresupuestoMinimo.setText("El presupuesto minimo no puede ser mayor que el maximo");
                }else{
                    tvErrorPresupuestoMinimo.setText("");
                }
                if (Integer.parseInt(presupuestoMaximo) < Integer.parseInt(presupuestoMinimo)) {
                    tvErrorPresupuestoMaximo.setTextColor(RED);
                    tvErrorPresupuestoMaximo.setText("El presupuesto maximo no puede ser menor que el minimo");
                }else{
                    tvErrorPresupuestoMaximo.setText("");
                }
            }else if(etPresupuestoMinimo.getText().toString().trim().isEmpty() && etPresupuestoMaximo.getText().toString().trim().isEmpty()) {
                tvErrorPresupuestoMinimo.setTextColor(RED);
                tvErrorPresupuestoMaximo.setTextColor(RED);
                tvErrorPresupuestoMaximo.setText("Introduce un presupuesto maximo");
                tvErrorPresupuestoMinimo.setText("Introduce un presupuesto minimo");
            }
            else{
                tvErrorPresupuestoMinimo.setText("");
                tvErrorPresupuestoMaximo.setText("");
            }
            try {
                // Crear el formato de la fecha
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

                // Obtener la fecha actual
                String hoy = sdf.format(new Date());

                // Obtener la fecha del EditText
                String fecha = etFechaPresupuesto.getText().toString();

                // Convertir ambas fechas a objetos Date
                Date fechasdf = sdf.parse(fecha);
                Date hoysdf = sdf.parse(hoy);

                // Comparar las fechas
                if (fechasdf.before(hoysdf)) {
                    tvErrorFechaPresupuesto.setTextColor(RED);
                    tvErrorFechaPresupuesto.setText("La fecha proporcionada no puede ser anterior a la de hoy");
                } else {
                    tvErrorFechaPresupuesto.setText("");
                }
            } catch (ParseException e) {
                tvErrorFechaPresupuesto.setTextColor(RED);
                tvErrorFechaPresupuesto.setText("Introduce una fecha válida");
            }

            Presupuesto presupuesto = new Presupuesto(
                    etNombrePresupuesto.getText().toString(),
                    etFechaPresupuesto.getText().toString(),
                    etPresupuestoMaximo.getText().toString(),
                    etPresupuestoMinimo.getText().toString()

            );
            if (tvErrorNombre.getText().toString().isEmpty() && tvErrorFechaPresupuesto.getText().toString().isEmpty() && tvErrorPresupuestoMinimo.getText().toString().isEmpty() && tvErrorPresupuestoMaximo.getText().toString().isEmpty()) {
                String NombrePresupuesto = etNombrePresupuesto.getText().toString();
                String FechaPresupuesto = etFechaPresupuesto.getText().toString();
                String PresupuestoMaximo = etPresupuestoMinimo.getText().toString();
                String PresupuestoMinimo = etPresupuestoMaximo.getText().toString();

                // Intent para enviar el objeto Viaje a otra actividad
                Intent intent = new Intent(PrincipalOrdinaria1T.this, PrincipalOrdinaria1TB.class);
                Presupuesto mipresupuesto = new Presupuesto(NombrePresupuesto, FechaPresupuesto, PresupuestoMaximo, PresupuestoMinimo);
                intent.putExtra(PRESUPUESTO,mipresupuesto);
                startActivity(intent); // Usar startActivity para iniciar la actividad
            }


        });

    }


    public static class Presupuesto implements Serializable{
        private String NombrePresupuesto;
        private String FechaPresupuesto;
        private String PresupuestoMaximo;
        private String PresupuestoMinimo;

        public Presupuesto(String NombrePresupuesto, String FechaPresupuesto, String PresupuestoMaximo, String PresupuestoMinimo){
            this.FechaPresupuesto = FechaPresupuesto;
            this.NombrePresupuesto = NombrePresupuesto;
            this.PresupuestoMaximo = PresupuestoMaximo;
            this.PresupuestoMinimo = PresupuestoMinimo;
        }

        // Getters y setters
        public String getNombrePresupuesto() { return NombrePresupuesto; }
        public void setNombrePresupuesto(String NombrePresupuesto) { this.NombrePresupuesto = NombrePresupuesto; }

        public String getFechaPresupuesto() { return FechaPresupuesto; }
        public void setFechaPresupuesto(String FechaPresupuesto) { this.FechaPresupuesto = FechaPresupuesto; }

        public String getPresupuestoMaximo() { return PresupuestoMaximo; }
        public void setPresupuestoMaximo(String PresupuestoMaximo) { this.PresupuestoMaximo = PresupuestoMaximo; }

        public String getPresupuestoMinimo() { return PresupuestoMinimo; }
        public void setPresupuestoMinimo(String PresupuestoMinimo) { this.PresupuestoMinimo = PresupuestoMinimo; }

        public String toString() {
            return "Nombre: " + NombrePresupuesto +
                    ", Fecha: " + FechaPresupuesto +
                    ", Presupuesto Minimo: " + PresupuestoMinimo +
                    ", Presupuesto Maximo; " + PresupuestoMaximo;
        }
    }


    }


