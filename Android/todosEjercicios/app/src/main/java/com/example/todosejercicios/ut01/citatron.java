package com.example.todosejercicios.ut01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.todosejercicios.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class citatron extends AppCompatActivity {

    TextView tvDni, tvNombre, tvApellidos, tvHora, tvFecha, tvErrorFecha, tvErrorHora, tvErrorGeneral, tvMostrarNombre, tvMostrarApellidos, tvFechaFin, tvHoraFin, tvMostrarDNI;
    Button btPedirCita;
    ImageView imgCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citatron);
        //init views para que pille todos los elementos que hay en la app
        initViews();
        //listeners de los botones, handlers de las funciones
        setListeners();

    }

    private void initViews() {
        tvDni = findViewById(R.id.tvDni);
        tvNombre = findViewById(R.id.tvNombre);
        tvHora = findViewById(R.id.tvHora);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        tvFecha = findViewById(R.id.tvFecha);
        tvErrorHora = findViewById(R.id.tvErrorHora);
        btPedirCita = findViewById(R.id.btPedirCita);
        tvErrorGeneral = findViewById(R.id.tvErrorGeneral);
        tvMostrarNombre = findViewById(R.id.tvMostrarNombre);
        tvMostrarApellidos = findViewById(R.id.tvMostrarApellidos);
        tvMostrarDNI = findViewById(R.id.tvMostrarDNI);
        tvFechaFin = findViewById(R.id.tvFechaFin);
        tvHoraFin = findViewById(R.id.tvHoraFin);
        imgCheck = findViewById(R.id.imgCheck);

    }

    private void setListeners() {
        tvFecha.setOnClickListener((View v) -> {
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
                    citatron.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        Calendar selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        int diaSemana = selectedDate.get(Calendar.DAY_OF_WEEK);
                        // on below line we are setting date to our text view.
                        if (diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY) {
                            tvErrorFecha.setText("Fecha erronea. Abrimos de lunes a viernes.");
                        } else {
                            // on below line we are setting date to our text view.
                            tvFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                            tvErrorFecha.setText("");
                        }

                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        //aqui listener de la hora
        tvHora.setOnClickListener((View v) -> {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    citatron.this,
                    (view, hourOfDay, minute1) -> {
                        if (hourOfDay >= 9 && hourOfDay < 14) {
                            String formattedTime = String.format("%02d:%02d", hourOfDay, minute1);
                            tvHora.setText(formattedTime);
                            tvErrorHora.setText("");
                        } else {
                            tvErrorHora.setText("Hora errónea. Abrimos de 9 a 14.");
                        }
                    }, hour, minute, true);
            timePickerDialog.show();
        });


        btPedirCita.setOnClickListener((View v) -> {
            //regex del dni usando match pattern
            String Nombre = tvNombre.getText().toString();
            String Apellidos = tvApellidos.getText().toString();
            String DNI = tvDni.getText().toString();
            String Fecha = tvFecha.getText().toString();
            String Hora = tvHora.getText().toString();

            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher matcher=pat.matcher(DNI);

            if(matcher.matches() && !Nombre.isEmpty() && !Apellidos.isEmpty() && !Fecha.isEmpty() &&!Hora.isEmpty()) {
                tvFecha.setVisibility(View.GONE);
                tvHora.setVisibility(View.GONE);
                tvApellidos.setVisibility(View.GONE);
                tvDni.setVisibility(View.GONE);
                btPedirCita.setVisibility(View.GONE);
                tvErrorGeneral.setVisibility(View.GONE);
                tvErrorFecha.setVisibility(View.GONE);
                tvErrorHora.setVisibility(View.GONE);
                tvNombre.setVisibility(View.GONE);


                tvMostrarNombre.setVisibility(View.VISIBLE);
                tvMostrarApellidos.setVisibility(View.VISIBLE);
                tvMostrarDNI.setVisibility(View.VISIBLE);
                //tvMensajeFinal.setVisibility(View.VISIBLE);
                imgCheck.setVisibility(View.VISIBLE);
                tvHoraFin.setVisibility(View.VISIBLE);
                tvFechaFin.setVisibility(View.VISIBLE);

                tvMostrarNombre.setText(Nombre);
                tvMostrarApellidos.setText(Apellidos);
                tvMostrarDNI.setText(DNI);

                tvFechaFin.setText("Fecha: ");
                tvFechaFin.append(Fecha);

                tvHoraFin.setText("Hora: ");
                tvHoraFin.append(Hora);
            }
            else{
                if (Nombre.isEmpty()) {
                    tvErrorGeneral.setText("Nombre vacio.");
                }


                if (!(matcher.matches()) || DNI.isEmpty()) {
                    tvErrorGeneral.setText("Error en el DNI o DNI vacio");
                } else {
                    tvErrorGeneral.setText("");
                }

                if (Fecha.isEmpty()) {
                    tvErrorFecha.setText("Seleccione una fecha.");
                } else {
                    tvErrorFecha.setText("");
                }

                if (Hora.isEmpty()) {
                    tvErrorHora.setText("Seleccione una hora.");
                } else {
                    tvErrorHora.setText("");
                }
            }


        });


    }




}