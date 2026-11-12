package com.example.tema5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listaPaises;
    TextView textoPais;

    String[] paises = {
            "España",
            "Dinamarca",
            "Italia",
            "Alemania",
            "Portugal",
            "Bélgica",
            "Países Bajos",
            "Grecia",
            "Monte Negro",
            "Polonia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPaises = findViewById(R.id.listaPaises);
        textoPais = findViewById(R.id.textoPais);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                paises
        );

        listaPaises.setAdapter(adaptador);

        listaPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view,
                                    int position, long id) {

                textoPais.setText(paises[position]);

            }
        });
    }
}