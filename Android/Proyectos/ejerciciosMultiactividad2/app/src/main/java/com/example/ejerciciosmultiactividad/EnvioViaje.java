package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvioViaje extends AppCompatActivity {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    TextView switchI, switchV;
    EditText horaIda, horaVuelta, fechaIda, fechaVuelta, DNI, direccionRecogida, nombre;
    Spinner ciudadesO, ciudadesD;
    Switch switchIdaVuelta;
    Button enviar;
    Viajero viajero = new Viajero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envioviaje);

        horaIda = findViewById(R.id.horaIda);
        horaVuelta = findViewById(R.id.horaVuelta);
        fechaIda = findViewById(R.id.fechaIda);
        fechaVuelta = findViewById(R.id.fechaVuelta);
        DNI = findViewById(R.id.vDNI);
        direccionRecogida = findViewById(R.id.vDireccion);
        nombre = findViewById(R.id.vNombre);
        ciudadesO = findViewById(R.id.ciudadesOrigen);
        ciudadesD = findViewById(R.id.ciudadesDestino);
        switchIdaVuelta = findViewById(R.id.switchIdaVuelta);
        switchI = findViewById(R.id.switchIda);
        switchV = findViewById(R.id.switchVuelta);
        enviar = findViewById(R.id.envioViaje);

        switchIdaVuelta.setOnClickListener(view -> {
            if (switchIdaVuelta.isChecked()) {
                switchV.setVisibility(View.VISIBLE);
                switchI.setVisibility(View.INVISIBLE);
                fechaVuelta.setVisibility(View.VISIBLE);
                horaVuelta.setVisibility(View.VISIBLE);
            } else {
                switchV.setVisibility(View.INVISIBLE);
                switchI.setVisibility(View.VISIBLE);
                fechaVuelta.setVisibility(View.INVISIBLE);
                horaVuelta.setVisibility(View.INVISIBLE);
            }
        });

        fechaIda.setOnClickListener(view -> showDatePickerDialog(fechaIda));

        fechaVuelta.setOnClickListener(view -> showDatePickerDialog(fechaVuelta));

        enviar.setOnClickListener(view -> {
            try {
                String nombreV = String.valueOf(nombre.getText());
                String direccion = String.valueOf(direccionRecogida.getText());
                String DNIv = String.valueOf(DNI.getText());
                String ciudadOrigenv = ciudadesO.getSelectedItem().toString();
                String ciudadDestinov = ciudadesD.getSelectedItem().toString();
                String horaIdav = String.valueOf(horaIda.getText());
                Date fechaIdav = sdf.parse(String.valueOf(fechaIda.getText()));
                Date fechaVueltav;
                String horaVueltav;

                Boolean fechasValidadas;
                Boolean DNIValidado = validadoDNI(DNIv);
                Boolean ciudadesValidadas = validadoCiudades(ciudadOrigenv, ciudadDestinov);

                if (!DNIValidado) {
                    DNI.setText("DNI NO VALIDO");
                }

                if (switchIdaVuelta.isChecked()) {
                    horaVueltav = String.valueOf(horaVuelta.getText());
                    fechaVueltav = sdf.parse(String.valueOf(fechaVuelta.getText()));
                    fechasValidadas = validadoFechas(fechaIdav, fechaVueltav);
                    if (!fechasValidadas) {
                        fechaIda.setText("ERROR DE FECHAS");
                        fechaVuelta.setText("ERROR DE FECHAS");
                    }
                    if (DNIValidado && fechasValidadas && ciudadesValidadas) {
                        viajero = new Viajero(nombreV, direccion, DNIv, ciudadOrigenv, ciudadDestinov, horaIdav, horaVueltav, fechaIdav, fechaVueltav);
                        viajero.setIdavuelta(true);
                        lanzar();
                    }
                } else {
                    if (DNIValidado && ciudadesValidadas) {
                        viajero = new Viajero(nombreV, direccion, DNIv, ciudadOrigenv, ciudadDestinov, horaIdav, fechaIdav);
                        lanzar();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showDatePickerDialog(EditText fecha) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((view, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = day + "-" + (month + 1) + "-" + year;
            fecha.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    void lanzar() {
        Intent intento = new Intent(this, ReciboViaje.class);
        intento.putExtra("ENVIO", viajero);
        startActivity(intento);
    }

    public boolean validadoCiudades(String ciudadOrigenv, String ciudadDestinov) {
        return !ciudadOrigenv.equals(ciudadDestinov);
    }

    public boolean validadoFechas(Date d1, Date d2) {
        return d1.compareTo(d2) < 0;
    }

    public boolean validadoDNI(String DNIv) {
        Pattern pat = Pattern.compile("[0-9]{7,8}[A-Za-z]");
        Matcher mat = pat.matcher(DNIv);
        return mat.matches();
    }
}
