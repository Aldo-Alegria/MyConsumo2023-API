package com.example.myconsumo20223.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myconsumo20223.R;

public class Busqueda extends AppCompatActivity {
    private ListView lista;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_main);


        lista = (ListView) findViewById(R.id.lista);
    }
}
