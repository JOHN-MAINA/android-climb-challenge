package com.example.jqhn.climbchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
}
