package com.example.heladeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spRecipiente;
    Button btGenerar;

    TextView tvChoco;
    TextView tvFresa;
    TextView tvVainilla;

    EditText etChoco;
    EditText etFresa;
    EditText etVainilla;

    String[] opciones = {"Cucurucho", "Cucurucho Chocolate", "Tarrina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spRecipiente = findViewById(R.id.spRecipiente);
        btGenerar = findViewById(R.id.btGenerar);

        tvChoco = findViewById(R.id.tvChoco);
        tvFresa = findViewById(R.id.tvFresa);
        tvVainilla = findViewById(R.id.tvVainilla);

        etChoco = findViewById(R.id.etChoco);
        etFresa = findViewById(R.id.etFresa);
        etVainilla = findViewById(R.id.etVainilla);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        spRecipiente.setAdapter(adapter);

        spRecipiente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcionSeleccionada = opciones[position];
                // Realiza alguna acción con la opción seleccionada
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acción cuando no se selecciona nada
            }
        });
        btGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los TextViews y EditTexts
                String choco = etChoco.getText().toString();
                String fresa = etFresa.getText().toString();
                String vainilla = etVainilla.getText().toString();

                Intent intent = new Intent(MainActivity.this, SegundaActividad.class);

                // Pasar la información a la siguiente actividad
                intent.putExtra("choco", choco);
                intent.putExtra("fresa", fresa);
                intent.putExtra("vainilla", vainilla);

                startActivity(intent);
            }
        });
    }
}
