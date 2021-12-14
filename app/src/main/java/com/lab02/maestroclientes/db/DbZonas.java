package com.lab02.maestroclientes.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.lab02.maestroclientes.MenuZona;
import com.lab02.maestroclientes.VerZona;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;

public class DbZonas  extends DbHelper{
    Context context;
    long id;
    public DbZonas(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarZona(String nom,String estado){
        try{
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("nombre",nom);
            values.put("estadoRegistro",estado);
            id=db.insert(TABLE_ZONA,null,values);

        }catch (Exception ex){
            ex.toString();

        }
        return id;
    }
    public ArrayList<Zona> mostrarZonas(int opcion){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ArrayList<Zona> listaZonas=new ArrayList<Zona>();
        Zona zona=null;
        Cursor cursorZona=null;
        if(opcion==1){
        cursorZona=db.rawQuery("SELECT * FROM "+TABLE_ZONA+" ORDER BY nombre",null);}
       else if(opcion==9){
            cursorZona=db.rawQuery("SELECT * FROM "+TABLE_ZONA+" WHERE estadoRegistro = 'A' ",null);

        }
        else{
        cursorZona=db.rawQuery("SELECT * FROM "+TABLE_ZONA+" ORDER BY codigo",null);}
       if( cursorZona.moveToFirst()){
           do{
               zona=new Zona();
               zona.setCodigo(cursorZona.getInt(0));
               zona.setNombre(cursorZona.getString(1));
               zona.setEstado(cursorZona.getString(2));
               listaZonas.add(zona);
           }while(cursorZona.moveToNext());
       }
       cursorZona.close();
       return listaZonas;

    }



    public boolean editarZonas(int codigo,String nombre,String estado){
        boolean correcto=false;
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_ZONA + " SET  nombre = '" + nombre + "', estadoRegistro = '" + estado + "' WHERE codigo='" + codigo + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;

    }
    public Zona verZona(int codigo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

         Zona zona = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_ZONA + " WHERE codigo = " + codigo + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            zona = new Zona();
            zona.setCodigo(cursorContactos.getInt(0));
            zona.setNombre(cursorContactos.getString(1));
            zona.setEstado(cursorContactos.getString(2));
        }

        cursorContactos.close();

        return zona;
    }


}
