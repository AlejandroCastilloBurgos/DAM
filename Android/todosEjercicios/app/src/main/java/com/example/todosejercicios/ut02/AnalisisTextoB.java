package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalisisTextoB extends AppCompatActivity {
    public static final String RESULTADO_ANALISIS = "";

    TextView tvResultadoAnalisis;
    Button btVolverPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis_texto_b);
        initviews();
        listeners();
        recibirIntent();
    }
    private void  initviews(){
        tvResultadoAnalisis = findViewById(R.id.tvResultadoAnalisis);
        btVolverPrincipal = findViewById(R.id.btVolverPrincipal);
    }

    private void recibirIntent() {
        Bundle info = getIntent().getExtras();

        String textoObtenido = info.getString(AnalisisTextoA.TEXTO_ANALIZAR);
        Map<Character, Integer> mapa = new LinkedHashMap<>();

        for (int i = 0; i < textoObtenido.length(); i++) {
            char c = textoObtenido.charAt(i);
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c); // Asegurar que el conteo es case-insensitive
                if (mapa.containsKey(c)) {
                    mapa.put(c, mapa.get(c) + 1);
                } else {
                    mapa.put(c, 1);
                }
            }
        }

        // Ordena la lista de acuerdo a la frecuencia de las letras
        List<Map.Entry<Character, Integer>> lista = new ArrayList<>(mapa.entrySet());
        Collections.sort(lista, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // Mostrar todas las letras y sus recuentos en el TextView
        StringBuilder sb = new StringBuilder();
        lista.forEach(entry -> sb.append("Letra ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));


        tvResultadoAnalisis.setText(sb.toString());
    }
    private void listeners(){
        btVolverPrincipal.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            String devuelveResultado = tvResultadoAnalisis.getText().toString();

            resultIntent.putExtra(AnalisisTextoA.RESULTADO_FINAL, devuelveResultado);
            setResult(RESULT_OK, resultIntent);
            finish(); // Cierra AnalisisB y devuelve el resultado a FibonacciA.
        });
    }



}