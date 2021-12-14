package com.lab02.maestroclientes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lab02.maestroclientes.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void maestroClientes(View view){
        Intent intent= new Intent(MainActivity.this,ModuloMaestroCliente.class);
        startActivity(intent);
    }

    public void onClik(View view){
        DbHelper dbHelper=new DbHelper(MainActivity.this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db,1,2);
        if(db!=null){
            Toast.makeText(MainActivity.this,"BASE DE DATOS   CREADA",Toast.LENGTH_LONG).show();
        }
    }
    public void onCrear(View view){
        DbHelper dbHelper=new DbHelper(MainActivity.this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        if(db!=null){
            Toast.makeText(MainActivity.this,"BASE DE DATOS   CREADA",Toast.LENGTH_LONG).show();
        }
    }
}