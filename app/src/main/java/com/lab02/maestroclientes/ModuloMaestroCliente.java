package com.lab02.maestroclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lab02.maestroclientes.Cliente.MenuCliente;
import com.lab02.maestroclientes.TipoCliente.MenuTipoCliente;

public class ModuloMaestroCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_maestro_cliente);
    }
    public void zonaMenu(View view){
        Intent intent= new Intent(ModuloMaestroCliente.this,MenuZona.class);
        startActivity(intent);
    }
    public void tipoClienteMenu(View view){
        Intent intent= new Intent(ModuloMaestroCliente.this, MenuTipoCliente.class);
        startActivity(intent);
    }
    public void clienteMenu(View view){
        Intent intent= new Intent(ModuloMaestroCliente.this, MenuCliente.class);
        startActivity(intent);
    }
}