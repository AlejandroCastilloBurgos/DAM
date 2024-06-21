package com.example.todosejercicios.ut01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.todosejercicios.R;
import android.widget.TextView;

import com.example.todosejercicios.ut01.Utils;

public class DNITEST extends AppCompatActivity {

    EditText etDNI;
    TextView tvError;
    Button btValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnitest);

        etDNI = findViewById(R.id.etDNI);
        tvError = findViewById(R.id.tvError);
        btValidar = findViewById(R.id.btValidar);

        btValidar.setOnClickListener(v -> {
            String dni = etDNI.getText().toString().toUpperCase();

            if (Utils.isValidDNI(dni)) {
                tvError.setVisibility(TextView.GONE);
            } else {
                tvError.setText("No válido");
                tvError.setVisibility(TextView.VISIBLE);
            }
        });


    }


}
