package com.example.todosejercicios.ut02;

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

import com.example.todosejercicios.R;

import java.util.ArrayList;
import java.util.List;

public class HeladoA extends AppCompatActivity {
    TextView tvVainilla, tvChocolate, tvFresa, tvTarro, tvError;
    EditText etVainilla, etChocolate, etFresa;
    Spinner spRecipiente;
    Button btGenerar;
    public static final String INFO_FRESA = "Fresa";
    public static final String INFO_VAINILLA = "Vainilla";
    public static final String INFO_CHOCO = "Chocolate";
    public static final String INFO_RECIPIENTE = "Recipiente";
    String recipiente;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helado);
        //init views para que pille todos los elementos que hay en la app
        initViews();
        //listeners de los botones, handlers de las funciones
        setListeners();
        //logica spinner
        spinnerHelado();
    }

    private void spinnerHelado() {
        spRecipiente = findViewById(R.id.spRecipiente);
        List<String> options = new ArrayList<>();
        options.add("Cucurucho");
        options.add("Cucurucho de Chocolate");
        options.add("Tarrina");

        // Crear un ArrayAdapter usando un simple spinner layout y la lista de opciones
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

        // Especificar el layout a usar cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al spinner
        spRecipiente.setAdapter(adapter);

        // Configurar un listener para cuando un ítem sea seleccionado
        spRecipiente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el ítem seleccionado
                recipiente = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional: Manejar casos donde ningún ítem está seleccionado
            }
        });
    }


    private void initViews(){
        tvVainilla = findViewById(R.id.tvVainilla);
        tvChocolate = findViewById(R.id.tvChocolate);
        tvFresa = findViewById(R.id.tvFresa);
        tvTarro = findViewById(R.id.tvTarro);
        etVainilla = findViewById(R.id.etVainilla);
        etChocolate = findViewById(R.id.etChocolate);
        etFresa = findViewById(R.id.etFresa);
        btGenerar = findViewById(R.id.btGenerar);
        tvError = findViewById(R.id.tvError);

    }
    private void setListeners(){
        btGenerar.setOnClickListener(view -> {
            Intent i = new Intent(this, HeladoB.class);
            String bolasFresa = etFresa.getText().toString();
            String bolasChoco = etChocolate.getText().toString();
            String bolasVaini = etVainilla.getText().toString();

            if(!bolasFresa.isEmpty() || !bolasChoco.isEmpty() || !bolasVaini.isEmpty() && !recipiente.isEmpty()){
                i.putExtra(INFO_FRESA, bolasFresa);
                i.putExtra(INFO_CHOCO, bolasChoco);
                i.putExtra(INFO_VAINILLA, bolasVaini);
                i.putExtra(INFO_RECIPIENTE, recipiente);

                startActivity(i);
            }else{
                tvError.setText("Rellena todos los datos.");
            }
        });


    }
}