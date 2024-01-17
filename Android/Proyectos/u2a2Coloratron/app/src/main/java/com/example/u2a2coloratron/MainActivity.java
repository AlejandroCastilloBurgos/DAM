package com.example.u2a2coloratron;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    EditText u2a2etnombre, u2a2etresultado;
    Button u2a2btpulsa;
    ToggleButton u2a2tbcolor;
    SeekBar u2a2sbrojo, u2a2sbgverde, u2a2sbazul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u2a2etnombre = findViewById(R.id.u2a2etnombre);
        u2a2etresultado = findViewById(R.id.u2a2etresultado);
        u2a2btpulsa = findViewById(R.id.u2a2btpulsa);
        u2a2tbcolor = findViewById(R.id.u2a2tbcolor);
        u2a2sbrojo = findViewById(R.id.u2a2sbrojo);
        u2a2sbgverde = findViewById(R.id.u2a2sbgverde);
        u2a2sbazul = findViewById(R.id.u2a2sbazul);

        u2a2btpulsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoIntroducido = u2a2etnombre.getText().toString();
                u2a2etresultado.setText(textoIntroducido);

                int colorRojo = u2a2sbrojo.getProgress();
                int colorVerde = u2a2sbgverde.getProgress();
                int colorAzul = u2a2sbazul.getProgress();

                int colorFinal = 0xFF000000 | (colorRojo << 16) | (colorVerde << 8) | colorAzul;
                u2a2etresultado.setBackgroundColor(colorFinal);
            }
        });
    }
}
