package com.lab02.maestroclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class MenuZona extends AppCompatActivity implements  SelectListener ,SearchView.OnQueryTextListener {
    RecyclerView listaZonas;
    ArrayList<Zona> listaArrayZonas;
    ListaZonaAdapter adapter;
    DbZonas dbzonas;
    SearchView txtBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_zona);
        listaZonas = findViewById(R.id.listaZonas);
        listaZonas.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);
        dbzonas = new DbZonas(MenuZona.this);

        listaArrayZonas = new ArrayList<>();

        adapter = new ListaZonaAdapter(dbzonas.mostrarZonas(1), this);
        listaZonas.setAdapter(adapter);
        txtBuscar.setOnQueryTextListener(this);


    }

    public void agregarZona(View view) {
        Intent intent = new Intent(MenuZona.this, RegistroZona.class);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(Zona zona) {
        int codigo = zona.getCodigo();
        //Toast.makeText(this,codigo+"", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MenuZona.this, VerZona.class);
        intent.putExtra("CODIGO", codigo);
        startActivity(intent);
        System.out.println(codigo);

    }

    public void ordenarNombres(View view) {
        adapter = new ListaZonaAdapter(dbzonas.mostrarZonas(1), this);
        listaZonas.setAdapter(adapter);
    }

    public void ordenarCodigo(View view) {
        adapter = new ListaZonaAdapter(dbzonas.mostrarZonas(0), this);
        listaZonas.setAdapter(adapter);
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