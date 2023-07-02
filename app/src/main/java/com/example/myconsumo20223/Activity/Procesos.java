package com.example.myconsumo20223.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myconsumo20223.Api.ServiceAPI;
import com.example.myconsumo20223.Model.Producto;
import com.example.myconsumo20223.R;
import com.example.myconsumo20223.Util.ConnectionREST;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Procesos extends AppCompatActivity {
    private EditText _etResultado;
    private EditText _etCodigo;
    private EditText _etNombre;
    private EditText _etPrecio;
    private EditText _etStock;
    private Button _btnGrabar;
    private Button _btnModificar;
    private Button _btnEliminar;
    private Button btnBuscar;

    private ServiceAPI serviceAPI;
    private java.lang.String String;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _etCodigo = (EditText) findViewById(R.id.etCodigo);

        _etNombre = (EditText) findViewById(R.id.etNombre);
        _etPrecio = (EditText) findViewById(R.id.etPrecio);
        _etStock = (EditText) findViewById(R.id.etStock);
        _etResultado = (EditText) findViewById(R.id.etResultado);
        _btnGrabar = (Button) findViewById(R.id.btnProcesar);
        _btnModificar = (Button) findViewById(R.id.btnModificar);
        _btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);


        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        load();
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encontrarProducto(_etCodigo.getText().toString());
                startActivity(new Intent(Procesos.this, Busqueda.class));
            }
        });

        _btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto pObj = new Producto(Integer.parseUnsignedInt(_etCodigo.getText().toString()),
                        _etNombre.getText().toString(),
                        Double.parseDouble(_etPrecio.getText().toString()),
                        Integer.parseInt(_etStock.getText().toString())
                );
                addProducto(pObj);
            }
        });

        _btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(Integer.parseInt(_etCodigo.getText().toString()));
            }
        });

        _btnModificar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Producto pObj = new Producto(Integer.parseInt(_etCodigo.getText().toString()),
                        _etNombre.getText().toString(),
                        Double.parseDouble(_etPrecio.getText().toString()),
                        Integer.parseInt(_etStock.getText().toString())
                );
                modifyProducto(pObj);
            }
        });


    }
    private void encontrarProducto(String id){
        Call<Producto> call = serviceAPI.find(String);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    mensaje("Los datos se buscaron con éxito!!!");
                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }
    private void eliminarProducto(int parseInt) {
        Call<Producto> call = serviceAPI.removeProducto(String);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    mensaje("Los datos se eliminaron satisfactoriamente!!!");
                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }

    private void modifyProducto(Producto pObj) {
        Call<Producto> call = serviceAPI.modifyProducto(pObj);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    Producto pro = response.body();

                    mensaje("Los datos se modificaron satisfactoriamente!!!");
                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }

    public void addProducto(Producto pObj)
    {
        Call<Producto> call = serviceAPI.addProducto(pObj);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {

                    Producto pro = response.body();
                    mensaje("Registro grabado satisfactoriamente!");
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
            }
        });
    }

    public void load()
    {
        //ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Producto>> call = serviceAPI.listProduct();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.isSuccessful())
                {
                    List<Producto> respuesta = response.body();
                    _etResultado.setText("\n\n\n\n");
                    for(Producto x:respuesta)
                    {
                        _etResultado.append("Código:" + x.getCodProducto()+ "Nombre:" + x.getNomProducto() + "Precio" + x.getPreProducto() + "Stock" + x.getStkProducto() + "\n");
                        Toast.makeText(getApplicationContext(),"" + x.getNomProducto(), Toast.LENGTH_LONG).show();
                        mensaje(x.getCodProducto() + "-" + x.getNomProducto());
                    }
                }
                else
                {
                    Toast.makeText(null,"Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(null,"Ocurrop un error", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }
}

