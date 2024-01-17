package com.example.recicleview;

// MainActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Candy> candyList;
    private CandyAdapter candyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnAdd);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        candyList = new ArrayList<>();
        candyAdapter = new CandyAdapter(candyList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(candyAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRandomCandy();
            }
        });
    }

    private void addRandomCandy() {
        String[] candyNames = {"Mazapanes", "Roscon", "Mantecado", "Trufas chocolate"};
        String randomCandyName = candyNames[new Random().nextInt(candyNames.length)];

        int calories;
        boolean nuts = false;
        switch (randomCandyName) {
            case "Mazapanes":
                calories = 147;
                nuts = false;
                break;
            case "Mantecado":
                calories = 250;
                nuts = false;
                break;
            case "Roscon":
                calories = 500;
                nuts = true;
                break;
            case "Trufas chocolate":
                calories = 334;
                nuts = false;
                break;
            default:
                calories = 0;
                break;
        }

       // boolean hasNuts = randomCandyName.equals("Roscon") || randomCandyName.equals("Turron");
        Candy newCandy = new Candy(randomCandyName, nuts, calories);

        candyList.add(newCandy);
        candyAdapter.notifyDataSetChanged();
    }


}
