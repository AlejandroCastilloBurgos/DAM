package com.example.todosejercicios.ut04;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.todosejercicios.R;

public class EjercicioPruebaPermisos extends AppCompatActivity {
    Button btLlamar;

    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_prueba_permisos);

        btLlamar = findViewById(R.id.btLlamar);
        // Register the permissions callback, which handles the user's response to the
        // system permissions dialog. Save the return value, an instance of
        // ActivityResultLauncher, as an instance variable.
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                llamar();
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(EjercicioPruebaPermisos.this, "Necesitamos permiso para llamar", Toast.LENGTH_SHORT).show();
            }
        });
        btLlamar.setOnClickListener((View v)->{
            llamadaClick();
        });
    }


    private void llamar() {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:673050338"));
        startActivity(phoneIntent);
    }


    public void llamadaClick() {
        if (ContextCompat.checkSelfPermission(
                EjercicioPruebaPermisos.this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            llamar();
        } else if (false) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected, and what
            // features are disabled if it's declined. In this UI, include a
            // "cancel" or "no thanks" button that lets the user continue
            // using your app without granting the permission.

            // Mostrar UI Dialog para explicar al usuarios la necesidad del permiso
            // Vamos a usar la de por defecto de Android. Se ejecuta en el else

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        }
    }
}