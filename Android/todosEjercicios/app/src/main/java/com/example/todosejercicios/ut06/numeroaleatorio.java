package com.example.todosejercicios.ut06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.todosejercicios.R;
public class numeroaleatorio extends AppCompatActivity {

    TextView tvNumero;
    Button btGenerar;
    ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeroaleatorio);

        tvNumero = findViewById(R.id.idtvResultado);
        btGenerar = findViewById(R.id.idbtJugar);
        pbCargando = findViewById(R.id.idpbCargando);

        numeroaleatorioViewModel vm = new ViewModelProvider(this).get(numeroaleatorioViewModel.class);

        vm.getNumero().observe(this, integer -> {
            // Actualizar la interfaz
            if(integer == numeroaleatorioViewModel.FAIL){
                tvNumero.setText("Error en el acceso a los datos");
            }else{
                tvNumero.setText(""+integer);
            }
            pbCargando.setVisibility(View.INVISIBLE);
            tvNumero.setVisibility(View.VISIBLE);
            btGenerar.setEnabled(true);
        });

        btGenerar.setEnabled(false);

        btGenerar.setOnClickListener((v)->{
            pbCargando.setVisibility(View.VISIBLE);
            tvNumero.setVisibility(View.INVISIBLE);
            btGenerar.setEnabled(false);
            vm.cargaNumero();
        });
    }
}