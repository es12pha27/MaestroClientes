package com.lab02.maestroclientes.TipoCliente;

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
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.TipoCliente;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class MenuTipoCliente extends AppCompatActivity  implements SelectListenerTipoCliente,SearchView.OnQueryTextListener{

    RecyclerView listaTipoCliente;
    ArrayList<TipoCliente> listaArrayTipoCliente;
    ListaTipoClienteAdapter adapter;
    SearchView txtBuscar;
    DbTipoCliente dbtipoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tipo_cliente);
        listaTipoCliente = findViewById(R.id.listaTipoCliente);
        listaTipoCliente.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);
        dbtipoCliente= new DbTipoCliente(MenuTipoCliente.this);

        listaArrayTipoCliente = new ArrayList<TipoCliente>();
        adapter = new ListaTipoClienteAdapter(dbtipoCliente.mostrarTipoCliente(1), this);
        listaTipoCliente.setAdapter(adapter);
        txtBuscar.setOnQueryTextListener(this);


    }

    public void agregarTipoCliente(View view) {
       Intent intent = new Intent(MenuTipoCliente.this, RegistroTipoCliente.class);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(TipoCliente tipoCliente) {
        int codigo = tipoCliente.getCodigo();
    //    Toast.makeText(this,codigo+"", Toast.LENGTH_SHORT).show();

       Intent intent = new Intent(MenuTipoCliente.this, VerTipoCliente.class);
        intent.putExtra("CODIGO", codigo);
        startActivity(intent);
        System.out.println(codigo);

    }

    public void ordenarNombres(View view) {
        adapter = new ListaTipoClienteAdapter(dbtipoCliente.mostrarTipoCliente(1), this);
        listaTipoCliente.setAdapter(adapter);
    }

    public void ordenarCodigo(View view) {
        adapter = new ListaTipoClienteAdapter(dbtipoCliente.mostrarTipoCliente(0), this);
        listaTipoCliente.setAdapter(adapter);
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