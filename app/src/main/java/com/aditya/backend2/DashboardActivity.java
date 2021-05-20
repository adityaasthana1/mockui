package com.aditya.backend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import soup.neumorphism.NeumorphCardView;

public class DashboardActivity extends AppCompatActivity {
    NeumorphCardView neumorphCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        neumorphCardView = findViewById(R.id.users_activity);

        neumorphCardView.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
        });
    }
}