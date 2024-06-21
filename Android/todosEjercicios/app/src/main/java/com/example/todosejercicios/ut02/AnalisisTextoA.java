package com.example.todosejercicios.ut02;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class AnalisisTextoA extends AppCompatActivity {

    public static final String TEXTO_ANALIZAR = "";

    public static final String RESULTADO_FINAL = "";

    EditText etIntroduceTexto;
    Button btAnaliza;
    TextView tvErrorAnalisis, tvResultadoAnalisisFinal;
    ActivityResultLauncher<Intent> Analizador;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis_texto);
        //init
        initViews();
        //listener+intent
        listener();
        //recibe intent
        setupActivityResultLauncher();
    }

    private void initViews(){
        etIntroduceTexto = findViewById(R.id.etIntroduceTexto);
        btAnaliza = findViewById(R.id.btAnaliza);
        tvErrorAnalisis = findViewById(R.id.tvErrorAnalisis);
        tvResultadoAnalisisFinal = findViewById(R.id.tvResultadoAnalisisFinal);
    }
    private void listener(){
        btAnaliza.setOnClickListener(view -> {
            Intent i = new Intent(this, AnalisisTextoB.class);
            String TextoParaAnalizar = etIntroduceTexto.getText().toString();

            if (!TextoParaAnalizar.isEmpty()) {
                i.putExtra(TEXTO_ANALIZAR, TextoParaAnalizar);
                Analizador.launch(i);
            } else {
                // Handle error: Show a message that both fields must be filled
            }
        });
    }
    private void setupActivityResultLauncher() {
        Analizador = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String nuevoN1 = data.getStringExtra(RESULTADO_FINAL);
                        tvResultadoAnalisisFinal.setText(nuevoN1);
                        etIntroduceTexto.setVisibility(View.GONE);
                        btAnaliza.setVisibility(View.GONE);
                    }
                }
        );
    }
}