package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class CountFragment extends Fragment {
    private int count = 0;
    private TextView textViewCount;
    private Button buttonCount;

    public CountFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.activity_count_fragment, container, false);
        textViewCount = view.findViewById(R.id.textViewCount);
        buttonCount = view.findViewById(R.id.buttonCount);

        buttonCount.setOnClickListener(v -> {
            count++;
            textViewCount.setText(String.valueOf(count));
        });

        return view;
    }
}
