package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class PrincipalOrdinaria1TB extends AppCompatActivity {

    TextView tvResultadoPresupuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_ordinaria1_tb);

        tvResultadoPresupuesto = findViewById(R.id.tvResultadoPresupuesto);

        PrincipalOrdinaria1T.Presupuesto presupuesto = (PrincipalOrdinaria1T.Presupuesto) getIntent().getSerializableExtra(PrincipalOrdinaria1T.PRESUPUESTO);
        tvResultadoPresupuesto.setText(presupuesto.toString());

        // Pasar el presupuesto al fragmento
        PresupuestoFragment fragment = new PresupuestoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESUPUESTO", presupuesto);
        fragment.setArguments(bundle);

        addFragment(R.id.fragmentPresupuesto, fragment);
    }

    private void addFragment(int containerId, PresupuestoFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}
