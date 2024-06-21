package com.example.todosejercicios.ut02;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;
public class FibonacciA extends AppCompatActivity {
    TextView tvN1, tvN2;
    Button btFibonacci;
    ActivityResultLauncher<Intent> fibonacciLauncher;

    public static final String INFO_N1 = "info_n1";
    public static final String INFO_N2 = "info_n2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);

        // Initialize views
        initView();
        // Initialize listeners
        listeners();
        // Setup ActivityResultLauncher
        setupActivityResultLauncher();
    }

    private void initView() {
        tvN1 = findViewById(R.id.tvN1);
        tvN2 = findViewById(R.id.tvN2);
        btFibonacci = findViewById(R.id.btFibonacci);
    }

    private void listeners() {
        btFibonacci.setOnClickListener(view -> {
            Intent i = new Intent(this, FibonacciB.class);
            String N1 = tvN1.getText().toString();
            String N2 = tvN2.getText().toString();

            if (!N1.isEmpty() && !N2.isEmpty()) {
                i.putExtra(INFO_N1, N1);
                i.putExtra(INFO_N2, N2);
                fibonacciLauncher.launch(i);
            } else {
                // Handle error: Show a message that both fields must be filled
            }
        });
    }

    private void setupActivityResultLauncher() {
        fibonacciLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String nuevoN1 = data.getStringExtra(INFO_N1);
                        String nuevoN2 = data.getStringExtra(INFO_N2);
                        tvN1.setText(nuevoN1);
                        tvN2.setText(nuevoN2);
                    }
                }
        );
    }
}
