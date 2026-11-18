package com.example.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Adaptador extends ArrayAdapter<Datos> {
    private Datos [] datos;
    public Adaptador(Context context, Datos[] datos) {
        super(context, R.layout.elementos, datos);
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
//Localizar su contexto
        LayoutInflater mostrado = LayoutInflater.from(getContext());
//inflar el Layout
        Object TextView;
        View elemento = mostrado.inflate(R.layout.elementos, parent
// Obtener referencias a los elementos del layout personalizado
                TextView text1 = elemento.findViewById(R.id.text1);
        TextView text2 = elemento.findViewById(R.id.text2);
        Configurar los datos
        text1.setText(datos[position].getTexto1()); // Texto principal
        text2.setText(datos[position].getTexto2()); // Texto secundario
        return elemento;

        attachToRoot:

        false):
}
