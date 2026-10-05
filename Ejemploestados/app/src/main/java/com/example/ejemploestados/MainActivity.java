package com.example.ejemploestados;

import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // On create es cuando se crea la activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asignamos un Layout a la activity
        setContentView(R.layout.activity_main);

        // Escribimos en el Logcat - Para verlo filtramos usando el tag Ejemplo
        Log.i("Ejemplo", "Estoy en on create");
    }

    protected void onStart() {
        super.onStart();
        Log.i("Ejemplo", "Estoy en onStart");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("Ejemplo", "Estoy en onRestart");
    }

    protected void onResume() {
        super.onResume();
        Log.i("Ejemplo", "Estoy en onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.i("Ejemplo", "Estoy en onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i("Ejemplo", "Estoy en onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ejemplo", "Estoy en onDestroy");
    }
}