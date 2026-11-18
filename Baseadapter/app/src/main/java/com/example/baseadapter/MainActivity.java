package com.example.baseadapter;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private List<Datos> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Llenar la lista con datos de ejemplo
        items = new ArrayList<>();

        items.add(new Datos(
                R.drawable.ic_launcher_foreground,
                "Título 1",
                "Contenido del elemento 1"
        ));

        items.add(new Datos(
                R.drawable.ic_launcher_foreground,
                "Título 2",
                "Contenido del elemento 2"
        ));

        items.add(new Datos(
                R.drawable.ic_launcher_foreground,
                "Título 3",
                "Contenido del elemento 3"
        ));

        items.add(new Datos(
                R.drawable.ic_launcher_foreground,
                "Título 4",
                "Contenido del elemento 4"
        ));

        // Configurar el adaptador personalizado
        adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);
    }
}