package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class HeladoB extends AppCompatActivity {

    TextView tvBolasHelado, tvTarrinaSeleccionada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helado_b);
        //init
        initViews();
        //intent
        recibirIntent();
        //
    }

    private void initViews(){
        tvBolasHelado = findViewById(R.id.tvBolasHelado);
        tvTarrinaSeleccionada = findViewById(R.id.tvTarrinaSeleccionada);


    }
    private void recibirIntent(){
        Bundle info = getIntent().getExtras();

        String bolasFresa = info.getString(HeladoA.INFO_FRESA);
        int bFresa = Integer.parseInt(bolasFresa);
        String  bolasChoco = info.getString(HeladoA.INFO_CHOCO);
        int bChoco = Integer.parseInt(bolasChoco);
        String bolasVainilla = info.getString(HeladoA.INFO_VAINILLA);
        int bVainilla = Integer.parseInt(bolasVainilla);
        String recipiente = info.getString(HeladoA.INFO_RECIPIENTE);

        for(int i = 1; i<=bFresa; i++){
            String textToAdd = "O";
            SpannableString spannable = new SpannableString(textToAdd);
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(255, 192, 203)), 0, textToAdd.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvBolasHelado.append(spannable);

        }
        for(int i = 1; i<=bChoco; i++){
            String textToAdd = "O";
            SpannableString spannable = new SpannableString(textToAdd);
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(255, 255, 0)), 0, textToAdd.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvBolasHelado.append(spannable);

        }
        for(int i = 1; i<=bVainilla; i++){
            String textToAdd = "O";
            SpannableString spannable = new SpannableString(textToAdd);
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(165, 42, 42)), 0, textToAdd.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvBolasHelado.append(spannable);

        }
        if(recipiente.equals("Cucurucho")){
            tvTarrinaSeleccionada.setText("V");
        } else if (recipiente.equals("Cucurucho Chocolate")) {
            tvTarrinaSeleccionada.setText("V");
            tvTarrinaSeleccionada.setTextColor(Color.rgb(128,64,0));
        } else if (recipiente.equals("Tarrina")) {
            tvTarrinaSeleccionada.setText("U");
        }

    }
}