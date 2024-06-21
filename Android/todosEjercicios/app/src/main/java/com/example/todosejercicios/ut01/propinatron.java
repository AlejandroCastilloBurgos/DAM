package com.example.todosejercicios.ut01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class propinatron extends AppCompatActivity {
    TextView tvNumAcumulado, tvCuenta;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btdel, btcalc, btdeltodo;
    RadioButton rbtbien, rbtnormal, rbtmal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propinatron);

        initViews();
        setListeners();
    }

    private void initViews() {
        tvNumAcumulado = findViewById(R.id.tvNumAcumulado);
        tvCuenta = findViewById(R.id.tvCuenta);
        btdeltodo = findViewById(R.id.btdeltodo);
        bt0 = findViewById(R.id.bt0);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        btcalc = findViewById(R.id.btcalc);
        btdel = findViewById(R.id.btdelete);
        rbtbien = findViewById(R.id.rbtbien);
        rbtnormal = findViewById(R.id.rbtnormal);
        rbtmal = findViewById(R.id.rbtmal);
    }

    private void setListeners() {
        View.OnClickListener numberListener = v -> {
            Button b = (Button) v;
            tvNumAcumulado.append(b.getText().toString());
        };

        bt0.setOnClickListener(numberListener);
        bt1.setOnClickListener(numberListener);
        bt2.setOnClickListener(numberListener);
        bt3.setOnClickListener(numberListener);
        bt4.setOnClickListener(numberListener);
        bt5.setOnClickListener(numberListener);
        bt6.setOnClickListener(numberListener);
        bt7.setOnClickListener(numberListener);
        bt8.setOnClickListener(numberListener);
        bt9.setOnClickListener(numberListener);

        btdel.setOnClickListener(v -> {
            String currentText = tvNumAcumulado.getText().toString();
            if (!currentText.isEmpty()) {
                tvNumAcumulado.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        btdeltodo.setOnClickListener(v -> resetAll());

        btcalc.setOnClickListener(v -> calculateTip());
    }

    private void resetAll() {
        tvNumAcumulado.setText("");
        tvCuenta.setText("");
        rbtbien.setChecked(false);
        rbtnormal.setChecked(false);
        rbtmal.setChecked(false);
    }

    private void calculateTip() {
        try {
            double tipPercentage = rbtbien.isChecked() ? 0.20 : rbtnormal.isChecked() ? 0.10 : 0.00;
            double amount = Double.parseDouble(tvNumAcumulado.getText().toString());
            double finalAmount = amount + (amount * tipPercentage);
            tvCuenta.setText(String.format("%.2f€", finalAmount));
        } catch (NumberFormatException e) {
            tvCuenta.setText("ERROR: Formato incorrecto.");
        }
    }
}
