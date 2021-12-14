package com.lab02.maestroclientes.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lab02.maestroclientes.EditarZona;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.VerZona;
import com.lab02.maestroclientes.db.DbCliente;
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Cliente;
import com.lab02.maestroclientes.entidades.TipoCliente;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class EditarCliente extends AppCompatActivity {
    EditText codigo,nombre,ruc,estado;
    Spinner spZ,spTC;
    int cod=0;

   TipoCliente tcliente;
    Button btnGuarda;
    boolean correcto=false;
    ArrayList<Zona> listaArrayZonas;
    ArrayList<TipoCliente> listaArrayTClientes;
    DbTipoCliente dbTclientes;
    Cliente cliente;
    Zona zona;
    DbZonas dbzonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        codigo=(EditText) findViewById(R.id.codCliMod);
        nombre=(EditText) findViewById(R.id.nomCliMod);
        ruc=(EditText) findViewById(R.id.rucMod);
        estado=(EditText) findViewById(R.id.estadCliMod);
        spZ=(Spinner)findViewById(R.id.spinnerZCliMod);
        spTC=(Spinner)findViewById(R.id.spinnerCliTMod);
        btnGuarda=(Button)findViewById(R.id.guardar);
        codigo.setInputType(InputType.TYPE_NULL);
        estado.setInputType(InputType.TYPE_NULL);
        //Inicio Llenar spinner
        dbzonas = new DbZonas(EditarCliente.this);
        listaArrayZonas = new ArrayList<Zona>();
        listaArrayZonas=dbzonas.mostrarZonas(1);
        dbTclientes = new DbTipoCliente(EditarCliente.this);
        listaArrayTClientes = new ArrayList<TipoCliente>();
        listaArrayTClientes=dbTclientes.mostrarTipoCliente(1);
        ArrayAdapter<Zona> adapter=new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,listaArrayZonas);
        ArrayAdapter<TipoCliente> adapterT=new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,listaArrayTClientes);
        spZ.setAdapter(adapter);
        spTC.setAdapter(adapterT);


        //
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

        DbCliente dbCliente=new DbCliente(EditarCliente.this);
        cliente=dbCliente.verCliente(cod);

        if(cliente!=null){

            codigo.setText(cliente.getCodigo()+"");
            nombre.setText(cliente.getNombre());
            ruc.setText(cliente.getRuc()+"");
            zona=dbzonas.verZona(cliente.getCodigozona());
            System.out.println("ZONA"+zona);
            estado.setText(cliente.getEstado());
            tcliente=dbTclientes.verTipoCliente(cliente.getCodigotipocliente());
            int positionZ =  getIndexByString(spZ,zona.getNombre());
            int positionTC =  getIndexByString(spZ,tcliente.getNombre());
            spZ.setSelection(positionZ);
            spTC.setSelection(positionTC);

        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zona=(Zona)spZ.getSelectedItem();
                tcliente=(TipoCliente) spTC.getSelectedItem();
                if(!nombre.getText().toString().equalsIgnoreCase("")& !ruc.getText().toString().equalsIgnoreCase("") /*& ruc.getText().toString().length()<10*/){
                    correcto = dbCliente.editarCliente(cod, nombre.getText().toString(),ruc.getText().toString(),zona.getCodigo(),tcliente.getCodigo(), estado.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarCliente.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarCliente.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarCliente.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private int getIndexByString(Spinner spinner, String string) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void verRegistro(){
        Intent intent = new Intent(this, VerCliente.class);
        intent.putExtra("CODIGO", cod);
        startActivity(intent);
    }
    public void cancelar(View view ){
        Intent intent = new Intent(this, VerCliente.class);
        intent.putExtra("CODIGO", cod);
        startActivity(intent);
    }

}