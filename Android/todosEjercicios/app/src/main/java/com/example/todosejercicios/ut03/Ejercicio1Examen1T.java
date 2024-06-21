package com.example.todosejercicios.ut03;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut01.citatron;
import com.example.todosejercicios.ut02.AnalisisTextoB;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Ejercicio1Examen1T extends AppCompatActivity {
    public static final String VIAJE = "VIAJE";
    EditText etFechaRegreso, etFechaSalida;
    Spinner spOrigen, spDestino;
    Button btReservar;
    CheckBox chkboxSoloIda;
    ActivityResultLauncher<Intent> miResultadoLauncher;


    TextView tvErrorViaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1_examen1_t);
        //initviews
        initViews();
        //listeners
        listenerReserva();
        listenerFecha();
        soloida();
        //intentdevuelto
        setupActivityResultLauncher();
    }
    private void setupActivityResultLauncher() {
        miResultadoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    Log.d(ContentValues.TAG,"VUELVE CANCELADO");
                    int code = result.getResultCode();
                    switch (code){
                        case RESULT_CANCELED:
                            break;
                        case Ejercicio1Examen1TB.CODIGO_RESET:
                            spOrigen.setSelection(0); // Ajusta el spinner de origen a la primera posición
                            spDestino.setSelection(0); // Ajusta el spinner de destino a la primera posición
                            etFechaSalida.setText(""); // Limpia el campo de texto de fecha de salida
                            etFechaRegreso.setText(""); // Limpia el campo de texto de fecha de regreso
                            chkboxSoloIda.setChecked(false); // Desmarca la casilla de solo ida
                    }
                });
    }

    public static class Viaje implements Serializable {
        private String lugarOrigen;
        private String lugarDestino;
        private String fechaSalida;
        private String fechaRegreso;
        private boolean esSoloIda;

        public Viaje(String lugarOrigen, String lugarDestino, String fechaSalida, String fechaRegreso, boolean esSoloIda) {
            this.lugarOrigen = lugarOrigen;
            this.lugarDestino = lugarDestino;
            this.fechaSalida = fechaSalida;
            this.fechaRegreso = fechaRegreso;
            this.esSoloIda = esSoloIda;
        }

        // Getters y setters
        public String getLugarOrigen() { return lugarOrigen; }
        public void setLugarOrigen(String lugarOrigen) { this.lugarOrigen = lugarOrigen; }

        public String getLugarDestino() { return lugarDestino; }
        public void setLugarDestino(String lugarDestino) { this.lugarDestino = lugarDestino; }

        public String getFechaSalida() { return fechaSalida; }
        public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }

        public String getFechaRegreso() { return fechaRegreso; }
        public void setFechaRegreso(String fechaRegreso) { this.fechaRegreso = fechaRegreso; }

        public boolean isEsSoloIda() { return esSoloIda; }
        public void setEsSoloIda(boolean esSoloIda) { this.esSoloIda = esSoloIda; }

        public String toString() {
            return "Origen: " + lugarOrigen +
                    ", Destino: " + lugarDestino +
                    ", Salida: " + fechaSalida +
                    (esSoloIda ? "" : ", Regreso: " + fechaRegreso) +
                    ", Solo ida: " + (esSoloIda ? "Sí" : "No");
        }
    }
    private void soloida(){

        // Establecer el listener para cambios en el estado del CheckBox, el compoudndbutton sirve para manejar el estado check o no check
        chkboxSoloIda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                soloida();
            }
        });

        if (chkboxSoloIda.isChecked()){
            etFechaRegreso.setVisibility(View.GONE);
        }else{
            etFechaRegreso.setVisibility(View.VISIBLE);
        }
    }
    private void listenerReserva(){
        //Fecha vuelta no puede ser antes de la fecha de salida, ciudad de origen y destino !=
        btReservar.setOnClickListener(v -> {
            tvErrorViaje.setTextColor(Color.RED);  // Establece el color rojo para todos los mensajes de error
            tvErrorViaje.setText("");  // Limpiar mensajes de error previos

            if (spOrigen.getSelectedItem().toString().equals(spDestino.getSelectedItem().toString())) {
                tvErrorViaje.setText("Error: La ciudad de origen y destino no pueden ser la misma.");
                return;
            }

            if (spOrigen.getSelectedItemPosition() == 0 || spDestino.getSelectedItemPosition() == 0) {
                tvErrorViaje.setText("Por favor selecciona una ciudad válida.");
                return;
            }
            //control de fecha, primero debemos cambiar
            //create the day format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            //obtener fecha de los etFechasSalida
            String fechaSalidaStr = etFechaSalida.getText().toString();
            String fechaRegresoStr = etFechaRegreso.getText().toString();
            //parse and compare the entries to know if its before or not
            if (chkboxSoloIda.isChecked()){
                //tvErrorViaje.setText("");
            }else{
                try {
                    Date fechaSalida = sdf.parse(fechaSalidaStr);
                    Date fechaRegreso = sdf.parse(fechaRegresoStr);

                    // Paso 4: Comparar las fechas
                    if (fechaRegreso.before(fechaSalida)) {
                        // Mostrar algún error o realizar alguna acción
                        tvErrorViaje.setText("La fecha de regreso no puede ser anterior a la fecha de salida.");
                        tvErrorViaje.setTextColor(Color.RED);
                    } else if (chkboxSoloIda.isChecked()) {
                        // No hay problema posible
                        tvErrorViaje.setText("");
                    } else {
                        // Las fechas son correctas, proceder con la lógica de negocio
                        tvErrorViaje.setText("");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejar el error de formato aquí
                    tvErrorViaje.setText("Formato de fecha incorrecto.");
                    tvErrorViaje.setTextColor(Color.RED);
                }
            }
            if (etFechaRegreso.getText().toString().trim().equals("") && !chkboxSoloIda.isChecked()) {
                tvErrorViaje.setText("Introduce una fecha de regreso.");
            }

            if (etFechaSalida.getText().toString().trim().equals("")) {
                tvErrorViaje.setText("Introduce una fecha");
            }
            //intent solo si no hay errores, tremendo

            Viaje viaje = new Viaje(
                    spOrigen.getSelectedItem().toString(),
                    spDestino.getSelectedItem().toString(),
                    etFechaSalida.getText().toString(),
                    etFechaRegreso.getText().toString(),
                    chkboxSoloIda.isChecked()
            );

            if (tvErrorViaje.getText().toString().isEmpty()) {
                String LugarOrigen = spOrigen.getSelectedItem().toString();
                String LugarDestino = spDestino.getSelectedItem().toString();
                String FechaSalida = etFechaSalida.getText().toString();
                String FechaRegreso = etFechaRegreso.getText().toString();
                boolean soloida = chkboxSoloIda.isChecked();

                // Intent para enviar el objeto Viaje a otra actividad
                Intent intent = new Intent(Ejercicio1Examen1T.this, Ejercicio1Examen1TB.class);
                Viaje miviaje = new Viaje(LugarOrigen, LugarDestino, FechaSalida, FechaRegreso,soloida);
                intent.putExtra(VIAJE,miviaje);
                miResultadoLauncher.launch(intent);  // Usar startActivity para iniciar la actividad
            }

        });
    }
    private void listenerFecha(){
        etFechaSalida.setOnClickListener((View v) -> {
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
                    Ejercicio1Examen1T.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        Calendar selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        int diaSemana = selectedDate.get(Calendar.DAY_OF_WEEK);
                        // on below line we are setting date to our text view.
                            // on below line we are setting date to our text view.
                            etFechaSalida.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                            tvErrorViaje.setText("");

                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        etFechaRegreso.setOnClickListener((View v) -> {
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
                    Ejercicio1Examen1T.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        Calendar selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        int diaSemana = selectedDate.get(Calendar.DAY_OF_WEEK);
                        // on below line we are setting date to our text view.
                        // on below line we are setting date to our text view.
                        etFechaRegreso.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        tvErrorViaje.setText("");

                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

    }


    private void initViews(){
        etFechaRegreso = findViewById(R.id.etFechaRegreso);
        etFechaSalida = findViewById(R.id.etFechaSalida);
        spOrigen = findViewById(R.id.spOrigen);
        spDestino = findViewById(R.id.spDestino);
        btReservar = findViewById(R.id.btReservar);
        chkboxSoloIda = findViewById(R.id.chkboxSoloIda);
        tvErrorViaje = findViewById(R.id.tvErrorViaje);

    }
}