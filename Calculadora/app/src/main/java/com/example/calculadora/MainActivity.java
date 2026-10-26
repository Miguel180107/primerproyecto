

package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;
    String numero = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar el display
        display = findViewById(R.id.display);

        // Botones del 0 al 9
        int[] numeros = {
                R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4,
                R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9
        };

        for (int id : numeros) {
            Button boton = findViewById(id);

            boton.setOnClickListener(v -> {
                String digito = ((Button) v).getText().toString();
                escribirNumero(digito);
            });
        }

        // Botones de operaciones
        int[] operaciones = {
                R.id.bmas, R.id.bmenos,
                R.id.bmultiplicar, R.id.bdividir
        };

        for (int id : operaciones) {
            Button boton = findViewById(id);

            boton.setOnClickListener(v -> {
                String operacion = ((Button) v).getText().toString();
                escribirOperacion(operacion);
            });
        }

        // Boton de la coma decimal
        findViewById(R.id.bcoma).setOnClickListener(v -> escribirComa());

        // Boton C: borrar ultimo caracter
        findViewById(R.id.bc).setOnClickListener(v -> {
            if (numero.length() > 1) {
                numero = numero.substring(0, numero.length() - 1);
            } else {
                numero = "0";
            }

            display.setText(numero);
        });

        // Boton AC: borrar entero lo que pone
        findViewById(R.id.bac).setOnClickListener(v -> {
            numero = "0";
            display.setText(numero);
        });

        // Boton igual
        findViewById(R.id.bigual).setOnClickListener(v -> calcular());
    }

    // Escribir numeros sin permitir ceros iniciales
    private void escribirNumero(String digito) {
        int posicion = ultimaOperacion();
        String actual = numero.substring(posicion + 1);

        if (actual.equals("0")) {
            numero = numero.substring(0, posicion + 1) + digito;
        } else {
            numero += digito;
        }

        display.setText(numero);
    }

    // Escribir operaciones matematicas
    private void escribirOperacion(String operacion) {
        char ultimo = numero.charAt(numero.length() - 1);

        if (ultimo == '+' || ultimo == '-' ||
                ultimo == '*' || ultimo == '/') {

            numero = numero.substring(0, numero.length() - 1);
        }

        numero += operacion;
        display.setText(numero);
    }

    // Evitar dos comas en el mismo numero
    private void escribirComa() {
        if (numero.isEmpty()) {
            numero = "0,";
            display.setText(numero);
            return;
        }

        char ultimo = numero.charAt(numero.length() - 1);

        if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '/') {
            numero += "0,";
            display.setText(numero);
            return;
        }

        int posicion = ultimaOperacion();
        String actual = numero.substring(posicion + 1);

        if (!actual.contains(",")) {
            numero += ",";
            display.setText(numero);
        }
    }



    // Buscar la ultima operacion
    private int ultimaOperacion() {
        int posicion = -1;

        for (int i = 0; i < numero.length(); i++) {
            char c = numero.charAt(i);

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                posicion = i;
            }
        }

        return posicion;
    }

    // Calcular el resultado
    private void calcular() {
        try {
            int posicion = -1;

            // Buscar la operacion
            for (int i = 1; i < numero.length(); i++) {
                char c = numero.charAt(i);

                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    posicion = i;
                    break;
                }
            }

            // Si no hay operacion, no hacer nada
            if (posicion == -1) {
                return;
            }

            String primero = numero.substring(0, posicion);
            String segundo = numero.substring(posicion + 1);

            if (primero.isEmpty() || segundo.isEmpty()) {
                return;
            }

            double n1 = Double.parseDouble(primero.replace(",", "."));
            double n2 = Double.parseDouble(segundo.replace(",", "."));

            double resultado = 0;
            char operacion = numero.charAt(posicion);

            switch (operacion) {
                case '+':
                    resultado = n1 + n2;
                    break;

                case '-':
                    resultado = n1 - n2;
                    break;

                case '*':
                    resultado = n1 * n2;
                    break;

                case '/':
                    if (n2 == 0) {
                        numero = "0";
                        display.setText("Error");
                        return;
                    }

                    resultado = n1 / n2;
                    break;
            }

            // Mostrar el resultado sin decimales innecesarios
            if (resultado == (long) resultado) {
                numero = String.valueOf((long) resultado);
            } else {
                numero = String.valueOf(resultado).replace(".", ",");
            }

            display.setText(numero);

        } catch (Exception e) {
            numero = "0";
            display.setText("Error");
        }
    }
}
