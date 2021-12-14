package com.lab02.maestroclientes.TipoCliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.RegistroZona;
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;

public class RegistroTipoCliente extends AppCompatActivity {
    EditText nombre;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tipo_cliente);
        nombre=(EditText) findViewById(R.id.nomTipoCliente);
    }


    public void Guardar(View view){
        if(!nombre.getText().toString().equalsIgnoreCase("")){
            DbTipoCliente dbtipocliente=new DbTipoCliente(RegistroTipoCliente.this);
            id= dbtipocliente.insertarTipoCliente(nombre.getText().toString(),"A");
            if(id>0){
                Toast.makeText(RegistroTipoCliente.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                limpiar();
            }else{
                Toast.makeText(RegistroTipoCliente.this, "REGISTRO  NO GUARDADO", Toast.LENGTH_SHORT).show();
            }}else{
            Toast.makeText(RegistroTipoCliente.this, "LLENE EL CAMPO NOMBRE", Toast.LENGTH_SHORT).show();
        }
    }
    private void limpiar(){
        nombre.setText("");
    }

}