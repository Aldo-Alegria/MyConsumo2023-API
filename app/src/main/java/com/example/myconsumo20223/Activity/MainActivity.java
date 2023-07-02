package com.example.myconsumo20223.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myconsumo20223.Api.ServiceAPI;
import com.example.myconsumo20223.Model.Producto;
import com.example.myconsumo20223.R;
import com.example.myconsumo20223.Util.ConnectionREST;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private ImageView huella;
    private EditText user, pass;
    private Button btnLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logeo_main);

        huella = (ImageView) findViewById(R.id.huella);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().equals("ALDO") && pass.getText().toString().equals("ALEGRIA27")) {

                    startActivity(new Intent(MainActivity.this, Procesos.class));
                    Toast.makeText(MainActivity.this,"TODO ESTÁ CORRECTO",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "ES INCORRECTO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}