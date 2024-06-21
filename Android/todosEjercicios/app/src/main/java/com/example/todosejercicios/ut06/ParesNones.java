package com.example.todosejercicios.ut06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.todosejercicios.R;


public class ParesNones extends AppCompatActivity {

    Button btPares, btNones, btReinicia;
    TextView tvResultadoParesNones, tvMarcadorPares, tvMarcadorNones, tvMarcadorPares1, tvMarcadorNones1, tvIntroduceNumero;
    EditText etSeleccionaNumero;
    ProgressBar pbCargandoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pares_nones);
        pbCargandoResultado = findViewById(R.id.pbCargandoResultado);
        btPares = findViewById(R.id.btPares);
        btNones=findViewById(R.id.btNones);
        btReinicia= findViewById(R.id.btReinicia);
        tvResultadoParesNones=findViewById(R.id.tvResultadoParesNones);
        tvMarcadorPares=findViewById(R.id.tvMarcadorPares);
        tvMarcadorNones=findViewById(R.id.tvMarcadorNones);
        tvMarcadorPares1=findViewById(R.id.tvMarcadorPares);
        tvMarcadorNones1=findViewById(R.id.tvMarcadorNones1);
        tvIntroduceNumero=findViewById(R.id.tvIntroduceNumero);
        etSeleccionaNumero=findViewById(R.id.etSeleccionaNumero);

        ParesNonesViewModel vm = new ViewModelProvider(this).get(ParesNonesViewModel.class);
        vm.getMisDatos().observe(this, integer -> {
            //actualizar la interfaz
            if(integer == ParesNonesViewModel.FAIL){
                tvResultadoParesNones.setText("Error accediendo a los datos");
            }else{
                tvResultadoParesNones.setText(""+integer + "\n");
            }
            pbCargandoResultado.setVisibility(View.INVISIBLE);
            //resto de botones

        });
        vm.getParNone().observe(this, Boolean->{
            //actualiza interfaz
            if(Boolean.equals("true")) {
                tvResultadoParesNones.append("Ganan los pares");
            }else{
                tvResultadoParesNones.append("Ganan los Nones");
            }

        });

        //por defecto
        btNones.setEnabled(true);
        btPares.setEnabled(true);
        btReinicia.setEnabled(true);
        pbCargandoResultado.setVisibility(View.INVISIBLE);
        tvMarcadorPares1.setVisibility(View.GONE);
        tvMarcadorPares.setVisibility(View.GONE);
        tvMarcadorNones.setVisibility(View.GONE);
        tvMarcadorNones1.setVisibility(View.GONE);

        btPares.setOnClickListener((v) -> {
            if (!etSeleccionaNumero.equals("")){
                pbCargandoResultado.setVisibility(View.VISIBLE);
                etSeleccionaNumero.setVisibility(View.INVISIBLE);
                btPares.setEnabled(false);
                btNones.setEnabled(false);
                btReinicia.setEnabled(true);
                String numero1 = etSeleccionaNumero.getText().toString();
                int Operando1 = Integer.parseInt(numero1);
                vm.procesarNumero(Operando1);
            }


        });
        btReinicia.setOnClickListener((v) -> {
            pbCargandoResultado.setVisibility(View.INVISIBLE);
            etSeleccionaNumero.setVisibility(View.VISIBLE);
            btPares.setEnabled(true);
            btNones.setEnabled(false);
            btReinicia.setEnabled(true);
            tvMarcadorNones.setText("");
            tvMarcadorNones1.setText("");
            tvMarcadorPares1.setText("");
            tvMarcadorPares1.setText("");


        });
        btNones.setOnClickListener((v) -> {
            if (!etSeleccionaNumero.equals("")){
                pbCargandoResultado.setVisibility(View.VISIBLE);
                etSeleccionaNumero.setVisibility(View.INVISIBLE);
                btPares.setEnabled(false);
                btNones.setEnabled(false);
                btReinicia.setEnabled(true);
                String numero1 = etSeleccionaNumero.getText().toString();
                int Operando1 = Integer.parseInt(numero1);
                vm.procesarNumero(Operando1);
            }
        });
    }
}