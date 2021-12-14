package com.lab02.maestroclientes.Cliente;

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
import com.lab02.maestroclientes.db.DbCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Cliente;
import com.lab02.maestroclientes.entidades.Zona;

public class VerCliente extends AppCompatActivity {
    TextView codigo,nombre,ruc,codZ,codTC,estado;
    int codigonum=0;
    Cliente cliente;
    boolean correcto=false;
    Button btnEliminar,btnActivar,btnInactivar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente);
        codigo=(TextView) findViewById(R.id.codigoClienteV);
        nombre=(TextView) findViewById(R.id.nombreClienteV);
        ruc=(TextView) findViewById(R.id.rucVer);
        codZ=(TextView) findViewById(R.id.zonaC);
        codTC=(TextView) findViewById(R.id.tipCCVer);
        estado=(TextView) findViewById(R.id.estadoClienteV);
        //BONTONES
        btnEliminar=(Button) findViewById(R.id.eliminarCliente);
        btnActivar=(Button) findViewById(R.id.activarCliente);
        btnInactivar=(Button) findViewById(R.id.inactivarCliente);
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

        DbCliente dbCliente=new DbCliente(VerCliente.this);
        cliente=dbCliente.verCliente(codigonum);
        if(cliente!=null){
            codigo.setText(cliente.getCodigo()+"");
            nombre.setText(cliente.getNombre());
            ruc.setText(cliente.getRuc()+"");
            codZ.setText(cliente.getCodigozona()+"");
            codTC.setText(cliente.getCodigotipocliente()+"");
            estado.setText(cliente.getEstado());
        }
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbCliente.editarCliente(codigonum, nombre.getText().toString(), ruc.getText().toString(),
                            Integer.parseInt(codZ.getText().toString()),Integer.parseInt(codTC.getText().toString()), "*");

                    if(correcto){
                        Toast.makeText(VerCliente.this, "SE ELIMINO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerCliente.this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = correcto = dbCliente.editarCliente(codigonum, nombre.getText().toString(),ruc.getText().toString(),
                            Integer.parseInt(codZ.getText().toString()),Integer.parseInt(codTC.getText().toString()), "A");

                    if(correcto){
                        Toast.makeText(VerCliente.this, "SE ACTIVO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerCliente.this, "ERROR AL ACTIVAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnInactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = correcto = dbCliente.editarCliente(codigonum, nombre.getText().toString(),ruc.getText().toString(),
                            Integer.parseInt(codZ.getText().toString()),Integer.parseInt(codTC.getText().toString()),"I");

                    if(correcto){
                        Toast.makeText(VerCliente.this, "SE INACTIVO", Toast.LENGTH_LONG).show();
                        verMenu();
                    } else {
                        Toast.makeText(VerCliente.this, "ERROR AL INACTIVAR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    public void editar(View view){
        Intent intent=new Intent(VerCliente.this, EditarCliente.class);
        intent.putExtra("CODIGO",codigonum);
        startActivity(intent);

    }
    public void verMenu(){
        Intent intent = new Intent(this, MenuCliente.class);
        startActivity(intent);
    }
}