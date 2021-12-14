package com.lab02.maestroclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Zona;

public class VerZona extends AppCompatActivity {
   TextView codigo,nombre,estado;
    int codigonum=0;
    Zona zona;
    boolean correcto=false;
    Button btnEliminar,btnActivar,btnInactivar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_zona);
        codigo=(TextView) findViewById(R.id.codigoZona);
        nombre=(TextView) findViewById(R.id.nombreZona);
        estado=(TextView) findViewById(R.id.estadoZona);
        //BONTONES
        btnEliminar=(Button) findViewById(R.id.eliminarZona);
        btnActivar=(Button) findViewById(R.id.activarZona);
        btnInactivar=(Button) findViewById(R.id.inactivarZona);
        //Recibir variable
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                codigonum = Integer.parseInt(null);
            } else {
                codigonum  = extras.getInt("CODIGO");
            }
        } else {
            codigonum  = (int) savedInstanceState.getSerializable("CODIGO");
        }

        DbZonas dbZonas=new DbZonas(VerZona.this);
        zona=dbZonas.verZona(codigonum);
        if(zona!=null){
            codigo.setText(zona.getCodigo()+"");
            nombre.setText(zona.getNombre());
            estado.setText(zona.getEstado());
        }
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbZonas.editarZonas(codigonum, nombre.getText().toString(), "*");

                    if(correcto){
                        Toast.makeText(VerZona.this, "SE ELIMINO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerZona.this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbZonas.editarZonas(codigonum, nombre.getText().toString(), "A");

                    if(correcto){
                        Toast.makeText(VerZona.this, "SE ACTIVO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerZona.this, "ERROR AL ACTIVAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnInactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbZonas.editarZonas(codigonum, nombre.getText().toString(), "I");

                    if(correcto){
                        Toast.makeText(VerZona.this, "SE INACTIVO", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerZona.this, "ERROR AL INACTIVAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    public void editar(View view){
        Intent intent=new Intent(VerZona.this,EditarZona.class);
        intent.putExtra("CODIGO",codigonum);
        startActivity(intent);

    }
    public void verMenu(){
        Intent intent = new Intent(this, MenuZona.class);
        startActivity(intent);
    }
}