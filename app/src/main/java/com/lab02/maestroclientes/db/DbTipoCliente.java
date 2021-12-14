package com.lab02.maestroclientes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.lab02.maestroclientes.entidades.TipoCliente;

import java.util.ArrayList;

public class DbTipoCliente extends DbHelper{
    Context context;
    long id;
    public DbTipoCliente(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarTipoCliente(String nom,String estado){
        try{
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("nombre",nom);
            values.put("estadoRegistro",estado);
            id=db.insert(TABLE_TIPOCLIENTE,null,values);

        }catch (Exception ex){
            ex.toString();

        }
        return id;
    }
    public ArrayList<TipoCliente> mostrarTipoCliente(int opcion){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ArrayList<TipoCliente> listaTipoCliente=new ArrayList<TipoCliente>();
        TipoCliente tipoCliente=null;
        Cursor cursorZona=null;
        if(opcion==1){
            cursorZona=db.rawQuery("SELECT * FROM "+TABLE_TIPOCLIENTE+" ORDER BY nombre",null);}
        else if(opcion==9){
            cursorZona=db.rawQuery("SELECT * FROM "+TABLE_TIPOCLIENTE+" WHERE estadoRegistro = 'A'",null);
        }
        else{
            cursorZona=db.rawQuery("SELECT * FROM "+TABLE_TIPOCLIENTE+" ORDER BY codigo",null);}
        if( cursorZona.moveToFirst()){
            do{
                tipoCliente=new TipoCliente();
                tipoCliente.setCodigo(cursorZona.getInt(0));
                tipoCliente.setNombre(cursorZona.getString(1));
                tipoCliente.setEstado(cursorZona.getString(2));
                listaTipoCliente.add(tipoCliente);
            }while(cursorZona.moveToNext());
        }
        cursorZona.close();
        return listaTipoCliente;

    }
    public boolean editarTipoClientes(int codigo,String nombre,String estado){
        boolean correcto=false;
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_TIPOCLIENTE + " SET  nombre = '" + nombre + "', estadoRegistro = '" + estado + "' WHERE codigo='" + codigo + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;

    }
    public TipoCliente verTipoCliente(int codigo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        TipoCliente tipoCliente = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_TIPOCLIENTE + " WHERE codigo = " + codigo + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            tipoCliente = new TipoCliente();
            tipoCliente.setCodigo(cursorContactos.getInt(0));
            tipoCliente.setNombre(cursorContactos.getString(1));
            tipoCliente.setEstado(cursorContactos.getString(2));
        }

        cursorContactos.close();

        return tipoCliente;
    }


}