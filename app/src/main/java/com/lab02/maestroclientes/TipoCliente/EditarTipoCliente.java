package com.lab02.maestroclientes.TipoCliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lab02.maestroclientes.EditarZona;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.VerZona;
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.TipoCliente;
import com.lab02.maestroclientes.entidades.Zona;

public class EditarTipoCliente extends AppCompatActivity {
    EditText codigo,nombre,estado;
    int cod=0;
    TipoCliente tipoCliente;
    Button btnGuarda;
    boolean correcto=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tipo_cliente);
        codigo=(EditText) findViewById(R.id.codTiMod);
        nombre=(EditText) findViewById(R.id.nomTiMod);
        estado=(EditText) findViewById(R.id.estadTinMod);
        btnGuarda=(Button)findViewById(R.id.guardarTi);
        codigo.setInputType(InputType.TYPE_NULL);
        estado.setInputType(InputType.TYPE_NULL);
        //Recibir variable
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                cod  = Integer.parseInt(null);
            } else {
                cod   = extras.getInt("CODIGO");
            }
        } else {
            cod  = (int) savedInstanceState.getSerializable("CODIGO");
        }

        DbTipoCliente dbTipoCliente=new DbTipoCliente(EditarTipoCliente.this);
        tipoCliente=dbTipoCliente.verTipoCliente(cod);
        if(tipoCliente!=null){
            codigo.setText(tipoCliente.getCodigo()+"");
            nombre.setText(tipoCliente.getNombre());
            estado.setText(tipoCliente.getEstado());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbTipoCliente.editarTipoClientes(cod, nombre.getText().toString(), estado.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarTipoCliente.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarTipoCliente.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarTipoCliente.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void verRegistro(){
        Intent intent = new Intent(this, VerTipoCliente.class);
        intent.putExtra("CODIGO", cod);
        startActivity(intent);
    }
    public void cancelar(View view ){
        Intent intent = new Intent(this, VerTipoCliente.class);
        intent.putExtra("CODIGO", cod);
        startActivity(intent);
    }

}