package com.lab02.maestroclientes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lab02.maestroclientes.db.DbZonas;

public class RegistroZona extends AppCompatActivity {
    EditText nombre;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_zona);
        nombre=(EditText) findViewById(R.id.nomZona);
    }


    public void Guardar(View view){
        if(!nombre.getText().toString().equalsIgnoreCase("")){
        DbZonas dbzonas=new DbZonas(RegistroZona.this);
        id=dbzonas.insertarZona(nombre.getText().toString(),"A");
        if(id>0){
            Toast.makeText(RegistroZona.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
            limpiar();
        }else{
            Toast.makeText(RegistroZona.this, "REGISTRO  NO GUARDADO", Toast.LENGTH_SHORT).show();
        }}else{
            Toast.makeText(RegistroZona.this, "LLENE EL CAMPO NOMBRE", Toast.LENGTH_SHORT).show();
        }
    }
    private void limpiar(){
        nombre.setText("");
    }

}