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

public class sumadenumeros extends AppCompatActivity {

    EditText etOperadorSuma1, etOperadorSuma2;
    TextView tvSumaSigno, tvOperadorFinal;
    Button btSuma, btReiniciaApp;
    ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumadenumeros);

        etOperadorSuma1 = findViewById(R.id.etOperadorSuma1);
        etOperadorSuma2 = findViewById(R.id.etOperadorSuma2);
        tvSumaSigno = findViewById(R.id.tvSumaSigno);
        btSuma = findViewById(R.id.btSuma);
        pbCargando = findViewById(R.id.pbCargando);
        tvOperadorFinal = findViewById(R.id.tvOperadorFinal);
        btReiniciaApp = findViewById(R.id.btReiniciaApp);

        sumadenumerosViewModel vm = new ViewModelProvider(this).get(sumadenumerosViewModel.class);
        vm.getMisDatos().observe(this, integer -> {
            // Actualizar la interfaz
            if(integer == sumadenumerosViewModel.FAIL){
                tvOperadorFinal.setText("Error en el acceso a los datos");
            }else{
                tvOperadorFinal.setText(""+integer);
            }
            pbCargando.setVisibility(View.INVISIBLE);
            tvOperadorFinal.setVisibility(View.VISIBLE);
            tvSumaSigno.setVisibility(View.INVISIBLE);
            etOperadorSuma2.setVisibility(View.INVISIBLE);
            etOperadorSuma1.setVisibility(View.INVISIBLE);
            //deshabilitamos boton mientras carga
            btSuma.setEnabled(false);
            btReiniciaApp.setEnabled(true);
        });

        //por defecto el boton esta habilitado al inicio
        btSuma.setEnabled(true);
        btReiniciaApp.setEnabled(false);

        btSuma.setOnClickListener((v)->{
            if (etOperadorSuma1.equals("") && etOperadorSuma2.equals("")) {
                pbCargando.setVisibility(View.VISIBLE);
                tvSumaSigno.setVisibility(View.INVISIBLE);
                etOperadorSuma2.setVisibility(View.INVISIBLE);
                etOperadorSuma1.setVisibility(View.INVISIBLE);
                btSuma.setEnabled(false);
                btReiniciaApp.setEnabled(false);
                String numero1 = etOperadorSuma1.getText().toString();
                String numero2 = etOperadorSuma2.getText().toString();
                int Operando1 = Integer.parseInt(numero1);
                int Operando2 = Integer.parseInt(numero2);
                vm.procesarNumero(Operando1, Operando2);
            }else{
                pbCargando.setVisibility(View.INVISIBLE);
                tvSumaSigno.setVisibility(View.INVISIBLE);
                etOperadorSuma2.setVisibility(View.INVISIBLE);
                etOperadorSuma1.setVisibility(View.INVISIBLE);
                btSuma.setEnabled(false);
                btReiniciaApp.setEnabled(true);
                tvOperadorFinal.setText("Error, tienes que introducir numeros en ambos operandos, pulsa el boton de reiniciar");
                tvOperadorFinal.setVisibility(View.VISIBLE);
            }
        });

        btReiniciaApp.setOnClickListener((v)->{
            tvOperadorFinal.setVisibility(View.INVISIBLE);
            pbCargando.setVisibility(View.INVISIBLE);
            tvSumaSigno.setVisibility(View.VISIBLE);
            etOperadorSuma2.setVisibility(View.VISIBLE);
            etOperadorSuma1.setVisibility(View.VISIBLE);
            etOperadorSuma2.setText("");
            etOperadorSuma1.setText("");
            btSuma.setEnabled(true);
            btReiniciaApp.setEnabled(false);
        });

        /*Si es visible entonces...
        if (tvOperadorFinal.getVisibility() == View.VISIBLE) {
        };
        */
    }

}