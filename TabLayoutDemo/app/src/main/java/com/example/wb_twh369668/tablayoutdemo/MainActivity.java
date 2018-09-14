package com.example.wb_twh369668.tablayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtBaseDisplay;
    private Button mBtBaseTablaywithfragment;
    private Button mBtBaseTablaywithfragment2;
    private Button mBtBaseTablaywithfragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtBaseDisplay = (Button) findViewById(R.id.baseDisplay);
        mBtBaseTablaywithfragment = (Button) findViewById(R.id.tablaywithfragment);
        mBtBaseTablaywithfragment2 = (Button) findViewById(R.id.tablaywithfragment2);
        mBtBaseTablaywithfragment3 = (Button) findViewById(R.id.tablaywithfragment3);
        mBtBaseDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BaseDisplayActivity.class);
                startActivity(intent);

            }
        });
        mBtBaseTablaywithfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TablaywithfragmentActivity.class);
                startActivity(intent);
            }
        });
        mBtBaseTablaywithfragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TablaywithfragmentActivity2.class);
                startActivity(intent);
            }
        });
        mBtBaseTablaywithfragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerAddFragment.class);
                startActivity(intent);
            }
        });
    }
}
