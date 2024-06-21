package com.example.todosejercicios.ut03;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todosejercicios.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class PredictExamenExtraordinaria1 extends AppCompatActivity {

    public static final String FIESTA = "Fiesta";
    EditText etFechaEvento, etNombreEvento,etNumeroMaxAsistentes, etNumeroMinAsistentes;
    TextView tvErrorNombreEvento, tvErrorFechaEvento, tvErrorMaxAsistentes,tvErrorMinAsistentes;

    ActivityResultLauncher<Intent> miResultadoLauncher;
    Button btReservaFiesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_examen_extraordinaria1);
        //intent devuelto
        setupActivityResultLauncher();
        //metemos las vistas
        tvErrorNombreEvento = findViewById(R.id.tvErrorNombreEvento);
        tvErrorFechaEvento = findViewById(R.id.tvErrorFechaEvento);
        tvErrorMaxAsistentes = findViewById(R.id.tvErrorMaxAsistentes);
        tvErrorMinAsistentes = findViewById(R.id.tvErrorMinAsistentes);
        etFechaEvento = findViewById(R.id.etFechaEvento);
        etNombreEvento = findViewById(R.id.etNombreEvento);
        etNumeroMinAsistentes = findViewById(R.id.etNumeroMinAsistentes);
        etNumeroMaxAsistentes = findViewById(R.id.etNumeroMaxAsistentes);
        btReservaFiesta = findViewById(R.id.btReservaFiesta);
        listenerFecha();
        listenerReserva();
    }

    //creamos un objeto para pasar todo mas facil

    public static class Fiesta implements Serializable {
        private String nombreFiesta;
        private String fechaFiesta;
        private String maxAsistentes;
        private String minAsistentes;

        public Fiesta(String nombreFiesta, String fechaFiesta, String maxAsistentes, String minAsistentes ){
            this.nombreFiesta = nombreFiesta;
            this.fechaFiesta = fechaFiesta;
            this.maxAsistentes = maxAsistentes;
            this.minAsistentes = minAsistentes;
        }

        //getters y setters

        public String getNombreFiesta() {
            return nombreFiesta;
        }

        public String getMinAsistentes() {
            return minAsistentes;
        }

        public String getMaxAsistentes() {
            return maxAsistentes;
        }

        public String getFechaFiesta() {
            return fechaFiesta;
        }

        public void setFechaFiesta(String fechaFiesta) {
            this.fechaFiesta = fechaFiesta;
        }

        public void setMaxAsistentes(String maxAsistentes) {
            this.maxAsistentes = maxAsistentes;
        }

        public void setMinAsistentes(String minAsistentes) {
            this.minAsistentes = minAsistentes;
        }

        public void setNombreFiesta(String nombreFiesta) {
            this.nombreFiesta = nombreFiesta;
        }

        public String toString(){
            return "Nombre: " + nombreFiesta +
                    ", Fecha: " + fechaFiesta +
                    ", Maximo de asistentes: " + maxAsistentes +
                    ", Minimo de asistentes: " + minAsistentes;
        }
    }


    private void listenerFecha(){
        etFechaEvento.setOnClickListener((View v) -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    PredictExamenExtraordinaria1.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        Calendar selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        int diaSemana = selectedDate.get(Calendar.DAY_OF_WEEK);
                        etFechaEvento.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        tvErrorFechaEvento.setText("");
                    },
                    year, month, day);
            datePickerDialog.show();
        });
    }






    private void setupActivityResultLauncher(){
        miResultadoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                Log.d(ContentValues.TAG, "FIESTA CANCELADA");
                int code = result.getResultCode();
                switch(code){
                    case RESULT_CANCELED:
                        break;
                    case PredictExamenExtraordinaria1B.CODIGO_RESET:
                        etFechaEvento.setText("");
                        etNombreEvento.setText("");
                        etNumeroMaxAsistentes.setText("");
                        etNumeroMinAsistentes.setText("");

                }
    });
    }

    private void listenerReserva(){
        btReservaFiesta.setOnClickListener(v ->{
            tvErrorFechaEvento.setTextColor(RED);
            tvErrorMaxAsistentes.setTextColor(RED);
            tvErrorMinAsistentes.setTextColor(RED);
            tvErrorNombreEvento.setTextColor(RED);
            tvErrorFechaEvento.setText("");
            tvErrorMaxAsistentes.setText("");
            tvErrorMinAsistentes.setText("");
            tvErrorNombreEvento.setText("");

            if (etNumeroMinAsistentes.getText().toString().trim().equals("")) {
                tvErrorMinAsistentes.setText("Introduce un numero");
            }else{
                tvErrorMinAsistentes.setText("");

            }

            if (etNumeroMaxAsistentes.getText().toString().trim().equals("")) {
                tvErrorMaxAsistentes.setText("Introduce un numero");
            }else{
                tvErrorMaxAsistentes.setText("");

            }

            //control aforo
            if(!tvErrorMinAsistentes.getText().toString().trim().equals("")){
            }else{
                int maximo = Integer.parseInt(etNumeroMaxAsistentes.getText().toString());
                int minimo = Integer.parseInt(etNumeroMinAsistentes.getText().toString());
                if(maximo < minimo){
                    tvErrorMaxAsistentes.setText("Introduce un numero mayor que el minimo");
                    tvErrorMinAsistentes.setText("Introduce un numoer menos que el mayor");
                }
            }




            if (etFechaEvento.getText().toString().trim().equals("")) {
                tvErrorFechaEvento.setText("Introduce una fecha");
            }else{
                //Control de fechas
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                //obtener fecha etFechas
                String fechaEventostr = etFechaEvento.getText().toString();
                //parse and compare
                try {
                    Date fechaSalida = sdf.parse(fechaEventostr);
                    //fecha de hoy
                    Date hoy = new Date();
                    // Paso 4: Comparar las fechas
                    if (fechaSalida.before(hoy)) {
                        // Mostrar algún error o realizar alguna acción
                        tvErrorFechaEvento.setText("La fecha de la fiesta no puede ser anterior a hoy.");
                    } else {
                        // Las fechas son correctas, proceder con la lógica de negocio
                        tvErrorFechaEvento.setTextColor(GREEN);
                        tvErrorFechaEvento.setText("");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejar el error de formato aquí
                    tvErrorFechaEvento.setText("Formato de fecha incorrecto.");
                    tvErrorFechaEvento.setTextColor(Color.RED);
                }

            }



            if (etNombreEvento.getText().toString().trim().equals("")) {
                tvErrorNombreEvento.setText("Introduce un nombre");
            }else{
                tvErrorNombreEvento.setText("");

            }


            Fiesta fiesta = new Fiesta(
                    etNombreEvento.getText().toString(),
                    etFechaEvento.getText().toString(),
                    etNumeroMaxAsistentes.getText().toString(),
                    etNumeroMinAsistentes.getText().toString()

            );

            if (tvErrorFechaEvento.getText().toString().isEmpty() && tvErrorNombreEvento.getText().toString().isEmpty() && tvErrorMinAsistentes.getText().toString().isEmpty() && tvErrorMaxAsistentes.getText().toString().isEmpty()){
                String nombreFiesta = etNombreEvento.getText().toString();
                String fechaFiesta = etFechaEvento.getText().toString();
                String maxAsistentes = etNumeroMaxAsistentes.getText().toString();
                String minAsistentes = etNumeroMinAsistentes.getText().toString();

                //intent
                Intent intent = new Intent(PredictExamenExtraordinaria1.this, PredictExamenExtraordinaria1B.class);
                Fiesta mifiesta = new Fiesta (nombreFiesta, fechaFiesta, maxAsistentes, minAsistentes);
                intent.putExtra(FIESTA, mifiesta);
                miResultadoLauncher.launch(intent);
            }



        });
    }




}
