package com.lab02.maestroclientes.TipoCliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lab02.maestroclientes.EditarZona;
import com.lab02.maestroclientes.MenuZona;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.VerZona;
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.TipoCliente;
import com.lab02.maestroclientes.entidades.Zona;

public class VerTipoCliente extends AppCompatActivity {
    TextView codigo,nombre,estado;
    int codigonum=0;
    TipoCliente tipoCliente;
    boolean correcto=false;
    Button btnEliminar,btnActivar,btnInactivar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tipo_cliente);
        codigo=(TextView) findViewById(R.id.codigoTipoCliente);
        nombre=(TextView) findViewById(R.id.nombreTipoCliente);
        estado=(TextView) findViewById(R.id.estadoTipoCliente);
        //BONTONES
        btnEliminar=(Button) findViewById(R.id.eliminarTipoCliente);
        btnActivar=(Button) findViewById(R.id.activarTipoCliente);
        btnInactivar=(Button) findViewById(R.id.inactivarTipoCliente);
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

        DbTipoCliente dbTipoCliente=new DbTipoCliente(VerTipoCliente.this);
        tipoCliente=dbTipoCliente.verTipoCliente(codigonum);
        if(tipoCliente!=null){
            codigo.setText(tipoCliente.getCodigo()+"");
            nombre.setText(tipoCliente.getNombre());
            estado.setText(tipoCliente.getEstado());
        }
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbTipoCliente.editarTipoClientes(codigonum, nombre.getText().toString(), "*");

                    if(correcto){
                        Toast.makeText(VerTipoCliente.this, "SE ELIMINO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerTipoCliente.this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbTipoCliente.editarTipoClientes(codigonum, nombre.getText().toString(), "A");

                    if(correcto){
                        Toast.makeText(VerTipoCliente.this, "SE ACTIVO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerTipoCliente.this, "ERROR AL ACTIVAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnInactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbTipoCliente.editarTipoClientes(codigonum, nombre.getText().toString(), "I");

                    if(correcto){
                        Toast.makeText(VerTipoCliente.this, "SE INACTIVO", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerTipoCliente.this, "ERROR AL INACTIVAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    public void editar(View view){
        Intent intent=new Intent(VerTipoCliente.this, EditarTipoCliente.class);
        intent.putExtra("CODIGO",codigonum);
        startActivity(intent);

    }
    public void verMenu(){
        Intent intent = new Intent(this, MenuTipoCliente.class);
        startActivity(intent);
    }
}