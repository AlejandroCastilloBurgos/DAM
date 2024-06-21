package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.todosejercicios.R;

public class PinponPrincipal extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinpon_principal);

        // Añadir los fragmentos
        addFragment(R.id.fragmentContainerView);
        addFragment(R.id.fragmentContainerView2);
        addFragment(R.id.fragmentContainerView3);
    }

    private void addFragment(int containerId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentPinpon fragment = new FragmentPinpon();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}