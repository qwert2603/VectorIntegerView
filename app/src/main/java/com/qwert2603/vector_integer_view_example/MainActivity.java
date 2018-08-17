package com.qwert2603.vector_integer_view_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.qwert2603.vector_integer_view.VectorIntegerView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    boolean animate() {
        return ((CheckBox) findViewById(R.id.animate_CheckBox)).isChecked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VectorIntegerView vectorIntegerView = findViewById(R.id.vectorIntegerView);

        findViewById(R.id.plus_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vectorIntegerView.setInteger(vectorIntegerView.getInteger().add(BigInteger.ONE), animate());
            }
        });
        findViewById(R.id.minus_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vectorIntegerView.setInteger(vectorIntegerView.getInteger().subtract(BigInteger.ONE), animate());
            }
        });
        findViewById(R.id.x17_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vectorIntegerView.setInteger(vectorIntegerView.getInteger().multiply(BigInteger.valueOf(17)), animate());
            }
        });
        findViewById(R.id.setNumber_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long number = 0;
                try {
                    number = Long.parseLong(((EditText) findViewById(R.id.number_EditText)).getText().toString());
                } catch (NumberFormatException ignored) {
                }
                vectorIntegerView.setInteger(number, animate());
            }
        });

    }
}
