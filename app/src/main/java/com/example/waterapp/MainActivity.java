package com.example.waterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView waterCounter;
    Button addLittre;
    Button removeLittre;
    int counter;
    SharedPreferences storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waterCounter = findViewById(R.id.waterCounter);
        addLittre = findViewById(R.id.addLittreButton);
        removeLittre = findViewById(R.id.removeLittreButton);
        storage = getSharedPreferences("storageKey", MODE_PRIVATE);
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("savedCounter", 0);
        }
        else {
            counter = storage.getInt("savedCounter", 0);
        }
        waterCounter.setText(String.valueOf(counter));
        addLittre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter += 1;
                waterCounter.setText(String.valueOf(counter));
            }
        });
        removeLittre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter -= 1;
                if (counter < 0) {
                    counter = 0;
                }
                waterCounter.setText(String.valueOf(counter));
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        storage = getSharedPreferences("storageKey", MODE_PRIVATE);
        storage.edit().putInt("savedCounter", counter).apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedCounter", counter);
    }
}