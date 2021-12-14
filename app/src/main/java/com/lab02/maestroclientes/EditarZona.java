package com.lab02.maestroclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Zona;

public class EditarZona extends AppCompatActivity {
    EditText codigo,nombre,estado;
    int cod=0;
    Zona zona;
    Button btnGuarda;
    boolean correcto=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_zona);
        codigo=(EditText) findViewById(R.id.codZoMod);
        nombre=(EditText) findViewById(R.id.nomZonMod);
        estado=(EditText) findViewById(R.id.estadZonMod);
        btnGuarda=(Button)findViewById(R.id.guardar);
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

        DbZonas dbZonas=new DbZonas(EditarZona.this);
        zona=dbZonas.verZona(cod);
        if(zona!=null){
            codigo.setText(zona.getCodigo()+"");
            nombre.setText(zona.getNombre());
            estado.setText(zona.getEstado());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")  ) {
                    correcto = dbZonas.editarZonas(cod, nombre.getText().toString(), estado.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarZona.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarZona.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarZona.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void verRegistro(){
        Intent intent = new Intent(this, VerZona.class);
        intent.putExtra("CODIGO", cod);
        startActivity(intent);
    }
    public void cancelar(View view ){
        Intent intent = new Intent(this, VerZona.class);
        intent.putExtra("CODIGO", cod);
        startActivity(intent);
    }

}