package com.example.ejerciciosmultiactividad;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class heladeriaB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heladeria_b);

        // Obtener datos de la primera actividad
        Intent intent = getIntent();
        int vainilla = intent.getIntExtra("cantidadVainilla", 0);
        int chocolate = intent.getIntExtra("cantidadChoco", 0);
        int fresa = intent.getIntExtra("cantidadFresa", 0);
        String recipiente = intent.getStringExtra("tipoRecipiente");

        // Mostrar la información en TextViews
        TextView tvRepresentacion = findViewById(R.id.tvRepresentacion);

        // Colorear según las especificaciones
        colorearRepresentacion(tvRepresentacion, vainilla, chocolate, fresa, recipiente);

        // Botón para finalizar la actividad
        Button btFinalizar = findViewById(R.id.btFinalizar);
        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finalizar la actividad
            }
        });
    }

    // Método para colorear la representación gráfica
    private void colorearRepresentacion(TextView textView, int vainilla, int chocolate, int fresa, String recipiente) {
        StringBuilder representacion = new StringBuilder();

        // Añadir O amarilla por cada vainilla
        appendColoredText(representacion, "O", ContextCompat.getColor(this, R.color.amarillo), vainilla);
        representacion.append("\n");

        // Añadir O marrón por cada chocolate
        appendColoredText(representacion, "O", ContextCompat.getColor(this, R.color.marron), chocolate);
        representacion.append("\n");

        // Añadir O rosa por cada fresa
        appendColoredText(representacion, "O", ContextCompat.getColor(this, R.color.rosa), fresa);
        representacion.append("\n");

        // Añadir \/ marrón claro o marrón oscuro según el recipiente
        if ("cucu.choco".equals(recipiente) || "cucurucho".equals(recipiente)) {
            appendColoredText(representacion, "\\/", ContextCompat.getColor(this, R.color.marron_claro), 1);
        } else if ("tarrina".equals(recipiente)) {
            // Añadir U si es tarrina
            appendColoredText(representacion, "U", ContextCompat.getColor(this, R.color.black), 1);
        }

        // Mostrar la representación en el TextView
        textView.setText(representacion.toString());
    }

    // Método para agregar texto con color al StringBuilder
    private void appendColoredText(StringBuilder builder, String text, int color, int count) {
        for (int i = 0; i < count; i++) {
            SpannableString spannableString = new SpannableString(text);
            spannableString.setSpan(new BackgroundColorSpan(color), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(spannableString);
        }
        builder.append("\n");
    }
}
