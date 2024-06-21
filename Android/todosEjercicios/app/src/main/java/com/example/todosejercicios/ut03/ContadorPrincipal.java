package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ContadorPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_principal);

        // Añadir los fragmentos
        addFragment(R.id.fragmentContainer1);
        addFragment(R.id.fragmentContainer2);
        addFragment(R.id.fragmentContainer3);
    }

    private void addFragment(int containerId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CountFragment fragment = new CountFragment();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}
