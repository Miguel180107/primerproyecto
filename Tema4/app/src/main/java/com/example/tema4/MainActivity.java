package com.example.tema4;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView miTexto = (TextView) findViewById(R.id.texto);

        miTexto.setText("Nuevo texto a mostrar");

        // Opción 1 para cambiar el color
        miTexto.setTextColor(Color.parseColor("#0000FF"));

        // Opción 2 para cambiar el color
        miTexto.setTextColor(Color.RED);

        // Cambio del texto a cursiva
        miTexto.setTypeface(null, Typeface.ITALIC);

        // Cambio del tamaño del texto
        miTexto.setTextSize(24);

        // Cambiar tipo de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);
    }




}