package com.example.cuentabar;


import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtTotal;
    AutoCompleteTextView edtCamarero;
    CheckBox chkPropina;
    SeekBar seekPropina;
    TextView txtPorcentaje, txtResultado;
    RadioGroup grupoPago;
    RadioButton rbEfectivo, rbTarjeta;
    RatingBar ratingServicio;
    Button btnCalcular;

    int porcentaje = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTotal = findViewById(R.id.edtTotal);
        edtCamarero = findViewById(R.id.edtCamarero);
        chkPropina = findViewById(R.id.chkPropina);
        seekPropina = findViewById(R.id.seekPropina);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        txtResultado = findViewById(R.id.txtResultado);
        grupoPago = findViewById(R.id.grupoPago);
        rbEfectivo = findViewById(R.id.rbEfectivo);
        rbTarjeta = findViewById(R.id.rbTarjeta);
        ratingServicio = findViewById(R.id.ratingServicio);
        btnCalcular = findViewById(R.id.btnCalcular);

        String[] camareros = {
                "Carlos", "María", "Javier",
                "Lucía", "Antonio", "Elena"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                camareros
        );

        edtCamarero.setAdapter(adaptador);
        edtCamarero.setThreshold(3);

        seekPropina.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {

                        porcentaje = progress;
                        txtPorcentaje.setText(
                                getString(R.string.propina_porcentaje, porcentaje)
                        );
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularCuenta();
            }
        });
    }

    private void calcularCuenta() {

        String texto = edtTotal.getText().toString().trim();

        if (texto.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        double cuenta;

        try {
            texto = texto.replace(',', '.');
            cuenta = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_numero));
            return;
        }

        if (Double.isNaN(cuenta) || Double.isInfinite(cuenta)
                || cuenta <= 0) {
            mostrarError(getString(R.string.error_positivo));
            return;
        }

        double propina = 0;

        if (chkPropina.isChecked()) {
            propina = cuenta * porcentaje / 100.0;
        }

        double total = cuenta + propina;

        String metodo;

        if (rbEfectivo.isChecked()) {
            metodo = getString(R.string.efectivo);
        } else if (rbTarjeta.isChecked()) {
            metodo = getString(R.string.tarjeta);
        } else {
            mostrarError(getString(R.string.error_pago));
            return;
        }

        float valoracion = ratingServicio.getRating();

        String camarero = edtCamarero.getText().toString().trim();

        if (camarero.isEmpty()) {
            camarero = getString(R.string.sin_camarero);
        }

        String resultado = String.format(
                Locale.getDefault(),
                getString(R.string.resultado_formato),
                cuenta, propina, total, metodo, valoracion, camarero
        );

        txtResultado.setTextColor(Color.rgb(24, 49, 83));
        txtResultado.setText(resultado);
    }

    private void mostrarError(String mensaje) {
        txtResultado.setTextColor(Color.RED);
        txtResultado.setText(mensaje);
    }
}
