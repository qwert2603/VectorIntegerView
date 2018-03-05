package com.qwert2603.vector_integer_view_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qwert2603.vector_integer_view.VectorIntegerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VectorIntegerView vectorIntegerView = findViewById(R.id.vectorIntegerView);

        findViewById(R.id.plus_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vectorIntegerView.setInteger(vectorIntegerView.getInteger() + 1, true);
            }
        });
        findViewById(R.id.minus_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vectorIntegerView.setInteger(vectorIntegerView.getInteger() - 1, true);
            }
        });
    }
}
