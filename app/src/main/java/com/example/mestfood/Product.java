package com.example.mestfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        getSupportActionBar().hide();
        TextView tv = (TextView) findViewById(R.id.tv);
        String username = getIntent().getStringExtra("username");
        tv.setText("Hoşgeldiniz " + username);
    }
}