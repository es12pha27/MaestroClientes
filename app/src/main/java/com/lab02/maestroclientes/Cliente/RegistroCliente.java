package com.lab02.maestroclientes.Cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lab02.maestroclientes.ListaZonaAdapter;
import com.lab02.maestroclientes.MenuZona;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.RegistroZona;
import com.lab02.maestroclientes.db.DbCliente;
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Cliente;
import com.lab02.maestroclientes.entidades.TipoCliente;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class RegistroCliente extends AppCompatActivity {
    ArrayList<Zona> listaArrayZonas;
    Spinner spinerZona,spinnerTipo;
    DbZonas dbzonas;
    ArrayList<TipoCliente> listaArrayTClientes;
    DbTipoCliente dbTclientes;
    Zona zona;
    TipoCliente tcliente;
    EditText nombre;
    EditText ruc;
    long id;
    DbCliente dbclientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);
        spinerZona=(Spinner)findViewById(R.id.spinnerZona);
        spinnerTipo=(Spinner)findViewById(R.id.spinnerTipo);
        nombre=(EditText)findViewById(R.id.nomCliente);
        ruc=(EditText)findViewById(R.id.ruc);
        dbzonas = new DbZonas(RegistroCliente.this);
        listaArrayZonas = new ArrayList<Zona>();
        listaArrayZonas=dbzonas.mostrarZonas(9);
        System.out.println("listaArrayZonas ARREGLO");
        System.out.println(listaArrayZonas);
        dbTclientes = new DbTipoCliente(RegistroCliente.this);
        listaArrayTClientes = new ArrayList<TipoCliente>();
        listaArrayTClientes=dbTclientes.mostrarTipoCliente(9);
        System.out.println("listaArrayTipo ARREGLO");
        System.out.println(listaArrayTClientes);
        ArrayAdapter<Zona> adapter=new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,listaArrayZonas);
        ArrayAdapter<TipoCliente> adapterT=new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,listaArrayTClientes);
        spinerZona.setAdapter(adapter);
        spinnerTipo.setAdapter(adapterT);

    }
    public void Guardar(View view){
        zona=(Zona)spinerZona.getSelectedItem();
        tcliente=(TipoCliente) spinnerTipo.getSelectedItem();
      if(!nombre.getText().toString().equalsIgnoreCase("")& !ruc.getText().toString().equalsIgnoreCase("") /*& ruc.getText().toString().length()<10*/){
           DbCliente dbCliente=new DbCliente(RegistroCliente.this);
           id=dbCliente.insertarCliente(nombre.getText().toString(),ruc.getText().toString(),zona.getCodigo(),tcliente.getCodigo(),"A");
            if(id>0){
                Toast.makeText(RegistroCliente.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                limpiar();
            }else{
                Toast.makeText(RegistroCliente.this, "REGISTRO  NO GUARDADO", Toast.LENGTH_SHORT).show();
            }}
        else{
            Toast.makeText(RegistroCliente.this, "LLENE EL CAMPO NOMBRE Y RUC", Toast.LENGTH_SHORT).show();
        }
    }
    private void limpiar(){
        nombre.setText("");
        ruc.setText("");
    }

}