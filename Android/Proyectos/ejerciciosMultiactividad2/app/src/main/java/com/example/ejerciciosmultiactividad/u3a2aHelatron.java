package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class u3a2aHelatron extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText etVainilla, etChocolate, etFresa;
    Spinner spRecipiente;
    Button btElegir;
    TextView tvError;
    public static final String INFO_CHOCO = "chocolate";
    public static final String INFO_FRESA = "fresa";
    public static final String INFO_VAINILLA = "vainilla";
    public static final String INFO_RECIPIENTE = "recipiente";
    String recipiente;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3a2a_helatron);

        etChocolate = findViewById(R.id.u3a2idetBolasChoco);
        etFresa = findViewById(R.id.u3a2idetBolasFresa);
        etVainilla = findViewById(R.id.u3a2idetBolasVaini);
        spRecipiente = findViewById(R.id.u3a2idspRecipiente);
        btElegir = findViewById(R.id.u3a2idbtElegir);
        tvError = findViewById(R.id.u3a2idtvError);


        btElegir.setOnClickListener(view -> {
            Intent i = new Intent(this, heladeriaB.class);
            String bolasFresa = etFresa.getText().toString();
            String bolasChoco = etChocolate.getText().toString();
            String bolasVaini = etVainilla.getText().toString();

            if(!bolasFresa.isEmpty() && !bolasChoco.isEmpty() && !bolasVaini.isEmpty() && !recipiente.isEmpty()){
                i.putExtra(INFO_FRESA, bolasFresa);
                i.putExtra(INFO_CHOCO, bolasChoco);
                i.putExtra(INFO_VAINILLA, bolasVaini);
                i.putExtra(INFO_RECIPIENTE, recipiente);

                startActivity(i);
            }else{
                tvError.setText("Rellena todos los datos.");
            }



        });

        spRecipiente.setOnItemSelectedListener(this);
    String[] recipientes = { "Cucurucho", "Cucurucho Chocolate", "Tarrina" };
    // Create the instance of ArrayAdapter
    // having the list of courses
    ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, recipientes);

    // set simple layout resource file
    // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Set the ArrayAdapter (ad) data on the
    // Spinner which binds data to spinner
        spRecipiente.setAdapter(ad);

}
    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        recipiente = spRecipiente.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}



