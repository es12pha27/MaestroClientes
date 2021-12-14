package com.lab02.maestroclientes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.lab02.maestroclientes.entidades.Cliente;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class DbCliente extends DbHelper {
    Context context;
    long id;
    public DbCliente(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarCliente(String nom,String ruc,int codZo,int codTip,String estado){

        try{
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("nombre",nom);
            values.put("ruc",ruc);
            values.put("codigozona",codZo);
            values.put("codigotipocliente",codTip);
            values.put("estadoRegistro",estado);
            id=db.insert(TABLE_CLIENTE,null,values);
        }catch (Exception ex){
            ex.toString();

        }
        return id;
    }
    public ArrayList<Cliente> mostrarCliente(int opcion){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ArrayList<Cliente> listaClientes=new ArrayList<Cliente>();
        Cliente cliente=null;
        Cursor cursorCliente=null;
        if(opcion==1){
            cursorCliente=db.rawQuery("SELECT * FROM "+TABLE_CLIENTE+" ORDER BY nombre",null);}
        else{
            cursorCliente=db.rawQuery("SELECT * FROM "+TABLE_CLIENTE+" ORDER BY codigo",null);}
        if( cursorCliente.moveToFirst()){
            do{
                cliente=new Cliente();
                cliente.setCodigo(cursorCliente.getInt(0));
                cliente.setNombre(cursorCliente.getString(1));
                cliente.setRuc(cursorCliente.getString(2));
                cliente.setCodigozona(cursorCliente.getInt(3));
                cliente.setCodigotipocliente(cursorCliente.getInt(4));
                cliente.setEstado(cursorCliente.getString(5));
                listaClientes.add(cliente);
            }while(cursorCliente.moveToNext());
        }
        cursorCliente.close();
        return listaClientes;

    }
    public boolean editarCliente(int codigo,String nombre,String ruc,int codZo,int codTip,String estado){
        boolean correcto=false;
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_CLIENTE + " SET  nombre = '" + nombre + "', ruc = '" + ruc + "', codigozona= '" + codZo +"', codigotipocliente= '" + codTip+"', estadoRegistro= '" + estado+"' WHERE codigo='" + codigo + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;

    }
    public Cliente verCliente(int codigo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

       Cliente cliente = null;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLE_CLIENTE + " WHERE codigo = " + codigo + " LIMIT 1", null);

        if (cursorCliente.moveToFirst()) {
            cliente= new Cliente();
            cliente.setCodigo(cursorCliente.getInt(0));
            cliente.setNombre(cursorCliente.getString(1));
            cliente.setRuc(cursorCliente.getString(2));
            cliente.setCodigozona(cursorCliente.getInt(3));
            cliente.setCodigotipocliente(cursorCliente.getInt(4));
            cliente.setEstado(cursorCliente.getString(5));
        }

        cursorCliente.close();

        return cliente;
    }


}
