package com.lab02.maestroclientes.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.lab02.maestroclientes.ListaZonaAdapter;
import com.lab02.maestroclientes.MenuZona;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.RegistroZona;
import com.lab02.maestroclientes.SelectListener;
import com.lab02.maestroclientes.VerZona;
import com.lab02.maestroclientes.db.DbCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Cliente;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class MenuCliente extends AppCompatActivity implements SelectListenerCliente, SearchView.OnQueryTextListener {
    RecyclerView listaClientes;
    ArrayList<Cliente> listaArrayClientes;
    DbCliente dbclientes;
    ListaClienteAdapter adapter;
    SearchView txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
        listaClientes = findViewById(R.id.listaClientes);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);
        dbclientes = new DbCliente(MenuCliente.this);
        listaArrayClientes = new ArrayList<Cliente>();
        adapter = new ListaClienteAdapter(dbclientes.mostrarCliente(1), this,MenuCliente.this);
        listaClientes.setAdapter(adapter);
        txtBuscar.setOnQueryTextListener(this);


    }

    public void agregarCliente(View view) {
      Intent intent = new Intent(MenuCliente.this, RegistroCliente.class);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(Cliente cliente) {
        int codigo = cliente.getCodigo();
        //Toast.makeText(this,codigo+"", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MenuCliente.this, VerCliente.class);
        intent.putExtra("CODIGO", codigo);
        startActivity(intent);
        System.out.println(codigo);

    }

    public void ordenarNombres(View view) {
        adapter = new ListaClienteAdapter(dbclientes.mostrarCliente(1), this,MenuCliente.this);
        listaClientes.setAdapter(adapter);
    }

    public void ordenarCodigo(View view) {
        adapter = new ListaClienteAdapter(dbclientes.mostrarCliente(0), this,MenuCliente.this);
        listaClientes.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }


}